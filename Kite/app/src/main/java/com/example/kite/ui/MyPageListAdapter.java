package com.example.kite.ui;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kite.R;
import com.example.kite.databinding.RowLayoutBinding;
import com.example.kite.utils.UserModelClass;


public class MyPageListAdapter extends PagedListAdapter<UserModelClass, MyPageListAdapter.MyViewHolder> {
    private ClickEvent clickEvent;
    MyPageListAdapter(ClickEvent clickEvent) {
        super(UserModelClass.DIFF_CALLBACK);
        this.clickEvent =clickEvent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_layout, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.binding.setModel(getItem(position));
        holder.binding.userLogin.setTag(position);
        holder.binding.userLogin.setOnClickListener(v -> {
            clickEvent.onClick(holder.binding.userLogin.getText().toString());
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;

        MyViewHolder(RowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
