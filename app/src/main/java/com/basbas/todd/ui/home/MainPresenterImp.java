package com.basbas.todd.ui.home;

import android.util.Log;

import com.basbas.todd.base.BasePresenter;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.network.ApiInterface;
import com.basbas.todd.network.ApiServer;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenterImp implements BasePresenter<MainView> {
    private MainView view;


    public void loadData() {
        view.requestProcess();
        ApiInterface service = ApiServer.getClient().create(ApiInterface.class);
        retrofit2.Call<List<ResponseData>> call = service.getData();
        call.enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, Response<List<ResponseData>> response) {

                if (response.isSuccessful()){
                    view.requestFinish();
                    view.loadData(response.body());
                    Log.e("TAG","Response data" + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(MainView view) {
        this.view =view;

    }

    @Override
    public void onDetach() {
        view = null;
    }
}
