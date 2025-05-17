package feature.noticelist.domain

import okhttp3.Interceptor
import okhttp3.Response
import java.util.TreeMap

class RequestInterceptor : Interceptor {

    private val TAG = "RequestInterceptor";

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")

        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val httpUrl = request.url.newBuilder().build()

        val headMap = HashMap<String, String>()
        when(request.method){
            "GET" -> {
                val paramsKey = httpUrl.queryParameterNames
                val map = TreeMap<String, String>()
                for (key: String in paramsKey){
                    map[key] = httpUrl.queryParameter(key).toString()
                }

            }

            "POST" -> {

            }
        }

    }


}