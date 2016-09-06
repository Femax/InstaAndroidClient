package ru.advantum.fedosov.insta.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fedosov on 9/6/16.
 */
public class InstaData {
    @SerializedName("_id")
    String id;
    @SerializedName("login")
    String login;
    @SerializedName("password")
    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InstaData{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
