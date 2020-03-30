package ro.mta.serverstatemonitor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity where users add devices to the Database
 */
public class AddDeviceActivity extends AppCompatActivity {
    // "add device" button
    Button add_btn;
    // device type spinner
    Spinner spinner;
    // choice of user through the spinner object
    String spinnerChoice = "";
    // database manager object
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        // show activity description
        Toast toast = Toast.makeText(AddDeviceActivity.this, "Add new device", Toast.LENGTH_SHORT);
        toast.show();

        // get Spinner handle
        spinner = findViewById(R.id.dev_type_spinner);
        final List<String> devTypeOptions = new ArrayList<>();
        // add spinner options
        devTypeOptions.add("PC");
        devTypeOptions.add("Server");
        devTypeOptions.add("Datacenter");
        // associate array adapter with spinner object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddDeviceActivity.this,android.R.layout.simple_list_item_1,devTypeOptions);
        spinner.setAdapter(adapter);

        // on spinner item selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // populate user choice String variable
                spinnerChoice = devTypeOptions.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast toast = Toast.makeText(AddDeviceActivity.this, "Please provide device type", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        add_btn = (Button) findViewById(R.id.add_dev_btn);
        // on "add device" button clicked
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText devNameEditText = (EditText) findViewById(R.id.dev_name_editText);
                final EditText devBrandEditText = (EditText) findViewById(R.id.dev_brand_editText);
                final EditText devIpEditText = (EditText) findViewById(R.id.dev_ip_editText);
                final EditText devLocationEditText = (EditText) findViewById(R.id.dev_location_editText);

                // get input for device name
                String devName = String.valueOf(devNameEditText.getText());
                dbManager = new DBManager(AddDeviceActivity.this);
                dbManager.open(DBContract.DB_READ_MODE);
                Cursor cursor = dbManager.devGetItemData(devName);
                // if device with same name already exists in the database
                if (cursor.getCount() > 0) {
                    @SuppressLint("ShowToast")
                    Toast toast = Toast.makeText(AddDeviceActivity.this, "A device with this name already exists!", Toast.LENGTH_SHORT);
                    toast.show();
                    // close database and exit OnClick method
                    dbManager.close();
                    return;
                }
                dbManager.close();
                String devBrand = String.valueOf(devBrandEditText.getText());
                String devIp = String.valueOf(devIpEditText.getText());
                String devLocation = String.valueOf(devLocationEditText.getText());
                String devType = spinnerChoice;

                // if all fields are not empty
                if (devName.isEmpty() || devBrand.isEmpty() || devIp.isEmpty() || devLocation.isEmpty() || devType.isEmpty()) {
                    Toast toast = Toast.makeText(AddDeviceActivity.this, "Please provide all data", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    dbManager = new DBManager(AddDeviceActivity.this);
                    // open database for writing
                    dbManager.open(DBContract.DB_WRITE_MODE);
                    // insert device info into database
                    dbManager.devInsert(devName, devBrand, devIp, devLocation, devType);
                    // close database
                    dbManager.close();
                    // return to DevsActivity
                    Intent intent = new Intent(AddDeviceActivity.this, DevsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
