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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        //set text views
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

        //set default values

        mPainDay.setmFingers(3);
        mPainDay.setmThumbs(3);
        mPainDay.setmWrists(3);
        mPainDay.setmElbows(3);
        mPainDay.setmShoulders(3);
        mPainDay.setmKnees(3);
        mPainDay.setmAnkles(3);
        mPainDay.setmFatigue(3);
        mPainDay.setmStiffness(3);
        mPainDay.setmOverall(3);
        mPainDay.setmNotes("");
        mPainDay.setmDate(System.currentTimeMillis() / 1000);

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
        //populate data
        mPainDay.setmFingers(Integer.parseInt(mtvFingers.getText().toString()));
        mPainDay.setmThumbs(Integer.parseInt(mtvThumbs.getText().toString()));
        mPainDay.setmWrists(Integer.parseInt(mtvWrists.getText().toString()));
        mPainDay.setmElbows(Integer.parseInt(mtvElbows.getText().toString()));
        mPainDay.setmShoulders(Integer.parseInt(mtvShoulders.getText().toString()));
        mPainDay.setmKnees(Integer.parseInt(mtvKnees.getText().toString()));
        mPainDay.setmAnkles(Integer.parseInt(mtvAnkles.getText().toString()));
        mPainDay.setmFatigue(Integer.parseInt(mtvFatigue.getText().toString()));
        mPainDay.setmStiffness(Integer.parseInt(mtvStiffness.getText().toString()));
        mPainDay.setmOverall(Integer.parseInt(mtvOverall.getText().toString()));
        mPainDay.setmNotes(mNotes.getText().toString());

        Calendar selectedDate = new GregorianCalendar(mDatePicker.getYear(),mDatePicker.getMonth(),mDatePicker.getDayOfMonth());
        Date d = null;
        d = selectedDate.getTime();
        mPainDay.setmDate(d.getTime() / 1000);
        //Validate Data
        if (mPainDay.validate()){
            //Save to DB
            SQLiteHelper sqlHelper = new SQLiteHelper(this);
            sqlHelper.createPainday(mPainDay);
            Toast.makeText(this,"New Day Saved",Toast.LENGTH_LONG).show();
            //navigate back up
            NavUtils.navigateUpFromSameTask(this);
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
                break;
            case R.id.sbThumbPain:
                mtvThumbs.setText(String.valueOf(progress));
                break;
            case R.id.sbWristPain:
                mtvWrists.setText(String.valueOf(progress));
                break;
            case R.id.sbElbowPain:
                mtvElbows.setText(String.valueOf(progress));
                break;
            case R.id.sbShoulderPain:
                mtvShoulders.setText(String.valueOf(progress));
                break;
            case R.id.sbKneePain:
                mtvKnees.setText(String.valueOf(progress));
                break;
            case R.id.sbAnklePain:
                mtvAnkles.setText(String.valueOf(progress));
                break;
            case R.id.sbFatigue:
                mtvFatigue.setText(String.valueOf(progress));
                mPainDay.setmFatigue(progress);
                break;
            case R.id.sbStiffness:
                mtvStiffness.setText(String.valueOf(progress));
                break;
            case R.id.sbOverall:
                mtvOverall.setText(String.valueOf(progress));
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

    }
}
