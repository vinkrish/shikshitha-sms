package com.shikshitha.shikshithasms.sms;

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

/**
 * Created by Vinay on 18-09-2017.
 */

class SmsPresenterImpl implements SmsPresenter, SmsInteractor.OnFinishedListener {

    private SmsView mView;
    private SmsInteractor mInteractor;

    SmsPresenterImpl(SmsView view, SmsInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void getClassList(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getClassList(schoolId, this);
        }
    }

    @Override
    public void getSectionList(long classId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getSectionList(classId, this);
        }
    }

    @Override
    public void getStudents(long sectionId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getStdudent(sectionId, this);
        }
    }

    @Override
    public void getTeachers(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getTeachers(schoolId, this);
        }
    }

    @Override
    public void sendSchoolSMS(Sms sms) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSchoolSMS(sms, this);
        }
    }

    @Override
    public void sendClassSMS(Sms sms) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendClassSMS(sms, this);
        }
    }

    @Override
    public void sendClassesSMS(SmsClass smsClass) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendClassesSMS(smsClass, this);
        }
    }

    @Override
    public void sendSectionSMS(Sms sms) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSectionSMS(sms, this);
        }
    }

    @Override
    public void sendSectionsSMS(SmsSection smsSection) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSectionsSMS(smsSection, this);
        }
    }

    @Override
    public void sendMaleSMS(Sms sms) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendMaleSMS(sms, this);
        }
    }

    @Override
    public void sendFemaleSMS(Sms sms) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendFemaleSMS(sms, this);
        }
    }

    @Override
    public void sendStudentSMS(SmsStudent smsStudent) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendStudentSMS(smsStudent, this);
        }
    }

    @Override
    public void sendTeacherSms(SmsTeacher smsTeacher) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendTeacherSMS(smsTeacher, this);
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
    public void onClassReceived(List<Clas> clasList) {
        if (mView != null) {
            mView.showClass(clasList);
            mView.hideProgress();
        }
    }

    @Override
    public void onSectionReceived(List<Section> sectionList) {
        if (mView != null) {
            mView.showSection(sectionList);
            mView.hideProgress();
        }
    }

    @Override
    public void onStudentReceived(List<Student> students) {
        if (mView != null) {
            mView.showStudent(students);
            mView.hideProgress();
        }
    }

    @Override
    public void onTeacherReceived(List<Teacher> teachers) {
        if (mView != null) {
            mView.showTeachers(teachers);
            mView.hideProgress();
        }
    }

    @Override
    public void onSmsSaved(Sms sms) {
        if (mView != null) {
            mView.smsSaved(sms);
            mView.hideProgress();
        }
    }
}
