package com.example.android.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.contactmanager.model.Contact;
import com.example.android.contactmanager.parameter.Parameters;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context){
        super(context, Parameters.DB_NAME,null,Parameters.DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + Parameters.TABLE_NAME + "("
                + Parameters.KEY_ID + " INTEGER PRIMARY KEY," + Parameters.KEY_NAME + " TEXT, "
                + Parameters.KEY_PHONE + " TEXT" + ")";
        Log.d("sachindb","Query is running: " + create);
        db.execSQL(create);
    }
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Parameters.KEY_NAME, contact.getName());
        values.put(Parameters.KEY_PHONE, contact.getPhoneNumber());

        db.insert(Parameters.TABLE_NAME,null,values);
        Log.d("sachindb","Successfully inserted to the database");
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generating query to read from database
        String select = "SELECT * FROM " + Parameters.TABLE_NAME+" ORDER BY " + Parameters.KEY_NAME + " ASC ";
        Cursor cursor = db.rawQuery(select,null);

        // Loop through all saved contacts
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return  contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Parameters.KEY_NAME, contact.getName());
        values.put(Parameters.KEY_PHONE, contact.getPhoneNumber());

        //Lets update now
        return db.update(Parameters.TABLE_NAME, values, Parameters.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});


    }

    public void deleteContact(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME,Parameters.KEY_NAME + "=?",new String[]{name});
        db.close();
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME,Parameters.KEY_ID + "=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactCount(){
        String query = "SELECT * FROM " + Parameters.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

}
