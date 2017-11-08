package com.quseit.payapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quseit.payapp.R;

/**
 * Created by quseitu on 2017/11/7.
 */

public class IconView extends RelativeLayout{
    public IconView(Context context) {
        super(context);
    }
    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

   public void init(Context context, AttributeSet attrs){
       TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.IconView);
        int width = array.getInt(R.styleable.IconView_iconWidth,0);
       int height =array.getInt(R.styleable.IconView_iconHeight,0);
       int bgSourceID = array.getResourceId(R.styleable.IconView_bgSourceID,0);
       String title = array.getString(R.styleable.IconView_title);
       String  iconSource = array.getString(R.styleable.IconView_iconSource);

       LayoutInflater.from(context).inflate(R.layout.icon_view,this,true);

       ImageView iconBg= (ImageView) findViewById(R.id.iconImg);
       TextView iconTitle = (TextView) findViewById(R.id.iconText);
       IconText iconContent = (IconText) findViewById(R.id.iconContent);
       iconBg.setImageResource(R.mipmap.ic_launcher);

//       iconBg.setImageDrawable(ContextCompat.getDrawable(context,bgSourceID));
//       iconTitle.setText(title);
//       iconContent.setText(iconSource);
   }
}
