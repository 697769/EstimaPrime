package Controle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DBHelper extends SQLiteOpenHelper {

    // TODO: 18/03/2016 = DataBase Name
    public static String DATABASE_NAME="estimaprimeDB";
    // TODO: 18/03/2016 = System Version
    public static int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.SQL_CREATE_USER);
        db.execSQL(Enterprise.SQL_CREATE_ENTERPRISE);
        //db.execSQL(Note.SQL_CREATE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ( newVersion > oldVersion ){
        }
    }

}
