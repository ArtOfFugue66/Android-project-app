package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class StatsActivity extends AppCompatActivity {
    Button home_btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        home_btn = (Button)findViewById(R.id.home_btn_stats);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home_intent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(home_intent);
            }
        });
    }
}
