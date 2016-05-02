package DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import DAL.DBHelper;
public class User {
    private DBHelper DAO_USER = null;
    // TODO: modify:05/04/2016 = CONSTRUCTOR's
    public User(Context context){
        if (DAO_USER == null){
            DAO_USER = new DBHelper(context);
        }
    }
    // TODO: modify:01/05/2016 = USER_INSERT's
    public void addUser(String new_email, String new_password){
        String sql = "INSERT INTO user(email,password) VALUES ('"+new_email.trim()+"','"+new_password.trim()+"');";
        SQLiteDatabase db = DAO_USER.getWritableDatabase();
        db.execSQL(sql);
    }
    // TODO: modify:01/05/2016 = USER_SELECT's
    public int getIdUser(String email){
            int idUser=0;
            String e,sql = "SELECT id_user,email FROM user u WHERE u.email = '"+email.trim()+"';";
            SQLiteDatabase db = DAO_USER.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()){
                do{
                    e = cursor.getString(1);
                    if (e.equals(email)){ //usuario encontrado
                        idUser= cursor.getInt(0); //id como primeiro campo da consulta
                        return idUser;
                    }
                }while(cursor.moveToNext());
            }
            return idUser;
        }
    public Boolean getEmail(String email){
        String e,sql = "SELECT email FROM user u WHERE u.email='"+email.trim()+"';";
        SQLiteDatabase db = DAO_USER.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                e = cursor.getString(0);
                if (e.equals(email)){
                    return true;
                }
            }while(cursor.moveToNext());
        }
        return false;
    }
    public String getPassword(String email){
        String sql = "SELECT email,password FROM user u WHERE u.email='"+email.trim()+"';";
        SQLiteDatabase db = DAO_USER.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        String e="",p="Not found";
        if (cursor.moveToFirst()){
            do{
                e = cursor.getString(0);
                if (e.equals(email)){
                    p = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return p;
    }
    // TODO: modify:01/05/2016 = USER_ARRAY's
    public ArrayList<String> getAllLogins(){
        String sql = "SELECT * FROM user;";
        SQLiteDatabase db = DAO_USER.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> users = null;

        if (cursor != null && cursor.moveToFirst()){
            users = new ArrayList<String>();
            do{ users.add(cursor.getString(1)); }while (cursor.moveToNext());
        }
        return users;
    }
    // TODO: modify:01/05/2016 = USER_DELETE's
    public void deleteAllUsers(){
        String sql = "DELETE FROM user;";
        SQLiteDatabase db = DAO_USER.getWritableDatabase();
        db.execSQL(sql);
        String sql2 = "DELETE FROM sqlite_sequence WHERE name='user';";
        db = DAO_USER.getWritableDatabase();
        db.execSQL(sql2);
    }
}
