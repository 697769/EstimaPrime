/*
 * Copyright EstimaPrime(c) 2016 - By Victor Sodré 528803
 */

package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import modelo.Note;

public class NoteDAO extends DBHelper {

    public static final String TABLE_NAME = "note";
    public static final String COLUMN_NAME_ID_NOTE = "id_note";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_VALOR = "valor";
    public static final String COLUMN_NAME_TIPO = "tipo";
    public static final String COLUMN_NAME_ID_ENTERPRISE = "id_enterprise";

    public static final String SQL_CREATE_NOTE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + COLUMN_NAME_ID_NOTE + " integer PRIMARY KEY autoincrement NOT NULL, "
                    + COLUMN_NAME_DATE + " text DEFAULT(DATE('YYYY-MM-DD')), "
                    + COLUMN_NAME_VALOR + " double NOT NULL, "
                    + COLUMN_NAME_TIPO + " integer NOT NULL, "
                    + COLUMN_NAME_ID_ENTERPRISE + " INTEGER NOT NULL,"
                    + "FOREIGN KEY (" + COLUMN_NAME_ID_ENTERPRISE + ") "
                    + "REFERENCES enterprise("+ COLUMN_NAME_ID_ENTERPRISE +"));";

    private SQLiteDatabase db;

    public NoteDAO(Context context) {
        super(context);
        db = getWritableDatabase();
    }

    public boolean insertNote(Note note){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_VALOR, note.getValor());
        contentValues.put(COLUMN_NAME_TIPO, note.getTipo());
        contentValues.put(COLUMN_NAME_ID_ENTERPRISE, note.getId_enterprise());
        return (db.insert(TABLE_NAME, null, contentValues) > 0);
    }
    public Double getEntradas(int CodigoDaEmpresa){
        double totalEntradas=0.0;
        String sql = "SELECT SUM("+COLUMN_NAME_VALOR+") FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ID_ENTERPRISE+" = "+CodigoDaEmpresa+" AND "+COLUMN_NAME_TIPO+" = 1;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                totalEntradas = cursor.getDouble(0);
                return totalEntradas;
            }while(cursor.moveToNext());
        }
        return totalEntradas;
    }
    public Double getSaidas(int CodigoDaEmpresa){
        double totalSaidas=0.0;
        String sql = "SELECT SUM("+COLUMN_NAME_VALOR+") FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ID_ENTERPRISE+" = "+CodigoDaEmpresa+" AND "+COLUMN_NAME_TIPO+" = 2;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                totalSaidas = cursor.getDouble(0);
                return totalSaidas;
            }while(cursor.moveToNext());
        }
        return totalSaidas;
    }
}
