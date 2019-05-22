package com.basbas.todd.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.basbas.todd.R;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.ui.add.AddDataActivity;
import com.basbas.todd.ui.detail.DetailActivity;
import com.basbas.todd.ui.home.adapter.RecylerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    ProgressBar progressBar;
    private MainPresenterImp presenter;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvData = (RecyclerView) findViewById(R.id.rv_data);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent sendData = new Intent(MainActivity.this, AddDataActivity.class);
                sendData.putExtra("flag","flag");
                startActivity(sendData);


            }
        });

        layoutManager = new LinearLayoutManager(this);
        //id dari recylerfivew
        rvData.setLayoutManager(layoutManager);

        presenter =  new MainPresenterImp();
        onAttachView();
        presenter.loadData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void requestProcess() {
       progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void requestFinish() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadData(List<ResponseData> data) {
        Log.e("TAG","Response data main" + new Gson().toJson(data));

        RecylerViewAdapter  recylerViewAdapter = new RecylerViewAdapter(this, data);
        rvData.setAdapter(recylerViewAdapter);
        recylerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseMsg(ResponseData sendResponse) {

    }

    @Override
    public void onErrorMsg(Throwable error) {

    }

    @Override
    public void onCatchError(Exception error) {

    }

    @Override
    public void refreshList(Intent intent) {

    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();

    }
}
