package com.example.test;

import android.os.Bundle;
import android.os.Message;
import android.content.Intent;

import android.util.Log;

import com.example.grammar.async.MyAsyncTask;
import com.example.grammar.async.MyHandler;
import com.example.grammar.async.MyTaskCallback;
import com.example.utils.MyApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 *  测试用例 --     对比不同方式实现多线程功能开发
 *  Thread:        高度封装不灵活、一个Thread任务完成后会自动销毁（创建和销毁线程，消耗资源）
 *  Runnable:      线程协作完成任务，共享资源（多个线程完成1个任务）
 *  Handle:        消息传递，工作线程传递到UI线程，确保更新UI时，线程安全
 *  IntentService: 按顺序执行任务，不适合多个数据同时请求的场景
 *  Async:         线程池的缓存线程 + 复用线程
 */
public class AsyncTests {

    //asyncTask
    private MyAsyncTask mTask;
    public void asyncTaskExecute(MyTaskCallback callback){
        if(null == mTask){
            mTask = new MyAsyncTask(callback);
            if (null != mTask){
                mTask.execute();
            }else {
                Log.i("my111", "没有对象");
            }
        }else {
            Log.i("my111", "初始化不完整||任务执行中");
        }
    }

    public void asyncTaskCancel(){
        if(null != mTask){
            Log.i("my111", "取消任务");
            mTask.cancel(true);
            mTask = null;
        }else {
            Log.i("my111", "任务不存在");
        }
    }

    public void intentTask(){
        Intent i1 = new Intent("com.qq.music");
        Bundle bundle1 = new Bundle();
        bundle1.putString("taskName1", "task1");
        i1.putExtras(bundle1);
        MyApplication.getAppContext().startService(i1);

        Intent i2 = new Intent("com.qq.music");
        Bundle bundle2 = new Bundle();
        bundle2.putString("taskName2", "task2");
        i2.putExtras(bundle2);
        MyApplication.getAppContext().startService(i2);
    }


    //handler
    MyHandler myHandler;
    public void handleTask(){
        myHandler = new MyHandler();
        new Thread(){
            @Override
            public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = "Hello";
            myHandler.sendMessage(msg);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
            Message msg = Message.obtain();
            msg.what = 2;
            msg.obj = "World";
            myHandler.sendMessage(msg);
            }
        }.start();
    }

    //
    public void threadPoolTest(){

        // 定长线程
        ExecutorService fixed = Executors.newFixedThreadPool(3);
        // 线程对象&&需要执行定任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Log.i("my111", "定长线程池");
            }
        };
        // 向线程池提交任务
        fixed.execute(task);
        // 关闭线程池
        fixed.shutdown();

        // 定时线程
        ScheduledExecutorService fixed1 = Executors.newScheduledThreadPool(3);
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                Log.i("my111", "定时线程池");
            }
        };
        // 延迟1s后执行
        fixed1.schedule(task1, 1, TimeUnit.SECONDS);
        // 延迟10s后，每隔1000ms执行任务
        fixed1.scheduleAtFixedRate(task, 10, 1000, TimeUnit.MILLISECONDS);
        // 关闭线程池
        fixed1.shutdown();

        // 缓存线程池
        ExecutorService fixed2 = Executors.newCachedThreadPool();
        // 线程对象&&需要执行定任务
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                Log.i("my111", "缓存线程池");
            }
        };
        // 向线程池提交任务
        fixed2.execute(task2);
        // 关闭线程池
        fixed2.shutdown();

        // 单线程缓存线程池：不适合并发但可能引起IO阻塞及影响UI操作：数据库操作/文件操作
        ExecutorService fixed3 = Executors.newSingleThreadExecutor();
        // 线程对象&&需要执行定任务
        Runnable task3 = new Runnable() {
            @Override
            public void run() {
                Log.i("my111", "单缓存线程池");
            }
        };
        // 向线程池提交任务
        fixed3.execute(task3);
        // 关闭线程池
        fixed3.shutdown();
    }
}
