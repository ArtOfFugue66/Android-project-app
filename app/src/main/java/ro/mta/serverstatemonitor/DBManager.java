package ro.mta.serverstatemonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class implementing database operations
 */
public class DBManager {
    private DBHelper dbHelper;

    private Context ctx;

    private SQLiteDatabase db;

    public DBManager(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Open SQLite database for reading / writing
     */
    public void open(int mode) throws SQLException {
        dbHelper = new DBHelper(ctx);
        if (mode == 0) // read from database
            db = dbHelper.getReadableDatabase();
        else if (mode == 1) // write to database
            db = dbHelper.getWritableDatabase();
    }

    /**
     * Close SQLite database
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Insert entry into devices table
     */
    public void devInsert(String name, String brand, String ip, String location, String type) {
        // return if device with given name already exists in the database
        if (devGetItemData(name).getCount() > 0) { return; }
        ContentValues dbEntryVals = new ContentValues();
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_NAME, name);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_BRAND, brand);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_IP, ip);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_LOCATION, location);
        dbEntryVals.put(DBContract.DevicesEntry.COLUMN_TYPE, type);
        db.insert(DBContract.DevicesEntry.TABLE_NAME, null, dbEntryVals);
    }

    /**
     * Get all entries in the devices table
     */
    public Cursor devGetAllData() {
        String[] cols = new String[] {
                DBContract.DevicesEntry._ID, DBContract.DevicesEntry.COLUMN_NAME, DBContract.DevicesEntry.COLUMN_BRAND,
                DBContract.DevicesEntry.COLUMN_IP, DBContract.DevicesEntry.COLUMN_LOCATION, DBContract.DevicesEntry.COLUMN_TYPE };
        Cursor cursor = db.query(DBContract.DevicesEntry.TABLE_NAME, cols, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    /**
     * Get specific device data
     */
    public Cursor devGetItemData(String name) {
        String[] cols = new String[] {
                DBContract.DevicesEntry._ID, DBContract.DevicesEntry.COLUMN_NAME, DBContract.DevicesEntry.COLUMN_BRAND,
                DBContract.DevicesEntry.COLUMN_IP, DBContract.DevicesEntry.COLUMN_LOCATION, DBContract.DevicesEntry.COLUMN_TYPE };
        Cursor cursor = db.query(DBContract.DevicesEntry.TABLE_NAME, cols, "name = ?", new String[]{name}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    /**
     * Delete device from table
     */
    public void devDeleteEntry(String name) {
        Cursor entryCursor = devGetItemData(name);
        if (entryCursor != null && entryCursor.getCount() > 0)
            db.delete(DBContract.DevicesEntry.TABLE_NAME, DBContract.DevicesEntry.COLUMN_NAME + " = ?", new String[]{name});
    }
}
