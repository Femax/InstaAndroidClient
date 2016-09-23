package ru.fedosov.insta.ui.users;


import ru.fedosov.insta.model.User;

/**
 * Created by fedosov on 9/6/16.
 */
public interface UserInfoView {
    public void onShowData(User user);

    public void error();

    public void onShowProgress();

    public void onHideProgress();
}
