package Controle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Note extends DBHelper {

    private SQLiteDatabase db;

    public Note(Context context) {
        super(context);
        db = getWritableDatabase();
    }
}
