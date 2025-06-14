package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.databinding.ActivityLoginBinding;

import java.util.concurrent.TimeUnit;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /***   生命周期方法   ***/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.i("my111", "LoginActivity -> onCreate");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        observeInput();
        binding.btnLogin.setOnClickListener( v -> {
            Log.i("my111", "成功登录");

            boolean cross = isTabular();
            Class destination;
            if (cross){
                destination = feature.product.ProductActivity.class;
            }else {
                destination = feature.tabroot.TabRootActivity.class;
            }
            Intent intent = new Intent(this, destination);
            this.startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        Log.i("my111", "LoginActivity -> onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(compositeDisposable != null){
            compositeDisposable.clear(); // 防止内存泄漏
        }
    }

    /***   私有方法   ***/
    //检测用户输入
    private void observeInput() {
        long detectFrequency = 100;
        Observable<CharSequence> usernameOb = RxTextView.textChanges(binding.etUsername)
                .skipInitialValue()
                .debounce(detectFrequency, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

        Observable<CharSequence> passwordOb = RxTextView.textChanges(binding.etPassword)
                .skipInitialValue()
                .debounce(detectFrequency, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable
                .add(Observable.combineLatest(
                        usernameOb,
                        passwordOb,
                new BiFunction<CharSequence, CharSequence, Object>() {
                    @Override
                    public Object apply(CharSequence username, CharSequence password) throws Exception {
                        return (isUsernameValid(username.toString()) &&
                                isPasswordValid(password.toString()));
                    }
                })
                .subscribe(isValid -> binding.btnLogin.setEnabled((Boolean) isValid))
        );
    }

    //判断用户名合法
    private boolean isUsernameValid(String username){
        return username != null && username.trim().length() >= 4;
    }

    //判断密码合法
    private boolean isPasswordValid(String password){
        return password != null && password.trim().length() >= 6;
    }

    private boolean isTabular() {
        return false;
    }
}
