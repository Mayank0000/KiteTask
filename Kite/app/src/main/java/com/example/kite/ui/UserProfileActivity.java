
package com.example.kite.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.kite.MyApplication;
import com.example.kite.R;
import com.example.kite.databinding.UserProfileLayoutBinding;
import com.example.kite.user_datasource.ProfileDataClass;
import com.example.kite.utils.Constant;
import com.example.kite.utils.ProfileModelClass;
import com.example.kite.utils.UserInfoHelper;
import com.example.kite.utils.ViewModelFactory;

import javax.inject.Inject;

public class UserProfileActivity extends AppCompatActivity implements UserInfoHelper {

    @Inject
    ViewModelFactory viewModelFactory;

    ProfileDataClass viewModel;

    UserProfileLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_profile_layout);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileDataClass.class);
        if(getIntent().getStringExtra("loginName")!=null){
            String loginName = getIntent().getStringExtra("loginName");
            viewModel.callProfileService(loginName,this);
        }
        init();
    }

    private void init() {
        if (!Constant.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constant.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void getUSerModel(ProfileModelClass userModelClass) {
        binding.userCompany.setText(userModelClass.getUserCompany());
        binding.userFollowers.setText(userModelClass.getUserFollowers());
        binding.userFollowing.setText(userModelClass.getUserFollowing());
        binding.userLocation.setText(userModelClass.getUserLocation());
        binding.userName.setText(userModelClass.getUserName());
        binding.userType.setText(userModelClass.getUserType());
        binding.userReposUrl.setText(userModelClass.getUserReposURL());
        binding.userUrl.setText(userModelClass.getUserURL());
    }
}
