package com.example.hello;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.grammar.asyncs.MyTaskCallback;
import com.example.utils.MyOpResources;

public class MyFragment extends Fragment {

    private TextView liveDataTextView;        // 文本显示框

    private TextView textView;                // 文本显示框
    private ProgressBar progress;             // 进度条

    private OnDataChangeListener listener;    // 回传操作给Activity
    private MyTaskCallback myTaskCallback;    // 响应异步数据更新

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_fragment, container, false);

        liveDataTextView = view.findViewById(R.id.my_liveData);

        textView = view.findViewById(R.id.my_textView);
        progress = view.findViewById(R.id.my_progress);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnDataChangeListener) context;
    }

    public interface OnDataChangeListener {
        void onDataChanged(String newData);
    }

    public void updateLiveData(String newData){
        liveDataTextView.setText(newData);
    }

    private void updateText(String thisIsDataFromActivity) {
        textView.setText(thisIsDataFromActivity);
    }

    private void updateProgressBar(Integer process) {
        progress.setProgress(process);
    }

    public MyTaskCallback getMyTaskCallback() {
        if(null == myTaskCallback){
            myTaskCallback = new MyTaskCallback() {
                public void onCancel() {
                    updateText(getAsyncDone(R.string.hello_load_cancel, 0));
                    updateProgressBar(0);
                }

                @Override
                public void onProcess(Integer process) {
                    updateText(getAsyncString(R.string.hello_load_process, process));
                    updateProgressBar(process);
                }

                @Override
                public void onComplete() {
                    updateText(getAsyncDone(R.string.hello_load_done, 100));
                    updateProgressBar(100);
                }
            };
        }
        return myTaskCallback;
    }

    /*
     * 资源类的字符串
     */
    @NonNull
    private String getResourceString(@StringRes int resId){
        return MyOpResources.getString(resId);
    }

    /*
     * 异步任务执行中的字符串
     */
    @NonNull
    private String getAsyncString(@StringRes int resId, Integer process) {
        return getResourceString(resId) + process + "(%s)";
    }

    /*
     * 异常任务结束的字符串
     */
    @NonNull
    private String getAsyncDone(@StringRes int resId, Integer process) {
        listener.onDataChanged(getResourceString(resId));
        return getResourceString(resId);
    }
}