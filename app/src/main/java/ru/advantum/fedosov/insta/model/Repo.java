package ru.advantum.fedosov.insta.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fedosov on 9/6/16.
 */
public class Repo {

    @SerializedName("id")
    private String idRep;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    public Repo() {}

    public String getId()       { return idRep; }
    public String getName()     { return name; }
    public String getFullName() { return fullName; }

    @Override
    public String toString() {
        return "Repo{" +
                "idRep='" + idRep + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

