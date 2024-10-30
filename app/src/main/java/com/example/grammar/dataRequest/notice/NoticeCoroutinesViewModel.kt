package com.example.dataRequests.notice

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NoticeCoroutinesViewModel: ViewModel() {

    val api by lazy { NoticeRetrofitManger.getApiService() }
    var articlesLiveData: MutableLiveData<MutableList<NoticeBean>?> = MutableLiveData()

    var apiError:MutableLiveData<Throwable> = MutableLiveData()

    @SuppressLint("NullSafeMutableLiveData")
    fun getArticles(page: Int) {

        val exception = CoroutineExceptionHandler { _, throwable ->
            apiError.postValue(throwable)
            Log.i("CoroutinesViewModel",throwable.message!!)
        }

        viewModelScope.launch(exception) {
            Log.i("my111", "... start request...")
            val response = api.getNoticeList(
                "ios",
                "1",
                "21",
                "zh_CN")
            Log.i("my111", response.toString())

            if ("success" == response.code) {
                articlesLiveData.postValue(mutableListOf(response.data!!))
            }else {
                articlesLiveData.postValue(mutableListOf())
            }
        }
    }

}