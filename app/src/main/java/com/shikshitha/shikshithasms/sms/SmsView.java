package com.shikshitha.shikshithasms.sms;

import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.model.Student;
import com.shikshitha.shikshithasms.model.Teacher;

import java.util.List;

/**
 * Created by Vinay on 18-09-2017.
 */

interface SmsView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void showClass(List<Clas> clasList);

    void showSection(List<Section> sectionList);

    void showStudent(List<Student> students);

    void showTeachers(List<Teacher> teachers);

    void smsSaved(Sms sms);
}
