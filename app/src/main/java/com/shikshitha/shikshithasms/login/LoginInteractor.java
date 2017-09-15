package com.shikshitha.shikshithasms.login;

import com.shikshitha.shikshithasms.model.Credentials;
import com.shikshitha.shikshithasms.model.TeacherCredentials;

/**
 * Created by Vinay on 28-03-2017.
 */

interface LoginInteractor {
    interface OnLoginFinishedListener{

        void onSuccess(TeacherCredentials credentials);

        void onPwdRecovered();

        void onNoUser();

        void onError(String message);
    }

    void login(Credentials credentials, OnLoginFinishedListener listener);

    void recoverPwd(String username, OnLoginFinishedListener listener);
}
