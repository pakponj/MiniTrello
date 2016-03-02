package com.example.pk.minitrello.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.ListEntry;

import java.util.List;

public class ListEntryRecyclerViewAdapter extends RecyclerView.Adapter<ListEntryRecyclerViewAdapter.ListEntryViewHolder> {

    private List<ListEntry> entries;

    public ListEntryRecyclerViewAdapter(List<ListEntry> entries) {
        if( entries == null) {
            throw new IllegalArgumentException("Entries must not be null");
        }
        this.entries = entries;
    }

    @Override
    public ListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.entry_cell, parent, false);
        return new ListEntryViewHolder(itemView);
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

    public final static class ListEntryViewHolder extends RecyclerView.ViewHolder {

        TextView subject;
        TextView body;

        public ListEntryViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            body = (TextView) itemView.findViewById(R.id.body);
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
