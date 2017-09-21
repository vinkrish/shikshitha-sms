package com.shikshitha.shikshithasms.smsinfo;

import com.shikshitha.shikshithasms.model.Sms;

import java.util.List;

/**
 * Created by Vinay on 21-09-2017.
 */

interface SmsInfoInteractor {
    interface OnFinishedListener {
        void onError(String message);

        void onRecentMessagesReceived(List<Sms> messages);

        void onMessageReceived(List<Sms> messages);
    }
    void getRecentMessages(long senderId, long messageId, SmsInfoInteractor.OnFinishedListener listener);

    void getMessages(long senderId, SmsInfoInteractor.OnFinishedListener listener);
}
