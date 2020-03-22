package ro.mta.serverstatemonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {
    private DBHelper dbHelper;

    private Context ctx;

    private SQLiteDatabase db;

    public DBManager(Context ctx) {
        this.ctx = ctx;
    }

    public DBManager open(int mode) throws SQLException {
        dbHelper = new DBHelper(ctx);
        if (mode == 0) // read from database
            db = dbHelper.getReadableDatabase();
        else if (mode == 1) // write to database
            db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void dbInsert(String name, String brand, String ip, String location, String type) {
        // return if device with given name already exists in the database
        if (dbGetItemData(name).getCount() > 0) { return; }
        ContentValues dbEntryVals = new ContentValues();
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_NAME, name);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_BRAND, brand);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_IP, ip);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_LOCATION, location);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_TYPE, type);
        db.insert(DBContract.DevicesEntry.TABLE_NAME, null, dbEntryVals);
    }

    public Cursor dbGetAllData() {
        String[] cols = new String[] {
                DBContract.DevicesEntry._ID, DBContract.DevicesEntry.COLUMN_NAME, DBContract.DevicesEntry.COLUMN_BRAND,
                DBContract.DevicesEntry.COLUMN_IP, DBContract.DevicesEntry.COLUMN_LOCATION, DBContract.DevicesEntry.COLUMN_TYPE };
        Cursor cursor = db.query(DBContract.DevicesEntry.TABLE_NAME, cols, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor dbGetItemData(String name) {
        String[] cols = new String[] {
                DBContract.DevicesEntry._ID, DBContract.DevicesEntry.COLUMN_NAME, DBContract.DevicesEntry.COLUMN_BRAND,
                DBContract.DevicesEntry.COLUMN_IP, DBContract.DevicesEntry.COLUMN_LOCATION, DBContract.DevicesEntry.COLUMN_TYPE };
        Cursor cursor = db.query(DBContract.DevicesEntry.TABLE_NAME, cols, "name = ?", new String[]{name}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public int dbUpdateData(long _id, String name, String brand, String ip, String location, String type) {
        ContentValues dbEntryVals = new ContentValues();
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_NAME, name);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_BRAND, brand);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_IP, ip);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_LOCATION, location);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_TYPE, type);
        return db.update(DBContract.DevicesEntry.TABLE_NAME, dbEntryVals, DBContract.DevicesEntry._ID + "=" + _id, null);
    }

    public void dbDeleteEntry(String name) {
        db.delete(DBContract.DevicesEntry.TABLE_NAME, DBContract.DevicesEntry.COLUMN_NAME + "=" + name, null);
    }

    public long dbGetNrRows(String table_name) {
        long count = DatabaseUtils.queryNumEntries(db, table_name);
        return count;
    }

    public void dbDropTable(String table_name) {
        if (table_name.equals(DBContract.DevicesEntry.TABLE_NAME))
            db.execSQL(DBContract.DevicesEntry.DEVS_DROP_TABLE);
        else if (table_name.equals(DBContract.TasksEntry.TABLE_NAME)) {
            db.execSQL(DBContract.TasksEntry.TASKS_DROP_TABLE);
        }
    }

    public void dbClearTable(String table_name) {
        if (table_name.equals(DBContract.DevicesEntry.TABLE_NAME)) {
            db.execSQL(DBContract.DevicesEntry.DEVS_CLEAR_TABLE);
        } else if (table_name.equals(DBContract.TasksEntry.TABLE_NAME)) {
            db.execSQL(DBContract.TasksEntry.TASKS_CLEAR_TABLE);
        }
    }
}
