package ru.advantum.fedosov.insta.rule;

import android.preference.PreferenceManager;
import android.text.TextUtils;

import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.util.AsyncUtils;
import ru.advantum.fedosov.insta.util.PrefUtils;


/**
 * Created by yurchenko on 19.04.2016.
 */
public final class LoginRule extends AbstractRule {

    private LoginRule(){}

    public static void setRememberLoginDetails(boolean isChecked) {
        PrefUtils.putBoolean(R.string.pref_remember_login_details, isChecked);
    }

    public static boolean isLoginDetailsStored() {
        return PrefUtils.getBoolean(R.string.pref_remember_login_details);
    }


    public static void clearLoginDetails() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit()
                .remove(getString(R.string.pref_login_email))
                .remove(getString(R.string.pref_login_password))
                .apply();
    }

    public static boolean isPasswordValid(String password) {
        return TextUtils.isEmpty(password);
    }

    public static boolean isEmailValid(String email) {
        return TextUtils.isEmpty(email);
    }

    public static void login(String email, String password) {
        AsyncUtils.login(email, password);
    }


    public static void onStartLogin() {


    }
}
