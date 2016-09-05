package ru.advantum.fedosov.insta.model;

/**
 * Created by fedosov on 9/5/16.
 */


import java.util.ArrayList;

import retrofit.http.GET;
import rx.Observable;


public interface GetModels {
    @GET("/api/users")
    Observable<ArrayList<User>> getUserList();
}
