package com.example.nativation.threadtest;

/**
 * 作者：Create on 2020/4/4 19:26  by  wzl
 * 描述：
 * 最近修改：2020/4/4 19:26 modify by wzl
 */
public class ThreadTest {
//       public static void main(String[] args){
//           playGame();
//           playmusic();
//       }

    private static void playmusic() {
           for (int i=0;i<5;i++){
               System.out.println("放音乐");
           }
    }

    private static void playGame() {
        for (int i=0;i<5;i++){
            System.out.println("打游戏");
        }
    }

}
