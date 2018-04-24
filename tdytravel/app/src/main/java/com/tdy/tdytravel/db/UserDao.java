package com.tdy.tdytravel.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*******************************
 * Created by tangdayi on 2018/4/23.
 * 作者:tangdayi
 * 日期:2018年04月23日22时34分
 * 文件:UserDao
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class UserDao {

    /***
     * 验证登录
     * @param context
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(Context context, String username, String password){
        DBHelpUtils dbHelpUtils = new DBHelpUtils(context);
        if(username!=null && password!=null){
            SQLiteDatabase db = dbHelpUtils.getReadableDatabase();
            if (db.isOpen()) {
                // select * from tb_user
                Cursor cursor = db.query(DBHelpUtils.tb_user, null, "username=? and password=?",
                        new String[]{ username ,password}, null, null, null);
                //检查登录
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("username"));
                        String pwd = cursor.getString(cursor.getColumnIndex("password"));
                        System.out.println("name:--->:"+name+"----->:"+password);
                        if (name.equals(username)&&pwd.equals(password)) {
                            close(db, cursor);
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    /***
     * 添加用户
     * @param context
     * @param username
     * @param password
     * @return
     */
    public static boolean addUser(Context context, String username, String password){
        DBHelpUtils dbHelpUtils = new DBHelpUtils(context);
        if(username!=null && password!=null){
            SQLiteDatabase db = dbHelpUtils.getReadableDatabase();
            if (db.isOpen()) {
                String sql ="insert into tb_user(username,password) values(?,?)";
                db.execSQL(sql,new Object[]{username,password});
                db.close();
                return true;
            }
        }
        return false;
    }


     /**
      * 关闭
     * @param db
     * @param cursor
     */
    protected static void close(SQLiteDatabase db, Cursor cursor) {
        if (cursor!=null) {
            cursor.close();
        }
        if(db!=null){
            db.close();
        }

    }

}
