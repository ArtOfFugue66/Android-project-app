package ro.mta.serverstatemonitor;

import android.provider.BaseColumns;

/**
 * Definitions class to be used by the DBManager class methods
 */
class DBContract {
    // database version and name
    static final int DB_VER = 1;
    // name of database file
    static final String DB_NAME = "SSM.db";
    // constant - open database for reading
    static final int DB_READ_MODE = 0;
    // constant - open database for writing
    static final int DB_WRITE_MODE = 1;

    /**
     * Devices table definitions class
     */
    static class DevicesEntry implements BaseColumns {
        // table name
        static final String TABLE_NAME = "devices";
        // table columns
        static final String COLUMN_NAME = "name"; // device name
        // device id _ID (Primary Key) is inherited from BaseColumns interface
        static final String COLUMN_BRAND = "brand"; // e.g., Cisco, Lenovo
        static final String COLUMN_IP = "ip"; // device public or private IPv4 address
        static final String COLUMN_LOCATION = "location"; // e.g., Bucharest, Unirii St.
        static final String COLUMN_TYPE = "type"; // e.g., server or computer

        // query - create devices table
        static final String DEVS_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_BRAND + " TEXT," +
                        COLUMN_IP + " TEXT," +
                        COLUMN_LOCATION + " TEXT," +
                        COLUMN_TYPE + " TEXT)";

        // query - clear whole table
        static final String DEVS_CLEAR_TABLE =
                "DELETE FROM " + TABLE_NAME;

        // query - drop table
        static final String DEVS_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
