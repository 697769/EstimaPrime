package br.com.estimaprime.aplicativo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Victor on 18/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    // TODO: 18/03/2016 = DataBase Name
    private static String DATABASE_NAME="db";
    // TODO: 18/03/2016 = System Version
    private static int VERSION = 1;

    // TODO: 18/03/2016 = DB_TABLE_CREATES
    private static String TABLE_LOGIN =
            "CREATE TABLE login("+
            "id_login INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "email TEXT,"+
            "password TEXT"+
            ");";

    private static String TABLE_ENTERPRISE =
            "CREATE TABLE enterprise("+
            "id_enterprise INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name TEXT,"+
            "id_login INTEGER,"+
            "FOREIGN KEY(id_login) REFERENCES login(id_login)"+
            ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_LOGIN);
        db.execSQL(TABLE_ENTERPRISE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
