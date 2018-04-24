package com.tdy.tdytravel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tdy.tdytravel.base.MyApplication;

/*******************************
 * Created by tangdayi on 2018/4/23.
 * 作者:tangdayi
 * 日期:2018年04月23日22时06分
 * 文件:DBHpleUtils
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class DBHelpUtils extends SQLiteOpenHelper{

    public final static String tb_user = "tb_user";
    public final static String db_name = "travel.db";
    public DBHelpUtils(Context context){

        super(context, db_name, null, 5);

    }

    /***
     * // 数据库第一次被创建的时候 调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String sql = "create table if not exists tb_user(_id INTEGER primary key autoincrement,username varchar(50),password varchar(50),mobile varchar(100),imageUrl varchar(200),sex varchar(10),date varchar(100),status varchar(20))";
        db.execSQL(sql);
    }

    /***
     * //修改数据库  版本
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion) {
            db.execSQL("drop table if exists" + tb_user);
            onCreate(db);
        }

    }

    public static String getTableName(String tableName){
        if(tableName==null){
            return "tb_test";
        }
        return tableName;
    }
}
