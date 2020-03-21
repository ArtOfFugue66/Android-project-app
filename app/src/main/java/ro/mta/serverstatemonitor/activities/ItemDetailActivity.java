package ro.mta.serverstatemonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ro.mta.serverstatemonitor.R;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        final Button devs_btn = findViewById(R.id.devs_btn_item_detail);
        devs_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start ToDoActivity from MainActivity
                startActivity(new Intent(ItemDetailActivity.this, DevsActivity.class));
            }
        });
    }

}
