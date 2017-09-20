package com.shikshitha.shikshithasms.api;

import com.shikshitha.shikshithasms.attendance.AttendanceSet;
import com.shikshitha.shikshithasms.model.AppVersion;
import com.shikshitha.shikshithasms.model.Attendance;
import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.Credentials;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.model.SmsClass;
import com.shikshitha.shikshithasms.model.SmsSection;
import com.shikshitha.shikshithasms.model.SmsStudent;
import com.shikshitha.shikshithasms.model.SmsTeacher;
import com.shikshitha.shikshithasms.model.Student;
import com.shikshitha.shikshithasms.model.Teacher;
import com.shikshitha.shikshithasms.model.TeacherCredentials;
import com.shikshitha.shikshithasms.model.Timetable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Vinay on 15-09-2017.
 */

public interface SmsApi {

    @Headers("content-type: application/json")
    @POST("teacher/login")
    Call<TeacherCredentials> login(@Body Credentials credentials);

    @Headers("content-type: application/json")
    @POST("sms/teacher/{username}")
    Call<Void> forgotPassword(@Path("username") String username);

    @GET("appversion/{versionId}/{appName}")
    Call<AppVersion> getAppVersion(@Path("versionId") int versionId,
                                   @Path("appName") String appName);

    @GET("class/school/{schoolId}")
    Call<List<Clas>> getClassList(@Path("schoolId") long schoolId);

    @GET("section/class/{classId}")
    Call<List<Section>> getSectionList(@Path("classId") long classId);

    @GET("student/section/{sectionId}")
    Call<List<Student>> getStudents(@Path("sectionId") long sectionId);

    @GET("teacher/school/{schoolId}")
    Call<List<Teacher>> getTeacherList(@Path("schoolId") long schoolId);

    //Attendance API

    @GET("timetable/section/{sectionId}/day/{dayOfWeek}")
    Call<List<Timetable>> getTimetable(@Path("sectionId") long sectionId,
                                       @Path("dayOfWeek") String dayOfWeek);

    @GET("app/attendance/section/{sectionId}/date/{dateAttendance}/session/{session}")
    Call<AttendanceSet> getAttendanceSet(@Path("sectionId") long sectionId,
                                         @Path("dateAttendance") String dateAttendance,
                                         @Path("session") int session);

    @POST("app/attendance")
    Call<Void> saveAttendance(@Body ArrayList<Attendance> attendances);

    @POST("app/attendance/delete")
    Call<Void> deleteAttendance(@Body ArrayList<Attendance> attendanceList);

    //SMS Message API

    @POST("smsmessage/school")
    Call<Sms> sendSchoolSMS(@Body Sms sms);

    @POST("smsmessage/class")
    Call<Sms> sendClassSMS(@Body Sms sms);

    @POST("smsmessage/classes")
    Call<Sms> sendClassesSMS(@Body SmsClass smsClass);

    @POST("smsmessage/section")
    Call<Sms> sendSectionSMS(@Body Sms sms);

    @POST("smsmessage/sections")
    Call<Sms> sendSectionsSMS(@Body SmsSection smsSection);

    @POST("smsmessage/school/male")
    Call<Sms> sendMaleSMS(@Body Sms sms);

    @POST("smsmessage/school/female")
    Call<Sms> sendFemaleSMS(@Body Sms sms);

    @POST("smsmessage/students")
    Call<Sms> sendStudentSMS(@Body SmsStudent smsStudent);

    @POST("smsmessage/teachers")
    Call<Sms> sendTeacherSMS(@Body SmsTeacher smsTeacher);
}
