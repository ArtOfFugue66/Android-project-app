package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

// TODO: add Toast object events in all activites except Main
public class MainActivity extends AppCompatActivity {
    Button todo_btn;
    Button stats_btn;
    Button devs_btn;
    DBHelper dbHelper;

    public MainActivity() {
        todo_btn = null;
        stats_btn = null;
        devs_btn = null;
        dbHelper = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo_btn = (Button)findViewById(R.id.todo_btn_main);
        stats_btn = (Button)findViewById(R.id.stats_btn_main);
        devs_btn = (Button)findViewById(R.id.devices_btn_main);

        /* populate Devices table */

        DBManager dbManager = new DBManager(MainActivity.this);
        // open database for writing
        dbManager.open(DBContract.DB_WRITE_MODE);
        // insert base entries in Devices table
        if (dbManager.dbGetNrRows(DBContract.DevicesEntry.TABLE_NAME) < 1) {
            dbManager.dbInsert("Dev1", "Cisco", "10.10.10.10", "Bucharest, Unirii St.", "Server");
            dbManager.dbInsert("Dev2", "Lenovo", "10.10.20.20", "Cluj, Piezisa St.", "PC");
            dbManager.dbInsert("Dev3", "Red Hat", "81.146.22.157", "Pitesti, Cartier Trivale", "Datacenter");
        }
        // close database
        dbManager.close();

        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todo_intent = new Intent(MainActivity.this, ToDoActivity.class);
                startActivity(todo_intent);
            }
        });

        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stats_intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(stats_intent);
            }
        });

        devs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent devs_intent = new Intent(MainActivity.this, DevsActivity.class);
//                devs_intent.putExtra("sql_db", dbHelper);
                startActivity(devs_intent);
            }
        });

    }
}













