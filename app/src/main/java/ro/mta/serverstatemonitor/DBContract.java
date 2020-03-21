package ro.mta.serverstatemonitor;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract() {}

    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "device";
        public static final String COLUMN_NAME_ID = "id"; // device id (Primary Key)
        public static final String COLUMN_NAME_NAME = "name"; // device name
        public static final String COLUMN_NAME_Brand = "brand"; // e.g., Cisco, Lenovo
        public static final String COLUMN_NAME_IP = "ip"; // device public or private IPv4 address
        public static final String COLUMN_NAME_location = "location"; // e.g., Bucharest, Unirii St.
        public static final String COLUMN_NAME_type = "type"; // e.g., server or computer
    }
}
