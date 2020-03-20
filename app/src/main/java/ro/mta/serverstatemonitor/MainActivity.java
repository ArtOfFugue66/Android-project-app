package ro.mta.serverstatemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

// TODO: get data of dummy objects
// TODO: display object names in ListView
// TODO: create onClickListener() for every item in the ListView
// TODO: make item onClick() method which launches a pop-up with detailed object data
public class MainActivity extends AppCompatActivity {
    private PortServiceDict portsServicesDict;
    private List<MockServer> serverList = null;

    public MainActivity() {
        portsServicesDict = PortServiceDict.getInstance();
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

        List<Integer> portList1 = null; portList1.add(22); portList1.add(23); portList1.add(25); portList1.add(80);
        serverList.add(new MockServer(portList1));
        List<Integer> portList2 = null; portList2.add(22); portList2.add(23); portList2.add(37); portList2.add(53);
        serverList.add(new MockServer(portList2));
    }
}
