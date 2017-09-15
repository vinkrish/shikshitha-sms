package com.shikshitha.shikshithasms.login;

import com.shikshitha.shikshithasms.model.Credentials;

/**
 * Created by Vinay on 28-03-2017.
 */

interface LoginPresenter {

    void validateCredentials(Credentials credentials);

    void pwdRecovery(String username);

    void onDestroy();
}
