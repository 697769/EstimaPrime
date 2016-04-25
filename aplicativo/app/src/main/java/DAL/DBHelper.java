package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Victor on 18/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    // TODO: 18/03/2016 = DataBase Name
    private static String DATABASE_NAME="db2";
    // TODO: 18/03/2016 = System Version
    private static int VERSION = 1;

    // TODO: 18/03/2016 = DB_TABLE_CREATES
    private static String TABLE_USER =
            "CREATE TABLE user("+
            "id_user INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "email TEXT,"+
            "password TEXT"+
            ");";

    private static String TABLE_ENTERPRISE =
            "CREATE TABLE enterprise("+
            "id_enterprise INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name TEXT,"+
            "id_user INTEGER,"+
            "FOREIGN KEY (id_user) REFERENCES user(id_user)"+
            ");";

    private static String TABLE_USER_ENTERPRISE =
            "CREATE TABLE user_enterprise("+
            "id_user INTEGER,"+
            "id_enterprise INTEGER,"+
            "FOREIGN KEY (id_user) REFERENCES user(id_user),"+
            "FOREIGN KEY (id_enterprise) REFERENCES enterprise(id_enterprise)"+
            ");";

    private static String TABLE_NOTE =
            "CREATE TABLE note("+
            "id_note INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "data DATE NOT NULL,"+
            "valor DECIMAL NOT NULL,"+
            "tipo TINYINT NOT NULL,"+
            "id_enterprise INTEGER NOT NULL,"+
            "FOREIGN KEY(id_enterprise) REFERENCES enterprise(id_enterprise)"+
            ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER);
        db.execSQL(TABLE_ENTERPRISE);
        //db.execSQL(TABLE_USER_ENTERPRISE);
        //db.execSQL(TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ( newVersion > oldVersion ){
        }
    }
}
