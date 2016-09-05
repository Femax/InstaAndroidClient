package ru.advantum.fedosov.insta.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fedosov on 9/5/16.
 */
public class User {

    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;


    private User(Parcel in) {
        this.id = in.readLong();
        this.login = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

