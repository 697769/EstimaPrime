package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DBHelper extends SQLiteOpenHelper {

    // TODO: 18/03/2016 = DataBase Name
    public static String DATABASE_NAME="estimaprime.db";
    // TODO: 18/03/2016 = System Version
    public static int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_CREATE_USER);
        db.execSQL(EnterpriseDAO.SQL_CREATE_ENTERPRISE);
        db.execSQL(NoteDAO.SQL_CREATE_NOTE);
        UserDAO.insertDefaultUser(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
