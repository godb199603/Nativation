package com.example.nativation.reftrofit;

/**
 * 作者：Create on 2020/3/28 16:59  by  wzl
 * 描述：
 * 最近修改：2020/3/28 16:59 modify by wzl
 */


//保存参数的注解值，参数值
public abstract class ParameterHandler {

    abstract void apply(RequestBuilder builder, String value);


    //抽象方法自我实现，由外面调用和赋值
    //传过来的是注解值（“ip”）
    static final class Query extends ParameterHandler {
        private String name;
        public Query(String name) {
            this.name = name;
        }


         //该类的实现类，外部调用时调用
        @Override
        void apply(RequestBuilder builder, String value) {
            if (value == null) {
                return;
            }
            //拼接query参数
            //name为注解的值，value为参数值实际值
            builder.addQueryParam(name, value);
        }
    }

    static final class Field extends ParameterHandler {
        private String name;
        public Field(String name) {
            this.name = name;
        }
        @Override
        void apply(RequestBuilder builder, String value) {
            if (value == null) {
                return;
            }
            builder.addFieldForm(name, value);
        }
    }
}

