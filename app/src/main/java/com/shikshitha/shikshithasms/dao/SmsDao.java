package com.shikshitha.shikshithasms.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.shikshitha.shikshithasms.model.Sms;
import com.shikshitha.shikshithasms.util.AppGlobal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinay on 18-09-2017.
 */

public class SmsDao {

    public static int insertSMSMessages(List<Sms> messages) {
        String sql = "insert into sms_info(Id, SchoolId, ClassId, SectionId, SenderId, " +
                "SenderName, SentTime, Message, SentTo, RecipientRole) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
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
                stmt.bindString(10, sms.getRecipientRole());
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

    public static List<Sms> getSmsMessages(long senderId) {
        List<Sms> messages = new ArrayList<>();
        SQLiteDatabase sqliteDatabase = AppGlobal.getSqlDbHelper().getReadableDatabase();
        String query = "select * from sms_info where SenderId=" + senderId + " order by Id desc limit 50";
        Cursor c = sqliteDatabase.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Sms message = new Sms();
            message.setId(c.getLong(c.getColumnIndex("Id")));
            message.setSchoolId(c.getLong(c.getColumnIndex("SchoolId")));
            message.setClassId(c.getLong(c.getColumnIndex("ClassId")));
            message.setSectionId(c.getLong(c.getColumnIndex("SectionId")));
            message.setSenderId(c.getLong(c.getColumnIndex("SenderId")));
            message.setSenderName(c.getString(c.getColumnIndex("SenderName")));
            message.setSentTime(c.getLong(c.getColumnIndex("SentTime")));
            message.setMessage(c.getString(c.getColumnIndex("Message")));
            message.setSentTo(c.getString(c.getColumnIndex("SentTo")));
            message.setRecipientRole(c.getString(c.getColumnIndex("RecipientRole")));
            messages.add(message);
            c.moveToNext();
        }
        c.close();
        return messages;
    }

    public static List<Sms> getSmsMessagesFromId(long senderId, long messageId) {
        List<Sms> messages = new ArrayList<>();
        SQLiteDatabase sqliteDatabase = AppGlobal.getSqlDbHelper().getReadableDatabase();
        String query = "select * from sms_info where SenderId=" + senderId + " and Id<" + messageId + " order by Id desc limit 50";
        Cursor c = sqliteDatabase.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Sms message = new Sms();
            message.setId(c.getLong(c.getColumnIndex("Id")));
            message.setSchoolId(c.getLong(c.getColumnIndex("SchoolId")));
            message.setClassId(c.getLong(c.getColumnIndex("ClassId")));
            message.setSectionId(c.getLong(c.getColumnIndex("SectionId")));
            message.setSenderId(c.getLong(c.getColumnIndex("SenderId")));
            message.setSenderName(c.getString(c.getColumnIndex("SenderName")));
            message.setSentTime(c.getLong(c.getColumnIndex("SentTime")));
            message.setMessage(c.getString(c.getColumnIndex("Message")));
            message.setSentTo(c.getString(c.getColumnIndex("SentTo")));
            message.setRecipientRole(c.getString(c.getColumnIndex("RecipientRole")));
            messages.add(message);
            c.moveToNext();
        }
        c.close();
        return messages;
    }

}
