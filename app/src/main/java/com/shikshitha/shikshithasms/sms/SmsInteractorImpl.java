package com.shikshitha.shikshithasms.sms;

import com.shikshitha.shikshithasms.App;
import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.api.ApiClient;
import com.shikshitha.shikshithasms.api.SmsApi;
import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.model.SmsClass;
import com.shikshitha.shikshithasms.model.SmsSection;
import com.shikshitha.shikshithasms.model.SmsStudent;
import com.shikshitha.shikshithasms.model.SmsTeacher;
import com.shikshitha.shikshithasms.model.Student;
import com.shikshitha.shikshithasms.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinay on 18-09-2017.
 */

class SmsInteractorImpl implements SmsInteractor {

    @Override
    public void getClassList(long schoolId, final SmsInteractor.OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Clas>> queue = api.getClassList(schoolId);
        queue.enqueue(new Callback<List<Clas>>() {
            @Override
            public void onResponse(Call<List<Clas>> call, Response<List<Clas>> response) {
                if(response.isSuccessful()) {
                    listener.onClassReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<List<Clas>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void getSectionList(long classId, final SmsInteractor.OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Section>> queue = api.getSectionList(classId);
        queue.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                if(response.isSuccessful()) {
                    listener.onSectionReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void getStdudent(long sectionId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Student>> queue = api.getStudents(sectionId);
        queue.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(response.isSuccessful()) {
                    listener.onStudentReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void getTeachers(long schoolId, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<List<Teacher>> queue = api.getTeacherList(schoolId);
        queue.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if(response.isSuccessful()) {
                    listener.onTeacherReceived(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendSchoolSMS(Sms sms, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendSchoolSMS(sms);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendClassSMS(Sms sms, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendClassSMS(sms);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendClassesSMS(SmsClass smsClass, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendClassesSMS(smsClass);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendSectionSMS(Sms sms, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendSectionSMS(sms);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendSectionsSMS(SmsSection smsSection, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendSectionsSMS(smsSection);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendMaleSMS(Sms sms, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendMaleSMS(sms);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendFemaleSMS(Sms sms, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendFemaleSMS(sms);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendStudentSMS(SmsStudent smsStudent, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendStudentSMS(smsStudent);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

    @Override
    public void sendTeacherSMS(SmsTeacher smsTeacher, final OnFinishedListener listener) {
        SmsApi api = ApiClient.getAuthorizedClient().create(SmsApi.class);

        Call<Sms> queue = api.sendTeacherSMS(smsTeacher);
        queue.enqueue(new Callback<Sms>() {
            @Override
            public void onResponse(Call<Sms> call, Response<Sms> response) {
                if(response.isSuccessful()) {
                    listener.onSmsSaved(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }
            @Override
            public void onFailure(Call<Sms> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }
}
