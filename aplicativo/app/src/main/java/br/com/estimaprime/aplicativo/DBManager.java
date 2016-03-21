package br.com.estimaprime.aplicativo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Victor on 19/03/2016.
 */
public class DBManager {

    private DBHelper dbHelper = null;

    public  DBManager(Context context){
        if (dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }

    // TODO: 19/03/2016 = INSERTS
    public void addLogin(String new_email, String new_password){
        String sql = "INSERT INTO login (email,password) VALUES ('"+new_email+"','"+new_password+"');";
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }
    public void addEnterprise(String new_name, int new_id_login){
        String sql = "INSERT INTO enterprise (name,id_login) VALUES ('"+new_name+"','"+new_id_login+"');";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    // TODO: 19/03/2016 = SELECTS
    public Boolean getEmail(String email){
        String e,sql = "SELECT email FROM login;";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        String sql = "SELECT email,password FROM login;";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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

    // TODO: 19/03/2016 = ARRAYS
    public ArrayList<String> getAllLogins(){
        String sql = "SELECT * FROM login;";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> logins = null;

        if (cursor != null && cursor.moveToFirst()){
            logins = new ArrayList<String>();
            do{ logins.add(cursor.getString(1)); }while (cursor.moveToNext());
        }
        return logins;
    }
}
