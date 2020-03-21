package ro.mta.serverstatemonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import ro.mta.serverstatemonitor.R;

public class DevsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);

        final Button home_btn = findViewById(R.id.home_btn_devs);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start ToDoActivity from MainActivity
                startActivity(new Intent(DevsActivity.this, MainActivity.class));
            }
        });
    }
}
