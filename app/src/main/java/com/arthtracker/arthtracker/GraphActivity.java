package com.arthtracker.arthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GraphActivity extends ActionBarActivity implements com.github.mikephil.charting.listener.OnChartGestureListener {
    private Toolbar toolbar;
    private LineChart mChart;

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
        List<PainDay> painDays = sqlHelper.getAllPainDays();
        //Reverse Order
        Collections.reverse(painDays);

        //Populate Chart
        setData(painDays.size(), 100, painDays);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        mChart.setDescription("Pain Over Time");
        mChart.setScaleEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setOnChartGestureListener(this);
        mChart.setPinchZoom(false);
        mChart.setHorizontalScrollBarEnabled(true);
        mChart.setVisibleXRange(7,7);
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
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, ''yy");

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(sdf.format(new Date(painDays.get(i).getmDate() * 1000)));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            yVals.add(new Entry((float)painDays.get(i).getmTotal(), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Date");

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.setColor(R.color.accentColor);
        set1.setCircleColor(R.color.accentColor);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        //set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(75);
        set1.setDrawFilled(true);
        //set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(), Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.d("Chart Touched", "Chart Touched");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.d("Chart Touched", "Chart Touched");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.d("Chart Touched", "Chart Touched");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        mChart.moveViewToX(me1.getX()+5);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.d("Chart Touched", "Chart Touched");
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}
