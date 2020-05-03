package com.example.nativation.okhttp;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * 作者：Create on 2020/3/24 20:33  by  wzl
 * 描述：
 * 最近修改：2020/3/24 20:33 modify by wzl
 */
public class HttpTask<T> implements Runnable{

    private IHttpRequest mIHttpRequest;
    //传什么数据   requestData
    public  HttpTask(String url,T requestData,IHttpRequest httpRequest,CallbackListener callbackListener){
        mIHttpRequest=httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(callbackListener);
        String content = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        mIHttpRequest.execute();
    }
}
