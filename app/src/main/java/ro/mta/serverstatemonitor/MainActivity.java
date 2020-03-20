package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

// TODO: get data of dummy objects
// TODO: display object names in ListView
// TODO: create onClickListener() for every item in the ListView
// TODO: make item onClick() method which launches a pop-up with detailed object data
public class MainActivity extends AppCompatActivity {

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
    }
}
