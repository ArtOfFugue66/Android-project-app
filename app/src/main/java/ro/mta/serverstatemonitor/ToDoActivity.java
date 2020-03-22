package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(ToDoActivity.this);
                AlertDialog alertDialog = new AlertDialog.Builder(ToDoActivity.this)
                        .setTitle("Add a task")
                        .setMessage("Enter the name of the task")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = String.valueOf(taskEditText.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
                // TODO: save user input in SharedPreference file / Database on "Add" button click
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
