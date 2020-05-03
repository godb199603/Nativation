package com.example.nativation.photo;

import android.content.Context;

/**
 * 作者：Create on 2020/3/26 21:43  by  wzl
 * 描述：
 * 最近修改：2020/3/26 21:43 modify by wzl
 */
public class Glide {
    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }
}
