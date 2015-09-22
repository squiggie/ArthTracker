package com.arthtracker.arthtracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.WeathericonsModule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity{
    private List<PainDay> painDays;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //replace actionBar with customer toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //set recyclerview for painday display
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();

        SummaryRVAdapter adapter = new SummaryRVAdapter(painDays, getApplicationContext());
        rv.setAdapter(adapter);

        //Icons
        Iconify.with(new WeathericonsModule());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
        }

        if (id == R.id.graph){
            startActivity(new Intent(this, GraphActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void startDay(View view){
        startActivity(new Intent(this, DayActivity.class));
    }

    private void initializeData(){
        SQLiteHelper sqlHelper = new SQLiteHelper(this);
        painDays = new ArrayList<>();
        painDays = sqlHelper.getAllPainDays();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}

