package com.arthtracker.arthtracker;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "ArthTracker";
    private static final String table_PAINDAY = "painday";
    private static final String painday_ID = "id";
    private static final String painday_DATE = "date";
    private static final String painday_FINGERS = "fingers";
    private static final String painday_THUMBS = "thumbs";
    private static final String painday_WRISTS = "wrists";
    private static final String painday_ELBOWS = "elbows";
    private static final String painday_SHOULDERS = "shoulders";
    private static final String painday_KNEES = "knees";
    private static final String painday_ANKLES = "ankles";
    private static final String painday_FATIGUE = "fatigue";
    private static final String painday_STIFFNESS = "stiffness";
    private static final String painday_OVERALL = "overall";
    private static final String painday_NOTES = "notes";

    private static final String[] COLUMNS = { painday_ID, painday_DATE, painday_FINGERS, painday_THUMBS, painday_WRISTS, painday_ELBOWS, painday_SHOULDERS, painday_KNEES, painday_ANKLES, painday_FATIGUE,painday_STIFFNESS, painday_OVERALL, painday_NOTES };

    public SQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create painday table
        String CREATE_PAINDAY_TABLE = "CREATE TABLE painday ( id INTEGER PRIMARY KEY AUTOINCREMENT, date LONG, fingers INTEGER, thumbs INTEGER, wrists INTEGER, elbows INTEGER, shoulders INTEGER, knees INTEGER, ankles INTEGER, fatigue INTEGER, stiffness INTEGER, overall INTEGER, notes TEXT)";
        db.execSQL(CREATE_PAINDAY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS painday");
        // drop table if already exists
        this.onCreate(db);
    }

    public boolean createPainday(PainDay painday) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // make values to be inserted
            ContentValues values = new ContentValues();
            values.put(painday_DATE, String.valueOf(painday.getmDate()));
            values.put(painday_FINGERS, String.valueOf(painday.getmFingers()));
            values.put(painday_THUMBS, String.valueOf(painday.getmThumbs()));
            values.put(painday_WRISTS, String.valueOf(painday.getmWrists()));
            values.put(painday_ELBOWS, String.valueOf(painday.getmElbows()));
            values.put(painday_SHOULDERS, String.valueOf(painday.getmShoulders()));
            values.put(painday_KNEES, String.valueOf(painday.getmKnees()));
            values.put(painday_ANKLES, String.valueOf(painday.getmAnkles()));
            values.put(painday_FATIGUE, String.valueOf(painday.getmFatigue()));
            values.put(painday_STIFFNESS, String.valueOf(painday.getmStiffness()));
            values.put(painday_OVERALL, String.valueOf(painday.getmOverall()));
            values.put(painday_NOTES, painday.getmNotes());

            db.insert(table_PAINDAY, null, values);

            // close database transaction
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public PainDay readPainDay(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_PAINDAY, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();

        PainDay painday = new PainDay();
        painday.setmID(cursor.getInt(0));
        painday.setmDate((cursor.getInt(1)));
        painday.setmFingers(cursor.getInt(2));
        painday.setmThumbs(cursor.getInt(3));
        painday.setmWrists(cursor.getInt(4));
        painday.setmElbows(cursor.getInt(5));
        painday.setmShoulders(cursor.getInt(6));
        painday.setmKnees(cursor.getInt(7));
        painday.setmAnkles(cursor.getInt(8));
        painday.setmFatigue(cursor.getInt(9));
        painday.setmStiffness(cursor.getInt(10));
        painday.setmOverall(cursor.getInt(11));
        painday.setmNotes(cursor.getString(12));

        return painday;
    }

    public List getAllPainDays() {
        List painDays = new LinkedList();

        String query = "SELECT  * FROM " + table_PAINDAY + " ORDER BY date DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        // parse all results
        PainDay painDay = null;
        if (cursor.moveToFirst()) {
            do {
                PainDay painday = new PainDay();
                painday.setmID(cursor.getInt(0));
                painday.setmDate(cursor.getInt(1));
                painday.setmFingers(cursor.getInt(2));
                painday.setmThumbs(cursor.getInt(3));
                painday.setmWrists(cursor.getInt(4));
                painday.setmElbows(cursor.getInt(5));
                painday.setmShoulders(cursor.getInt(6));
                painday.setmKnees(cursor.getInt(7));
                painday.setmAnkles(cursor.getInt(8));
                painday.setmFatigue(cursor.getInt(9));
                painday.setmStiffness(cursor.getInt(10));
                painday.setmOverall(cursor.getInt(11));
                painday.setmNotes(cursor.getString(12));                // Add book to books

                painDays.add(painday);
            } while (cursor.moveToNext());
        }
        return painDays;
    }

    public List getCurrentPainDays() {
        List painDays = new LinkedList();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -30);

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(table_PAINDAY, // a. table
                COLUMNS, " date > ? ORDER BY date DESC", new String[] { String.valueOf(c.getTimeInMillis()/1000) }, null, null, null, null);

        // parse all results
        PainDay painDay = null;
        if (cursor.moveToFirst()) {
            do {
                PainDay painday = new PainDay();
                painday.setmID(cursor.getInt(0));
                painday.setmDate(cursor.getInt(1));
                painday.setmFingers(cursor.getInt(2));
                painday.setmThumbs(cursor.getInt(3));
                painday.setmWrists(cursor.getInt(4));
                painday.setmElbows(cursor.getInt(5));
                painday.setmShoulders(cursor.getInt(6));
                painday.setmKnees(cursor.getInt(7));
                painday.setmAnkles(cursor.getInt(8));
                painday.setmFatigue(cursor.getInt(9));
                painday.setmStiffness(cursor.getInt(10));
                painday.setmOverall(cursor.getInt(11));
                painday.setmNotes(cursor.getString(12));                // Add book to books

                painDays.add(painday);
            } while (cursor.moveToNext());
        }
        return painDays;
    }

    public int updatePainDay(PainDay painDay) {

        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("id", painDay.getmID());
        values.put("date", painDay.getmDate());
        values.put("fingers", painDay.getmFingers());
        values.put("thumbs", painDay.getmThumbs());
        values.put("wrists", painDay.getmWrists());
        values.put("elbows", painDay.getmElbows());
        values.put("shouders", painDay.getmShoulders());
        values.put("knees", painDay.getmKnees());
        values.put("ankles", painDay.getmAnkles());
        values.put("fatigue", painDay.getmFatigue());
        values.put("stiffness", painDay.getmStiffness());
        values.put("overall", painDay.getmOverall());
        values.put("notes", painDay.getmNotes());

        // update
        int i = db.update(table_PAINDAY, values, painday_ID + " = ?", new String[] { String.valueOf(painDay.getmID()) });

        db.close();
        return i;
    }

    public void deleteBook(PainDay painday) {
        SQLiteDatabase db = this.getWritableDatabase();

        // delete painday
        db.delete(table_PAINDAY, painday_ID + " = ?", new String[] { String.valueOf(painday.getmID()) });
        db.close();
    }
}
