package com.example.nativation.okhttp;

import java.io.InputStream;

/**
 * 作者：Create on 2020/3/24 20:29  by  wzl
 * 描述：
 * 最近修改：2020/3/24 20:29 modify by wzl
 */
public interface CallbackListener {
    void onSuccess(InputStream inputStream);
    void onFailure();
}
