package com.quseit.pay;

/**
 * Created by quseitu on 2017/12/21.
 */

import com.landicorp.android.scan.camera.CameraManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import payapp.quseit.com.paylib.R;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ScanView extends View {


    //维护cameraManager变量
    private CameraManager cameraManager;

    //刷新界面的时间
    private static final long ANIMATION_DELAY = 10L ;
    private static final int OPAQUE = 0xFF ;

    //四个绿色边角对应的长度
    private int ScreenRate ;
    //四个绿色边角对应的宽度
    private static final int CORNER_WIDTH = 5 ;

    //扫描框中中间线的宽度
    private static final int MiDDLE_LINE_WIDTH = 3 ;

    //扫描框中的中间线与扫描框左右的间隙
    private static final int MIDDLE_LINE_PADDING = 5;

    //中间那条线每次刷新移动的距离
    private static final int SPEEN_DISTANCE = 5 ;

    //手机的屏幕密度
    private static float density ;

    //字体大小
    private static final int TEXT_SIZE = 16 ;

    //字体距离扫描框下面的距离
    private static final int TEXT_PADDING_TOP = 30;

    //画笔对象的引用
    private Paint paint ;

    //中间滑动线的最顶端位置
    private int slideTop ;
    //中间滑动线的最底端位置
    private int slideBottom ;

    private Bitmap resultBitmap ;
    private final int maskColor ;
    private final int resultColor ;

    private final int resultPointColor ;

    boolean isFirst ;

    public ScanView( Context context , AttributeSet attrs ){
        super(context , attrs);

        density = context.getResources().getDisplayMetrics().density;
        //像素转化成dp
        ScreenRate = (int) (20*density);

        paint = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);

        resultPointColor = resources.getColor(R.color.possible_result_points);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //中间的扫描框,想要修改扫描框的大小可以去CameraManager里面修改
        Rect frame = new Rect(0+60,0+320,canvas.getWidth()-60,canvas.getHeight()-320);
        Log.e("tag---->","top:"+frame.top+"--->left"+frame.left+"--->right:"+frame.right+"--->bottom:"+frame.bottom);
        if( frame == null ){
            return;
        }

        //初始化中间线滑动的最上边和最下边
        if( !isFirst ){
            isFirst = true ;
            slideTop = frame.top;
            slideBottom = frame.bottom ;
        }

        //获取屏幕的宽和高
        int width = canvas.getWidth() ;
        int height = canvas.getHeight() ;

        paint.setColor(resultBitmap != null ? resultColor : maskColor );

        //画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面
        //扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        }else {

            //画扫描框边上的角，总共8个部分
            paint.setColor(Color.GREEN);
            canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
                    + ScreenRate, frame.bottom, paint);
            canvas.drawRect(frame.left, frame.bottom - ScreenRate,
                    frame.left + CORNER_WIDTH, frame.bottom, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
                    frame.right, frame.bottom, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
                    frame.right, frame.bottom, paint);


            //绘制中间的线,每次刷新界面，中间的线往下移动SPEEN_DISTANCE
            slideTop += SPEEN_DISTANCE;
            if(slideTop >= frame.bottom){
                slideTop = frame.top;
            }
            canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MiDDLE_LINE_WIDTH/2, frame.right - MIDDLE_LINE_PADDING,slideTop + MiDDLE_LINE_WIDTH/2, paint);

            //画扫描框下面的字
            paint.setColor(Color.WHITE);
            paint.setTextSize(TEXT_SIZE * density);
            paint.setAlpha(0x40);
            paint.setTypeface(Typeface.create("System", Typeface.BOLD));
            canvas.drawText("请将二维码对准屏幕正中央扫描框", frame.left, (float) (frame.bottom + (float)TEXT_PADDING_TOP *density), paint);


            //只刷新扫描框的内容，其他地方不刷新
            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
                    frame.right, frame.bottom);
        }
    }
}
