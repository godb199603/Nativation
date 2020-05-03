package com.example.nativation.pintu;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nativation.R;

public class PinTuActivity extends AppCompatActivity {

    //每一行与每一列的图片个数
    private int ImageX=3;
    private int ImageY=3;
    //图片的总数
    private int imgCount=ImageX*ImageY;
    //空白的图片位置
    private int Emptypos=imgCount-1;
    //空白区域的id
    private int Emptyid=R.id.img8;

    private TextView textView;
    private ImageButton imageView;
    private ImageButton imageView1;
    private ImageButton imageView2;
    private ImageButton imageView3;
    private ImageButton imageView4;
    private ImageButton imageView5;
    private ImageButton imageView6;
    private ImageButton imageView7;
    private ImageButton imageView8;
    private int[] images={R.mipmap.link_0,R.mipmap.link_1,R.mipmap.link_2,R.mipmap.link_3,R.mipmap.link_4,R.mipmap.link_5,
            R.mipmap.link_6,R.mipmap.link_7,R.mipmap.link_8};
    private int[] imagedex=new int[images.length];
    //总时间线
    private int time=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                time++;
                textView.setText("时间"+time+"秒");
                //循环发消息
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_tu);
        initView();
        handler.sendEmptyMessageDelayed(1,1000);
        Log.i("Random1","打乱");
        SortRandom();


    }

    private void SortRandom() {
        for (int i=0;i<imagedex.length;i++){
            imagedex[i]=i;
        }
        Log.i("Random3","shengc");
        //随机20次交换
        int ran1,ran2;
        for (int i=0;i<imagedex.length;i++){
           ran1=(int)(Math.random()*(imagedex.length-1));
          do {
               ran2=(int)(Math.random()*(imagedex.length-1));
               if(ran1!=ran2){
                  //2个随机数不相同跳出循环
                  break;
              }
          }while(true);
          swap(ran1,ran2);
        }
        Log.i("Random2","shengc");
        imageView.setImageResource(images[imagedex[0]]);
        imageView1.setImageResource(images[imagedex[1]]);
        imageView2.setImageResource(images[imagedex[2]]);
        imageView3.setImageResource(images[imagedex[3]]);
        imageView4.setImageResource(images[imagedex[4]]);
        imageView5.setImageResource(images[imagedex[5]]);
        imageView6.setImageResource(images[imagedex[6]]);
        imageView7.setImageResource(images[imagedex[7]]);
        imageView8.setImageResource(images[imagedex[8]]);
    }

    private void swap(int ran1, int ran2) {
        int temp=imagedex[ran1];
        imagedex[ran1]=imagedex[ran2];
        imagedex[ran2]=temp;
    }

    private void initView() {
        textView=findViewById(R.id.textpin);
        imageView=findViewById(R.id.img);
        imageView1=findViewById(R.id.img1);
        imageView2=findViewById(R.id.img2);
        imageView3=findViewById(R.id.img3);
        imageView4=findViewById(R.id.img4);
        imageView5=findViewById(R.id.img5);
        imageView6=findViewById(R.id.img6);
        imageView7=findViewById(R.id.img7);
        imageView8=findViewById(R.id.img8);

    }


    public void onClick(View view) {
        int id=view.getId();
        switch(id){
            case R.id.img:
                move(R.id.img,0);
                break;
            case R.id.img1:
                move(R.id.img1,1);
                break;
            case R.id.img2:
                move(R.id.img2,2);
                break;
            case R.id.img3:
                move(R.id.img3,3);
                break;
            case R.id.img4:
                move(R.id.img4,4);
                break;
            case R.id.img5:
                move(R.id.img5,5);
                break;
            case R.id.img6:
                move(R.id.img6,6);
                break;
            case R.id.img7:
                move(R.id.img7,7);
                break;
            case R.id.img8:
                move(R.id.img8,8);
                break;
        }
    }

    private void move(int resid, int postion) {
        //当前处于第几行第几列
        int siteX=postion/ImageX;
        int siteY=postion%ImageY;
        //空白位置的坐标
        int emptyx=Emptypos/ImageX;
        int emptyy=Emptypos%ImageY;
        //判断可以移动，行数相同，列数差1，列数相同，行数差1
        int x=Math.abs(siteX-emptyx);
        int y=Math.abs(siteY-emptyy);
        if((x==0&&y==1)||(y==0&&x==1)){
            ImageButton imageView=findViewById(resid);
            imageView.setVisibility(View.INVISIBLE);
            //对空白区域进行设置
            ImageButton empty=findViewById(Emptyid);

            empty.setImageResource(images[imagedex[postion]]);
            //移动之后，将空间设可见
            empty.setVisibility(View.VISIBLE);
            //将改变的过程记录到数组中
            swap(postion,Emptypos);
            //新的空白区域的位置和id改变
            Emptypos=postion;
            Emptyid=resid;
        }
            pintusuccess();

    }

    private void pintusuccess() {
        boolean loop=true;
        for(int i=0;i<imagedex.length;i++) {
            if (imagedex[i] != i) {
                loop = false;
                break;
            }
        }
      if(loop){
          //停止计时
          handler.removeMessages(1);
          imageView.setClickable(false);
          imageView1.setClickable(false);
          imageView2.setClickable(false);
          imageView3.setClickable(false);
          imageView4.setClickable(false);
          imageView5.setClickable(false);
          imageView6.setClickable(false);
          imageView7.setClickable(false);
          imageView8.setClickable(false);
          imageView8.setImageResource(images[8]);
          imageView8.setVisibility(View.VISIBLE);
          Toast.makeText(this, "您成功了", Toast.LENGTH_SHORT).show();
      }

        }


    public void onRestart(View view) {

        imageView.setClickable(true);
        imageView1.setClickable(true);
        imageView2.setClickable(true);
        imageView3.setClickable(true);
        imageView4.setClickable(true);
        imageView5.setClickable(true);
        imageView6.setClickable(true);
        imageView7.setClickable(true);
        imageView8.setClickable(true);
        //找到之前隐藏的图片
        ImageButton imageButton=findViewById(Emptyid);
        imageButton.setVisibility(View.VISIBLE);
        //重新设置空白图片
        ImageButton imageButton2=findViewById(R.id.img8);
        imageButton2.setVisibility(View.INVISIBLE);
        Emptyid=R.id.img8;
        Emptypos=imgCount-1;
        SortRandom();
        handler.removeMessages(1);
        //时间归零
        time=0;
        textView.setText("时间"+time+"秒");
        handler.sendEmptyMessageDelayed(1,1000);
        //重新排列
    }
}
