package com.timen4.ronnny.customscrollview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by luore on 2016/7/24.
 */
public class CustomScrollView extends ViewGroup {
    int mScreenHeight;
    private int mStartY;
    private int mEnd;
    private Scroller mScroller;

    public CustomScrollView(Context context) {
        this(context,null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        mScreenHeight=wm.getDefaultDisplay().getHeight();
        mScroller = new Scroller(getContext());
    }

    //在onMeasure中测量子view
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //set the ViewGroup's height
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();

        lp.height=mScreenHeight*childCount;
        setLayoutParams(lp);
        //绘制子view的位置
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                childView.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartY = y;
                //当我们再次触碰屏幕时，如果之前的滚动动画还没有停止，我们也让他立即停止
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:

                int dY= mStartY -y;
                //判断滚动的距离不超出上下边缘的限制
                if(getScrollY()<0||getScrollY()>getHeight()-mScreenHeight){
                    dY=0;
                }
                //让我们的view滚动相应的dy距离
                scrollBy(0,dY);

                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStartY;
                if(dScrollY>0){//向上滚动的情框
                    if (dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{//向下滚动的情框
                    if(-dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}