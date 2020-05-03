package com.example.nativation.bigimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;

/**
 * 作者：Create on 2020/3/31 21:43  by  wzl
 * 描述：
 * 最近修改：2020/3/31 21:43 modify by wzl
 */
public class BigView extends View implements GestureDetector.OnGestureListener,View.OnTouchListener{
    //实际上mRect的大小为原来图片的大小
    private Rect mRect;
    private BitmapFactory.Options mOptions;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    //图片的真实大小
    private int mImageWidth;
    private int mImageHeight;
    //得到测量的高度
    private int mViewWidth;
    private int mViewHeight;
    private BitmapRegionDecoder mBitmapRegionDecoder;
    private float mScale;
    private Bitmap mBitmap;
    public BigView(Context context) {
        super(context);
    }

    public BigView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BigView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //第一步设置bigview的成员变量
        mRect=new Rect();
        //对内存进行复用
        mOptions=new BitmapFactory.Options();
        //加入手势识别
        mGestureDetector=new GestureDetector(context,this);
        //滚动类
        mScroller=new Scroller(context);
        setOnTouchListener(this);
    }


    //设置图片，由于我们加载图片时候，只加载一部分
    public void setImage(InputStream is){
        //获取到图片的宽和高，又不能将图片加载进入内存
        mOptions.inJustDecodeBounds=true;
        BitmapFactory.decodeStream(is,null,mOptions);
        mImageWidth=mOptions.outWidth;
        mImageHeight=mOptions.outHeight;
        //开启复用
        mOptions.inMutable=true;
        //设置rgb555
        mOptions.inPreferredConfig= Bitmap.Config.RGB_565;
        mOptions.inJustDecodeBounds=false;

        //创建区域解码器,解码我们所在区域
        try {
            mBitmapRegionDecoder=BitmapRegionDecoder.newInstance(is,false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //第三步，开始测量，测量图片的缩放比例
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth=getMeasuredWidth();
        mViewHeight=getMeasuredHeight();
        //确定加载图片区域
        mRect.left=0;
        mRect.top=0;
        mRect.right=mImageWidth;
        mScale=mViewWidth/(float)mImageWidth;
        mRect.bottom=(int)(mViewHeight/mScale);
    }

    //第4步
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mBitmapRegionDecoder==null){
            return;
        }
        //启动复用
        //复用的bit毛必须与之前的bitmap尺寸相同
        mOptions.inBitmap=mBitmap;
        //指定解码区域
        mBitmap=mBitmapRegionDecoder.decodeRegion(mRect,mOptions);
        //对矩阵进行缩放，得到view的大小
        Matrix matrix=new Matrix();
        matrix.setScale(mScale,mScale);
        canvas.drawBitmap(mBitmap,matrix,null);
    }



    //第5步处理点击事件
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //直接将事件交给手势处理
        return mGestureDetector.onTouchEvent(motionEvent);
    }
   //将手按下去
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //如果移动没有停止，强行停止
        if (!mScroller.isFinished()){
            mScroller.forceFinished(true);
        }
        //继续接受后序事件   return false不处理后序事件
        return true;
    }

    //处理滑动
    //参数motionEvent开始事件，手指按下去获取坐标
    // motionEvent1当前事件坐标
    //V V1在x与y周上移动的距离
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float x, float y) {
        //上下移动的时候，mRect需要改变显示的区域
        mRect.offset(0,(int)y);
        //处理到达顶部与底部的情况
        if(mRect.bottom>mImageHeight){
            mRect.bottom=mImageHeight;
            mRect.top=mImageHeight-(int)(mViewHeight/mScale);
        }else{
            if (mRect.top<0){
                mRect.top=0;
                mRect.bottom=(int)(mViewHeight/mScale);
            }
        }
        invalidate();
        return false;
    }


   //处理惯性问题
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float x, float y) {
        mScroller.fling(0,mRect.top,0,(int)-y,0,0,0,
                mImageHeight-mImageHeight-(int)(mViewHeight/mScale));
        return false;
    }

    @Override
    public void computeScroll() {
        if (mScroller.isFinished()){
            return;
        }
        //滑动没有结束的
        if (mScroller.computeScrollOffset()){
             mRect.top=mScroller.getCurrY();
             mRect.bottom=mRect.top+(int)(mViewHeight/mScale);
             invalidate();
        }
//        super.computeScroll();
    }

    //处理计算结果
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }



    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }



}
