package com.shikshitha.shikshithasms.model;

/**
 * Created by Vinay on 18-09-2017.
 */

public class SectionSet extends Section {
    private boolean isSelected;

    public SectionSet(long id, String sectionName) {
        super(id, sectionName);
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
