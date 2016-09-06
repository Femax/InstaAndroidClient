package ru.advantum.fedosov.insta.ui.users;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.model.User;
import ru.advantum.fedosov.insta.ui.base.BaseAbstractActivity;

/**
 * Created by fedosov on 9/6/16.
 */
@ActivityRef(resource = R.layout.activity_users, knifeEnabled = true, busEnabled = true)
public class UserInfoActivity extends BaseAbstractActivity implements UserInfoView {

    @Bind(R.id.userinfo)
    protected TextView textView;

    private UserInfoPresenter mUsersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsersPresenter = new UserInfoPresenter(this);
        mUsersPresenter.getData();
    }

    @Override
    public void onShowData(User user) {
        textView.setText(user.toString());
    }

    @Override
    public void error() {

        textView.setError("error");
    }

    @Override
    public void onShowProgress() {
        showProgressDialog(true);
    }

    @Override
    public void onHideProgress() {
        showProgressDialog(false);
    }
}
