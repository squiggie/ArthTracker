package com.arthtracker.arthtracker;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;

public class SettingsActivity extends ActionBarActivity {
    @Bind(R.id.app_bar) Toolbar toolbar;
    @Bind(R.id.chkWeather) CheckBox chkWeather;
    @Bind(R.id.divider) ImageView divider;
    @Bind(R.id.etZip) EditText etZip;
    @Bind(R.id.rlSettings) RelativeLayout rlSettings;
    @Bind(R.id.tvWeatherSub)TextView tvWeatherSub;
    @Bind(R.id.fabSettings) FloatingActionButton fabSettings;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        //set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Shared Prefs
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        params = (RelativeLayout.LayoutParams)divider.getLayoutParams();

        //Set Preference States
        chkWeather.setChecked(prefs.getBoolean("weather", true));
        etZip.setText(prefs.getString("zipcode",""));
        WeatherChecked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
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
    @OnClick(R.id.fabSettings)
    public void Save(){
        Boolean validated = true;
        //Save Checks
        editor.putBoolean("weather",chkWeather.isChecked());

        //Validate Input

        if (chkWeather.isChecked()){
            String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(etZip.getText());

            if (matcher.matches()){
                editor.putString("zipcode",etZip.getText().toString());
            }
            else{
                Toast.makeText(this, "Invalid Zip Code. Please try again.", Toast.LENGTH_LONG).show();
                etZip.setText("");
                validated = false;
            }
        }
        //Save Input
        if (validated){
            editor.apply();
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    @OnClick(R.id.chkWeather)
    public void WeatherChecked(){
        if (chkWeather.isChecked()){
            //move divider down
            etZip.setVisibility(View.VISIBLE);
            params.addRule(RelativeLayout.BELOW, etZip.getId());
            divider.setLayoutParams(params);
        }
        else{
            //move divider up
            etZip.setVisibility(View.INVISIBLE);
            etZip.setText("");
            params.addRule(RelativeLayout.BELOW, tvWeatherSub.getId());
            divider.setLayoutParams(params);
        }
    }
}
