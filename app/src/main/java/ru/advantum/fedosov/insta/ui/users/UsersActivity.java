package ru.advantum.fedosov.insta.ui.users;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.model.RestClient;
import ru.advantum.fedosov.insta.model.User;
import ru.advantum.fedosov.insta.ui.base.BaseAbstractActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedosov on 9/6/16.
 */
@ActivityRef(resource = R.layout.activity_users,knifeEnabled = true, busEnabled = true)
public class UsersActivity extends BaseAbstractActivity {

    @Bind(R.id.userinfo)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
