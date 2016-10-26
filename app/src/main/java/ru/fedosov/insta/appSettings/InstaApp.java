package ru.fedosov.insta.appSettings;

import android.app.Application;


import ru.fedosov.insta.util.PrefUtils;

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
        PrefUtils.init(getBaseContext());
    }

}
