package com.basbas.todd.base;

import androidx.appcompat.app.AppCompatActivity;

public interface BasePresenter <V> {
    void onAttach(V view);
    void onDetach();

}