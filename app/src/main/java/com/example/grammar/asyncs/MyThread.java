package com.example.grammar.asyncs;

/*
*   优点： 封装了线程操作所需方法
*   缺点： 一个线程 = 一个实例对象 = 一个耗时任务
*   完成任务后自动回收
* */
public class MyThread extends Thread{

    private int tick = 100;
    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        super.run();

        while (tick > 0){
            tick--;
            System.out.println(name + "剩余票数:" + tick);
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
