package com.shikshitha.shikshithasms.smsinfo;

/**
 * Created by Vinay on 21-09-2017.
 */

interface SmsInfoPresenter {
    void getRecentMessages(long senderId, long messageId);

    void getMessages(long senderId);

    void onDestroy();
}
