package com.shikshitha.shikshithasms.model;

import java.util.List;

public class SmsStudent {
    private List<Student> students;
    private Sms sms;

    public SmsStudent(List<Student> students, Sms sms) {
        this.students = students;
        this.sms = sms;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

}
