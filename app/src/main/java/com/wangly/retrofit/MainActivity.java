package com.wangly.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wangly.retrofit.bean.RunkData;
import com.wangly.retrofit.net.ResultCallBack;
import com.wangly.retrofit.net.RetrofitApi;

public class MainActivity extends AppCompatActivity {
    private TextView tv_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Content = (TextView) findViewById(R.id.tv_content);
    }

    //TODO: Request NetWork
    public void Request(View view) {
       /* OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.testUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);

        Call<ResponseBody> call = serviceInterface.getRankJsonString("40");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("MainActivity", "当前线程：" + Thread.currentThread().getId());
                if (response.isSuccess()) {
                    try {
                        String json = response.body().string();

                        if (json.startsWith("\uFEFF")) {
                            json = json.substring(1, json.length());
                        }
                        Log.i("MainActivity", "网络数据内容：" + json);

                        Gson gson = new Gson();
                        RunkData runkData = gson.fromJson(json, RunkData.class);
                        tv_Content.setText(runkData.getCity());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("MainActivity", "网络错误数据：" + t.getMessage() + "\n"
                );
            }
        });*/



        //------修改之后的----------

        RetrofitApi.getInstance().getRunkInfo("40", RunkData.class, new ResultCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public <T> void onComplete(T t) {
                RunkData info = (RunkData) t;
                tv_Content.setText(info.toString());


            }
        });
    }
}
