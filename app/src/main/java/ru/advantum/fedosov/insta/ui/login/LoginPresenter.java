package ru.advantum.fedosov.insta.ui.login;

import android.util.Log;

import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.model.RestClient;
import ru.advantum.fedosov.insta.model.service.UserJson;
import ru.advantum.fedosov.insta.rule.LoginRule;
import ru.advantum.fedosov.insta.util.PrefUtils;
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
        mLoginView.showProgress();
        if (LoginRule.isPasswordValid(password)) {
                onPasswordError();
        }
        if (LoginRule.isEmailValid(email)) {
            onLoginError();
        }



        RestClient.getInstance().getModelsObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserJson>() {

                    @Override
                    public void onCompleted() {
                        mLoginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoginError();
                    }

                    @Override
                    public void onNext(UserJson userJson) {

                        Log.d("ok", userJson.toString());
                        PrefUtils.putString(R.string.pref_token, userJson.getToken());
                        mLoginView.navigateToOtherActivity();
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
