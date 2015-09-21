package com.arthtracker.arthtracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphActivity extends ActionBarActivity implements OnChartValueSelectedListener {
    private Toolbar toolbar;
    private LineChart mChart;
    private List<PainDay> mPainDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChart = (LineChart) findViewById(R.id.chart);

        SQLiteHelper sqlHelper = new SQLiteHelper(this);
        mPainDays = sqlHelper.getAllPainDays();

        //Populate Chart
        setData(mPainDays.size(), 100, mPainDays);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        mChart.setDescription("Pain Over Time");
        mChart.setScaleEnabled(true);
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setVisibleXRange(7,10);
        //Hide legend
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

        mChart.setOnChartValueSelectedListener(this);

        mChart.setBackgroundColor(Color.WHITE);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar summary_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.graph){
            startActivity(new Intent(this,GraphActivity.class));
        }

        if (id ==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setData(int count, float range, List<PainDay> painDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/''yy");

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(sdf.format(new Date(painDays.get(i).getmDate() * 1000)));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            yVals.add(new Entry((float)painDays.get(i).getmTotal(), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "");

        set1.setColor(getResources().getColor(R.color.primaryColor));
        set1.setCircleColor(getResources().getColor(R.color.accentColor));
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setFillColor(getResources().getColor(R.color.accentColor));
        set1.setHighLightColor(getResources().getColor(R.color.accentColor));
        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
