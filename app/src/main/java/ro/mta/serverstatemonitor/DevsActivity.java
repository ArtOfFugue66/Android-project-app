package ro.mta.serverstatemonitor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity where users can view the devices present in the database
 */
public class DevsActivity extends AppCompatActivity {
    // main activity navigation button
    Button home_btn;
    // statistics activity navigation button
    Button stats_btn;
    // list of devices to be displayed
    List<Device> deviceList;
    // custom ListView adapter
    DevsListViewAdapter customAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);

        // show activity description
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(DevsActivity.this, "Tap device to view location\nLong tap to delete", Toast.LENGTH_SHORT);
        toast.show();

        home_btn = (Button) findViewById(R.id.home_btn_devs);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start MainActivity from DevsActivity
                startActivity(new Intent(DevsActivity.this, MainActivity.class));
            }
        });

        stats_btn = (Button) findViewById(R.id.stats_btn_devs);
        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start StatsActivity from DevsActivity
                startActivity(new Intent(DevsActivity.this, StatsActivity.class));
            }
        });

        // fetch devices data from database
        deviceList = getDeviceList();
        if (deviceList == null)
            deviceList = new ArrayList<>();

        customAdapter = new DevsListViewAdapter(this, deviceList);
        final ListView listView = findViewById(R.id.devs_listview);
        listView.setAdapter(customAdapter);

        // on devices list item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get selected device
                Device selectedDevice = deviceList.get(position);
                if (selectedDevice != null) {
                    // get selected device's name and location
                    String selectedDevLocation = selectedDevice.getLocation();
                    // google maps query URI
                    Uri intentUri = Uri.parse("geo:0,0?q=" + selectedDevLocation);
                    // open google maps activity on device item click
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } else {
                    customAdapter.notifyDataSetChanged();
                }
            }
        });

        // Create "delete item?" dialog on item long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // get selected device
                final Device selectedDevice = deviceList.get(position);
                // get selected device name
                final String devName = selectedDevice.getName();
                AlertDialog alertDialog = new AlertDialog.Builder(DevsActivity.this)
                        .setTitle("Delete device?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBManager dbManager = new DBManager(DevsActivity.this);
                                // open database for writing
                                dbManager.open(DBContract.DB_WRITE_MODE);
                                // delete selected device
                                dbManager.devDeleteEntry(devName);
                                // close database
                                dbManager.close();
                                // reflect changes in ListView through use of the adapter
                                customAdapter.notifyDataSetChanged();
                                // reflect changes in List
                                deviceList.remove(selectedDevice);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_device:
                Intent createDevIntent = new Intent(DevsActivity.this, AddDeviceActivity.class);
                startActivity(createDevIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<Device> getDeviceList() {
        List<Device> devices = new ArrayList<>();
        // get object data from SQLite database
        DBManager dbManager = new DBManager(DevsActivity.this);
        // open database for reading
        dbManager.open(DBContract.DB_READ_MODE);

        // read all data from Devices table
        try (Cursor cursor = dbManager.devGetAllData()) {
            // move to first Cursor item
            cursor.moveToFirst();
            // iterate through non-empty Cursor object
            if (cursor.getCount() > 0) {
                do {
                    String[] deviceData = new String[]{
                            // get Cursor item (device) data
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_BRAND)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_IP)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_LOCATION)),
                            cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_TYPE))
                    };
                    devices.add(new Device(deviceData[0], deviceData[1], deviceData[2], deviceData[3], deviceData[4], R.drawable.icons_server_80px));
                } while (cursor.moveToNext());
            } else return null;
        }

        // close database
        dbManager.close();

        return devices;
    }

    @Override
    public void onResume() {
        customAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onPause() {
        customAdapter.notifyDataSetChanged();
        super.onPause();
    }
}
