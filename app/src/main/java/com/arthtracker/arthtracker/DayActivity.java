package com.arthtracker.arthtracker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
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

import org.apache.http.HttpException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DayActivity extends ActionBarActivity implements PainItems.OnFragmentInteractionListener, SeekBar.OnSeekBarChangeListener, DatePicker.OnDateChangedListener{
    @Bind(R.id.app_bar) Toolbar toolbar;
    @Bind(R.id.tvFingerPainScore) TextView mtvFingers;
    @Bind(R.id.tvThumbPainScore) TextView mtvThumbs;
    @Bind(R.id.tvWristPainScore) TextView mtvWrists;
    @Bind(R.id.tvShoulderPainScore) TextView mtvShoulders;
    @Bind(R.id.tvKneePainScore) TextView mtvKnees;
    @Bind(R.id.tvElbowPainScore) TextView mtvElbows;
    @Bind(R.id.tvAnklePainScore) TextView  mtvAnkles;
    @Bind(R.id.tvFootPainScore) TextView mtvFeet;
    @Bind(R.id.tvFatigueScore) TextView  mtvFatigue;
    @Bind(R.id.tvStiffnessScore) TextView  mtvStiffness;
    @Bind(R.id.tvOverallScore) TextView  mtvOverall;
    @Bind(R.id.datePicker) DatePicker  mDatePicker;
    @Bind(R.id.notes) TextView  mNotes;
    @Bind(R.id.sbFingerPain) SeekBar msbFingerPain;
    @Bind(R.id.sbThumbPain) SeekBar msbThumbPain;
    @Bind(R.id.sbWristPain) SeekBar msbWristPain;
    @Bind(R.id.sbElbowPain) SeekBar msbElbowPain;
    @Bind(R.id.sbShoulderPain) SeekBar msbShoulderPain;
    @Bind(R.id.sbKneePain) SeekBar msbKneePain;
    @Bind(R.id.sbAnklePain) SeekBar msbAnklePain;
    @Bind(R.id.sbFootPain) SeekBar msbFootPain;
    @Bind(R.id.sbFatigue) SeekBar msbFatigue;
    @Bind(R.id.sbStiffness) SeekBar msbStiffness;
    @Bind(R.id.sbOverall) SeekBar msbOverall;

    private PainDay mPainDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ButterKnife.bind(this);

        //set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            msbFootPain.setProgress(mPainDay.getmFeet());
            msbFatigue.setProgress(mPainDay.getmFatigue());
            msbStiffness.setProgress(mPainDay.getmStiffness());
            msbOverall.setProgress(mPainDay.getmOverall());
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(mPainDay.getmDate() * 1000);
            mDatePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), this);
            mDatePicker.setEnabled(false);
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
            mPainDay.setmFeet(3);
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

        //Get Weather if applicable
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (prefs.getBoolean("weather",false) & prefs.getString("zipcode","") != ""){
            try{
                checkWeather(prefs.getString("zipcode",""));
            }
            catch(Exception e){
                Toast.makeText(this, "Oops something happend when trying to get weather information. Weather data might not be saved!", Toast.LENGTH_LONG).show();
            }
        }
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
        msbFootPain.setOnSeekBarChangeListener(this);
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

    public void checkWeather(String zip){
        if (mPainDay.getmWeather() == "" || mPainDay.getmWeather() == null) {//not existing day
            JSONclient client = new JSONclient(this, l);
            String url = "http://api.wunderground.com/api/13ff1e7f75830cc2/conditions/q/" + zip + ".json";
            client.execute(url);
        }
    }

    GetJSONListener l = new GetJSONListener(){
        @Override
        public void onRemoteCallComplete(JSONObject jsonFromNet){
            if (jsonFromNet != null ){
                mPainDay.setmWeather(jsonFromNet.toString());
            }
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
        mPainDay.setmFeet(Integer.parseInt(mtvFeet.getText().toString()));
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
                //Update It
                sqlHelper.updatePainDay(mPainDay);
                Toast.makeText(this,"Day Updated",Toast.LENGTH_LONG).show();
                //navigate back up
                NavUtils.navigateUpFromSameTask(this);
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
            case R.id.sbFootPain:
                mtvFeet.setText(String.valueOf(progress));
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
