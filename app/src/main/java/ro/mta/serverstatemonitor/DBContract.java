package ro.mta.serverstatemonitor;

import android.provider.BaseColumns;

/**
 * Definitions class
 */
public class DBContract {
    // database version and name
    public static final int DB_VER = 1;
    public static final String DB_NAME = "SSM.db";
    public static final int DB_READ_MODE = 0;
    public static final int DB_WRITE_MODE = 1;

    /**
     * Devices table definitions class
     */
    static class DevicesEntry implements BaseColumns {
        // table name
        public static final String TABLE_NAME = "devices";
        // table columns
        public static final String COLUMN_NAME = "name"; // device name
        // device id _ID (Primary Key) is inherited from BaseColumns interface
        public static final String COLUMN_BRAND = "brand"; // e.g., Cisco, Lenovo
        public static final String COLUMN_IP = "ip"; // device public or private IPv4 address
        public static final String COLUMN_LOCATION = "location"; // e.g., Bucharest, Unirii St.
        public static final String COLUMN_TYPE = "type"; // e.g., server or computer

        // create table query
        public static final String DEVS_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_BRAND + " TEXT," +
                        COLUMN_IP + " TEXT," +
                        COLUMN_LOCATION + " TEXT," +
                        COLUMN_TYPE + " TEXT)";

        // clear whole table query
        public static final String DEVS_CLEAR_TABLE =
                "DELETE FROM " + TABLE_NAME;

        // drop table query
        public static final String DEVS_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    /**
     * TODO: Tasks table definition class
     */
    static class TasksEntry implements BaseColumns {
        // table name
        public static final String TABLE_NAME = "tasks";

        // table columns

        // create table query

        // clear whole table query
        public static final String TASKS_CLEAR_TABLE =
                "DELETE FROM " + TABLE_NAME;

        // drop table query
        public static final String TASKS_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
