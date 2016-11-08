package com.wangly.retrofit.net;

import com.google.gson.Gson;
import com.wangly.retrofit.bean.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangly on 2016/10/31.
 */

public class RetrofitApi {
    public final static int CONNECT_TIME = 10;
    public final static int READ_TIME = 20;
    private ServiceInterface serviceInterface;


    public RetrofitApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);
    }

    public static RetrofitApi getInstance() {
        return new RetrofitApi();
    }


    public <T> void getRunkInfo(String userid, final Class<T> cls, final ResultCallBack callBack) {
        Call<ResponseBody> call = serviceInterface.getRankJsonString(userid);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                T t = null;
                if (response.isSuccess()) {

                    try {
                        String json = response.body().string();
                        Gson gson = new Gson();
                        t = gson.fromJson(json, cls);
                        if (null != callBack) {
                            callBack.onComplete(t);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (null != callBack) {
                    callBack.onFailure(t.getMessage());
                }
            }
        });
    }


}
