package com.shikshitha.shikshithasms.model;

/**
 * Created by Vinay on 04-04-2017.
 */

public class TeacherSet extends Teacher {
    private boolean isSelected;

    public TeacherSet(long id, String username, String name) {
        super(id, username, name);
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
