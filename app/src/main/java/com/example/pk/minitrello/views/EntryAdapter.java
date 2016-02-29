package com.example.pk.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pk.minitrello.R;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {
    public EntryAdapter(Context context, int resource, List<Entry> objects) {
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

        Entry entry = getItem(position);
        subject.setText(entry.getSubject());
        body.setText(entry.getBody());

        return v;
    }

}
