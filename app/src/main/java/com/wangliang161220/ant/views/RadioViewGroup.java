package com.wangliang161220.ant.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang161220.ant.R;

/**
 * Created by wangliang on 2016/8/7.
 */
public class RadioViewGroup extends LinearLayout {

    private int[] mDrawables , mOldDrawables;
    private String [] mTxts ;
    private float marginL,marginT,marginR,marginB , density;
    private int color , checkedColor , textSize = 14, mDrawableToTxtPadding = 5 , mWidth , mChildWidth ;
    private TextView[] mViews;
    private int prePosition = -1;
    private PostPosition postPosition;

    public RadioViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadioViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public RadioViewGroup(Context context) {
        super(context);
        init();
    }

    public void init(){
        this.setOrientation(LinearLayout.HORIZONTAL);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;
    }

    /*设置数据源，分别为图片数组，文字数组*/
    public void setData(int [] drawables , int [] olddrawables , String [] txts ){
        if(drawables.length > 0 && txts.length >0){
            this.mDrawables = drawables;
            this.mTxts = txts;
            this.mOldDrawables = olddrawables;
            if(drawables.length == txts.length)
                this.setWeightSum(drawables.length);
            mViews = new TextView[drawables.length];

            if(mChildWidth == 0)
                invalidate();
        }
    }
    public void setPostPosition(PostPosition postPosition){
        this.postPosition = postPosition;
    }
    public void setTxtColor(int color , int checkedColor){
        this.color = color;
        this.checkedColor = checkedColor;
    }
    /*图片和文字的间距*/
    public void setPadding(int padding){
        this.mDrawableToTxtPadding = padding;
    }
    public void setTextSize(int size){
        this.textSize = size;
    }

    public void myaddView(){
        if(mViews == null || mViews.length == 0) return;
        for(int i=0;i<mViews.length;i++) {
            mViews[i] = new TextView(getContext());
            mViews[i].setText(mTxts[i]);
            Drawable drawable = getResources().getDrawable(mOldDrawables[i]);
            drawable.setBounds(0 , 0 , drawable.getMinimumWidth() , drawable.getMinimumHeight());
            mViews[i].setCompoundDrawables(null , drawable , null , null);
            mViews[i].setCompoundDrawablePadding(mDrawableToTxtPadding);
            mViews[i].setTextColor(getResources().getColor(color));
            mViews[i].setTextSize(textSize);
            mViews[i].setTag(i);
            mViews[i].setPadding(0 , 10 , 0 , 10);
            addView(mViews[i]);
        }
       setSelected(0);
    }

    public void setSelected(int position){
        Drawable drawable;
        /*上一次选中的变灰*/
        if(prePosition == position) return;
        if(prePosition >= 0) {
            drawable = getResources().getDrawable(mOldDrawables[prePosition]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mViews[prePosition].setCompoundDrawables(null, drawable, null, null);
            mViews[prePosition].setTextColor(getResources().getColor(color));
        }

        /*这一次的变红*/
        drawable = getResources().getDrawable(mDrawables[position]);
        drawable.setBounds(0 , 0 , drawable.getMinimumWidth() , drawable.getMinimumHeight());
        mViews[position].setCompoundDrawables(null , drawable , null , null);
        mViews[position].setTextColor(getResources().getColor(checkedColor));

        prePosition = position;

        invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        setSelected((int)event.getX()/mChildWidth);
        postPosition.position((int)event.getX()/mChildWidth);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec , int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec , heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec , heightMeasureSpec);
        this.mChildWidth = mWidth/mDrawables.length;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed , l , t, r , b);
        for(int i=0 ; i<getChildCount();i++){
            View view = getChildAt(i);
            int cWidth = view.getMeasuredWidth();
            view.layout(i*mChildWidth+(mChildWidth - cWidth)/2 , 0 , (i+1)*mChildWidth - (mChildWidth - cWidth)/2 , getHeight());
        }
    }
}
