package com.example.patri.piechart;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import android.widget.RadioButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private float[] yData = {35f, 29f, 19f, 14f, 22f};
    private String[] xData = {"Family", "Career", "Health", "Football", "Education"};
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.idPieChart);

       // pieChart.setDescription("");
        pieChart.setRotationEnabled(true);
       // pieChart.setScaleEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        //pieChart.setCenterText("QOL Areas");
        pieChart.setCenterTextSize(10);
        pieChart.setEntryLabelTextSize(40f);
        pieChart.setDrawEntryLabels(false);
        //pieChart.setBackgroundColor(Color.MAGENTA);
        addDataSet(pieChart);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

//                int pos1 = e.toString().indexOf("(sum): ");
//                String value = e.toString().substring(pos1 + 7);
//                for(int i=0; i<yData.length; i++) {
//                    if (yData[i] == Float.parseFloat(value)) {
//                        pos1 = i;
//                        break;
//                    }
//                }
//                String region = xData[pos1+1];
//                Toast.makeText(MainActivity.this, "Employee " + region + "\n" + "value " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet(PieChart chart) {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        for(int i=0; i<yData.length; i++)
            yEntries.add(new PieEntry(yData[i], xData[i]));

        for(int i=0; i<xData.length; i++)
            xEntries.add(xData[i]);

        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntries, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);
        pieDataSet.setFormSize(20f);

        //add colours to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getLegend().getCalculatedLineSizes();
        pieChart.invalidate();
    }

}