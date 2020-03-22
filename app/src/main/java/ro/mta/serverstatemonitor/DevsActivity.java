package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

// TODO: make ListView scrollable
// TODO: add + button on title bar and display AlertDialog to let user add device
public class DevsActivity extends AppCompatActivity {
    Button home_btn = null;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);

        home_btn = (Button) findViewById(R.id.home_btn_devs);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start ToDoActivity from MainActivity
                startActivity(new Intent(DevsActivity.this, MainActivity.class));
            }
        });

        /* get object data from SQLite database */
        DBManager dbManager = new DBManager(DevsActivity.this);
        // open database for reading
        dbManager.open(DBContract.DB_READ_MODE);
        // read all data from Devices table
        try (Cursor cursor = dbManager.dbGetAllData()) {
            cursor.moveToFirst();
            List<Device> deviceList = new ArrayList<>();
            // iterate through Cursor object
            if (cursor.getCount() > 0) {
                do {
                    String[] deviceData = new String[]{
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_BRAND)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_IP)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_LOCATION)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_TYPE))
                    };
                    deviceList.add(new Device(deviceData[0], deviceData[1], deviceData[2], deviceData[3], deviceData[4], R.drawable.icons_server_80px));
                } while (cursor.moveToNext());
            }
            DevsListViewAdapter customAdapter = new DevsListViewAdapter(this, deviceList);
            final ListView listView = findViewById(R.id.devs_listview);
            listView.setAdapter(customAdapter);
        }
        // close database
        dbManager.close();
    }
}
