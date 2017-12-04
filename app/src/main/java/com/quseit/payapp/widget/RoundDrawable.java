package com.quseit.payapp.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

public class RoundDrawable extends BitmapDrawable {


    private final Paint paint;
    private float radisu;
    private RectF rectF;

    public RoundDrawable(Resources resources, Bitmap bitmap,int color){
        super(resources,bitmap);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        radisu = resources.getDisplayMetrics().density*8;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(rectF,radisu,radisu,paint);
    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        rectF = new RectF(left,top,right,bottom);
    }
}