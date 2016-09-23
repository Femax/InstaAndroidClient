package ru.fedosov.insta.model.service;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ru.fedosov.insta.model.User;
import rx.Observable;

/**
 * Created by fedosov on 9/6/16.
 */
public interface InstaService {

    @POST("/reg")
    rx.Observable<String> reg(@Body  HashMap<String, Object> body);

    @POST("/im")
    rx.Observable<User> im();

    @POST("/im/upload")
    rx.Observable<String> uploadImage(@Body  HashMap<String, Object> body);

    @POST("/im/addinsta")
    rx.Observable<String> addinsta(@Body  HashMap<String, Object> body);

    @GET("/im")
    rx.Observable<User> getUsers(@Header("x-access-token") String token);
    @POST("login")
    Observable<UserJson> loginToken(@Body LoginJson body);

}
