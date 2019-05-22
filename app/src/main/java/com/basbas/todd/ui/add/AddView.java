package com.basbas.todd.ui.add;

import com.basbas.todd.base.BaseView;
import com.basbas.todd.model.ResponseData;

import java.util.List;

public interface AddView extends BaseView {
    void requestProcess();
    void requestFinish();
    void onResponseMsg(ResponseData sendResponse);
    void onErrorMsg(String error);
    void succsessPost();
    void succsessEdit();
    void succsessDelete();

}
