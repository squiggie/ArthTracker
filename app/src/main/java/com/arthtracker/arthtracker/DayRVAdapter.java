package com.arthtracker.arthtracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Squiggie on 7/28/2015.
 */
public class DayRVAdapter extends RecyclerView.Adapter<DayRVAdapter.DayViewHolder> {
    private String[] titles;
    private Context context;

    DayRVAdapter(String[] titles, Context context){
        this.titles = titles;
        this.context = context;
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pain_list_item, viewGroup, false);
        DayViewHolder dvh = new DayViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(DayViewHolder dayViewHolder, int position) {
        dayViewHolder.mTitle.setText(titles[position]);
        dayViewHolder.mScore.setText("3");
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder implements SeekBar.OnSeekBarChangeListener {

        TextView mTitle;
        TextView mScore;
        SeekBar mSeekbar;

        public DayViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.txtPainTitle);
            mScore = (TextView)itemView.findViewById(R.id.txtPainScore);
            mSeekbar = (SeekBar)itemView.findViewById((R.id.painSeekBar));
            mSeekbar.setOnSeekBarChangeListener(this);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mScore.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
