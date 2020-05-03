package com.example.nativation.okhttp;

/**
 * 作者：Create on 2020/3/24 20:28  by  wzl
 * 描述：
 * 最近修改：2020/3/24 20:28 modify by wzl
 */
public interface IHttpRequest {

    // 封装请求接口
    void setUrl(String url);
    void setData(byte[] data);
    void setListener(CallbackListener callbackListener);
    void execute();


}
