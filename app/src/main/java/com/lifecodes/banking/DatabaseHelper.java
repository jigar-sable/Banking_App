package com.lifecodes.banking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(5120260321,'Austin',8912.00,'austin21@gmail.com','XXXXXXXXXXXX9878','SHK05319815')");
        db.execSQL("insert into user_table values(2403197642,'Rina',6421.67,'rina03@gmail.com','XXXXXXXXXXXX2051','JDK12046738')");
        db.execSQL("insert into user_table values(2401578905,'Jerrica',2510.56,'jerrica01@gmail.com','XXXXXXXXXXXX8645','KVM12034576')");
        db.execSQL("insert into user_table values(3561287981,'Carter',3567.01,'carter09@gmail.com','XXXXXXXXXXXX8923','LDN24036791')");
        db.execSQL("insert into user_table values(9842104344,'Jhon',7954.48,'jhon05@gmail.com','XXXXXXXXXXXX0275','SWJ87036349')");
        db.execSQL("insert into user_table values(8704564208,'Mac',6458.16,'mac50@gmail.com','XXXXXXXXXXXX2630','OWD24016754')");
        db.execSQL("insert into user_table values(8794531201,'Chris',8064.00,'chris03@gmail.com','XXXXXXXXXXXX4621','DJN34056190')");
        db.execSQL("insert into user_table values(6405187915,'Michel',9072.22,'michel07@gmail.com','XXXXXXXXXXXX1921','WKS45063721')");
        db.execSQL("insert into user_table values(7061590812,'Marcy',5640.46,'marcy05@gmail.com','XXXXXXXXXXXX3024','ZSK97206037')");
        db.execSQL("insert into user_table values(7312058461,'Cathi',8704.90,'cathi20@gmail.com','XXXXXXXXXXXX9625','QLS78120641')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
