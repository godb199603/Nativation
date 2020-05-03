package com.example.nativation.reftrofit;

import com.example.nativation.reftrofit.http.Field;
import com.example.nativation.reftrofit.http.GET;
import com.example.nativation.reftrofit.http.POST;
import com.example.nativation.reftrofit.http.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.HttpUrl;


/**
 * 作者：Create on 2020/3/28 16:04  by  wzl
 * 描述：
 * 最近修改：2020/3/28 16:04 modify by wzl
 */
public class ServiceMethod {



    final Call.Factory callFactory; //Okhttp的实现接口
    final HttpUrl httpUrl;//接口请求地址
    private String httpMethod;   //方法的请求方式   get post
    private String relativeUrl;   //方法注解的值   “ip/upnews”
    private ParameterHandler[] parameterHandlers;//方法参数的数组。每个对象包括参数值，参数注解值
    private boolean hasBody;   //是否有请求体


    private ServiceMethod(Builder builder) {
        this.callFactory = builder.retrofit.getCallFavtory();
        this.httpUrl = builder.retrofit.getBaseUrl();
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.parameterHandlers = builder.parameterHandlers;
        this.hasBody = hasBody;
    }



    public Call toCall(Object[] args) {
        //拼装
        RequestBuilder requestBuilder = new RequestBuilder(httpMethod, httpUrl, relativeUrl, hasBody);

        ParameterHandler[] parameterHandlers = this.parameterHandlers;
        //要做一个检测，如果收集到的参数值数量长度和service里定义的不一样 那样肯定是不行的
        //orgs为真实参数长度。method为注解找出
        int argumentCount = args != null ? args.length : 0;
        if (argumentCount != parameterHandlers.length){
            throw new IllegalArgumentException("收集参数错误！");
        }
        for (int i = 0; i < argumentCount; i++) {
            //循环拼接
            parameterHandlers[i].apply(requestBuilder, (String) args[i]);
        }
        return callFactory.newCall(requestBuilder.build());

    }


    static final class Builder {
        private RetrofitManager retrofit;

        Method method;  //带注解的方法

        Annotation[] methodAnnotations; // 一个方法的所有注解

        Annotation[][] paramsAnnotationArray; //参数的注解，多个参数，一个参数多个注解

        private ParameterHandler[] parameterHandlers;//方法参数的数组。每个对象包括参数值，参数注解值

        private String httpMethod;   //方法的请求方式   get post

        private String relativeUrl;   //方法注解的值 ”/ip/ipMews“

        private boolean hasBody;   //是否有请求体


        Builder(RetrofitManager retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            //方法的所有注解
            this.methodAnnotations = method.getAnnotations();
            //方法参数的所有注解
            this.paramsAnnotationArray = method.getParameterAnnotations();
        }

       public ServiceMethod build() {
            //遍历方法的每一个注解  存在get或者post
            //获取方法的注解
            for (Annotation methodAnnotation : methodAnnotations) {
                parseMethodAnnotation(methodAnnotation);
            }
           //方法参数的注解
           int parameterCount = paramsAnnotationArray.length;  //实际上代表的是外层  参数的个数
           //初始化参数对象数组
           parameterHandlers = new ParameterHandler[parameterCount];
           for (int i = 0; i < parameterCount; i++) {
               Annotation[] parameterAnnotations = paramsAnnotationArray[i];   //每一个参数的对应的所有注解
               if (parameterAnnotations == null) {
                   throw new IllegalArgumentException("参数注解不存在");
               }
               //获取参数的注解值以及参数值(“key”)
               parameterHandlers[i] = parsePrarmeter(parameterAnnotations);
           }
           return new ServiceMethod(this);
        }

        private void parseMethodAnnotation(Annotation methodAnnotation) {
            if (methodAnnotation instanceof GET) {
                //解析get方法。最后一个参数是看是否有请求体，get请求是没有请求体的
                parseHttpMethodPath("GET", ((GET) methodAnnotation).value(), false);
            } else if (methodAnnotation instanceof POST) {
                parseHttpMethodPath("POST", ((POST) methodAnnotation).value(), true);
            }
        }

        private void parseHttpMethodPath(String type, String value, boolean hasBody) {
            //将请求的方式赋值，get或者post
            this.httpMethod = type;
            //("/api/login")
            this.relativeUrl = value;
            this.hasBody = hasBody;
        }

        /**
         * 解析每个参数的所有注解
         *
         * @param parameterAnnotations 参数的注解数组
         * @return
         */
        private ParameterHandler parsePrarmeter(Annotation[] parameterAnnotations) {
            ParameterHandler result = null;
            //遍历每个参数的每个注解
            for (Annotation parameterAnnotation : parameterAnnotations) {
                //参数的注解可能为Query或者Field
                ParameterHandler parameterHandler = parseParameterAnnotation(parameterAnnotation);
                //如果找不到，继续寻找
                if (parameterHandler == null){
                    continue;
                }
                result = parameterHandler;
            }
            return result;
        }


        /**
         * @param parameterAnnotation 而解析
         * @return
         */
        private ParameterHandler parseParameterAnnotation(Annotation parameterAnnotation) {
            if (parameterAnnotation instanceof Query) {
                //参数名@Query(“id”)
                Query query = (Query) parameterAnnotation;
                //“id”
                String name = query.value();
                return new ParameterHandler.Query(name);
            } else if (parameterAnnotation instanceof Field) {
                // @Field(“id”)
                Field query = (Field) parameterAnnotation;
                //“id”
                String name = query.value();
                return new ParameterHandler.Field(name);
            }
            return null;
        }


    }
}
