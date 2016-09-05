package ru.advantum.fedosov.insta.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.event.LoginEvent;
import ru.advantum.fedosov.insta.rule.LoginRule;
import ru.advantum.fedosov.insta.ui.base.BaseAbstractActivity;
import ru.advantum.fedosov.insta.util.PrefUtils;

/**
 * A login screen that offers login via email/password.
 */
@ActivityRef(resource = R.layout.activity_login, knifeEnabled = true, busEnabled = true)
public class LoginActivity extends BaseAbstractActivity {

    private static final String STATE_EMAIL = "STATE_EMAIL";
    private static final String STATE_PASS = "STATE_PASS";

    @Bind(R.id.login_user_name_field)
    protected EditText mEmailView;
    @Bind(R.id.login_password_field)
    protected EditText mPasswordView;
    @Bind(R.id.login_remember_me)
    protected CheckBox mRememberMeCheckBox;


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
        mRememberMeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                LoginRule.setRememberLoginDetails(isChecked));
        mPasswordView.setOnEditorActionListener((AppTextView, id, keyEvent) -> {
            boolean isDone = id == R.id.login_password_ime || id == EditorInfo.IME_NULL
                    || id == EditorInfo.IME_ACTION_DONE;
            if (isDone) attemptLogin();
            return isDone;
        });


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
        PrefUtils.putString(R.string.pref_server, getString(R.string.server));
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (LoginRule.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.login_error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (LoginRule.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.login_error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) focusView.requestFocus();
        else {
            showProgressDialog(true);
            LoginRule.login(email, password);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        showProgressDialog(false);
        switch (event.getType()) {
            case SUCCESS:
                LoginRule.onSuccessLogin(this, mEmailView.getText().toString(),
                        mPasswordView.getText().toString(), false);
                break;
            case FAILURE:
                Toast.makeText(this, getString(R.string.message_failure), Toast.LENGTH_SHORT).show();
                PrefUtils.remove(R.string.pref_server);
                break;
            case NETWORK:
                Toast.makeText(this, getString(R.string.message_no_network), Toast.LENGTH_SHORT).show();
                PrefUtils.remove(R.string.pref_server);
                break;
            case INVALID_INPUT:
                mPasswordView.setError(getString(R.string.login_error_incorrect_password));
                PrefUtils.remove(R.string.pref_server);
                break;
            default:
                Toast.makeText(this, "idk", Toast.LENGTH_SHORT).show();
                PrefUtils.remove(R.string.pref_server);
                break;
        }
    }
}


