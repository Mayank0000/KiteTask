package com.example.kite.utils;

public class ProfileModelClass {
    private String userName;
    private String userCompany;
    private String userLocation;
    private String userURL;
    private String userType;
    private String userFollowers;
    private String userFollowing;
    private String userReposURL;

    public ProfileModelClass(String userName, String userCompany, String userLocation, String userURL, String userType, String userFollowers, String userFollowing, String userReposURL) {
        this.userName = userName;
        this.userCompany = userCompany;
        this.userLocation = userLocation;
        this.userURL = userURL;
        this.userType = userType;
        this.userFollowers = userFollowers;
        this.userFollowing = userFollowing;
        this.userReposURL = userReposURL;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getUserURL() {
        return userURL;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public String getUserReposURL() {
        return userReposURL;
    }
}
