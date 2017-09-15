package com.shikshitha.shikshithasms.attendance;

import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.Timetable;

import java.util.List;

/**
 * Created by Vinay on 21-04-2017.
 */

interface AttendanceView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void showOffline(String tableName);

    void showClass(List<Clas> clasList);

    void showSection(List<Section> sectionList);

    void showTimetable(List<Timetable> timetableList);

    void showAttendance(AttendanceSet attendanceSet);

    void attendanceSaved();

    void attendanceDeleted();
}
