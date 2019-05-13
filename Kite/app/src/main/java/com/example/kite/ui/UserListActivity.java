
package com.example.kite.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kite.MyApplication;
import com.example.kite.R;
import com.example.kite.databinding.PagingDemoLayoutBinding;
import com.example.kite.utils.Constant;
import com.example.kite.utils.ViewModelFactory;

import java.util.Objects;

import javax.inject.Inject;

public class UserListActivity extends AppCompatActivity implements ClickEvent{

    @Inject
    ViewModelFactory viewModelFactory;

    PagingLibViewModel viewModel;

    PagingDemoLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.paging_demo_layout);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PagingLibViewModel.class);
        init();
    }

    private void init() {

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        MyPageListAdapter adapter = new MyPageListAdapter(this::onClick);
        binding.list.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        binding.list.addItemDecoration(decoration);


        if (!Constant.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constant.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }

        viewModel.getListLiveData().observe(this, adapter::submitList);

        viewModel.getProgressLoadStatus().observe(this, status -> {
            if (Objects.requireNonNull(status).equalsIgnoreCase(Constant.LOADING)) {
                binding.progress.setVisibility(View.VISIBLE);
            } else if (status.equalsIgnoreCase(Constant.LOADED)) {
                binding.progress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(String loginName) {
        Intent intent = new Intent(this,UserProfileActivity.class);
        intent.putExtra("loginName",loginName);
        startActivity(intent);
    }

}
