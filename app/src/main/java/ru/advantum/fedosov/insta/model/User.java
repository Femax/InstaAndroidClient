package ru.advantum.fedosov.insta.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedosov on 9/5/16.
 */
public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("instaData")
    @Expose
    List<InstaData> instaData = new ArrayList<>();

    private User(String _id,String login,List<InstaData> instaData) {
        this.id = _id;
        this.login = login;
        this.instaData = instaData;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", instaData=" + instaData +
                '}';
    }

    public List<InstaData> getInstaData() {
        return instaData;
    }

    public void setInstaData(List<InstaData> instaData) {
        this.instaData = instaData;
    }
}

