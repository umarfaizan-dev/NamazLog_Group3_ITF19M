import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.classactivity.Person;


public class DB {
    public class DBHandler extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "students.db";
        private static final String TABLE_NAME = "students";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_DATE = "date";
        private static final String COLUMN_NAMAZ = "namaz";
        private static final String COLUMN_JAMAT = "jamat";

        DBHandler(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_NAMAZ + " TEXT,"
                    +COLUMN_DATE + "TEXT,"
                    + COLUMN_JAMAT + " INTEGER"
                    + ")";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            onCreate(db);
        }
/*
        public void insertStudent(Person person) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, person.getName());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }*/

        public void updateStudent(Person person) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, person.getName());
            values.put(COLUMN_DATE,person.getDate());
            values.put(COLUMN_NAMAZ, person.getNamazCount().toString());
            values.put(COLUMN_JAMAT, person.getJamat().toString());

            db.update(TABLE_NAME, values, COLUMN_DATE + " = ?", new String[] {person.getDate()});
            db.close();
        }

        /*public void deleteStudent(String rollNo) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[] {rollNo});
            db.close();
        }*/


        public List<Person> selectAllRecords() {
            List<Person> person = new ArrayList<>();

            String sql = "SELECT * FROM " + TABLE_NAME;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String namaz = cursor.getString(cursor.getColumnIndex(COLUMN_NAMAZ));
                    boolean jamat = cursor.getInt(cursor.getColumnIndex(COLUMN_JAMAT));
                    String date = cursor.getInt(cursor.getColumnIndex(COLUMN_DATE));
                    person.add(new Person(name, date, namaz,jamat));
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return person;
        }
    }
}
