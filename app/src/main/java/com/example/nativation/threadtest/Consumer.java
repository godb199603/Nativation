package com.example.nativation.threadtest;

/**
 * 作者：Create on 2020/4/5 22:42  by  wzl
 * 描述：
 * 最近修改：2020/4/5 22:42 modify by wzl
 */
public class Consumer implements Runnable {
    private ShareResource shareResource;
    public Consumer(ShareResource shareResource) {
        this.shareResource = shareResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
           shareResource.popop();
        }
    }
}
