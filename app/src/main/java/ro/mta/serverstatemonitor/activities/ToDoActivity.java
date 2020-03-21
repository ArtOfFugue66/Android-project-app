package ro.mta.serverstatemonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import ro.mta.serverstatemonitor.R;

public class ToDoActivity extends AppCompatActivity {
    Button devs_btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        devs_btn = (Button)findViewById(R.id.devs_btn_to_do);
        devs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent devs_intent = new Intent(ToDoActivity.this, DevsActivity.class);
                startActivity(devs_intent);
            }
        });
    }
}
