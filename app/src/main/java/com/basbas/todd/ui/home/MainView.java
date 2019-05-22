package com.basbas.todd.ui.home;

import android.content.Intent;

import com.basbas.todd.base.BaseView;
import com.basbas.todd.model.ResponseData;

import java.util.List;

public interface MainView extends BaseView {
    void requestProcess();
    void requestFinish();
    void loadData(List<ResponseData> data);
    void onResponseMsg(ResponseData sendResponse);
    void onErrorMsg(Throwable error);
    void onCatchError(Exception error);
    void refreshList(Intent intent);
}
