package ro.mta.serverstatemonitor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) { super(context, DBContract.DB_NAME, null, DBContract.DB_VER); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.DevicesEntry.DEVS_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(DBContract.DevicesEntry.DEVS_DROP_TABLE);
        onCreate(db);
    }

}
