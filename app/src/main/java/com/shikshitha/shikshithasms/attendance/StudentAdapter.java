package com.shikshitha.shikshithasms.attendance;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.model.StudentSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 23-04-2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private ArrayList<StudentSet> items;
    private Context context;

    StudentAdapter(ArrayList<StudentSet> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public ArrayList<StudentSet> getDataSet() {
        return items;
    }

    @UiThread
    public void setDataSet(ArrayList<StudentSet> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mark_attendance_item, parent, false);
        return new StudentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roll_no) TextView rollNo;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.attendance_status) ImageView attendanceStatus;
        @BindView(R.id.toggle_attendance) LinearLayout toggleAttendance;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final StudentSet studentSet) {
            rollNo.setText(String.valueOf(studentSet.getRollNo()));
            name.setText(studentSet.getName());
            updateAttendanceStatus(studentSet.isSelected());
            toggleAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(studentSet.isSelected()) {
                        studentSet.setSelected(false);
                    } else {
                        studentSet.setSelected(true);
                    }
                    updateAttendanceStatus(studentSet.isSelected());
                }
            });
        }

        private void updateAttendanceStatus(Boolean b) {
            if(b) {
                attendanceStatus.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cancel_black));
            } else {
                attendanceStatus.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_black));
            }
        }

    }
}
