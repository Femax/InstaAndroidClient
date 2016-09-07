package ru.advantum.fedosov.insta.ui.users;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.model.InstaData;
import ru.advantum.fedosov.insta.model.User;
import ru.advantum.fedosov.insta.ui.base.BaseAbstractActivity;

/**
 * Created by fedosov on 9/6/16.
 */
@ActivityRef(resource = R.layout.activity_users, knifeEnabled = true, busEnabled = true)
public class UserInfoActivity extends BaseAbstractActivity implements UserInfoView {

    @Bind(R.id.userinfo)
    protected TextView mTextView;
    @Bind(R.id.recyclerview)
    protected RecyclerView mRecyclerView;

    private ListAdapter mAdapter;
    private UserInfoPresenter mUsersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsersPresenter = new UserInfoPresenter(this);
        mUsersPresenter.getData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter(new ArrayList<InstaData>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onShowData(User user) {
        mAdapter.setData(user.getInstaData());
        mTextView.setText(user.getLogin());
    }

    @Override
    public void error() {

        mTextView.setError("error");
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
