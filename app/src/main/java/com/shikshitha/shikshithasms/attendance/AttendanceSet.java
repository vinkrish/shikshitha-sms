package com.shikshitha.shikshithasms.attendance;

import com.shikshitha.shikshithasms.model.Attendance;
import com.shikshitha.shikshithasms.model.Student;

import java.util.ArrayList;

/**
 * Created by Vinay on 22-04-2017.
 */

public class AttendanceSet {
    private ArrayList<Attendance> attendanceList;
    private ArrayList<Student> students;

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
