package com.example.nativation.threadtest;

/**
 * 作者：Create on 2020/4/5 22:35  by  wzl
 * 描述：
 * 最近修改：2020/4/5 22:35 modify by wzl
 */
public class Producer implements Runnable {
    private ShareResource shareResource;
    public Producer(ShareResource shareResource) {
        this.shareResource = shareResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                shareResource.push("春哥哥","男");
            }else {
                shareResource.push("凤姐","女");
            }
        }
    }
}
