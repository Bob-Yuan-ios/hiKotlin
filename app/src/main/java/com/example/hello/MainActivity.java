package com.example.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.hello.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.AsyncTests;
import com.example.grammar.async.MyTaskCallback;
import com.example.utils.MyOpResources;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AsyncTests asyncTests;
    private MyTaskCallback myTaskCallback;    // 响应异步数据更新

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("my111", "↩️");
        if(null == asyncTests){
            asyncTests = new AsyncTests();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("my111", "绑定");

        binding.myExcButton.setOnClickListener( v -> {
            Log.i("my111", "启动异步任务事件");
            asyncTests.asyncTaskExecute(getMyTaskCallback());
        });

        binding.myCancelButton.setOnClickListener(v -> {
            Log.i("my111", "取消异步任务事件");
            asyncTests.asyncTaskCancel();
        });
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom);
                    return insets;
        });

        Log.i("my111", "创建结束");
    }

    public MyTaskCallback getMyTaskCallback() {
        if(null == myTaskCallback){
            myTaskCallback = new MyTaskCallback() {
                public void onCancel() {
                    updateText(getAsyncDone(R.string.hello_load_cancel, 0));
                    updateProgressBar(0);
                    Log.i("my111", "activity cancel");
                }

                @Override
                public void onProcess(Integer process) {
                    updateText(getAsyncString(R.string.hello_load_process, process));
                    updateProgressBar(process);
                    Log.i("my111", "activity onProcess:" + process);
                }

                @Override
                public void onComplete() {
                    updateText(getAsyncDone(R.string.hello_load_done, 100));
                    updateProgressBar(100);
                    Log.i("my111", "activity onComplete");
                }
            };
        }
        return myTaskCallback;
    }

    private  void updateText(String text){
        if (text != null){
            binding.myTextView.setText(text);
        }
    }

    private void updateProgressBar(Integer process){
        binding.myProgress.setProgress(process, true);
    }

    private String  getAsyncDone(@StringRes int ResId, Integer value){
        return MyOpResources.getString(ResId);
    }

    private String getAsyncString(@StringRes int ResId, Integer value) {
        return MyOpResources.getString(ResId) + value;
    }

}