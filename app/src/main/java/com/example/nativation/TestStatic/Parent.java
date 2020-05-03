package com.example.nativation.TestStatic;

/**
 * 作者：Create on 2020/3/29 16:10  by  wzl
 * 描述：
 * 最近修改：2020/3/29 16:10 modify by wzl
 */

public class Parent {
    public static void staticMethod() {
        System.out.println("Parent staticMethod run");

    }

    public static void staticMethod2() {
        System.out.println("Parent staticMethod2 run");

    }
    public void method() {
        System.out.println("Parent method run");

    }
    private void method3() {
        System.out.println("Parent method run");
    }

}
