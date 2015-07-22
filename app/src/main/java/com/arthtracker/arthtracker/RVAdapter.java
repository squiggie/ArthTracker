package com.arthtracker.arthtracker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.pavlospt.CircleView;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.arthtracker.arthtracker.R.color.*;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    List<PainDay> painDays;
    Context context;

    RVAdapter(List<PainDay> painDays,Context context){
        this.painDays = painDays;
        this.context = context;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView date;
        TextView stiffness;
        TextView overall;
        CircleView circle;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            //fatigue = (TextView)itemView.findViewById(R.id.fatigue);
            date = (TextView)itemView.findViewById(R.id.date);
            //overall = (TextView)itemView.findViewById(R.id.score);
            circle = (CircleView)itemView.findViewById((R.id.circle));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        //personViewHolder.fatigue.setText("Fatigue: " + painDays.get(i).getmFatigue());

        personViewHolder.date.setText(sdf.format(painDays.get(i).getmDate()));
        //personViewHolder.overall.setText(String.valueOf((painDays.get(i).getmOverall())));
        personViewHolder.circle.setTitleText(String.valueOf((painDays.get(i).getmOverall())));
        if (painDays.get(i).getmOverall() <= 33){
            //personViewHolder.circle.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
            personViewHolder.circle.setStrokeColor(context.getResources().getColor(R.color.red));
        }
        else if ((painDays.get(i).getmOverall() > 33) && (painDays.get(i).getmOverall() <= 66)){
            //personViewHolder.circle.setBackground(context.getResources().getDrawable(R.drawable.yellow_circle));
            //personViewHolder.overall.setTextColor(Color.BLACK);
            personViewHolder.circle.setStrokeColor(context.getResources().getColor(R.color.yellow));

        }
        else{
            //personViewHolder.circle.setBackground(context.getResources().getDrawable(R.drawable.green_circle));
            personViewHolder.circle.setStrokeColor(context.getResources().getColor(R.color.green));

        }

    }

    @Override
    public int getItemCount() {
        return painDays.size();
    }
}