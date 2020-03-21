package ro.mta.serverstatemonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

import ro.mta.serverstatemonitor.MockServer;
import ro.mta.serverstatemonitor.R;

// TODO: get data of dummy objects from SQLite database
// TODO: display object names in ListView
// TODO: create onClickListener() for every item in the ListView
// TODO: make item onClick() method which launches a pop-up with detailed object data

public class MainActivity extends AppCompatActivity {
    private List<MockServer> serverList = null;
    Button todo_btn = null;
    Button stats_btn = null;
    Button devs_btn = null;

    public MainActivity() {
        serverList = null;
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
                startActivity(devs_intent);
            }
        });

        // TODO: populate ServerList variable

        // TODO: populate ListView object with data from ServerList variable
        // TODO: use CustomListViewAdapter adapter instead of ArrayAdapter
//        ArrayAdapter defaultAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_dropdown_item_1line,
//                Collections.singletonList("Test element"));
//        ListView lv = findViewById(R.id.devices_listview);
//        lv.setAdapter(defaultAdapter);
    }
}
