package com.example.nativation.okhttp;

import java.io.InputStream;

/**
 * 作者：Create on 2020/3/24 21:18  by  wzl
 * 描述：
 * 最近修改：2020/3/24 21:18 modify by wzl
 */
public interface IJsonListener<T>  {
    void onSuccess(T t);
    void onFailure();
}
