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
import com.shikshitha.shikshithasms.model.ClasSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 18-09-2017.
 */

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private ArrayList<ClasSet> items;

    ClassAdapter(ArrayList<ClasSet> items) {
        this.items = items;
    }

    public ArrayList<ClasSet> getDataSet() {
        return items;
    }

    @UiThread
    public void setDataSet(ArrayList<ClasSet> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_member_item, parent, false);
        return new ClassAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClassAdapter.ViewHolder holder, int position) {
        final ClasSet clasSet = items.get(position);
        holder.name.setText(clasSet.getClassName());
        holder.isSelected.setChecked(clasSet.isSelected());
        holder.isSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                clasSet.setSelected(b);
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
