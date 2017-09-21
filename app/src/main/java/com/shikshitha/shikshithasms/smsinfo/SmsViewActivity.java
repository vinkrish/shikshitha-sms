package com.shikshitha.shikshithasms.smsinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.model.Sms;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmsViewActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.sent_to)
    TextView sentTo;

    private Sms sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_view);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sms = (Sms) extras.getSerializable("sms");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(sms.getRecipientRole());
        DateTime dateTime = new DateTime(sms.getSentTime());
        getSupportActionBar().setSubtitle(DateTimeFormat.forPattern("dd-MMM, HH:mm").print(dateTime));

        message.setText(sms.getMessage());
        sentTo.setText(sms.getSentTo());
    }
}
