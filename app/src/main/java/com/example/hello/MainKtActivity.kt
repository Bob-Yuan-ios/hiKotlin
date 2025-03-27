package com.example.hello

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dataRequests.notice.NoticeCoroutinesViewModel

class MainKtActivity: AppCompatActivity() {

    // 属性
    private var showDataView: TextView? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(NoticeCoroutinesViewModel::class.java)
    }

    // 生命周期方法
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_fragment)
        initView()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeObserver()
    }

    // 初始化视图和数据
    private fun initView(){
        val downloadBtn = findViewById<Button>(R.id.my_excButton)
        downloadBtn.setOnClickListener{
            viewModel.getArticles()
        }
        showDataView = findViewById(R.id.my_liveData)
    }

    // 开启监听
    private fun startObserver(){
        viewModel.articlesLiveData.observe(this, Observer {
            it?.run {
                if (this.size > 0) {
                    val text = StringBuilder()
                    this.forEach {
                        text.append(it.comment + "\n")
                    }
                    showDataView?.text = text
                }
            }
        })

        viewModel.apiError.observe(this, Observer {
            Log.i("my111", "数据请求出错原因:\n" + it.message.toString())
        })
    }

    // 移除监听
    private fun removeObserver(){
        viewModel.articlesLiveData.removeObservers(this);
        viewModel.apiError.removeObservers(this);
    }

}