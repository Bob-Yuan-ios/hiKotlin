package com.example.grammar.async;

public interface MyTaskCallback {

    // async任务进行中
    void onProcess(Integer process);

    // async任务取消
    void onCancel();

    // async任务完成
    void onComplete();
}
