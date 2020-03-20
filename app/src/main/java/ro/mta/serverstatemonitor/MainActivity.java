package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import 

import java.util.Collections;
import java.util.List;

// TODO: get data of dummy objects
// TODO: display object names in ListView
// TODO: create onClickListener() for every item in the ListView
// TODO: make item onClick() method which launches a pop-up with detailed object data
public class MainActivity extends AppCompatActivity {
    private List<MockServer> serverList = null;

    public MainActivity() {
        serverList = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button todo_btn = findViewById(R.id.todo_btn);
        todo_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start activity which lets user edit a TO-DO list
                startActivity(new Intent(MainActivity.this, ToDoActivity.class));
            }
        });

        // TODO: populate ServerList variable
        ArrayAdapter defaultAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Collections.singletonList("Test element"));
        ListView lv = findViewById(R.id.devices_listview);
        lv.setAdapter(defaultAdapter);
        // TODO: populate ListView object with data from ServerList variable
    }
}
