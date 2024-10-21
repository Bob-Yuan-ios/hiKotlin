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

    private var showDataView: TextView? = null
    private val viewModel by lazy { ViewModelProvider(this).get(NoticeCoroutinesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_fragment)
        initView()
        startObserver()
    }

    private fun initView(){
        val downloadBtn = findViewById<Button>(R.id.my_excButton)
        downloadBtn.setOnClickListener{
            viewModel.getArticles(1)
        }
        showDataView = findViewById(R.id.my_liveData)
    }

    private fun startObserver(){
        viewModel.articlesLiveData.observe(this, Observer {
            it?.run {
                if (this.size > 0) {
                    val text = StringBuilder()
                    this.forEach {
                        text.append(it.comment + "\n")
                    }
                    showDataView?.text = text
                    Log.i("my111===", it.toString())
                }
            }
        })

        viewModel.apiError.observe(this, Observer {

        })
    }
}