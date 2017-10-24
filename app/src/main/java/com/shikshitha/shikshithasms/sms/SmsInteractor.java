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

interface SmsInteractor {
    interface OnFinishedListener {
        void onError(String message);

        void onClassReceived(List<Clas> clasList);

        void onSectionReceived(List<Section> sectionList);

        void onStudentReceived(List<Student> students);

        void onTeacherReceived(List<Teacher> teachers);

        void onSmsSaved(Sms sms);
    }

    void getClassList(long schoolId, SmsInteractor.OnFinishedListener listener);

    void getSectionList(long classId, SmsInteractor.OnFinishedListener listener);

    void getStudent(long sectionId, SmsInteractor.OnFinishedListener listener);

    void getTeachers(long schoolId, SmsInteractor.OnFinishedListener listener);

    void sendSchoolSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendAllStudentsSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendClassSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendClassesSMS(SmsClass smsClass, SmsInteractor.OnFinishedListener listener);

    void sendSectionSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendSectionsSMS(SmsSection smsSection, SmsInteractor.OnFinishedListener listener);

    void sendMaleSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendFemaleSMS(Sms sms, SmsInteractor.OnFinishedListener listener);

    void sendStudentSMS(SmsStudent smsStudent, SmsInteractor.OnFinishedListener listener);

    void sendTeacherSMS(SmsTeacher smsTeacher, SmsInteractor.OnFinishedListener listener);
}
