package ru.advantum.fedosov.insta.ui.login;

/**
 * Created by fedosov on 9/6/16.
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

}
