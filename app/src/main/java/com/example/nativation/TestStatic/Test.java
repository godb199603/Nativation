package com.example.nativation.TestStatic;

/**
 * 作者：Create on 2020/3/29 16:18  by  wzl
 * 描述：
 * 最近修改：2020/3/29 16:18 modify by wzl
 */

public class Test {

    public static void main(String[] args) {
        Parent child=new Son();
//        child.method2();
        child.staticMethod2();
        child.staticMethod(); //输出：Parent staticMethod run
        Son s=new Son();
        s.staticMethod();//输出：Parent staticMethod run
//        s.method3();
    }

}
