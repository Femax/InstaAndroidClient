package ru.advantum.fedosov.insta.model;

import android.util.Log;

import java.util.HashMap;
import java.util.List;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.model.service.InstaService;
import ru.advantum.fedosov.insta.model.service.LoginJson;
import ru.advantum.fedosov.insta.model.service.UserJson;
import ru.advantum.fedosov.insta.util.PrefUtils;
import rx.Observable;

/**
 * Created by fedosov on 9/6/16.
 */
public class RestClient {

    private static InstaService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:80/")

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(InstaService.class);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    private InstaService getWeatherService() {
        return apiService;
    }

    public Observable<UserJson> getModelsObservable() {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),"shit");
        return  apiService.loginToken(new LoginJson("asd","asd"));
    }

    public Observable<User> getUsers(){
       return   apiService.getUsers(PrefUtils.getString(R.string.pref_token));
    }
}