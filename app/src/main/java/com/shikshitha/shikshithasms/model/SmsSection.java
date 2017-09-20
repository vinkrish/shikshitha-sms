package com.shikshitha.shikshithasms.model;

import java.util.List;

public class SmsSection {
    private List<Section> sections;
    private Sms sms;

    public SmsSection(List<Section> sections, Sms sms) {
        this.sections = sections;
        this.sms = sms;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

}
