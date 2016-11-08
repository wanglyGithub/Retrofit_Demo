package com.wangly.retrofit.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangly on 2016/10/31.
 */

public interface ServiceInterface{

    @GET("MemberInfo/UserInfo/")
    Call<ResponseBody> getRankJsonString(@Query("userid") String userid);

}
