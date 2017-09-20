package com.shikshitha.shikshithasms.sms;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.model.SectionSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 18-09-2017.
 */

class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder>{
    private ArrayList<SectionSet> items;

    SectionAdapter(ArrayList<SectionSet> items) {
        this.items = items;
    }

    public ArrayList<SectionSet> getDataSet() {
        return items;
    }

    @UiThread
    public void setDataSet(ArrayList<SectionSet> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_member_item, parent, false);
        return new SectionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SectionAdapter.ViewHolder holder, int position) {
        final SectionSet sectionSet = items.get(position);
        holder.name.setText(sectionSet.getSectionName());
        holder.isSelected.setChecked(sectionSet.isSelected());
        holder.isSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sectionSet.setSelected(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.checkBox)
        CheckBox isSelected;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
