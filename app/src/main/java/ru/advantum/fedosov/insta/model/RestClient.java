package ru.advantum.fedosov.insta.model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import ru.advantum.fedosov.insta.model.service.GitHubService;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedosov on 9/6/16.
 */
public class RestClient {
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static Observable<ArrayList<Repo>> observableModelsList;
    private static GitHubService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(GitHubService.class);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    private  GitHubService getWeatherService() {
        return apiService;
    }

    public Observable<List<Repo>> getModelsObservable() {

        return apiService.listRepos("femax");
    }
}