package com.arthtracker.arthtracker;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DayActivity extends ActionBarActivity implements fragment_pain_items.OnFragmentInteractionListener, SeekBar.OnSeekBarChangeListener{
    private Toolbar toolbar;
    private String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        //set toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        values = new String[] { "Finger Pain:", "Thumb Pain:", "Wrist Pain:",
                "Elbow Pain:", "Shoulder Pain:", "Knee Pain:", "Ankle Pain:", "Fatigue:", "Stiffness:", "Overall:"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        setListeners();
    }

    private void setListeners(){
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
        //Create PainDay object

        //Validate Date

        //Save to DB
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
                TextView tv1 = (TextView)findViewById(R.id.tvFingerPainScore);
                tv1.setText(String.valueOf(progress));
                break;
            case R.id.sbThumbPain:
                TextView tv2 = (TextView)findViewById(R.id.tvThumbPainScore);
                tv2.setText(String.valueOf(progress));
                break;
            case R.id.sbWristPain:
                TextView tv3 = (TextView)findViewById(R.id.tvWristPainScore);
                tv3.setText(String.valueOf(progress));
                break;
            case R.id.sbElbowPain:
                TextView tv4 = (TextView)findViewById(R.id.tvElbowPainScore);
                tv4.setText(String.valueOf(progress));
                break;
            case R.id.sbShoulderPain:
                TextView tv5 = (TextView)findViewById(R.id.tvShoulderPainScore);
                tv5.setText(String.valueOf(progress));
                break;
            case R.id.sbKneePain:
                TextView tv6 = (TextView)findViewById(R.id.tvKneePainScore);
                tv6.setText(String.valueOf(progress));
                break;
            case R.id.sbAnklePain:
                TextView tv7 = (TextView)findViewById(R.id.tvAnklePainScore);
                tv7.setText(String.valueOf(progress));
                break;
            case R.id.sbFatigue:
                TextView tv8 = (TextView)findViewById(R.id.tvFatigueScore);
                tv8.setText(String.valueOf(progress));
                break;
            case R.id.sbStiffness:
                TextView tv9 = (TextView)findViewById(R.id.tvStiffnessScore);
                tv9.setText(String.valueOf(progress));
                break;
            case R.id.sbOverall:
                TextView tv10 = (TextView)findViewById(R.id.tvOverallScore);
                tv10.setText(String.valueOf(progress));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
