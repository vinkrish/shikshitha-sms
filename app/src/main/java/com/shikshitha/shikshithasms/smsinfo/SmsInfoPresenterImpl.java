package com.shikshitha.shikshithasms.smsinfo;

import com.shikshitha.shikshithasms.model.Sms;

import java.util.List;

/**
 * Created by Vinay on 21-09-2017.
 */

class SmsInfoPresenterImpl implements SmsInfoPresenter, SmsInfoInteractor.OnFinishedListener {
    private SmsInfoView mView;
    private SmsInfoInteractor mInteractor;

    SmsInfoPresenterImpl(SmsInfoView view, SmsInfoInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void getRecentMessages(long senderId, long messageId) {
        if(mView != null) {
            mView.showProgress();
            mInteractor.getRecentMessages(senderId, messageId, this);
        }
    }

    @Override
    public void getMessages(long senderId) {
        if(mView != null) {
            mView.showProgress();
            mInteractor.getMessages(senderId, this);
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.hideProgress();
            mView.showError(message);
        }
    }

    @Override
    public void onRecentMessagesReceived(List<Sms> messages) {
        if(mView != null) {
            mView.showRecentMessages(messages);
            mView.hideProgress();
        }
    }

    @Override
    public void onMessageReceived(List<Sms> messages) {
        if(mView != null) {
            mView.showMessages(messages);
            mView.hideProgress();
        }
    }
}
