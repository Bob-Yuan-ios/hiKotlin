package com.example.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.MyApplication;
import com.example.hello.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;



    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("my111", "绑定视图窗口");

        binding.myExcButton.setOnClickListener( v -> {
            Log.i("my111", "启动异步任务事件");
        });

        binding.myCancelButton.setOnClickListener(v -> {
            Log.i("my111", "取消异步任务事件");
        });
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Log.i("my111", "创建窗口开发");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                binding.getRoot(),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom);
                    return insets;
        });

        Log.i("my111", "创建窗口结束");
    }


    private  void updateText(String text){
        if (text != null){
            binding.myTextView.setText(text);
        }
    }

    private void updateProgressBar(Integer process){
        binding.myProgress.setProgress(process, true);
    }

    private String getAsyncDone(@StringRes int ResId){
        return getString1(ResId);
    }

    private String getAsyncString(@StringRes int ResId, Integer value) {
        return getString1(ResId) + value;
    }

    private String getString1(@StringRes int ResId){
        return MyApplication.getAppContext().getString(ResId);
    }
}