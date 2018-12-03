package vakili.ramin.civildictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vakili.ramin.civildictionary.entities.Word;

/**
 * Created by RaminV on 12/3/2018.
 */
public class DatabaseHelper2 extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION    = 1;
    private static final String DATABASE_NAME       = "dic.DB";

    private static final String DATABASE_DROP_TABLE = "DROP TABLE IF EXISTS words_table";


    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE  TABLE  IF NOT EXISTS words_table (id INTEGER NOT NULL, word TEXT, meaning TEXT" +
                ",voice TEXT,image TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            //db.execSQL(ALTER_TABLE);
        }
        db.execSQL(DATABASE_DROP_TABLE);
        onCreate(db);
    }

    public void insertEntry(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", 1);
        cv.put("word", word.getWord());
        cv.put("meaning", word.getMeaning());
        cv.put("voice", word.getVoice());
        cv.put("image", word.getImage());
        db.insert("words_table", null, cv);
        db.close();
    }


    /*
    public void addNote(NoteItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note_id", item.id);
        cv.put("note_text", item.text);
        cv.put("isreminder", "0");
        cv.put("year", "0");
        cv.put("month", "0");
        cv.put("day", "0");
        cv.put("hour", "0");
        cv.put("minute", "0");
        db.insert("note_table", null, cv);
        db.close();
    }


    public void saveNote(NoteItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE note_table set note_text='" + item.text + "' where note_id=" + item.id;
        db.execSQL(query);
        db.close();

    }


    public void setReminder(NoteItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE note_table set isreminder='1',year='" + item.reminderYear +
                "',month='" + item.reminderMonth + "',day='" + item.reminderday + "',hour='" +
                item.reminderHour + "',minute='" + item.reminderMinute + "' where note_id=" + item.id;

        Log.i("SET:" + item.reminderYear, "" + item.reminderMonth + "-" + item.reminderday);
        db.execSQL(query);
        db.close();

    }


    public int getIsReminder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select isreminder from note_table where note_id=" + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(cursor.getColumnIndex("isreminder")));
    }


    public void setIsReminderToDB(int id, int isReminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE note_table set isreminder='" + isReminder + "' where note_id=" + id;
        db.execSQL(query);
        db.close();

    }


    public void deleteNote(NoteItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete from note_table where note_id=" + item.id;
        db.execSQL(query);

    }


    public NoteItem getAnItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from note_table where note_id=" + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        NoteItem item = new NoteItem();
        item.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("note_id")));
        item.text = cursor.getString(cursor.getColumnIndex("note_text"));
        item.isReminder = Integer.parseInt(cursor.getString(cursor.getColumnIndex("isreminder")));
        item.reminderYear = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
        item.reminderMonth = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
        item.reminderday = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
        item.reminderHour = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hour")));
        item.reminderMinute = Integer.parseInt(cursor.getString(cursor.getColumnIndex("minute")));
        Log.i("GET:" + item.reminderYear, "" + item.reminderMonth + "-" + item.reminderday);
        cursor.close();
        return item;

    }


    public ArrayList<NoteItem> getNoteItems() {
        ArrayList<NoteItem> listItems = new ArrayList<NoteItem>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from note_table";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                NoteItem item = new NoteItem();
                item.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("note_id")));
                item.text = cursor.getString(cursor.getColumnIndex("note_text"));
                item.isReminder = Integer.parseInt(cursor.getString(cursor.getColumnIndex("isreminder")));
                if (item.isReminder == 1) {
                    item.reminderYear = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
                    item.reminderMonth = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
                    item.reminderday = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
                    item.reminderHour = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hour")));
                    item.reminderMinute = Integer.parseInt(cursor.getString(cursor.getColumnIndex("minute")));

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(item.reminderYear, item.reminderMonth, item.reminderday, item.reminderHour, item.reminderMinute);
                    //nega mikonim ke az time alan az time remider gozashte ta dg neshoonesh nadim
                    //vaghti ye obj jadid az Calender ijad mikoni timesh mishe currentTime
                    if (Calendar.getInstance().getTimeInMillis() > calendar.getTimeInMillis()) {
                        item.isReminder = 0;
                        setIsReminderToDB(item.id, 0);
                    }
                }

                listItems.add(item);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return listItems;

    }
    */

}
