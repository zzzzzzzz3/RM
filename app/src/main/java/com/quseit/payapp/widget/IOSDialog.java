package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quseit.payapp.R;


public class IOSDialog extends Dialog {

    private TextView mTitle;
    private TextView mContent;
    private Button mPositiveButton;
    private Button mNegativeButton;

    public IOSDialog(Context context) {
        super(context);
    }

    public IOSDialog(Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ios, null);
        setContentView(view);
        initView(view);
    }

    private void initView(View view) {
        mTitle = (TextView) view.findViewById(R.id.dialog_title);
        mContent = (TextView) view.findViewById(R.id.dialog_content);
        mPositiveButton = (Button) view.findViewById(R.id.dialog_positive_btn);
        mNegativeButton = (Button) view.findViewById(R.id.dialog_nagative_btn);
    }

    public IOSDialog setTitle(String title) {
        if (title.equals("")) {
            mTitle.setVisibility(View.GONE);
        } else {
            mTitle.setText(title);
        }
        return this;
    }

    public IOSDialog setContent(String content) {
        if (content.equals("")) {
            mContent.setVisibility(View.GONE);
        } else {
            mContent.setText(content);
        }
        return this;
    }

    public IOSDialog setPositiveButton(String text, final OnPositiveClickListener listener) {
        if (text != null && !text.equals("")) {
            mPositiveButton.setVisibility(View.VISIBLE);
            mPositiveButton.setText(text);
            if (listener != null) {
                mPositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        listener.onPositiveClick();
                    }
                });
            }
        }
        return this;
    }

    public IOSDialog setNegativeButton(String text, final OnNegativeClickListener listener) {
        if (text != null && !text.equals("")) {
            mNegativeButton.setVisibility(View.VISIBLE);
            mNegativeButton.setText(text);
            if (listener != null) {
                mNegativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        listener.onNegativeClick();
                    }
                });
            }
        }
        return this;
    }


    public interface OnPositiveClickListener {
        void onPositiveClick();
    }

    public interface OnNegativeClickListener {
        void onNegativeClick();
    }
}