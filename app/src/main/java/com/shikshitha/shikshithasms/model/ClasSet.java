package com.shikshitha.shikshithasms.model;

/**
 * Created by Vinay on 18-09-2017.
 */

public class ClasSet extends Clas {
    private boolean isSelected;

    public ClasSet(long id, String className) {
        super(id, className);
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
