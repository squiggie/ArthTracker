package com.arthtracker.arthtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.joanzapata.iconify.widget.IconTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SummaryRVAdapter extends RecyclerView.Adapter<SummaryRVAdapter.PainDayHolder> {
    List<PainDay> painDays;
    Context context;

    SummaryRVAdapter(List<PainDay> painDays, Context context){
        this.painDays = painDays;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PainDayHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.summary_item, viewGroup, false);
        ButterKnife.bind(this, v);
        PainDayHolder pvh = new PainDayHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PainDayHolder painDayHolder, final int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        double total = painDays.get(i).getmTotal();

        painDayHolder.notes.setText(painDays.get(i).getmNotes());
        painDayHolder.date.setText(sdf.format(new Date(painDays.get(i).getmDate() * 1000)));
        painDayHolder.circle.setTitleText(String.format("%.0f", total));

        //Long click to delete
        painDayHolder.cv.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //delete day
                                SQLiteHelper sqlHelper = new SQLiteHelper(context);
                                sqlHelper.deletePainDay(painDays.get(painDayHolder.getPosition()));
                                remove(painDayHolder.getPosition());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to delete this day?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });

        //click to open day
        painDayHolder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DayActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("PainDay", painDays.get(painDayHolder.getPosition()));
                context.startActivity(i);
            }
        });

        //Overall Circle
        if (total <= 33) {
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.green));
        } else if ((total > 33) && (total <= 66)) {
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.yellow));
        } else {
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.red));
        }

        //Individual Circles
        //Finger
        if (painDays.get(i).getmFingers() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fingerCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmFingers() == 2 || painDays.get(i).getmFingers() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fingerCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fingerCircle.setImageDrawable(myIcon);

        }
        //Thumb
        if (painDays.get(i).getmThumbs() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.thumbCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmThumbs() == 2 || painDays.get(i).getmThumbs() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.thumbCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.thumbCircle.setImageDrawable(myIcon);

        }
        //Wrists
        if (painDays.get(i).getmWrists() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.wristCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmWrists() == 2 || painDays.get(i).getmWrists() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.wristCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.wristCircle.setImageDrawable(myIcon);

        }
        //Elbows
        if (painDays.get(i).getmElbows() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.elbowCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmElbows() == 2 || painDays.get(i).getmElbows() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.elbowCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.elbowCircle.setImageDrawable(myIcon);

        }
        //Knees
        if (painDays.get(i).getmKnees() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.kneeCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmKnees() == 2 || painDays.get(i).getmKnees() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.kneeCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.kneeCircle.setImageDrawable(myIcon);

        }
        //Ankles
        if (painDays.get(i).getmAnkles() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.ankleCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmAnkles() == 2 || painDays.get(i).getmAnkles() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.ankleCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.ankleCircle.setImageDrawable(myIcon);

        }
        //Feet
        if (painDays.get(i).getmFeet() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.feetCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmFeet() == 2 || painDays.get(i).getmFeet() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.feetCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.feetCircle.setImageDrawable(myIcon);

        }
        //Fatigue
        if (painDays.get(i).getmFatigue() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fatigueCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmFatigue() == 2 || painDays.get(i).getmFatigue() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fatigueCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.fatigueCircle.setImageDrawable(myIcon);

        }
        //Stiffness
        if (painDays.get(i).getmStiffness() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.stiffnessCircle.setImageDrawable(myIcon);
        } else if (painDays.get(i).getmStiffness() == 2 || painDays.get(i).getmStiffness() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.stiffnessCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.stiffnessCircle.setImageDrawable(myIcon);
        }
        //Overall
        if (painDays.get(i).getmOverall() <= 1) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.overallCircle.setImageDrawable(myIcon);
        }
        else if (painDays.get(i).getmOverall() == 2 || painDays.get(i).getmOverall() == 3) {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.overallCircle.setImageDrawable(myIcon);
        } else {
            Drawable myIcon = context.getResources().getDrawable(R.drawable.circle_small);
            myIcon.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            myIcon.setAlpha(90);
            painDayHolder.overallCircle.setImageDrawable(myIcon);

        }

        //Weather Icon and Text
        if (painDays.get(i).getmWeather() != null && painDays.get(i).getmWeather() != ""){
            try {
                JSONObject json = new JSONObject(painDays.get(i).getmWeather());
                //Get the instance of JSONArray that contains JSONObjects
                JSONObject observation = json.optJSONObject("current_observation");
                String weatherIcon = observation.optString("icon").toString();
                switch (weatherIcon){
                    case "clear":
                        painDayHolder.weatherIcon.setText("{wi_day_sunny}");
                        break;
                    case "cloudy":
                        painDayHolder.weatherIcon.setText("{wi_day_cloudy}");
                        break;
                    case "partlycloudy":
                        painDayHolder.weatherIcon.setText("{wi_day_cloudy_high}");
                        break;
                    case "flurries":
                        painDayHolder.weatherIcon.setText("{wi_day_snow}");
                        break;
                    case "hazy":
                        painDayHolder.weatherIcon.setText("{wi_day_haze}");
                        break;
                    case "rain":
                        painDayHolder.weatherIcon.setText("{wi_day_rain}");
                        break;
                    case "sleet":
                        painDayHolder.weatherIcon.setText("{wi_day_sleet}");
                        break;
                    case "thunderstorm":
                        painDayHolder.weatherIcon.setText("{wi_day_thunderstorm}");
                        break;
                    case "fog":
                        painDayHolder.weatherIcon.setText("{wi_day_fog}");
                        break;
                }

                painDayHolder.weatherText.setText(createWeatherText(observation));
            } catch (JSONException e) {
            }
        }
    }
    @Override
    public int getItemCount() {
        return painDays.size();
    }

    public void remove(int position) {
        painDays.remove(position);
        notifyItemRemoved(position);
    }

    protected String createWeatherText(JSONObject weather){
        String weatherText = "";
        String city = "";
        String temp = weather.optString("temp_f").toString();
        String humidity = weather.optString("relative_humidity").toString();
        String pressure = weather.optString("pressure_in").toString() + weather.optString("pressure_trend").toString();;
        String precip = weather.optString("precip_today_in").toString();
        String icon = weather.optString("icon").toString();

        JSONObject location = weather.optJSONObject("display_location");
        city = location.optString("city");

        weatherText = city + " " + temp + "f | " + "Humidity "+ humidity + " | Pressure " + pressure + " | Precip " + precip + " | Icon " + icon;

        return weatherText;
    }

    public class PainDayHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cv) CardView cv;
        @Bind(R.id.date) TextView date;
        @Bind(R.id.notes) TextView notes;
        @Bind(R.id.circle) CircleView circle;
        @Bind(R.id.fingerCircle) ImageView fingerCircle;
        @Bind(R.id.thumbCircle) ImageView thumbCircle;
        @Bind(R.id.wristCircle) ImageView wristCircle;
        @Bind(R.id.elbowCircle) ImageView elbowCircle;
        @Bind(R.id.kneeCircle) ImageView kneeCircle;
        @Bind(R.id.ankleCircle) ImageView ankleCircle;
        @Bind(R.id.feetCircle) ImageView feetCircle;
        @Bind(R.id.fatigueCircle) ImageView fatigueCircle;
        @Bind(R.id.stiffnessCircle) ImageView stiffnessCircle;
        @Bind(R.id.overallCircle) ImageView overallCircle;
        @Bind(R.id.weatherIcon) IconTextView weatherIcon;
        @Bind(R.id.weatherText) TextView weatherText;



        PainDayHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /*
            cv = @Bind(R.id.cv);
            date = (TextView)itemView.findViewById(R.id.date);
            circle = (CircleView)itemView.findViewById((R.id.circle));
            notes = (TextView)itemView.findViewById((R.id.notes));
            fingerCircle = (ImageView)itemView.findViewById(R.id.fingerCircle);
            thumbCircle = (ImageView)itemView.findViewById(R.id.thumbCircle);
            wristCircle = (ImageView)itemView.findViewById(R.id.wristCircle);
            elbowCircle = (ImageView)itemView.findViewById(R.id.elbowCircle);
            kneeCircle = (ImageView)itemView.findViewById(R.id.kneeCircle);
            ankleCircle = (ImageView)itemView.findViewById(R.id.ankleCircle);
            fatigueCircle = (ImageView)itemView.findViewById(R.id.fatigueCircle);
            stiffnessCircle = (ImageView)itemView.findViewById(R.id.stiffnessCircle);
            overallCircle = (ImageView)itemView.findViewById(R.id.overallCircle);
            weatherIcon = (IconTextView)itemView.findViewById(R.id.weatherIcon);
            weatherText = (TextView)itemView.findViewById(R.id.weatherText);
            */
        }
    }
}

