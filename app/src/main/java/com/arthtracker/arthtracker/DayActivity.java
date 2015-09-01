package com.arthtracker.arthtracker;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayActivity extends ActionBarActivity implements PainItems.OnFragmentInteractionListener, SeekBar.OnSeekBarChangeListener, DatePicker.OnDateChangedListener{
    private Toolbar toolbar;
    private TextView mtvFingers;
    private TextView mtvThumbs;
    private TextView mtvWrists;
    private TextView mtvShoulders;
    private TextView mtvKnees;
    private TextView mtvElbows;
    private TextView  mtvAnkles;
    private TextView  mtvFatigue;
    private TextView  mtvStiffness;
    private TextView  mtvOverall;
    private DatePicker  mDatePicker;
    private TextView  mNotes;
    private PainDay mPainDay;
    private SeekBar msbFingerPain;
    private SeekBar msbThumbPain;
    private SeekBar msbWristPain;
    private SeekBar msbElbowPain;
    private SeekBar msbShoulderPain;
    private SeekBar msbKneePain;
    private SeekBar msbAnklePain;
    private SeekBar msbFatigue;
    private SeekBar msbStiffness;
    private SeekBar msbOverall;
    private CheckBox mchkWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        //set toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //create objects
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
        msbFingerPain = (SeekBar)findViewById(R.id.sbFingerPain);
        msbThumbPain = (SeekBar)findViewById(R.id.sbThumbPain);
        msbWristPain = (SeekBar)findViewById(R.id.sbWristPain);
        msbElbowPain = (SeekBar)findViewById(R.id.sbElbowPain);
        msbShoulderPain = (SeekBar)findViewById(R.id.sbShoulderPain);
        msbKneePain = (SeekBar)findViewById(R.id.sbKneePain);
        msbAnklePain = (SeekBar)findViewById(R.id.sbAnklePain);
        msbFatigue = (SeekBar)findViewById(R.id.sbFatigue);
        msbStiffness = (SeekBar)findViewById(R.id.sbStiffness);
        msbOverall = (SeekBar)findViewById(R.id.sbOverall);
        mchkWeather = (CheckBox)findViewById(R.id.chkWeather);

        PainDay p = (PainDay)this.getIntent().getSerializableExtra("PainDay");
        if(p!=null){
            //Edit PainDay - Set current values
            mPainDay = p;
            mtvFingers.setText(String.valueOf(mPainDay.getmFingers()));
            mtvThumbs.setText(String.valueOf(mPainDay.getmThumbs()));
            mtvWrists.setText(String.valueOf(mPainDay.getmWrists()));
            mtvShoulders.setText(String.valueOf(mPainDay.getmShoulders()));
            mtvKnees.setText(String.valueOf(mPainDay.getmKnees()));
            mtvElbows.setText(String.valueOf(mPainDay.getmElbows()));
            mtvAnkles.setText(String.valueOf(mPainDay.getmAnkles()));
            mtvFatigue.setText(String.valueOf(mPainDay.getmFatigue()));
            mtvStiffness.setText(String.valueOf(mPainDay.getmStiffness()));
            mtvOverall.setText(String.valueOf(mPainDay.getmOverall()));
            mNotes.setText(String.valueOf(mPainDay.getmNotes()));
            msbFingerPain.setProgress(mPainDay.getmFingers());
            msbFingerPain.setProgress(mPainDay.getmFingers());
            msbThumbPain.setProgress(mPainDay.getmThumbs());
            msbWristPain.setProgress(mPainDay.getmWrists());
            msbShoulderPain.setProgress(mPainDay.getmShoulders());
            msbKneePain.setProgress(mPainDay.getmKnees());
            msbElbowPain.setProgress(mPainDay.getmElbows());
            msbAnklePain.setProgress(mPainDay.getmAnkles());
            msbFatigue.setProgress(mPainDay.getmFatigue());
            msbStiffness.setProgress(mPainDay.getmStiffness());
            msbOverall.setProgress(mPainDay.getmOverall());
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(mPainDay.getmDate() * 1000);
            mDatePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), this);
            mchkWeather.setChecked(true);
            mchkWeather.setClickable(false);
        }
        else{
            //New PainDay - Set Default Values
            mPainDay = new PainDay();
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
            //DatePicker Listener
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            mDatePicker.init(year, month, day, this);
            mDatePicker.setMaxDate(new Date().getTime());
        }

        setListeners();
    }

    private void setListeners(){
        //SeekBar Listeners
        msbFingerPain.setOnSeekBarChangeListener(this);
        msbThumbPain.setOnSeekBarChangeListener(this);
        msbWristPain.setOnSeekBarChangeListener(this);
        msbElbowPain.setOnSeekBarChangeListener(this);
        msbShoulderPain.setOnSeekBarChangeListener(this);
        msbKneePain.setOnSeekBarChangeListener(this);
        msbAnklePain.setOnSeekBarChangeListener(this);
        msbFatigue.setOnSeekBarChangeListener(this);
        msbStiffness.setOnSeekBarChangeListener(this);
        msbOverall.setOnSeekBarChangeListener(this);

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

    public void checkWeather(View v){
        if (mchkWeather.isChecked()){
            if (mPainDay.getmWeather() == "" || mPainDay.getmWeather() == null){
                JSONclient client = new JSONclient(this, l);
                String url = "http://api.wunderground.com/api/13ff1e7f75830cc2/conditions/q/65619.json";
                client.execute(url);
            }
        }
    }

    GetJSONListener l = new GetJSONListener(){

        @Override
        public void onRemoteCallComplete(JSONObject jsonFromNet) {
            mPainDay.setmWeather(jsonFromNet.toString());
         }
    };

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
            SQLiteHelper sqlHelper = new SQLiteHelper(this);
            //Validate date doesn't already exist
            if (!sqlHelper.painDayExists(mPainDay.getmDate())) {
                //Save to DB
                if (sqlHelper.createPainday(mPainDay)) {
                    Toast.makeText(this, "New Day Saved", Toast.LENGTH_LONG).show();
                    //navigate back up
                    NavUtils.navigateUpFromSameTask(this);
                } else {
                    Toast.makeText(this, "Oops something went wrong. Try Again!", Toast.LENGTH_LONG).show();
                }
            }
            else{
                //Date already exists
                Toast.makeText(this,"An entry exists for this date!",Toast.LENGTH_LONG).show();
            }
        }
        else{
            //Data not valid
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
