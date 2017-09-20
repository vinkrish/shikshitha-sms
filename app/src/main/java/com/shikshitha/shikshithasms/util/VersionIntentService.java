package com.shikshitha.shikshithasms.util;

import android.app.IntentService;
import android.content.Intent;

import com.shikshitha.shikshithasms.App;
import com.shikshitha.shikshithasms.BuildConfig;
import com.shikshitha.shikshithasms.api.ApiClient;
import com.shikshitha.shikshithasms.api.SmsApi;
import com.shikshitha.shikshithasms.model.AppVersion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VersionIntentService extends IntentService {

    public VersionIntentService() {
        super("VersionIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<AppVersion> queue = api.getAppVersion(BuildConfig.VERSION_CODE, "sms");
        queue.enqueue(new Callback<AppVersion>() {
            @Override
            public void onResponse(Call<AppVersion> call, Response<AppVersion> response) {
                if(response.isSuccessful()) {
                    SharedPreferenceUtil.saveAppVersion(App.getInstance(), response.body());
                }
            }

            @Override
            public void onFailure(Call<AppVersion> call, Throwable t) {
            }
        });
    }

}
