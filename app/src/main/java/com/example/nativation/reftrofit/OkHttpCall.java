package com.example.nativation.reftrofit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 作者：Create on 2020/3/28 16:04  by  wzl
 * 描述：
 * 最近修改：2020/3/28 16:04 modify by wzl
 */
public class OkHttpCall implements Call {


    private ServiceMethod serviceMethod;
    private Object[] args;
    private Call rawCall;

    public OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
        //最终实现的是rawcall的方法
        this.rawCall = serviceMethod.toCall(args);
    }


    @Override
    public Response execute() throws IOException {
        return rawCall.execute();
    }

    @Override
    public void enqueue(Callback callback) {
        rawCall.enqueue(callback);
    }

    @Override
    public boolean isExecuted() {
        return rawCall.isExecuted();
    }

    @Override
    public void cancel() {
       rawCall.cancel();
    }

    @Override
    public boolean isCanceled() {
        return rawCall.isCanceled();
    }

    @Override
    public Call clone() {
        return new OkHttpCall(serviceMethod,args);
    }

    @Override
    public Request request() {
        return rawCall.request();
    }
}
