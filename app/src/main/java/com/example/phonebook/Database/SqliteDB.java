package com.example.phonebook.Database;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phonebook.Contact;

import java.util.ArrayList;

public class SqliteDB extends SQLiteOpenHelper {

    public static final String DB_name="my_sqlite.db";
    public static final int DB_version=1;

    Context context;
//    public static final String table_user="my_user";
//    public static final String ID="id";
//    public static final String NAME="name";
//    public static final String MOBILE="mobile";
//    public static final String PICTURE="picture";

    public SqliteDB(Context context) {
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String query="CREATE TABLE IF NOT EXISTS "+ table_user +" ( " +
//                ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                NAME + " TEXT NOT NULL , " +
//                MOBILE + " TEXT )";
//        //ADD IMG
        String query = "CREATE TABLE Contacts (cID INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , phone VARCHAR , image INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String query= "DROP TABLE IF EXISTS " + table_user;
//        db.execSQL(query);
//
//        onCreate(db);
        String contacts = "DROP TABLE IF EXISTS Contacts";
        db.execSQL(contacts);

    }

    public void add_Contact(String namee,String phonee){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",namee );
        cv.put("phone",phonee );
        db.insert("Contacts",null,cv);
        db.close();

    }
    public Cursor show_all_data(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = " SELECT * FROM Contacts";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }

    public void update_data(String nam ,String ph,int pos){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",nam);
        values.put("phone",ph);
        database.update("Contacts",values,"cID=" + pos,null);
    }

    public ArrayList<Contact> peaple(){
        String query;
        query = "SELECT * FROM Contacts";

        ArrayList<Contact> linkedList = new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        Contact contact;

        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();

                contact.setId(cursor.getInt(cursor.getColumnIndex("cID")));
                contact.setTit(cursor.getString(cursor.getColumnIndex("name")));
                contact.setPh(cursor.getString(cursor.getColumnIndex("phone")));
                linkedList.add(contact);
            } while (cursor.moveToNext());
        }
        return linkedList;

    }

    public boolean deleteCourse (int pos){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from Contacts where cID ="+ pos);
//        database.rawQuery("delete name from Contacts where cID="+pos,null);
        return true;
    }


//    public boolean delet_item(int pos){
//        SQLiteDatabase database = this.getWritableDatabase();
////        String query = "delete name from Contacts where cID =" + pos;
////        Cursor cursor = database.rawQuery(query,null);
//        long result = database.delete("Contacts","cID="+ pos,null );
//        if(result == 0)
//            return false;
//        else
//            return true;
//    }

}
