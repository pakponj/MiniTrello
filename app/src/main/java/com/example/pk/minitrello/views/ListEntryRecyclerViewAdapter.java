package com.example.pk.minitrello.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.activities.CreateCardScreen;
import com.example.pk.minitrello.activities.ShowRecycleEntryScreen;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;

import java.util.List;

public class ListEntryRecyclerViewAdapter extends RecyclerView.Adapter<ListEntryRecyclerViewAdapter.ListEntryViewHolder> {

    private List<ListEntry> entries;
    private Activity activity;
    private int boardIndex;

    public ListEntryRecyclerViewAdapter(List<ListEntry> entries, Activity activity , int boardIndex) {
        if( entries == null) {
            throw new IllegalArgumentException("Entries must not be null");
        }
        this.entries = entries;
        this.activity = activity;
        this.boardIndex = boardIndex;
    }

    @Override
    public ListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.entry_view_cell, parent, false);
//                inflate(R.layout.entry_cell, parent, false);
        return new ListEntryViewHolder(itemView, activity , boardIndex);
    }

    @Override
    public void onBindViewHolder(ListEntryViewHolder holder, int position) {
        ListEntry entry = entries.get(position);
        holder.subject.setText(entry.getName());
        holder.body.setText(entry.getDesc());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ListEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subject;
        TextView body;
        Button button;
        int entryIndex = -1;

        public ListEntryViewHolder(View itemView, final Activity activity , final int boardIndex ) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            body = (TextView) itemView.findViewById(R.id.body);
            button = (Button) itemView.findViewById(R.id.add_card_button);
            /*button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, CreateCardScreen.class);
                    intent.putExtra("createCard", entryIndex);
                    activity.startActivity(intent);
                }
            });*/
            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    entryIndex = getLayoutPosition();
                    Log.e("Long ", "Clicked");
                    Board temp = Storage.getInstance().getBoard(boardIndex);
                    temp.getChildren().remove(entryIndex);
                    notifyDataSetChanged();
                    return false;
                }
            });

        }

        @Override
        public void onClick(View v) {
            entryIndex = getLayoutPosition();
            Log.e("Entry's Index: ", "Entry's index: "+ entryIndex);
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, CreateCardScreen.class);
                    intent.putExtra("boardIndex",boardIndex);
                    intent.putExtra("entryIndex", entryIndex);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public void add(ListEntry entry, int position) {
        entries.add(position, entry);
        notifyItemInserted(position);
    }

    public void remove(ListEntry entry) {
        int position = entries.indexOf(entry);
        entries.remove(position);
        notifyItemRemoved(position);
    }


}
