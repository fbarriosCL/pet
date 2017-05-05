package com.example.felipe.pet_project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.felipe.pet_project.model.Owner;
import com.example.felipe.pet_project.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 04-05-17.
 */

public class dbHelper extends SQLiteOpenHelper {
    // configuration database
    private static final String TAG = "DbHelper";
    private static final String DATABASE_NAME = "PetDevelopment";
    private static final int DATABASE_VERSION = 1;
    // table Names
    private static final String TABLE_PET = "pet";
    private static final String TABLE_OWNER = "owner";
    // attributes for owner
    public static final String COLUMN_RUT = "rut";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TELEPHONE = "telephone";
    // attributes for pets
    public static final String COLUMN_TYPE_PET = "type_pet";
    public static final String COLUMN_COLOR = "color_pet";
    public static final String COLUMN_RACE = "race_pet";
    public static final String COLUMN_NECKLACE = "necklace_pet";
    public static final String COLUMN_OWNER_ID = "owner_id";

    private String sqlTableOwner = "CREATE TABLE "+TABLE_OWNER+" ("+
            COLUMN_RUT+" text,"+
            COLUMN_NAME+" text, "+
            COLUMN_ADDRESS+"text"+
            COLUMN_TELEPHONE+" string );";

    private String sqlTablePet ="CREATE TABLE "+TABLE_PET+" ("+
            COLUMN_TYPE_PET+" text,"+
            COLUMN_COLOR+" text, "+
            COLUMN_RACE+ "text,"+
            COLUMN_OWNER_ID+" integer primary key autoincrement," +
            COLUMN_NECKLACE+" string );";


    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlTablePet);
        db.execSQL(sqlTableOwner);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PET);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNER);
            onCreate(db);
        }
    }
    private static dbHelper mDbHelper;

    public static synchronized dbHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.

        if (mDbHelper == null) {
            mDbHelper = new dbHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    private dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertOwner(Owner owner) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_RUT, owner.rut);
            values.put(COLUMN_NAME, owner.name);
            values.put(COLUMN_ADDRESS, owner.address);
            values.put(COLUMN_TELEPHONE, owner.telephone);

            db.insertOrThrow(TABLE_OWNER, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add pet to database");
        } finally {

            db.endTransaction();
        }
    }

    public void insertPet(Pet pet) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TYPE_PET, pet.type);
            values.put(COLUMN_COLOR, pet.color);
            values.put(COLUMN_RACE, pet.race);
            values.put(COLUMN_NECKLACE, pet.necklace);
            values.put(COLUMN_OWNER_ID, pet.owner_id);

            db.insertOrThrow(TABLE_OWNER, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add owner to database");
        } finally {

            db.endTransaction();
        }
    }

    public List<Pet> getAllPets() {

        List<Pet> pets = new ArrayList<>();

        String SELECT_PET = "SELECT * FROM " + TABLE_PET;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_PET, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Pet pet = new Pet();
                    pet.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_PET));
                    pet.color = cursor.getString(cursor.getColumnIndex(COLUMN_COLOR));
                    pet.race = cursor.getString(cursor.getColumnIndex(COLUMN_RACE));
                    pet.necklace = cursor.getString(cursor.getColumnIndex(COLUMN_NECKLACE));
                    pet.owner_id = cursor.getInt(cursor.getColumnIndex(COLUMN_OWNER_ID));
                    pets.add(pet);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get pets from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return pets;
    }

    public List<Owner> getAllOwners() {

        List<Owner> owners = new ArrayList<>();

        String SELECT_OWNER = "SELECT * FROM " + TABLE_OWNER;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_OWNER, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Owner owner = new Owner();
                    owner.address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    owner.rut = cursor.getString(cursor.getColumnIndex(COLUMN_RUT));
                    owner.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    owner.telephone = cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE));
                    owners.add(owner);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get owners from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return owners;
    }
}


