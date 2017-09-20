package com.shikshitha.shikshithasms.model;

import java.util.List;

public class SmsTeacher {
    private List<Teacher> teachers;
    private Sms sms;

    public SmsTeacher(List<Teacher> teachers, Sms sms) {
        this.teachers = teachers;
        this.sms = sms;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

}
