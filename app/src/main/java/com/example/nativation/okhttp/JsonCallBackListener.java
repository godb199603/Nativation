package com.example.nativation.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



/**
 * 作者：Create on 2020/3/24 20:57  by  wzl
 * 描述：
 * 最近修改：2020/3/24 20:57 modify by wzl
 */
public class JsonCallBackListener<T> implements  CallbackListener{


    public JsonCallBackListener(Class<T> responseclass,IJsonListener iJsonListener) {
        this.responseclass = responseclass;
        this.iJsonListener=iJsonListener;
    }

    private Class<T> responseclass;
    private Handler mHandler=new Handler(Looper.getMainLooper());
    private IJsonListener iJsonListener;


    @Override
    public void onSuccess(InputStream inputStream) {
    //将流转换为对应的类型
        String response=getContent(inputStream);
        final T clazz= JSON.parseObject(response,responseclass);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                iJsonListener.onSuccess(clazz);
            }
        });
    }

    @Override
    public void onFailure() {

    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            StringBuilder str = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } catch (IOException e) {

                System.out.println("error" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("error" + e.toString());
                }
            }
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
