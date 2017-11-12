package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quseit.payapp.R;


public class DownloadDialog extends Dialog {

    private TextView titleTv;
    private TextView msgTv;
    private ProgressBar progressbar;
    private Button cancleBtn;
    private Button sureBtn;

    private boolean canClose = false;

    public DownloadDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_download,null);
        setContentView(view);
        initView(view);
        setCancelable(false);
    }

    private void initView(View view) {
        titleTv = (TextView) view.findViewById(R.id.title);
        msgTv = (TextView) view.findViewById(R.id.msg);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        cancleBtn = (Button) view.findViewById(R.id.cancel);
        sureBtn = (Button) view.findViewById(R.id.sure);
    }

    public DownloadDialog setTitle(String title){
        titleTv.setText(title);
        return this;
    }

    public DownloadDialog setMsg(String msg){
        msgTv.setText(msg);
        return this;
    }

    public DownloadDialog setProgressVisibility(int visibility){
        progressbar.setVisibility(visibility);
        return this;
    }

    public DownloadDialog setProgress(int progress){
        progressbar.setVisibility(View.VISIBLE);
        progressbar.setProgress(progress);
        return this;
    }

    public DownloadDialog setCanClose(boolean close){
        canClose = close;
        return this;
    }

    public DownloadDialog setButtonEnable(boolean enable){
        cancleBtn.setEnabled(enable);
        sureBtn.setEnabled(enable);
        return this;
    }

    public DownloadDialog setOnButtonClickListener(final OnButtonClickListener listener){
        if (listener!=null){
            cancleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (canClose){
                        dismiss();
                    }else {
                        listener.onCancle();
                    }
                }
            });

            sureBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (canClose){
                        dismiss();
                    }else {
                        listener.onSure();
                    }
                }
            });
        }
        return this;
    }

    public interface OnButtonClickListener{
        void onCancle();
        void onSure();
    }
}