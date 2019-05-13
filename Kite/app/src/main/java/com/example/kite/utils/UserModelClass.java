package com.example.kite.utils;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class UserModelClass {

    private String loginName;
    public String getLoginName() {
        return loginName;
    }

    public UserModelClass(String loginName) {
        this.loginName = loginName;
    }

    public static DiffUtil.ItemCallback<UserModelClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.loginName.equals(newItem.loginName);
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        UserModelClass article = (UserModelClass) obj;
        return article.loginName.equals(this.loginName);
    }

}
