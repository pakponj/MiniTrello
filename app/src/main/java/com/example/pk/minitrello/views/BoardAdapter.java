package com.example.pk.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;

import java.util.List;

public class BoardAdapter extends ArrayAdapter<Board> {
    public BoardAdapter(Context context, int resource, List<Board> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.board_cell, null);
        }

        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView desc = (TextView) v.findViewById(R.id.body);

        Board board = getItem(position);
        subject.setText(board.getName());
        desc.setText(board.getDesc());

        return v;
    }
}
