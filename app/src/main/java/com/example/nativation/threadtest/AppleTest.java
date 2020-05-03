package com.example.nativation.threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：Create on 2020/4/4 22:07  by  wzl
 * 描述：
 * 最近修改：2020/4/4 22:07 modify by wzl
 */
public class AppleTest {

    private final Lock lock=new ReentrantLock();

    public class Apple implements Runnable {
        private int num = 50;
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                eat();
            }
        }
            private void eat() {
                lock.lock();
                if (num > 0){
                    try {
                        System.out.println(Thread.currentThread().getName() + "吃了编号为" + num + "的苹果");
                        num--;
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }






//     class Persion extends Thread {
//        private int num=50;
//        public Persion(String name){
//            super(name);
//        }
//        @Override
//        public void run() {
//            for (int i=0;i<20;i++){
//                if(num>0){
//                    System.out.println(super.getName()+"吃了编号为"+num-- +"的苹果");
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Apple apple = new Apple();
//        new Thread(apple, "A").run();
//        new Thread(apple, "B").run();
//        new Thread(apple, "C").run();
//    }
//
//
//    public class Apple implements Runnable {
//        private int num = 50;
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 20; i++) {
//                synchronized (this) {
//                    if (num > 0) {
//                        eat();
//                    }
//                }
//              synchronized private void eat() {
//                    System.out.println(Thread.currentThread().getName() + "吃了编号为" + num-- + "的苹果");
//                }
//
//            }
//        }
//
//
//        public class Persion extends Thread {
//            private int num = 50;
//
//            public Persion(String name) {
//                super(name);
//            }
//
//            @Override
//            public void run() {
//                for (int i = 0; i < 20; i++) {
//                    if (num > 0) {
//                        System.out.println(super.getName() + "吃了编号为" + num-- + "的苹果");
//                    }
//                }
//            }
//        }
//    }

//        public static void main(String[] args) {
//        ShareResource shareResource=new ShareResource();
//        new Thread(new Producer(shareResource)).run();
//        new Thread(new Consumer(shareResource)).run();
//    }




}
