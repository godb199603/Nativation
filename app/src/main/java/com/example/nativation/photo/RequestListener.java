package com.example.nativation.photo;

import android.graphics.Bitmap;

/**
 * 作者：Create on 2020/3/26 19:43  by  wzl
 * 描述：
 * 最近修改：2020/3/26 19:43 modify by wzl
 */
public interface RequestListener {

    boolean onSuccess(Bitmap bitmap);
    boolean onFailure();

}
