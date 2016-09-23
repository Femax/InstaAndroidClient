package ru.fedosov.insta.rule;

import android.content.Context;

import ru.fedosov.insta.appSettings.InstaApp;

abstract class AbstractRule {

    protected static Context getContext() {
        return InstaApp.getInstance().getApplicationContext();
    }

    protected static String getString(int res) {
        return getContext().getString(res);
    }
}