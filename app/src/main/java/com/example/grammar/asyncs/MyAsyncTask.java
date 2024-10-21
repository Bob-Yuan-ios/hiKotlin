package com.example.grammar.asyncs;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask {

    private MyTaskCallback mCallback;
    public MyAsyncTask(MyTaskCallback callback){
        mCallback = callback;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            int count = 0;
            int length = 1;

            while (count < 99){
                count += length;
                publishProgress(count);
                Thread.sleep(500); //模拟耗时任务：例如下载数据package
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        if(null != mCallback){
            mCallback.onProcess(Integer.parseInt(values[0].toString()));
        }
    }

    @Override
    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
        if(null != mCallback){
            mCallback.onComplete();
        }
    }

    @Override
    protected void onCancelled() {
        if(null != mCallback){
            mCallback.onCancel();
        }
    }
}
