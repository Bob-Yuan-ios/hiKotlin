package feature.noticelist.data

import feature.noticelist.domain.NoticeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.net.Proxy
import java.util.concurrent.TimeUnit

object NoticeRetrofitManger {

    @Volatile
    private var mApi: NoticeApi? = null //保证变量在多线程间立即可见，防止指令重排

    private const val READ_TIME_OUT = 10L
    private const val CONNECTION_TIME_OUT = 10L

    private var API_URL = "https://appcfg.cryptouat.com"

    fun getApiService(): NoticeApi {
        if (null == mApi){
            synchronized(this){
                if(null == mApi){
                    val okHttpClient = buildOKHttpClient()
                    mApi = buildRetrofit(
                        API_URL,
                        okHttpClient
                    ).create(NoticeApi::class.java)
                }
            }
        }

        return mApi!!
    }

//    val mApi: NoticeApi by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
//        val okHttpClient = buildOKHttpClient()
//        buildRetrofit(API_URL, okHttpClient).create(NoticeApi::class.java)
//    }

    private fun buildOKHttpClient(): OkHttpClient.Builder{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
    }

    private fun buildRetrofit(baseUrl: String, builder: OkHttpClient.Builder): Retrofit {
        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }
}