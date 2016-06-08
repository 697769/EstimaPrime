package Controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import Modelo.UserM;

public class User extends DBHelper {

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_ID_USER = "id_user";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    public static final String SQL_CREATE_USER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + COLUMN_NAME_ID_USER + " INTEGER PRIMARY KEY autoincrement NOT NULL, "
                    + COLUMN_NAME_EMAIL + " text NOT NULL, "
                    + COLUMN_NAME_PASSWORD + " text NOT NULL)";

    private SQLiteDatabase db;

    public User(Context context) {
        super(context);
        db = getWritableDatabase();
    }
    public static void insertDefaultUser(SQLiteDatabase db) {
        UserM user = new UserM();
        user.setEmail("admin@admin");
        user.setPassword("123");

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_EMAIL, user.getEmail());
        contentValues.put(COLUMN_NAME_PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, contentValues);
    }
    public boolean insertUser(UserM user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_EMAIL, user.getEmail());
        contentValues.put(COLUMN_NAME_PASSWORD, user.getPassword());
        return (db.insert(TABLE_NAME, null, contentValues) > 0);
    }
    public boolean verifyEmail(String email) {
        String query = "SELECT 1 FROM user WHERE email=?";
        Cursor c = db.rawQuery(query, new String[]{email.trim()});
        if (c.moveToFirst()) return false;
        return true;
    }
    public boolean checkLogin(String email, String senha) {
        String query = "SELECT 1 FROM user WHERE email=? and password=?";
        Cursor c = db.rawQuery(query, new String[]{email.trim(), senha.trim()});
        if (c.moveToFirst()) return true;
        return false;
    }
//    private DBHelper DAO_USER = null;
//    // TODO: modify:05/04/2016 = CONSTRUCTOR's
//    public User(Context context){
//        if (DAO_USER == null){
//            DAO_USER = new DBHelper(context);
//        }
//    }
//    // TODO: modify:01/05/2016 = USER_INSERT's
//    public void addUser(String new_email, String new_password){
//        String sql = "INSERT INTO user(email,password) VALUES ('"+new_email.trim()+"','"+new_password.trim()+"');";
//        SQLiteDatabase db = DAO_USER.getWritableDatabase();
//        db.execSQL(sql);
//    }
//
//    // TODO: modify:01/05/2016 = USER_SELECT's
    public int getIdUser(String email){
            int idUser=0;
            String e,sql = "SELECT id_user,email FROM user u WHERE u.email = '"+email.trim()+"';";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()){
                do{
                    e = cursor.getString(1);
                    if (e.equals(email)){
                        idUser = cursor.getInt(0);
                        return idUser;
                    }
                }while(cursor.moveToNext());
            }
            return idUser;
        }
//    public Boolean getEmail(String email){
//        String e,sql = "SELECT email FROM user u WHERE u.email='"+email.trim()+"';";
//        SQLiteDatabase db = DAO_USER.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor.moveToFirst()){
//            do{
//                e = cursor.getString(0);
//                if (e.equals(email)){
//                    return true;
//                }
//            }while(cursor.moveToNext());
//        }
//        return false;
//    }
//    public String getPassword(String email){
//        String sql = "SELECT email,password FROM user u WHERE u.email='"+email.trim()+"';";
//        SQLiteDatabase db = DAO_USER.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        String e="",p="Not found";
//        if (cursor.moveToFirst()){
//            do{
//                e = cursor.getString(0);
//                if (e.equals(email)){
//                    p = cursor.getString(1);
//                    break;
//                }
//            }while(cursor.moveToNext());
//        }
//        return p;
//    }
//    // TODO: modify:01/05/2016 = USER_ARRAY's
//    public ArrayList<String> getAllLogins(){
//        String sql = "SELECT * FROM user;";
//        SQLiteDatabase db = DAO_USER.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        ArrayList<String> users = null;
//
//        if (cursor != null && cursor.moveToFirst()){
//            users = new ArrayList<String>();
//            do{ users.add(cursor.getString(1)); }while (cursor.moveToNext());
//        }
//        return users;
//    }
//    // TODO: modify:01/05/2016 = USER_DELETE's
//    public void deleteAllUsers(){
//        String sql = "DELETE FROM user;";
//        SQLiteDatabase db = DAO_USER.getWritableDatabase();
//        db.execSQL(sql);
//        String sql2 = "DELETE FROM sqlite_sequence WHERE name='user';";
//        db = DAO_USER.getWritableDatabase();
//        db.execSQL(sql2);
//    }
}
