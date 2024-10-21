package com.example.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.AsyncTests;

public class MainActivity extends AppCompatActivity implements MyFragment.OnDataChangeListener {

    MyFragment mFragment;
    private AsyncTests asyncTests;

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

        mFragment = (MyFragment) getSupportFragmentManager().
                findFragmentById(R.id.my_fragment);

        findViewById(R.id.my_excButton).setOnClickListener(
            v -> {
                asyncTests.asyncTaskExecute(mFragment.getMyTaskCallback());
            }
        );

        findViewById(R.id.my_cancelButton).setOnClickListener(
            v -> {
                asyncTests.asyncTaskCancel();
            }
        );
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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

    @Override
    public void onDataChanged(String newData) {
        String appendData = "加载状态更新:(" + newData + ")";
        Log.i("my111", appendData);
    }
}