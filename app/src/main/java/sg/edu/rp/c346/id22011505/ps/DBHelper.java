package sg.edu.rp.c346.id22011505.ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start version with 1
    // increment by 1 whenever db schema changes.

    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_TASK = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Title = "title";
    private static final String COLUMN_Singers = "singers";
    private static final String COLUMN_Year = "year";
    private static final String COLUMN_Star = "star";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_Title + " TEXT,"
                + COLUMN_Singers + " TEXT,"
                + COLUMN_Year + " Integer,"
                + COLUMN_Star + " Integer ) ";

        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        // Create table(s) again
        onCreate(db);

    }

    public void insertSong(String title, String singers, int year, int star) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_Title, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_Singers, singers);
        values.put(COLUMN_Year, year);
        values.put(COLUMN_Star, star);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_TASK, null, values);
        // Close the database connection
        db.close();

    }

    public ArrayList<Song> getTitle(boolean ascending) {
        ArrayList<Song> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM " + TABLE_TASK + " ORDER BY " + COLUMN_Title + " " + sortOrder;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, year, stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getSinger(boolean ascending) {
        ArrayList<Song> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM " + TABLE_TASK + " ORDER BY " + COLUMN_Singers + " " + sortOrder;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, year, stars);
                tasks.add(song);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }


    public ArrayList<String> getSongYear() {
        // Create an ArrayList that holds String objects
        ArrayList<String> Songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title, COLUMN_Singers, COLUMN_Year, COLUMN_Star};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            do {
                Songs.add(String.valueOf(cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return Songs;
    }





}
