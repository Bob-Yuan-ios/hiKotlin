package com.example.grammar.async;

import android.util.Log;

/*
 * 优点：线程协作完成任务，共享资源（多个线程完成1个任务）
 */
public class MyRunnable implements Runnable{

    private int tick = 100;

    @Override
    public void run() {

        while (tick > 0){
            tick--;
            Log.i("my111", Thread.currentThread().getName() + "剩余票数:" + tick);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
