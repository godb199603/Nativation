package com.example.nativation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.SeekBar;

import com.example.nativation.TestStatic.MyOK;
import com.example.nativation.photo.Glide;
import com.example.nativation.photo.RequestListener;
import com.example.nativation.reftrofit.ParameterHandler;
import com.example.nativation.reftrofit.RetrofitManager;
import com.example.nativation.reftrofit.WeatherApiService;
import com.example.nativation.reftrofit.WeatherBean;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//
//        Glide.with(this).load("http://").loading(R.mipmap.ic_launcher).setListener(new RequestListener() {
//            @Override
//            public boolean onSuccess(Bitmap bitmap) {
//                return false;
//            }
//
//            @Override
//            public boolean onFailure() {
//                return false;
//            }
//        }).into(imageview);


//        Retrofit retrofit = new Retrofit.Builder()
//                //设置数据解析器
//                .addConverterFactory(GsonConverterFactory.create())
//                //设置网络请求的Url地址,自动拼接
//                .baseUrl("http://v.juhe.cn/")
//                .build();






        RetrofitManager retrofit2 = new RetrofitManager.Builder()
                .baseUrl("http://whois.pconline.com.cn").bulid();
        WeatherApiService weatherApiService = retrofit2.create(WeatherApiService.class);
        Call call = weatherApiService.getweather(2, "天津", "1e1c1eeb0c1fc5127a99ad664b595908");
//        InputStream inputStream=null;
//        try {
//            inputStream=getAssets().open("big.png");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bigview.setImage(is);

    }
}
