package com.arthtracker.arthtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        PainDayHolder pvh = new PainDayHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PainDayHolder painDayHolder, final int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        double total = painDays.get(i).getmTotal();

        painDayHolder.notes.setText(painDays.get(i).getmNotes());
        painDayHolder.date.setText(sdf.format(new Date(painDays.get(i).getmDate() * 1000)));
        painDayHolder.circle.setTitleText(String.format("%.0f",total));

        //Long click to delete
        painDayHolder.cv.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(final View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
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
        painDayHolder.cv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("Click", "Short Click");
            }
        });

        if ( total <= 33){
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.green));
        }
        else if ((total > 33) && (total <= 66)){
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.yellow));
        }
        else{
            painDayHolder.circle.setStrokeColor(context.getResources().getColor(R.color.red));
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

    public class PainDayHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView date;
        TextView notes;
        CircleView circle;

        PainDayHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            date = (TextView)itemView.findViewById(R.id.date);
            circle = (CircleView)itemView.findViewById((R.id.circle));
            notes = (TextView)itemView.findViewById((R.id.notes));
        }
    }
}