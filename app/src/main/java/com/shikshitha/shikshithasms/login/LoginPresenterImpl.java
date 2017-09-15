package com.shikshitha.shikshithasms.login;

import com.shikshitha.shikshithasms.model.Credentials;
import com.shikshitha.shikshithasms.model.TeacherCredentials;

/**
 * Created by Vinay on 28-03-2017.
 */

class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor interactor;

    LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(Credentials credentials) {
        if(loginView != null) {
            loginView.showProgress();
            interactor.login(credentials, this);
        }
    }

    @Override
    public void pwdRecovery(String username) {
        if(loginView != null) {
            loginView.showProgress();
            interactor.recoverPwd(username, this);
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onSuccess(TeacherCredentials credentials) {
        if(loginView != null) {
            loginView.saveUser(credentials);
            loginView.hideProgress();
            loginView.navigateToDashboard();
        }
    }

    @Override
    public void onPwdRecovered() {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.pwdRecovered();
        }
    }

    @Override
    public void onNoUser() {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.noUser();
        }
    }

    @Override
    public void onError(String message) {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.showError(message);
        }
    }
}
