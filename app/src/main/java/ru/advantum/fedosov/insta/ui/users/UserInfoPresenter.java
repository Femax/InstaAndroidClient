package ru.advantum.fedosov.insta.ui.users;

import android.util.Log;

import ru.advantum.fedosov.insta.model.RestClient;
import ru.advantum.fedosov.insta.model.User;
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
        mUserInfoView.onShowProgress();
        RestClient.getInstance().getUsers().observeOn(Schedulers.newThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                mUserInfoView.onHideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("UserInfoPresenter",e.getMessage());
                mUserInfoView.onHideProgress();
                mUserInfoView.error();
            }

            @Override
            public void onNext(User user) {
                mUserInfoView.onShowData(user);
            }
        });

    }

    public void onStopGetData() {

    }
}
