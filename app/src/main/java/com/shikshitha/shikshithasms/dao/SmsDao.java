package com.shikshitha.shikshithasms.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.util.AppGlobal;

import java.util.List;

/**
 * Created by Vinay on 18-09-2017.
 */

public class SmsDao {

    public static int insertSMSMessages(List<Sms> messages) {
        String sql = "insert into sms_info(Id, SchoolId, ClassId, SectionId, SenderId, " +
                "SenderName, SentTime, Message, SentTo) " +
                "values(?,?,?,?,?,?,?,?,?)";
        SQLiteDatabase db = AppGlobal.getSqlDbHelper().getWritableDatabase();
        db.beginTransactionNonExclusive();
        SQLiteStatement stmt = db.compileStatement(sql);
        try {
            for(Sms sms: messages) {
                stmt.bindLong(1, sms.getId());
                stmt.bindLong(2, sms.getSchoolId());
                stmt.bindLong(3, sms.getClassId());
                stmt.bindLong(4, sms.getSectionId());
                stmt.bindLong(5, sms.getSenderId());
                stmt.bindString(6, sms.getSenderName());
                stmt.bindLong(7, sms.getSentTime());
                stmt.bindString(8, sms.getMessage());
                stmt.bindString(9, sms.getSentTo());
                stmt.executeInsert();
                stmt.clearBindings();
            }
        } catch (Exception e) {
            db.endTransaction();
            return 0;
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return 1;
    }

}
