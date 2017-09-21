package com.shikshitha.shikshithasms.smsinfo;

import com.shikshitha.shikshithasms.model.Sms;

import java.util.List;

/**
 * Created by Vinay on 21-09-2017.
 */

interface SmsInfoView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void showRecentMessages(List<Sms> messages);

    void showMessages(List<Sms> messages);
}
