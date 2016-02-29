package com.example.pk.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.ListEntry;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<ListEntry> {
    public EntryAdapter(Context context, int resource, List<ListEntry> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.entry_cell, null);
        }

        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView body = (TextView) v.findViewById(R.id.body);

        ListEntry entry = getItem(position);
        subject.setText(entry.getName());
        body.setText(entry.getDesc());

        return v;
    }

}
