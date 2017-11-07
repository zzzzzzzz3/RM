package com.quseit.payapp.widget;

/**
 * Created by quseitu on 2017/11/6.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class IconText extends android.support.v7.widget.AppCompatTextView{
    public static final String FONT_PATH = "fonts/icomoon.ttf";
    public static Typeface mTypeface;
    public IconText(Context context) {
        this(context, null);
    }

    public IconText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if(mTypeface == null){
            mTypeface = Typeface.createFromAsset(getContext().getAssets(), FONT_PATH);
        }
        setTypeface(mTypeface);
    }
}
