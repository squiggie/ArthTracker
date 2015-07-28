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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActionBarActivity{
    private Toolbar toolbar;
    private List<PainDay> painDays;
    private RecyclerView rv;
    private PainDay pain1;
    private PainDay pain2;
    private PainDay pain3;
    private PainDay pain4;
    private PainDay pain5;
    private PainDay pain6;
    private PainDay pain7;
    private PainDay pain8;
    private PainDay pain9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //replace actionBar with customer toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();

        SummaryRVAdapter adapter = new SummaryRVAdapter(painDays, getApplicationContext());
        rv.setAdapter(adapter);

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
            return true;
        }

        if (id == R.id.graph){
            startActivity(new Intent(this, Graph.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void startDay(View view){
        startActivity(new Intent (this, DayActivity.class));
    }

    private void initializeData(){
        painDays = new ArrayList<>();
        pain1 = new PainDay();
        pain2 = new PainDay();
        pain3 = new PainDay();
        pain4 = new PainDay();
        pain5 = new PainDay();
        pain6 = new PainDay();
        pain7 = new PainDay();
        pain8 = new PainDay();
        pain9 = new PainDay();
        Date c = Calendar.getInstance().getTime();

        pain1.setmDate(c);
        pain1.setmOverall(96);
        pain2.setmDate(c);
        pain2.setmOverall(66);
        pain3.setmDate(c);
        pain3.setmOverall(33);
        pain4.setmDate(c);
        pain4.setmOverall(75);
        pain5.setmDate(c);
        pain5.setmOverall(45);
        pain6.setmDate(c);
        pain6.setmOverall(78);
        pain7.setmDate(c);
        pain7.setmOverall(10);
        pain8.setmDate(c);
        pain8.setmOverall(45);
        pain9.setmDate(c);
        pain9.setmOverall(13);
        //pain.setmFatigue(2);
        //pain.setmStiffness(4);

        painDays.add(pain1);
        painDays.add(pain2);
        painDays.add(pain3);
        painDays.add(pain4);
        painDays.add(pain5);
        painDays.add(pain6);
        painDays.add(pain7);
        painDays.add(pain8);
        painDays.add(pain9);


    }

}

