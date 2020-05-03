package com.example.nativation.okhttp;

/**
 * 作者：Create on 2020/3/24 21:25  by  wzl
 * 描述：
 * 最近修改：2020/3/24 21:25 modify by wzl
 */
public class NetUtils {


    //1.URL 2.参数 3.返回值 4.返回接口
    public static<T,M> void sendJsonRequest(String url,T requestData,
                                            Class<M> response, IJsonListener listener){
        JsonHttpRequest httpRequest = new JsonHttpRequest();
        CallbackListener callbackListener = new JsonCallBackListener<>(response,listener);

        //最终目标
        HttpTask httpTask = new HttpTask(url,requestData,httpRequest,callbackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
