package com.basbas.todd.ui.add;

import com.basbas.todd.base.BasePresenter;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.network.ApiInterface;
import com.basbas.todd.network.ApiServer;
import com.basbas.todd.ui.home.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPresenterImp implements BasePresenter<AddView> {
    AddView view;

    void postData(String title, String body, String id) {
        view.requestProcess();
        ApiInterface service = ApiServer.getClient().create(ApiInterface.class);
        retrofit2.Call<ResponseData> call = service.postData(title, body, "1");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    view.requestFinish();
                    view.succsessPost();

                } else {
                    view.requestFinish();
                    view.onErrorMsg("Error Post Data");
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                view.requestFinish();
                view.onErrorMsg("Error Post Data");

            }
        });

    }

    void editData(String title, String body, String id) {
        view.requestProcess();
        ApiInterface service = ApiServer.getClient().create(ApiInterface.class);
        retrofit2.Call<ResponseData> call = service.editData(title, body, "1");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    view.requestFinish();
                    view.succsessEdit();

                } else {
                    view.requestFinish();
                    view.onErrorMsg("Error Edit Data");
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                view.requestFinish();
                view.onErrorMsg("Error Edit Data");

            }
        });

    }

    public void deleteData() {
        view.requestProcess();
        ApiInterface service = ApiServer.getClient().create(ApiInterface.class);
        retrofit2.Call<ResponseData> call = service.deleteData();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    view.requestFinish();
                    view.succsessDelete();

                } else {
                    view.requestFinish();
                    view.succsessDelete();
                  //  view.onErrorMsg("Error Edit Data");
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                view.requestFinish();
                view.onErrorMsg("Error Edit Data");

            }
        });

    }

    @Override
    public void onAttach(AddView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {

        this.view = null;
    }
}
