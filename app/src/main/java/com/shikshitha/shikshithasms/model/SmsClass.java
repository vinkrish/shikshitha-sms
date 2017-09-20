package com.shikshitha.shikshithasms.model;

import java.util.List;

public class SmsClass {
    private List<Clas> classes;
    private Sms sms;

    public SmsClass(List<Clas> classes, Sms sms) {
        this.classes = classes;
        this.sms = sms;
    }

    public List<Clas> getClasses() {
        return classes;
    }

    public void setClasses(List<Clas> classes) {
        this.classes = classes;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

}
