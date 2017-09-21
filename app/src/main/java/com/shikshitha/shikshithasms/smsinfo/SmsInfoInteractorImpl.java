package com.shikshitha.shikshithasms.smsinfo;

import com.shikshitha.shikshithasms.App;
import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.api.ApiClient;
import com.shikshitha.shikshithasms.api.SmsApi;
import com.shikshitha.shikshithasms.model.Sms;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinay on 21-09-2017.
 */

public class SmsInfoInteractorImpl implements SmsInfoInteractor {
    @Override
    public void getRecentMessages(long senderId, long messageId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<ArrayList<Sms>> queue = api.getSMSMessagesAboveId(senderId, messageId);
        queue.enqueue(new Callback<ArrayList<Sms>>() {
            @Override
            public void onResponse(Call<ArrayList<Sms>> call, Response<ArrayList<Sms>> response) {
                if(response.isSuccessful()) {
                    listener.onRecentMessagesReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sms>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void getMessages(long senderId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<ArrayList<Sms>> queue = api.getSMSMessages(senderId);
        queue.enqueue(new Callback<ArrayList<Sms>>() {
            @Override
            public void onResponse(Call<ArrayList<Sms>> call, Response<ArrayList<Sms>> response) {
                if(response.isSuccessful()) {
                    listener.onMessageReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sms>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }
}
