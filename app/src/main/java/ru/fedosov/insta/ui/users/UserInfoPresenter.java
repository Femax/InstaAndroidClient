package ru.fedosov.insta.ui.users;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import ru.fedosov.insta.model.RestClient;
import ru.fedosov.insta.model.User;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedosov on 9/6/16.
 */
public class UserInfoPresenter {

    private UserInfoView mUserInfoView;

    public UserInfoPresenter(UserInfoView userInfoView) {
        this.mUserInfoView = userInfoView;
    }

    public void getData() {
        /**Та же история **/
        RestClient.getInstance().getUsers()
                .subscribeOn(Schedulers.newThread())
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserInfoPresenter", e.getMessage());
                        mUserInfoView.error();
                    }

                    @Override
                    public void onNext(User user) {
                        mUserInfoView.onShowData(user);
                        unsubscribe();
                    }
                });

    }

    public void onStopGetData() {

    }
}
