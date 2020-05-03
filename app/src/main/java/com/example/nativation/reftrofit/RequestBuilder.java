package com.example.nativation.reftrofit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者：Create on 2020/3/28 17:18  by  wzl
 * 描述：
 * 最近修改：2020/3/28 17:18 modify by wzl
 */
public class RequestBuilder {


    //方法的请求方式(“Get,post”)
    private final String method;
    //接口的请求地址https:www.163.com
    private final HttpUrl baseUrl;
    //方法注解的值  ("/ip/newIp")
    private String relativeUrl;
    //请求url完整构建者
    private HttpUrl.Builder urlBuilder;
    //form表单构建者
    private FormBody.Builder formBuilder;
    //构建完整请求，包含url method  body
    private final Request.Builder requestBuilder;

    public RequestBuilder(String method, HttpUrl baseUrl, String relativeUrl, boolean hasBody) {
        this.method = method;
        this.baseUrl = baseUrl;
        this.relativeUrl = relativeUrl;
        this.requestBuilder = new Request.Builder();
        //有请求体，构建表单
        if (hasBody) formBuilder = new FormBody.Builder();
    }

    public RequestBuilder(String method, HttpUrl httpUrl, String relativeUrl, HttpUrl.Builder urlBuilder, FormBody.Builder formBuilder, Request.Builder requestBuilder) {
        this.method = method;
        this.baseUrl = httpUrl;
        this.relativeUrl = relativeUrl;
        this.urlBuilder = urlBuilder;
        this.formBuilder = formBuilder;
        this.requestBuilder = requestBuilder;
    }



       //注解的值@query(”ip“)
       //参数值value
    public void addQueryParam(String name, String value) {
        if (relativeUrl != null) {
            //将https：www.163.com拼接/ip/ipnews
            urlBuilder = baseUrl.newBuilder(relativeUrl);
            if (urlBuilder == null) {
                throw new IllegalArgumentException("url 为空");
            }
            //每次请求都会实例化 需要重置
            relativeUrl = null;
        }
        urlBuilder.addQueryParameter(name, value);
    }

    public void addFieldForm(String name, String value) {
        formBuilder.add(name, value);
    }




    Request build() {
        //定义局部变量   保证每次值都不一样， 易回收
        HttpUrl url;
        if (urlBuilder != null) {
            url = urlBuilder.build();
        } else {
            url = baseUrl.resolve(relativeUrl);
            if (url == null) {
                throw new IllegalArgumentException("build 时url 为空");
            }
        }

        //如果请求中有表单，构建方法中会初始化formBuilder 然后在实例化请求体
        RequestBody body = null;
        if (formBuilder != null) {
            body = formBuilder.build();
        }

        return requestBuilder
                .url(url)
                .method(method, body)
                .build();
    }


}
