package ru.fedosov.insta.ui.login;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import ru.fedosov.insta.R;
import ru.fedosov.insta.model.RestClient;
import ru.fedosov.insta.model.service.UserJson;
import ru.fedosov.insta.rule.LoginRule;
import ru.fedosov.insta.util.PrefUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedosov on 9/6/16.
 */
public class LoginPresenter {
    private LoginView mLoginView;

    public LoginPresenter(LoginView loginView) {
        this.mLoginView = loginView;
    }

    public void login(String email, String password) {
        if (LoginRule.isPasswordValid(password)) {
                onPasswordError();
        }
        if (LoginRule.isEmailValid(email)) {
            onLoginError();
        }


        /**Не знаю где правильно распалогать их если нет бд.Конечно как вариант можно создать RxJavaUtils но нормально ли это?**/
        RestClient.getInstance().getModelsObservable(email,password)
                .subscribeOn(Schedulers.newThread())
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserJson>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            onLoginError();
                        }
                        catch (Throwable e2){
                            Log.d("LoginPresenter",e2.getMessage());
                        }
                    }

                    @Override
                    public void onNext(UserJson userJson) {

                        Log.d("ok", userJson.toString());
                        PrefUtils.putString(R.string.pref_token, userJson.getToken());
                        mLoginView.navigateToOtherActivity();
                        unsubscribe();
                    }
                });
    }

    private void onLoginError() {
        mLoginView.onError();
    }

    public void onDestroy() {
        mLoginView = null;
    }

    public void onUserNameError() {
        if (mLoginView != null) {
            mLoginView.setUsernameError();
        }
    }

    public void onPasswordError() {
        if (mLoginView != null) {
            mLoginView.setPasswordError();
        }
    }
    public void onSuccess(){
        if(mLoginView !=null){
            mLoginView.navigateToOtherActivity();
        }
    }
}
