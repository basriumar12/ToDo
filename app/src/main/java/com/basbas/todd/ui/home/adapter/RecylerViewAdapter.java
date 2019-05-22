package com.basbas.todd.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basbas.todd.R;
import com.basbas.todd.model.ResponseData;
import com.basbas.todd.ui.detail.DetailActivity;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyHolder> {
    Context con;
    List<ResponseData> dataList;


    public RecylerViewAdapter(Context con, List<ResponseData> dataList) {
        this.con = con;
        this.dataList = dataList;
    }



    @NonNull
    @Override
    public RecylerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecylerViewAdapter.MyHolder holder, final int position) {

        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData = new Intent(con, DetailActivity.class);
                sendData.putExtra("title",dataList.get(position).getTitle().toString());
                sendData.putExtra("content",dataList.get(position).getBody().toString());
                sendData.putExtra("id",dataList.get(position).getUserId());
                con.startActivity(sendData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
