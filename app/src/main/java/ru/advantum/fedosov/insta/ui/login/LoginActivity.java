package ru.advantum.fedosov.insta.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.rule.LoginRule;
import ru.advantum.fedosov.insta.ui.base.BaseAbstractActivity;
import ru.advantum.fedosov.insta.ui.users.UserInfoActivity;
import ru.advantum.fedosov.insta.util.PrefUtils;

/**
 * A login screen that offers login via email/password.
 */
@ActivityRef(resource = R.layout.activity_login, knifeEnabled = true, busEnabled = true)
public class LoginActivity extends BaseAbstractActivity implements LoginView {

    private static final String STATE_EMAIL = "STATE_EMAIL";
    private static final String STATE_PASS = "STATE_PASS";

    @Bind(R.id.login_user_name_field)
    protected EditText mEmailView;
    @Bind(R.id.login_password_field)
    protected EditText mPasswordView;
    @Bind(R.id.login_remember_me)
    protected CheckBox mRememberMeCheckBox;

    private LoginPresenter mLoginPresenter;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRememberMeCheckBox.setChecked(LoginRule.isLoginDetailsStored());
        if (savedInstanceState == null) {
            LoginRule.onStartLogin();
            if (mRememberMeCheckBox.isChecked()) {
                mEmailView.setText(PrefUtils.getString(R.string.pref_login_email));
                mPasswordView.setText(PrefUtils.getString(R.string.pref_login_password));
            }
        } else {
            mEmailView.setText(savedInstanceState.getString(STATE_EMAIL));
            mPasswordView.setText(savedInstanceState.getString(STATE_PASS));
        }
        mLoginPresenter = new LoginPresenter(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_EMAIL, mEmailView.getText().toString());
        outState.putString(STATE_PASS, mPasswordView.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mLoginPresenter.login(email, password);

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onLoginEvent(LoginEvent event) {
//        showProgressDialog(false);
//        switch (event.getType()) {
//            case SUCCESS:
//                LoginRule.onSuccessLogin(this, mEmailView.getText().toString(),
//                        mPasswordView.getText().toString(), false);
//                break;
//            case FAILURE:
//                Toast.makeText(this, getString(R.string.message_failure), Toast.LENGTH_SHORT).show();
//                PrefUtils.remove(R.string.pref_server);
//                break;
//            case NETWORK:
//                Toast.makeText(this, getString(R.string.message_no_network), Toast.LENGTH_SHORT).show();
//                PrefUtils.remove(R.string.pref_server);
//                break;
//            case INVALID_INPUT:
//                mPasswordView.setError(getString(R.string.login_error_incorrect_password));
//                PrefUtils.remove(R.string.pref_server);
//                break;
//            default:
//                Toast.makeText(this, "idk", Toast.LENGTH_SHORT).show();
//                PrefUtils.remove(R.string.pref_server);
//                break;
//        }
//    }

    @Override
    public void showProgress() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgress() {
        showProgressDialog(false);
    }

    @Override
    public void setUsernameError() {
        mEmailView.setError("Не верный логин");
    }

    @Override
    public void setPasswordError() {
        mPasswordView.setError("Не верный пароль");
    }

    @Override
    public void navigateToOtherActivity() {
        startActivity(new Intent(this, UserInfoActivity.class));
        finish();
    }

    @Override
    public void onError() {

    }

}


