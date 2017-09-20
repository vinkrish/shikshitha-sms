package com.shikshitha.shikshithasms.sqlite;

/**
 * Created by Vinay.
 */
interface SqlConstant {

    String DATABASE_NAME = "sms.db";
    int DATABASE_VERSION = 1;

    String CREATE_TEACHER = "CREATE TABLE teacher (" +
            "  Id INTEGER," +
            "  Name TEXT," +
            "  Image TEXT," +
            "  Username TEXT," +
            "  Password TEXT," +
            "  SchoolId INTEGER," +
            "  DateOfBirth date," +
            "  Mobile TEXT," +
            "  Qualification TEXT," +
            "  DateOfJoining date," +
            "  Gender TEXT," +
            "  Email TEXT" +
            ")";

    String CREATE_SERVICE = "CREATE TABLE service (" +
            " Id INTEGER, " +
            " SchoolId INTEGER, " +
            " IsMessage TEXT, " +
            " IsSms TEXT, " +
            " IsChat TEXT, " +
            " IsAttendance TEXT, " +
            " IsAttendanceSms TEXT," +
            " IsHomework TEXT, " +
            " IsHomeworkSms TEXT," +
            " IsTimetable TEXT," +
            " IsReport TEXT" +
            ")";

    String CREATE_SMS_INFO = "CREATE TABLE sms_info (" +
            "  Id INTEGER PRIMARY KEY," +
            "  SchoolId INTEGER," +
            "  ClassId INTEGER," +
            "  SectionId INTEGER," +
            "  SenderId INTEGER," +
            "  SenderName TEXT," +
            "  SentTime INTEGER," +
            "  Message TEXT," +
            "  SentTo TEXT" +
            ")";

    String CREATE_ATTENDANCE = "CREATE TABLE attendance (" +
            "  Id INTEGER PRIMARY KEY," +
            "  SectionId INTEGER," +
            "  StudentId INTEGER," +
            "  StudentName TEXT," +
            "  SubjectId INTEGER," +
            "  Type TEXT," +
            "  Session INTEGER," +
            "  DateAttendance date," +
            "  TypeOfLeave TEXT" +
            ")";

    String CREATE_CLASS = "CREATE TABLE class (" +
            "  Id INTEGER," +
            "  ClassName TEXT," +
            "  SchoolId INTEGER," +
            "  TeacherId INTEGER," +
            "  AttendanceType TEXT" +
            ")";

    String CREATE_SECTION = "CREATE TABLE section (" +
            "  Id INTEGER," +
            "  SectionName TEXT," +
            "  ClassId INTEGER," +
            "  TeacherId INTEGER" +
            ")";

    String CREATE_TIMETABLE = "CREATE TABLE timetable (" +
            "  Id INTEGER," +
            "  SectionId INTEGER," +
            "  DayOfWeek TEXT," +
            "  PeriodNo INTEGER," +
            "  SubjectId INTEGER," +
            "  SubjectName TEXT," +
            "  TeacherName TEXT," +
            "  TimingFrom time," +
            "  TimingTo time" +
            ")";

    String CREATE_STUDENT = "CREATE TABLE student (" +
            "  Id INTEGER," +
            "  Name TEXT," +
            "  SchoolId INTEGER," +
            "  ClassId INTEGER," +
            "  SectionId INTEGER," +
            "  AdmissionNo TEXT," +
            "  RollNo INTEGER," +
            "  Username TEXT," +
            "  Password TEXT," +
            "  Image TEXT," +
            "  FatherName TEXT," +
            "  MotherName TEXT," +
            "  DateOfBirth date," +
            "  Gender TEXT," +
            "  Email TEXT," +
            "  Mobile1 TEXT," +
            "  Mobile2 TEXT," +
            "  Street TEXT," +
            "  City TEXT," +
            "  District TEXT," +
            "  State TEXT," +
            "  Pincode TEXT" +
            ")";
}
