package com.arthtracker.arthtracker;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    List<PainDay> painDays;
    Context context;

    RVAdapter(List<PainDay> painDays,Context context){
        this.painDays = painDays;
        this.context = context;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView fatigue;
        TextView stiffness;
        TextView overall;
        FloatingActionButton fab;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            //fatigue = (TextView)itemView.findViewById(R.id.fatigue);
            //stiffness = (TextView)itemView.findViewById(R.id.stiffness);
            overall = (TextView)itemView.findViewById(R.id.overall);
            fab = (FloatingActionButton)itemView.findViewById((R.id.fab));
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
        //personViewHolder.fatigue.setText("Fatigue: " + painDays.get(i).getmFatigue());
        //personViewHolder.stiffness.setText("Stiffness: " + painDays.get(i).getmStiffness());
        personViewHolder.overall.setText("Overall: " + painDays.get(i).getmOverall());
        if (painDays.get(i).getmOverall() > 66){
            personViewHolder.fab.setBackgroundTintList(context.getResources().getColorStateList(R.color.fabGreen));
        }
    }

    @Override
    public int getItemCount() {
        return painDays.size();
    }
}