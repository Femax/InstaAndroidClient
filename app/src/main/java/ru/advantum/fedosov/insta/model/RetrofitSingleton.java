package ru.advantum.fedosov.insta.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import retrofit.RxJavaCallAdapterFactory;
import ru.advantum.fedosov.insta.appSettings.Const;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by fedosov on 9/5/16.
 */
public class RetrofitSingleton {
    private static final String TAG = RetrofitSingleton.class.getSimpleName();

    private static Observable<ArrayList<User>> observableRetrofit;
    private static BehaviorSubject<ArrayList<User>> observableModelsList;
    private static Subscription subscription;

    private RetrofitSingleton() {
    }

    public static void init() {
        Log.d(TAG, "init");

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        GetModels apiService = retrofit.create(GetModels.class);

        observableRetrofit = apiService.getUserList();
    }

    public static void resetModelsObservable() {
        observableModelsList = BehaviorSubject.create();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = observableRetrofit.subscribe(new Subscriber<ArrayList<User>>() {
            @Override
            public void onCompleted() {
                //do nothing
            }

            @Override
            public void onError(Throwable e) {
                observableModelsList.onError(e);
            }

            @Override
            public void onNext(ArrayList<User> models) {
                observableModelsList.onNext(models);
            }
        });
    }

    public static Observable<ArrayList<User>> getModelsObservable() {
        if (observableModelsList == null) {
            resetModelsObservable();
        }
        return observableModelsList;
    }
}
