package com.shikshitha.shikshithasms.sms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.shikshitha.shikshithasms.R;
import com.shikshitha.shikshithasms.attendance.AttendanceActivity;
import com.shikshitha.shikshithasms.dao.TeacherDao;
import com.shikshitha.shikshithasms.login.LoginActivity;
import com.shikshitha.shikshithasms.model.Clas;
import com.shikshitha.shikshithasms.model.ClasSet;
import com.shikshitha.shikshithasms.model.Section;
import com.shikshitha.shikshithasms.model.SectionSet;
import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.model.SmsClass;
import com.shikshitha.shikshithasms.model.SmsSection;
import com.shikshitha.shikshithasms.model.SmsStudent;
import com.shikshitha.shikshithasms.model.SmsTeacher;
import com.shikshitha.shikshithasms.model.Student;
import com.shikshitha.shikshithasms.model.StudentSet;
import com.shikshitha.shikshithasms.model.Teacher;
import com.shikshitha.shikshithasms.model.TeacherSet;
import com.shikshitha.shikshithasms.sqlite.SqlDbHelper;
import com.shikshitha.shikshithasms.util.DividerItemDecoration;
import com.shikshitha.shikshithasms.util.NetworkUtil;
import com.shikshitha.shikshithasms.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmsActivity extends AppCompatActivity implements SmsView,
        AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.message_layout) TextInputLayout messageLayout;
    @BindView(R.id.message) EditText message;
    @BindView(R.id.target_spinner) Spinner targetSpinner;
    @BindView(R.id.class_layout) LinearLayout classLayout;
    @BindView(R.id.classes_layout) LinearLayout classesLayout;
    @BindView(R.id.section_layout) LinearLayout sectionLayout;
    @BindView(R.id.sections_layout) LinearLayout sectionsLayout;
    @BindView(R.id.student_layout) LinearLayout studentLayout;
    @BindView(R.id.teacher_layout) LinearLayout teacherLayout;
    @BindView(R.id.class_spinner) Spinner classSpinner;
    @BindView(R.id.section_spinner) Spinner sectionSpinner;
    @BindView(R.id.class_recycler_view) RecyclerView classView;
    @BindView(R.id.section_recycler_view) RecyclerView sectionView;
    @BindView(R.id.student_recycler_view) RecyclerView studentView;
    @BindView(R.id.teacher_recycler_view) RecyclerView teacherView;
    @BindView(R.id.progress) ProgressBar progressBar;

    private Teacher teacher;
    private SmsPresenter presenter;
    private ClassAdapter classAdapter;
    private SectionAdapter sectionAdapter;
    private StudentAdapter studentAdapter;
    private TeacherAdapter teacherAdapter;

    private String[] target = {"Whole School/College", "Class", "Multiple Classes",
            "Section", "Multiple Sections", "All Male Students",
            "All Female Students", "Students", "Teachers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        teacher = TeacherDao.getTeacher();

        initView();

        presenter = new SmsPresenterImpl(this, new SmsInteractorImpl());

        if(NetworkUtil.isNetworkAvailable(this)) {
            presenter.getClassList(teacher.getSchoolId());
        } else {
            showSnackbar("You are offline");
        }
    }

    private void initView() {
        ArrayAdapter<String> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, target);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetSpinner.setAdapter(adapter);
        targetSpinner.setOnItemSelectedListener(this);

        classView.setLayoutManager(new LinearLayoutManager(this));
        classView.setNestedScrollingEnabled(false);
        classView.setItemAnimator(new DefaultItemAnimator());
        classView.addItemDecoration(new DividerItemDecoration(this));
        classAdapter = new ClassAdapter(new ArrayList<ClasSet>(0));
        classView.setAdapter(classAdapter);

        sectionView.setLayoutManager(new LinearLayoutManager(this));
        sectionView.setNestedScrollingEnabled(false);
        sectionView.setItemAnimator(new DefaultItemAnimator());
        sectionView.addItemDecoration(new DividerItemDecoration(this));
        sectionAdapter = new SectionAdapter(new ArrayList<SectionSet>(0));
        sectionView.setAdapter(sectionAdapter);

        studentView.setLayoutManager(new LinearLayoutManager(this));
        studentView.setNestedScrollingEnabled(false);
        studentView.setItemAnimator(new DefaultItemAnimator());
        studentView.addItemDecoration(new DividerItemDecoration(this));
        studentAdapter = new StudentAdapter(new ArrayList<StudentSet>(0));
        studentView.setAdapter(studentAdapter);

        teacherView.setLayoutManager(new LinearLayoutManager(this));
        teacherView.setNestedScrollingEnabled(false);
        teacherView.setItemAnimator(new DefaultItemAnimator());
        teacherView.addItemDecoration(new DividerItemDecoration(this));
        teacherAdapter = new TeacherAdapter(new ArrayList<TeacherSet>(0));
        teacherView.setAdapter(teacherAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sms_overflow, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu (Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attendance_activity:
                startActivity(new Intent(this, AttendanceActivity.class));
                return true;
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(MenuItem item) {
        if(message.getText().toString().isEmpty()){
            messageLayout.setError(getString(R.string.empty_msg));
        } else {
            Sms sms =  new Sms();
            sms.setSchoolId(teacher.getSchoolId());
            sms.setSenderId(teacher.getId());
            sms.setSenderName(teacher.getName());
            sms.setSentTime(System.currentTimeMillis());
            sms.setMessage(message.getText().toString());
            switch (targetSpinner.getSelectedItem().toString()) {
                case "Whole School/College":
                    sms.setSentTo("Whole School/College");
                    presenter.sendSchoolSMS(sms);
                    break;
                case "Class":
                    sms.setClassId(((Clas) classSpinner.getSelectedItem()).getId());
                    sms.setSentTo("Class");
                    presenter.sendClassSMS(sms);
                    break;
                case "Multiple Classes":
                    List<Clas> classes = new ArrayList<>();
                    StringBuilder classNames = new StringBuilder();
                    for(ClasSet clasSet: classAdapter.getDataSet()) {
                        if(clasSet.isSelected()) {
                            classes.add(new Clas(clasSet.getId(), clasSet.getClassName()));
                            classNames.append(clasSet.getClassName()).append(",");
                        }
                    }
                    if(classes.size() > 0) {
                        sms.setSentTo("Multiple Classes: " + classNames.substring(0, classNames.length()-1));
                        presenter.sendClassesSMS(new SmsClass(classes, sms));
                    } else {
                        showSnackbar("Please select classes");
                    }
                    break;
                case "Section":
                    sms.setSectionId(((Section) sectionSpinner.getSelectedItem()).getId());
                    sms.setSentTo("Section");
                    presenter.sendSectionSMS(sms);
                    break;
                case "Multiple Sections":
                    List<Section> sections = new ArrayList<>();
                    StringBuilder sectionNames = new StringBuilder();
                    for(SectionSet sectionSet: sectionAdapter.getDataSet()) {
                        if(sectionSet.isSelected()) {
                            sections.add(new Section(sectionSet.getId(), sectionSet.getSectionName()));
                            sectionNames.append(sectionSet.getSectionName()).append(",");
                        }
                    }
                    if(sections.size() > 0) {
                        sms.setSentTo("Multiple Section: " + sectionNames.substring(0, sectionNames.length()-1));
                        presenter.sendSectionsSMS(new SmsSection(sections, sms));
                    } else {
                        showSnackbar("Please select sections");
                    }
                    break;
                case "All Male Students":
                    presenter.sendMaleSMS(sms);
                    sms.setSentTo("All Male Students");
                    break;
                case "All Female Students":
                    presenter.sendFemaleSMS(sms);
                    sms.setSentTo("All Female Students");
                    break;
                case "Students":
                    List<Student> students = new ArrayList<>();
                    StringBuilder studentNames = new StringBuilder();
                    for(StudentSet studentSet: studentAdapter.getDataSet()) {
                        if(studentSet.isSelected()) {
                            students.add(new Student(studentSet.getId(), studentSet.getRollNo(), studentSet.getUsername(), studentSet.getName()));
                            studentNames.append(studentSet.getName()).append(",");
                        }
                    }
                    if(students.size() > 0) {
                        sms.setSentTo("Students: " + studentNames.substring(0, studentNames.length()-1));
                        presenter.sendStudentSMS(new SmsStudent(students, sms));
                    } else {
                        showSnackbar("Please select students");
                    }
                    break;
                case "Teachers":
                    List<Teacher> teachers = new ArrayList<>();
                    StringBuilder teacherNames = new StringBuilder();
                    for(TeacherSet teacherSet: teacherAdapter.getDataSet()) {
                        if(teacherSet.isSelected()) {
                            teachers.add(new Teacher(teacherSet.getId(), teacherSet.getUsername(), teacherSet.getName()));
                            teacherNames.append(teacherSet.getName()).append(",");
                        }
                    }
                    if(teachers.size() > 0) {
                        sms.setSentTo("Teachers: " + teacherNames.substring(0, teacherNames.length()-1));
                        presenter.sendTeacherSms(new SmsTeacher(teachers, sms));
                    } else {
                        showSnackbar("Please select teachers");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void logout() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure you want to logout?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferenceUtil.logout(SmsActivity.this);
                SqlDbHelper.getInstance(SmsActivity.this).deleteTables();
                startActivity(new Intent(SmsActivity.this, LoginActivity.class));
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.target_spinner:
                targetSelected(targetSpinner.getSelectedItem().toString());
                break;
            case R.id.class_spinner:
                if(NetworkUtil.isNetworkAvailable(this)) {
                    presenter.getSectionList(((Clas) classSpinner.getSelectedItem()).getId());
                } else {
                    showSnackbar("You are offline");
                }
                break;
            case R.id.section_spinner:
                if(NetworkUtil.isNetworkAvailable(this)) {
                    presenter.getStudents(((Section) sectionSpinner.getSelectedItem()).getId());
                } else {
                    showSnackbar("You are offline");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void targetSelected(String target) {
        clearTarget();
        switch (target) {
            case "Whole School/College":
                break;
            case "Class":
                classLayout.setVisibility(View.VISIBLE);
                break;
            case "Section":
                classLayout.setVisibility(View.VISIBLE);
                sectionLayout.setVisibility(View.VISIBLE);
                break;
            case "Multiple Classes":
                classesLayout.setVisibility(View.VISIBLE);
                ArrayList<ClasSet> clasSets = new ArrayList<>();
                for(Clas c: retrieveClasses(classSpinner)) {
                    clasSets.add(new ClasSet(c.getId(), c.getClassName()));
                }
                classAdapter.setDataSet(clasSets);
                break;
            case "Multiple Sections":
                classLayout.setVisibility(View.VISIBLE);
                sectionsLayout.setVisibility(View.VISIBLE);
                refreshSections();
                break;
            case "Students":
                classLayout.setVisibility(View.VISIBLE);
                sectionLayout.setVisibility(View.VISIBLE);
                studentLayout.setVisibility(View.VISIBLE);
                break;
            case "Teachers":
                teacherLayout.setVisibility(View.VISIBLE);
                presenter.getTeachers(teacher.getSchoolId());
                break;
            default:
                break;
        }
    }

    private void clearTarget() {
        classLayout.setVisibility(View.GONE);
        classesLayout.setVisibility(View.GONE);
        sectionLayout.setVisibility(View.GONE);
        sectionsLayout.setVisibility(View.GONE);
        studentLayout.setVisibility(View.GONE);
        teacherLayout.setVisibility(View.GONE);
        teacherAdapter.clearDataSet();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showClass(List<Clas> clasList) {
        ArrayAdapter<Clas> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clasList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);
        classSpinner.setOnItemSelectedListener(this);
        if(NetworkUtil.isNetworkAvailable(this)) {
            presenter.getSectionList(((Clas) classSpinner.getSelectedItem()).getId());
        } else {
            showSnackbar("You are offline");
        }
    }

    public List<Clas> retrieveClasses(Spinner spinner) {
        Adapter adapter = spinner.getAdapter();
        int n = adapter.getCount();
        List<Clas> classes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            classes.add((Clas)adapter.getItem(i));
        }
        return classes;
    }

    @Override
    public void showSection(List<Section> sectionList) {
        ArrayAdapter<Section> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sectionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(adapter);
        sectionSpinner.setOnItemSelectedListener(this);
        if(sectionList.size() == 1 && sectionList.get(0).getSectionName().equals("none")) {
            sectionLayout.setVisibility(View.GONE);
        } else {
            if(targetSpinner.getSelectedItem().toString().equals("Section") ||
                    targetSpinner.getSelectedItem().toString().equals("Students")) {
                sectionLayout.setVisibility(View.VISIBLE);
            } else if(targetSpinner.getSelectedItem().toString().equals("Multiple Sections")) {
                refreshSections();
            }
        }
    }

    public List<Section> retrieveSections(Spinner spinner) {
        Adapter adapter = spinner.getAdapter();
        int n = adapter.getCount();
        List<Section> sections = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sections.add((Section) adapter.getItem(i));
        }
        return sections;
    }

    private void refreshSections() {
        ArrayList<SectionSet> sectionSets = new ArrayList<>();
        for(Section s: retrieveSections(sectionSpinner)) {
            sectionSets.add(new SectionSet(s.getId(), s.getSectionName()));
        }
        sectionAdapter.setDataSet(sectionSets);
    }

    @Override
    public void showStudent(List<Student> students) {
        ArrayList<StudentSet> studentSets = new ArrayList<>();
        for(Student s: students) {
            studentSets.add(new StudentSet(s.getId(), s.getRollNo(), s.getUsername(), s.getName()));
        }
        studentAdapter.setDataSet(studentSets);
    }

    @Override
    public void showTeachers(List<Teacher> teachers) {
        ArrayList<TeacherSet> teacherSets = new ArrayList<>();
        for(Teacher t: teachers) {
            teacherSets.add(new TeacherSet(t.getId(), t.getUsername(), t.getName()));
        }
        teacherAdapter.setDataSet(teacherSets);
    }

    @Override
    public void smsSaved(Sms sms) {

    }

    public void selectAllStudents(View view) {
        showProgress();
        ArrayList<StudentSet> studentSets = studentAdapter.getDataSet();
        for(StudentSet studentSet : studentSets) {
            studentSet.setSelected(true);
        }
        studentAdapter.setDataSet(studentSets);
        hideProgress();
    }

    public void selectAllTeachers(View view) {
        showProgress();
        ArrayList<TeacherSet> teacherSets = teacherAdapter.getDataSet();
        for(TeacherSet teacherSet : teacherSets) {
            teacherSet.setSelected(true);
        }
        teacherAdapter.setDataSet(teacherSets);
        hideProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}