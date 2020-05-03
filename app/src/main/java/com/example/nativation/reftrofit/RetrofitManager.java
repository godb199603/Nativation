package com.example.nativation.reftrofit;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;


/**
 * 作者：Create on 2020/3/28 10:17  by  wzl
 * 描述：
 * 最近修改：2020/3/28 10:17 modify by wzl
 */
public class RetrofitManager{

    //接口请求的地址
    private HttpUrl baseUrl;
    //发起okhttp请求
    private Call.Factory callFactory;
    //缓存集合  避免同一个接口重复去解读，浪费性能
    //key是host.get()，value是该方法的属性封装
    private Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();




    //不让外面new
    public RetrofitManager(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.callFactory = builder.callFactory;
    }
    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    public Call.Factory getCallFavtory() {
        return callFactory;
    }



    public <T> T create(Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //拦截某方法的内容，方法名，方法注解值，方法的参数注解值，方法的参数值
                //将信息封装到一个bean类中
                ServiceMethod serviceMethod = loadServiceMethod(method);

                return new OkHttpCall(serviceMethod,args);

            }
        });
    }


    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result = serviceMethodCache.get(method);
        if (result != null){
            return result;
        }
        //同步锁。避免同时调用时都去获取一个新的，这样锁了之后进去再获取一次，确保
        synchronized (this){
            result = serviceMethodCache.get(method);
            if (result == null) {
                //新建bean类的时候需要获取到url，所以需要将retrofit传进去
                result = new ServiceMethod.Builder(this,method).build();
                serviceMethodCache.put(method,result);
            }
        }
        return result;
    }




    public static final class Builder {

        //接口请求的地址
        private HttpUrl baseUrl;
        //发起okhttp请求
        private Call.Factory callFactory;


        public Builder callFactory(okhttp3.Call.Factory factory) {
//            this.callFactory = checkNotNull(factory, "factory == null");
            if (factory == null) {
                throw new NullPointerException("factory can not be null");
            }
            this.callFactory = factory;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
//            checkNotNull(baseUrl, "baseUrl == null");
            if (baseUrl.isEmpty()) {
                throw new NullPointerException("url can not be null");
            }
            this.baseUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public RetrofitManager bulid(){
                if (baseUrl == null) {
                    throw new IllegalStateException("Base URL required.");
                }

                if (callFactory == null) {
                    callFactory = new OkHttpClient();
                }
            return  new RetrofitManager(this);
        }


    }
}
