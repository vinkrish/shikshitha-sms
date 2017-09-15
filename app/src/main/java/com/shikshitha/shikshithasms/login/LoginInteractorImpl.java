package com.shikshitha.shikshithasms.login;

import com.shikshitha.shikshithasms.App;
import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.api.ApiClient;
import com.shikshitha.shikshithasms.api.SmsApi;
import com.shikshitha.shikshithasms.model.Credentials;
import com.shikshitha.shikshithasms.model.TeacherCredentials;
import com.shikshitha.shikshithasms.util.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinay on 28-03-2017.
 */

class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final Credentials credentials, final OnLoginFinishedListener listener) {
        SmsApi authApi = ApiClient.getClient().create(SmsApi.class);

        Call<TeacherCredentials> queue = authApi.login(credentials);
        queue.enqueue(new Callback<TeacherCredentials>() {
            @Override
            public void onResponse(Call<TeacherCredentials> call, Response<TeacherCredentials> response) {
                if(response.isSuccessful()) {
                    SharedPreferenceUtil.saveAuthorizedUser(App.getInstance(), credentials.getUsername());
                    listener.onSuccess(response.body());
                } else {
                    listener.onError("mobile number and password don't match");
                }
            }

            @Override
            public void onFailure(Call<TeacherCredentials> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void recoverPwd(String username, final OnLoginFinishedListener listener) {
        SmsApi authApi = ApiClient.getClient().create(SmsApi.class);

        Call<Void> queue = authApi.forgotPassword(username);
        queue.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    listener.onPwdRecovered();
                } else {
                    //APIError error = ErrorUtils.parseError(response);
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }
}
