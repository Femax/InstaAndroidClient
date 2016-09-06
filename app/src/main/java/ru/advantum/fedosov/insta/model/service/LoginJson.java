package ru.advantum.fedosov.insta.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fedosov on 9/6/16.
 */
public class LoginJson {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    public LoginJson(String login,String password) {
        this.login = login;
        this.password = password;
    }

}
