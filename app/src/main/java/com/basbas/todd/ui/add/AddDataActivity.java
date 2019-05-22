package com.basbas.todd.ui.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basbas.todd.R;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.ui.home.MainActivity;

public class AddDataActivity extends AppCompatActivity implements AddView {


    String getTitle, getBody, getUserId;

    Button btnSave;
    EditText edtTitle, edtBody;
    AddPresenterImp presenterImp;
    ProgressBar progressBar;
    String dataFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.GONE);


        initView();
        getData();
        presenterImp = new AddPresenterImp();
        onAttachView();
        addData();


    }

    private void getData() {

        Bundle bundle = getIntent().getExtras();
        dataFlag = bundle.getString("flag");
        getTitle = bundle.getString("title");
        getBody = bundle.getString("content");
        getUserId = bundle.getString("id");
        Log.e("tag","get data"+getUserId);

        if (getTitle!=null) {
            edtBody.setText(getBody);
            edtTitle.setText(getTitle);
            btnSave.setText("Edit");
        }



    }


    private void initView() {
        btnSave = (Button) findViewById(R.id.btn_save_data);
        edtBody = (EditText) findViewById(R.id.edt_body);
        edtTitle = (EditText) findViewById(R.id.edt_title);

    }

    private void addData() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtBody.getText().toString().isEmpty() || edtTitle.getText().toString().isEmpty()) {
                    Toast.makeText(AddDataActivity.this, "Field can't empty", Toast.LENGTH_SHORT).show();
                } else {

                    if (dataFlag ==null){
                        presenterImp.editData(edtTitle.getText().toString(),
                                edtBody.getText().toString(), "1");


                    } else {

                        presenterImp.postData(edtTitle.getText().toString(),
                                edtBody.getText().toString(), "1");
                    }
                }
            }
        });

    }

    @Override
    public void requestProcess() {
        progressBar.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);

    }

    @Override
    public void requestFinish() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onResponseMsg(ResponseData sendResponse) {

    }

    @Override
    public void onErrorMsg(String error) {
        Toast.makeText(this, "failed post data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succsessPost() {
        Toast.makeText(this, "succes post data", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddDataActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public void succsessEdit() {
        Toast.makeText(this, "succes edit data", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddDataActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public void succsessDelete() {

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
