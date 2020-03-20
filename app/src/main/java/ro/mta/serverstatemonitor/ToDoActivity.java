package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        Toast.makeText(ToDoActivity.this, "Add or edit tasks", Toast.LENGTH_SHORT).show();
    }
}
