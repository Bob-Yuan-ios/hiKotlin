package com.example.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.grammar.async.MyTaskCallback;
import com.example.hello.databinding.ActivityMainBinding;
import com.example.test.AsyncTests;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AsyncTests asyncTests;
    private MyTaskCallback myTaskCallback;    // 响应异步数据更新
    private OnDataChangeListener listener;    // 回传操作给Activity

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
            Log.i("my111", "hello task");
            asyncTests.asyncTaskExecute(getMyTaskCallback());
        });

        binding.myCancelButton.setOnClickListener(v -> {
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

    // 接口，暴露给外部使用
    public interface OnDataChangeListener {
        void onDataChanged(String newData);
    }

    public MyTaskCallback getMyTaskCallback() {
        if(null == myTaskCallback){
            myTaskCallback = new MyTaskCallback() {
                public void onCancel() {
//                    updateText(getAsyncDone(R.string.hello_load_cancel, 0));
//                    updateProgressBar(0);
                }

                @Override
                public void onProcess(Integer process) {
//                    updateText(getAsyncString(R.string.hello_load_process, process));
//                    updateProgressBar(process);
                }

                @Override
                public void onComplete() {
//                    updateText(getAsyncDone(R.string.hello_load_done, 100));
//                    updateProgressBar(100);
                }
            };
        }
        return myTaskCallback;
    }
}