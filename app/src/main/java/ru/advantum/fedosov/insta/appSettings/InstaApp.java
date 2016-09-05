package ru.advantum.fedosov.insta.appSettings;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by fedosov on 9/5/16.
 */
public class InstaApp extends Application {
    private static InstaApp sInstance;
    private static boolean preJellyBean;

    public static InstaApp getInstance() {
        return sInstance;
    }

    public static boolean isPreJellyBean() {
        return preJellyBean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) sInstance = this;
        JodaTimeAndroid.init(this);

    }

}
