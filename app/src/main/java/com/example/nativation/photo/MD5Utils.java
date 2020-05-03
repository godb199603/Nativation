package com.example.nativation.photo;

/**
 * 作者：Create on 2020/3/26 19:47  by  wzl
 * 描述：
 * 最近修改：2020/3/26 19:47 modify by wzl
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5相关工具类
 * Created by tsy on 16/8/15.
 */
public class MD5Utils {

    private static final String TAG = "MD5Utils";

    /**
     * md5加密
     * @param plainText 待加密字符串
     * @return 加密后32位字符串
     */
    public static String getMd5(String plainText) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
//            LogUtils.e(TAG, "getMd5 error", e);
            return null;
        }

    }
}
