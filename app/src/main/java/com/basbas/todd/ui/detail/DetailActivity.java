package com.basbas.todd.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basbas.todd.R;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.ui.add.AddDataActivity;
import com.basbas.todd.ui.add.AddPresenterImp;
import com.basbas.todd.ui.add.AddView;
import com.basbas.todd.ui.home.MainActivity;

public class DetailActivity extends AppCompatActivity implements AddView {


    String getTitle, getBody, getUserId;
    Intent intent;
    TextView tvTitle, tvBody;
    Button btnDelete, btnUpdate;
    ProgressBar progressBar;
    AddPresenterImp presenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        getData();
        tvTitle.setText(getTitle);
        tvBody.setText(getBody);

        initOnClick();
        presenterImp = new AddPresenterImp();
        onAttachView();




    }

    private void initOnClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData = new Intent(DetailActivity.this, AddDataActivity.class);
                sendData.putExtra("title",tvTitle.getText().toString());
                sendData.putExtra("content",tvBody.getText().toString());
                sendData.putExtra("id",getUserId);
                startActivity(sendData);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenterImp.deleteData();
            }
        });

    }

    private void getData() {

        Bundle bundle=getIntent().getExtras();
        getTitle = bundle.getString("title");
        getBody = bundle.getString("content");
        getUserId = bundle.getString("id");

    }

    private void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title_at_detail);
        tvBody = (TextView)findViewById(R.id.tv_content_at_detail);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnUpdate = (Button) findViewById(R.id.btn_edit);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void requestProcess() {
        progressBar.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

    }

    @Override
    public void requestFinish() {
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onResponseMsg(ResponseData sendResponse) {

    }

    @Override
    public void onErrorMsg(String error) {
        Toast.makeText(this, "Failed Delete", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void succsessPost() {

    }

    @Override
    public void succsessEdit() {

    }

    @Override
    public void succsessDelete() {
        Toast.makeText(this, "Success Delete", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAttachView() {
        presenterImp.onAttach(this);
    }

    @Override
    public void onDetachView() {
    presenterImp.onDetach();
    }
}
