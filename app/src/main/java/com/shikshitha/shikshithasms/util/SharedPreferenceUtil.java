package com.shikshitha.shikshithasms.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.shikshitha.shikshithasms.model.AppVersion;
import com.shikshitha.shikshithasms.model.Authorization;
import com.shikshitha.shikshithasms.model.TeacherCredentials;

public class SharedPreferenceUtil {

    public static void saveTeacher(Context context, TeacherCredentials credentials) {
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("mobileNo", credentials.getMobileNo());
        editor.putString("authToken", credentials.getAuthToken());
        editor.putLong("schoolId", credentials.getSchoolId());
        editor.putString("schoolName", credentials.getSchoolName());
        editor.apply();
    }

    public static TeacherCredentials getTeacher(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        TeacherCredentials response = new TeacherCredentials();
        response.setMobileNo(sharedPref.getString("mobileNo", ""));
        response.setAuthToken(sharedPref.getString("authToken", ""));
        response.setSchoolId(sharedPref.getLong("schoolId", 0));
        response.setSchoolName(sharedPref.getString("schoolName", ""));
        return response;
    }

    public static void logout(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("mobileNo", "");
        editor.putString("authToken", "");
        editor.putLong("schoolId", 0);
        editor.apply();
    }

    public static void saveAuthorizedUser(Context context, String user) {
        SharedPreferences sharedPref = context.getSharedPreferences("fcm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user", user);
        editor.apply();
    }

    public static AppVersion getAppVersion(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("ver", Context.MODE_PRIVATE);
        AppVersion appVersion = new AppVersion();
        appVersion.setVersionId(sharedPref.getInt("version_id", 0));
        appVersion.setVersionName(sharedPref.getString("version_name", ""));
        appVersion.setStatus(sharedPref.getString("version_status", ""));
        return appVersion;
    }

    public static void saveAppVersion(Context context, AppVersion appVersion) {
        SharedPreferences sharedPref = context.getSharedPreferences("ver", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("version_id", appVersion.getVersionId());
        editor.putString("version_name", appVersion.getVersionName());
        editor.putString("version_status", appVersion.getStatus());
        editor.apply();
    }

    public static boolean isUpdatePrompted(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("ver", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("is_prompted", false);
    }

    public static void updatePrompted(Context context, boolean isPrompted) {
        SharedPreferences sharedPref = context.getSharedPreferences("ver", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("is_prompted", isPrompted);
        editor.apply();
    }

}
