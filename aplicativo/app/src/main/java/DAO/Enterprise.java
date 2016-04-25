package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import DAL.DBHelper;

/**
 * Created by Victor on 05/04/2016.
 */
public class Enterprise {

    private DBHelper DAO_ENTERPRISE = null;
    // TODO: modify:05/04/2016 = CONSTRUCTOR's
    public Enterprise(Context context){
        if (DAO_ENTERPRISE == null){
            DAO_ENTERPRISE = new DBHelper(context);
        }
    }
    // TODO: modify:05/04/2016 = INSERT's
    public void addEnterprise(String new_name, int new_id_login){
        String sql = "INSERT INTO enterprise(name,id_user) VALUES ('"+new_name+"',"+new_id_login+");";
        SQLiteDatabase db = DAO_ENTERPRISE.getWritableDatabase();
        db.execSQL(sql);
    }
    // TODO: modify:05/04/2016 = DELETE's
    public void deleteAllEnterprises(){
        String sql = "DELETE FROM enterprise";
        SQLiteDatabase db = DAO_ENTERPRISE.getWritableDatabase();
        db.execSQL(sql);
    }
    // TODO: modify:05/04/2016 = ARRAY's
    public ArrayList<String> getAllEnterprises(){
        String sql = "SELECT * FROM enterprise;";
        SQLiteDatabase db = DAO_ENTERPRISE.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> enterprises = null;

        if (cursor != null && cursor.moveToFirst()){
            enterprises = new ArrayList<String>();
            do{
                enterprises.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return enterprises;
    }



}
