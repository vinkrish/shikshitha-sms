package com.shikshitha.shikshithasms.smsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.dao.SmsDao;
import com.shikshitha.shikshithasms.dao.TeacherDao;
import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.model.Teacher;
import com.shikshitha.shikshithasms.util.DividerItemDecoration;
import com.shikshitha.shikshithasms.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmsInfoActivity extends AppCompatActivity implements SmsInfoView{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.noMessage)
    LinearLayout noMessage;

    private Teacher teacher;
    private SmsInfoAdapter adapter;
    private SmsInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_info);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        teacher = TeacherDao.getTeacher();

        presenter = new SmsInfoPresenterImpl(this, new SmsInfoInteractorImpl());

        refreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBackupMessages();
            }
        });

        setupRecyclerView();

        getBackupMessages();
    }

    private void getBackupMessages() {
        List<Sms> messages = SmsDao.getSmsMessages(teacher.getId());
        adapter.setDataSet(messages);
        if (NetworkUtil.isNetworkAvailable(this)) {
            if (messages.size() == 0) {
                presenter.getMessages(teacher.getId());
            } else {
                presenter.getRecentMessages(teacher.getId(), adapter.getDataSet().get(0).getId());
            }
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        adapter = new SmsInfoAdapter(new ArrayList<Sms>(0), mItemListener);
        recyclerView.setAdapter(adapter);

    }

    SmsInfoAdapter.OnItemClickListener mItemListener = new SmsInfoAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Sms sms) {
            Intent intent = new Intent(SmsInfoActivity.this, SmsViewActivity.class);
            Bundle args = new Bundle();
            if(sms != null){
                args.putSerializable("sms", sms);
            }
            intent.putExtras(args);
            startActivity(intent);
        }
    };

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        refreshLayout.setRefreshing(false);
        showSnackbar(message);
    }

    @Override
    public void showRecentMessages(List<Sms> messages) {
        adapter.insertDataSet(messages);
        recyclerView.smoothScrollToPosition(0);
        backupMessages(messages);
    }

    @Override
    public void showMessages(List<Sms> messages) {
        if(messages.size() == 0) {
            noMessage.setVisibility(View.VISIBLE);
        } else {
            noMessage.setVisibility(View.GONE);
            adapter.setDataSet(messages);
            recyclerView.smoothScrollToPosition(0);
            backupMessages(messages);
        }
    }

    private void backupMessages(final List<Sms> messages) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SmsDao.insertSMSMessages(messages);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
