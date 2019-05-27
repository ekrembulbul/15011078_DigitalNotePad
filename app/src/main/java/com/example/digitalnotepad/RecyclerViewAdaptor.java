package com.example.digitalnotepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>
{
    private static String TAG = "RecyclerViewAdaptor";

    private Context mContext;
    private ArrayList<Note> mNote;

    public RecyclerViewAdaptor(Context mContext, ArrayList<Note> mNote)
    {
        this.mContext = mContext;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Log.d(TAG, "onCreateViewHolder: started.");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i)
    {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.title.setText(mNote.get(i).getTitle());
        viewHolder.date.setText(mNote.get(i).getDate());
        viewHolder.hour.setText(mNote.get(i).getHour());
        viewHolder.adress.setText(mNote.get(i).getAdress());

        if (mNote.get(i).getPriority() == 0)
        {
            viewHolder.priority.setText("High");
        }
        else if (mNote.get(i).getPriority() == 1)
        {
            viewHolder.priority.setText("Medium");
        }
        else if (mNote.get(i).getPriority() == 2)
        {
            viewHolder.priority.setText("Low");
        }

        viewHolder.constraintLayout.setBackgroundColor(mNote.get(i).getColor());

        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onClick: clicked on: " + mNote.get(i).getTitle());
                Intent intent = new Intent(mContext, ShowNote.class);
                intent.putExtra("showNote", mNote.get(i));
                intent.putExtra("noteIndex", i);
                ((Activity)mContext).startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView date;
        TextView hour;
        TextView adress;
        TextView priority;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            init();
        }

        private void init()
        {
            title = itemView.findViewById(R.id.title_layout);
            date = itemView.findViewById(R.id.date_layout);
            hour = itemView.findViewById(R.id.hour_layout);
            adress = itemView.findViewById(R.id.adress_layout);
            priority = itemView.findViewById(R.id.priority_layout);
            constraintLayout = itemView.findViewById(R.id.constraint_layout);
        }
    }
}
