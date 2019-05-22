package com.basbas.todd.network;

import com.basbas.todd.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("posts")
    Call<List<ResponseData>> getData();

    @FormUrlEncoded
    @POST("posts")
    Call<ResponseData> postData(@Field("title") String title,
                                @Field("body") String body,
                                @Field("userId") String userId);

    @FormUrlEncoded
    @PUT("posts/1")
    Call<ResponseData> editData(
                                //@Path("id") String id,
                                @Field("title") String title,
                                @Field("body") String body,
                                @Field("userId") String userId);

    @DELETE("posts/1")
    Call<ResponseData> deleteData();

}
