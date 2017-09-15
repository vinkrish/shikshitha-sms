package com.shikshitha.shikshithasms.attendance;

import com.shikshitha.shikshithasms.App;
import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.api.SmsApi;
import com.shikshitha.shikshithasms.api.ApiClient;
import com.shikshitha.shikshithasms.model.Attendance;
import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.Timetable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinay on 21-04-2017.
 */

class AttendanceInteractorImpl implements AttendanceInteractor {
    @Override
    public void getClassList(long schoolId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Clas>> queue = api.getClassList(schoolId);
        queue.enqueue(new Callback<List<Clas>>() {
            @Override
            public void onResponse(Call<List<Clas>> call, Response<List<Clas>> response) {
                if(response.isSuccessful()) {
                    listener.onClassReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                    listener.loadOffline("class");
                }
            }
            @Override
            public void onFailure(Call<List<Clas>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
                listener.loadOffline("class");
            }
        });
    }

    @Override
    public void getSectionList(long classId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Section>> queue = api.getSectionList(classId);
        queue.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                if(response.isSuccessful()) {
                    listener.onSectionReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                    listener.loadOffline("section");
                }
            }
            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
                listener.loadOffline("section");
            }
        });
    }

    @Override
    public void getTimetable(long sectionId, String dayOfWeek, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Timetable>> queue = api.getTimetable(sectionId, dayOfWeek);
        queue.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
                if(response.isSuccessful()) {
                    listener.onTimetableReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                    listener.loadOffline("timetable");
                }
            }
            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
                listener.loadOffline("timetable");
            }
        });
    }

    @Override
    public void getAttendance(long sectionId, String date, int session, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<AttendanceSet> queue = api.getAttendanceSet(sectionId, date, session);
        queue.enqueue(new Callback<AttendanceSet>() {
            @Override
            public void onResponse(Call<AttendanceSet> call, Response<AttendanceSet> response) {
                if(response.isSuccessful()) {
                    listener.onAttendanceReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                    listener.loadOffline("attendance");
                }
            }
            @Override
            public void onFailure(Call<AttendanceSet> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
                listener.loadOffline("attendance");
            }
        });
    }

    @Override
    public void saveAttendance(ArrayList<Attendance> attendances, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Void> queue = api.saveAttendance(attendances);
        queue.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    listener.onAttendanceSaved();
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void deleteAttendance(ArrayList<Attendance> attendances, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Void> queue = api.deleteAttendance(attendances);
        queue.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    listener.onAttendanceDeleted();
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }
}
