package com.example.pk.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Card;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.card_cell, null);
        }

        final TextView subject = (TextView) v.findViewById(R.id.subject);
        final TextView body = (TextView) v.findViewById(R.id.body);

        Card card = getItem(position);
        subject.setText(card.getName());

        return v;
    }
}
