package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Startup / home activity
 */
public class MainActivity extends AppCompatActivity {
    // ToDoActivity navigation button
    Button todo_btn;
    // StatsActivity navigation button
    Button stats_btn;
    // DevsActivity navigation button
    Button devs_btn;

    public MainActivity() {
        todo_btn = null;
        stats_btn = null;
        devs_btn = null;
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

    }
}













