package com.example.nativation.photo;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.net.URL;

/**
 * 作者：Create on 2020/3/26 16:46  by  wzl
 * 描述：
 * 最近修改：2020/3/26 16:46 modify by wzl
 */
public class BitmapRequest {

    public BitmapRequest(Context context) {
        this.context = context;
    }
    //请求路径
    private String url;
    //上下文
    private Context context;
    //需要加载图片的控件
    private SoftReference<ImageView> imageView;
    //占位图片
    private int resId;
    //回调对象，把图片返回
    private RequestListener requestListener;
    //图片标识
    private String urlMd5;


    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public int getResId() {
        return resId;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }




    public BitmapRequest load(String url){
    this.url=url;
    this.urlMd5=MD5Utils.getMd5(url);
    return this;
    }
    public BitmapRequest loading(int resId){
        this.resId=resId;
        return this;
    }
    public BitmapRequest setListener(RequestListener requestListener){
        this.requestListener=requestListener;
        return this;
    }
    public void into(ImageView imageView){
        imageView.setTag(this.urlMd5);
        this.imageView=new SoftReference<>(imageView);
         //将请求丢到队列中
        RequestManager.getInstance().addBitmapRequest(this);
    }




}
