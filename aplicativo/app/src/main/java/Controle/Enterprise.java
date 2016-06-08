package Controle; // Package
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Enterprise extends DBHelper {

    public static final String TABLE_NAME = "enterprise";
    public static final String COLUMN_NAME_ID_ENTERPRISE = "id_enterprise";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_ID_USER = "id_user";
    public static final String SQL_CREATE_ENTERPRISE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + COLUMN_NAME_ID_ENTERPRISE + " INTEGER PRIMARY KEY autoincrement NOT NULL, "
                    + COLUMN_NAME_NAME + " text NOT NULL, "
                    + COLUMN_NAME_ID_USER + " INTEGER,"
                    + "FOREIGN KEY (" + COLUMN_NAME_ID_USER + ") REFERENCES user("+ COLUMN_NAME_ID_USER +")";

    private SQLiteDatabase db;

    public Enterprise(Context context) {
        super(context);
        db = getWritableDatabase();
    }

//    private DBHelper DAO_ENTERPRISE = null; //instanciação do Objeto DBHelper(funções com banco de dados SQLite)
//    // TODO: modify:05/04/2016 = CONSTRUCTOR's
//    public Enterprise(Context context){
//        if (DAO_ENTERPRISE == null){
//            DAO_ENTERPRISE = new DBHelper(context);
//        }
//    }
//    // TODO: modify:05/04/2016 = ENTERPRISE_INSERT's
//    public void addEnterprise(String new_name, int new_id_login){
//        String sql = "INSERT INTO enterprise(name,id_user) VALUES ('"+new_name.trim()+"',"+new_id_login+");";
//        SQLiteDatabase db = DAO_ENTERPRISE.getWritableDatabase();
//        db.execSQL(sql);
//    }
//    // TODO: modify:05/04/2016 = ENTERPRISE_DELETE's
//    public void deleteAllEnterprises(){
//        String sql = "DELETE FROM enterprise";
//        SQLiteDatabase db = DAO_ENTERPRISE.getWritableDatabase();
//        db.execSQL(sql);
//        String sql2 = "DELETE FROM sqlite_sequence WHERE name='enterprise';";
//        db = DAO_ENTERPRISE.getWritableDatabase();
//        db.execSQL(sql2);
//    }
//    // TODO: modify:05/04/2016 = ENTERPRISE_SELECT's
//    public int getIdEnterprise(int enteprise){
//        int idEnterprise=0;
//        String sql = "SELECT id_enterprise,name,id_user FROM enterprise WHERE id_enterprise = '"+enteprise+"';";
//        int e=0;
//        SQLiteDatabase db = DAO_ENTERPRISE.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor.moveToFirst()){
//            do{
//                e = cursor.getInt(0);
//                if (e == enteprise){ //empresa encontrada
//                    idEnterprise= cursor.getInt(0); //id como primeiro campo da consulta
//                    return idEnterprise;
//                }
//            }while(cursor.moveToNext());
//        }
//        return idEnterprise;
//    }
//    // TODO: modify:05/04/2016 = ENTERPRISE_ARRAY's
//    public ArrayList<String> getAllEnterprises(){
//        String sql = "SELECT * FROM enterprise;";
//        SQLiteDatabase db = DAO_ENTERPRISE.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        ArrayList<String> enterprises = null;
//
//        if (cursor != null && cursor.moveToFirst()){
//            enterprises = new ArrayList<String>();
//            do{
//                enterprises.add(cursor.getString(1));
//            }while (cursor.moveToNext());
//        }
//        return enterprises;
//    }
//    public ArrayList<String> getUserEnterprises(int user){
//        String sql = "SELECT id_enterprise,name,id_user FROM enterprise WHERE id_user = "+user+";";
//        SQLiteDatabase db = DAO_ENTERPRISE.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        ArrayList<String> enterprises = new ArrayList<String>();
//        if (cursor != null && cursor.moveToFirst()){
//            do{
//                enterprises.add(cursor.getString(0)+" - "+cursor.getString(1));
//            }while (cursor.moveToNext());
//        }
//        return enterprises;
//    }
}
