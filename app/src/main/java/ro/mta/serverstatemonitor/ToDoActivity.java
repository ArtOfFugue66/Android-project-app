package ro.mta.serverstatemonitor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Activity which lets user add and delete tasks
 */
public class ToDoActivity extends AppCompatActivity {
    // DevsActivity navigation button
    Button devs_btn = null;
    // names of tasks
    public static ArrayList<String> taskNames;
    // tasks array adapter
    public static ArrayAdapter<String> tasksAdapter;
    // preferences handle used to save tasks
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        Toast toast = Toast.makeText(ToDoActivity.this, "Add and view tasks\nLong press task to remove", Toast.LENGTH_LONG);
        toast.show();

        // read tasks from SharedPreferences file and populate list
        taskNames = readTasksFromStorage("Tasks");
        if (taskNames == null)
            taskNames = new ArrayList<String>();

        tasksAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                taskNames);

        final ListView tasksListView = findViewById(R.id.todo_listview);
        tasksListView.setAdapter(tasksAdapter);

        // go to devices activity on button press
        devs_btn = (Button) findViewById(R.id.devs_btn_to_do);
        devs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent devs_intent = new Intent(ToDoActivity.this, DevsActivity.class);
                startActivity(devs_intent);
            }
        });

        // remove task from list on item long press
        tasksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String taskname = taskNames.get(position);
                AlertDialog alertDialog = new AlertDialog.Builder(ToDoActivity.this)
                        .setTitle("Delete task?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                taskNames.remove(taskname);
                                tasksAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method which stores tasks in shared preference file
     * when activity moves into Paused state
     */
    @Override
    public void onPause() {
        storeTasks(taskNames, "Tasks");
        super.onPause();
    }

    /**
     * Method which shows dialog and adds task
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(ToDoActivity.this);
                AlertDialog alertDialog = new AlertDialog.Builder(ToDoActivity.this)
                        .setTitle("Add task")
                        .setMessage("Enter the name of the task")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String taskName = String.valueOf(taskEditText.getText());
                                if (taskNames != null) {
                                    taskNames.add(taskName); // add task name to List
                                } else {
                                    taskNames = new ArrayList<String>();
                                    taskNames.add(taskName);
                                }
                                tasksAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Method which saves task names list content in shared preference file
     */
    public void storeTasks(ArrayList<String> list, String key) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ToDoActivity.this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = prefs.edit();
        // serialize list contents
        String jsonTasks = new Gson().toJson(taskNames);
        // save in shared preferences as HashSet
        editor.putString(key, jsonTasks);
        editor.apply(); // !
    }

    /**
     * Method which retrieves task names from shared preference file
     */
    public ArrayList<String> readTasksFromStorage(String key) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ToDoActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
