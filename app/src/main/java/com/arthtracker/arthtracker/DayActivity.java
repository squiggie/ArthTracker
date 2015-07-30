package com.arthtracker.arthtracker;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayActivity extends ActionBarActivity implements fragment_pain_items.OnFragmentInteractionListener, SeekBar.OnSeekBarChangeListener, DatePicker.OnDateChangedListener{
    private Toolbar toolbar;
    private String[] values;
    private TextView mtvFingers;
    private TextView mtvThumbs;
    private TextView mtvWrists;
    private TextView mtvElbows;
    private TextView mtvShoulders;
    private TextView mtvKnees;
    private TextView mtvAnkles;
    private TextView mtvFatigue;
    private TextView mtvStiffness;
    private TextView mtvOverall;
    private DatePicker mDatePicker;
    private EditText mNotes;
    private PainDay mPainDay = new PainDay();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        //set toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set scores
        mtvFingers = (TextView)findViewById(R.id.tvFingerPainScore);
        mtvThumbs = (TextView)findViewById(R.id.tvThumbPainScore);
        mtvWrists = (TextView)findViewById(R.id.tvWristPainScore);
        mtvShoulders = (TextView)findViewById(R.id.tvShoulderPainScore);
        mtvKnees= (TextView)findViewById(R.id.tvKneePainScore);
        mtvElbows = (TextView)findViewById(R.id.tvElbowPainScore);
        mtvAnkles= (TextView)findViewById(R.id.tvAnklePainScore);
        mtvFatigue = (TextView)findViewById(R.id.tvFatigueScore);
        mtvStiffness= (TextView)findViewById(R.id.tvStiffnessScore);
        mtvOverall = (TextView)findViewById(R.id.tvOverallScore);
        mDatePicker = (DatePicker)findViewById(R.id.datePicker);

        mNotes = (EditText)findViewById(R.id.notes);

        setListeners();
    }

    private void setListeners(){
        //SeekBar Listeners
        SeekBar sbFingerPain = (SeekBar)findViewById(R.id.sbFingerPain);
        sbFingerPain.setOnSeekBarChangeListener(this);
        SeekBar sbThumbPain = (SeekBar)findViewById(R.id.sbThumbPain);
        sbThumbPain.setOnSeekBarChangeListener(this);
        SeekBar sbWristPain = (SeekBar)findViewById(R.id.sbWristPain);
        sbWristPain.setOnSeekBarChangeListener(this);
        SeekBar sbElbowPain = (SeekBar)findViewById(R.id.sbElbowPain);
        sbElbowPain.setOnSeekBarChangeListener(this);
        SeekBar sbShoulderPain = (SeekBar)findViewById(R.id.sbShoulderPain);
        sbShoulderPain.setOnSeekBarChangeListener(this);
        SeekBar sbKneePain = (SeekBar)findViewById(R.id.sbKneePain);
        sbKneePain.setOnSeekBarChangeListener(this);
        SeekBar sbAnklePain = (SeekBar)findViewById(R.id.sbAnklePain);
        sbAnklePain.setOnSeekBarChangeListener(this);
        SeekBar sbFatigue = (SeekBar)findViewById(R.id.sbFatigue);
        sbFatigue.setOnSeekBarChangeListener(this);
        SeekBar sbStiffness = (SeekBar)findViewById(R.id.sbStiffness);
        sbStiffness.setOnSeekBarChangeListener(this);
        SeekBar sbOverall = (SeekBar)findViewById(R.id.sbOverall);
        sbOverall.setOnSeekBarChangeListener(this);

        //DatePicker Listener
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year,month,day,this);
        mDatePicker.setMaxDate(new Date().getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_day, menu);
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

        if (id ==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveDay(View v){
        //Validate Data
        if (mPainDay.validate()){
            //Save to DB
        }
        else{
            Toast.makeText(this,"Oops something went wrong. Try again!",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        switch (id)
        {
            case R.id.sbFingerPain:
                mtvFingers.setText(String.valueOf(progress));
                mPainDay.setmFingers(progress);
                break;
            case R.id.sbThumbPain:
                mtvThumbs.setText(String.valueOf(progress));
                mPainDay.setmThumbs(progress);
                break;
            case R.id.sbWristPain:
                mtvWrists.setText(String.valueOf(progress));
                mPainDay.setmWrists(progress);
                break;
            case R.id.sbElbowPain:
                mtvElbows.setText(String.valueOf(progress));
                mPainDay.setmElbows(progress);
                break;
            case R.id.sbShoulderPain:
                mtvShoulders.setText(String.valueOf(progress));
                mPainDay.setmShoulders(progress);
                break;
            case R.id.sbKneePain:
                mtvKnees.setText(String.valueOf(progress));
                mPainDay.setmKnees(progress);
                break;
            case R.id.sbAnklePain:
                mtvAnkles.setText(String.valueOf(progress));
                mPainDay.setmAnkles(progress);
                break;
            case R.id.sbFatigue:
                mtvFatigue.setText(String.valueOf(progress));
                mPainDay.setmFatigue(progress);
                break;
            case R.id.sbStiffness:
                mtvStiffness.setText(String.valueOf(progress));
                mPainDay.setmStiffness(progress);
                break;
            case R.id.sbOverall:
                mtvOverall.setText(String.valueOf(progress));
                mPainDay.setmOverall(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date d = null;
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        d = cal.getTime();
        mPainDay.setmDate(d);
    }
}
