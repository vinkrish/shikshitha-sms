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

interface SmsPresenter {
    void getClassList(long schoolId);

    void getSectionList(long classId);

    void getStudents(long sectionId);

    void getTeachers(long schoolId);

    void sendSchoolSMS(Sms sms);

    void sendAllStudentsSMS(Sms sms);

    void sendClassSMS(Sms sms);

    void sendClassesSMS(SmsClass smsClass);

    void sendSectionSMS(Sms sms);

    void sendSectionsSMS(SmsSection smsSection);

    void sendMaleSMS(Sms sms);

    void sendFemaleSMS(Sms sms);

    void sendStudentSMS(SmsStudent smsStudent);

    void sendTeacherSms(SmsTeacher smsTeacher);

    void onDestroy();
}
