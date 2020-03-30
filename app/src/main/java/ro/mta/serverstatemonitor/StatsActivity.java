package ro.mta.serverstatemonitor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity which displays graphs based on program data
 */
public class StatsActivity extends AppCompatActivity {
    // MainActivity navigation button
    Button home_btn = null;
    // DevsActivity navigation button
    Button devs_btn = null;
    // list of device types for plot
    List<String> devTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // show activity description
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(StatsActivity.this, "View devices statistics", Toast.LENGTH_SHORT);
        toast.show();

        // go to main activity on button press
        home_btn = (Button)findViewById(R.id.home_btn_stats);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home_intent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(home_intent);
            }
        });

        // go to devices activity on button press
        devs_btn = (Button) findViewById(R.id.devs_btn_stats);
        devs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent devs_intent = new Intent(StatsActivity.this, DevsActivity.class);
                startActivity(devs_intent);
            }
        });

        DBManager dbManager = new DBManager(StatsActivity.this);
        // open database for reading
        dbManager.open(DBContract.DB_READ_MODE);
        try (Cursor cursor = dbManager.devGetAllData()) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                // get type from all devices in the database
                do {
                    devTypes.add(cursor.getString(cursor.getColumnIndex(DBContract.DevicesEntry.COLUMN_TYPE)));
                } while (cursor.moveToNext());
            }
        }
        // close database
        dbManager.close();

        // create bar chart
        makeBarChart(devTypes);
    }

    /**
     * Method which creates a bar chart with animation using
     * https://github.com/PhilJay/MPAndroidChart
     */
    private void makeBarChart(List<String> devTypes) {
        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        BarData barData = new BarData(getXAxisValues(), getDataSets(devTypes));
        barChart.setData(barData);
        barChart.setDescription("");
        barChart.animateXY(2000,2000);
        barChart.invalidate();
    }

    /**
     * Method which counts each device type in the list
     */
    private ArrayList<BarDataSet> getDataSets(List<String> devTypes) {
        int countPC = 0, countServer = 0, countDatacenter = 0;

        // count how many PCs, how many Servers and how many Datacenters
        for (int i = 0; i < devTypes.size(); i ++) {
            if (devTypes.get(i).contains("PC")) {
                countPC++;
            } else if (devTypes.get(i).contains("Server")) {
                countServer++;
            } else if (devTypes.get(i).contains("Datacenter")) {
                countDatacenter++;
            }
        }

        /* create bar chart dataset objects */
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry vs1entry = new BarEntry(countPC, 0);
        valueSet1.add(vs1entry);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry vs2entry = new BarEntry(countServer, 1);
        valueSet2.add(vs2entry);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry vs3entry = new BarEntry(countDatacenter, 2);
        valueSet3.add(vs3entry);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "PCs");
        barDataSet1.setColor(Color.rgb(155, 0, 0));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Servers");
        barDataSet2.setColor(Color.rgb(0,  155, 0));

        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Datacenters");
        barDataSet3.setColor(Color.rgb(0, 0,155));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        return dataSets;
    }

    /**
     * Method which returns top graph labels for the X axis
     */
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("");
        xAxis.add("");
        return xAxis;
    }
}
