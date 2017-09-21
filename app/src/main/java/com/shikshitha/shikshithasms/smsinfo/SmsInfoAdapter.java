package com.shikshitha.shikshithasms.smsinfo;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.model.Sms;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 21-09-2017.
 */

class SmsInfoAdapter extends RecyclerView.Adapter<SmsInfoAdapter.ViewHolder>{
    private List<Sms> items;
    private final OnItemClickListener listener;

    interface OnItemClickListener {
        void onItemClick(Sms group);
    }

    SmsInfoAdapter(List<Sms> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    void setDataSet(List<Sms> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @UiThread
    void insertDataSet(List<Sms> items) {
        this.items.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }

    List<Sms> getDataSet() {
        return items;
    }

    @Override
    public SmsInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SmsInfoAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipient_role)
        TextView recipientRole;
        @BindView(R.id.sent_date)
        TextView sentDate;
        @BindView(R.id.message)
        TextView message;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final Sms sms, final OnItemClickListener listener) {
            recipientRole.setText("To: " + sms.getRecipientRole());
            DateTime dateTime = new DateTime(sms.getSentTime());
            sentDate.setText(DateTimeFormat.forPattern("dd-MMM, HH:mm").print(dateTime));
            message.setText(sms.getMessage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(sms);
                }
            });
        }
    }
}
