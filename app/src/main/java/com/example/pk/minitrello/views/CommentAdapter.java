package com.example.pk.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pk.minitrello.R;

import org.w3c.dom.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.comment_cell, null);
        }

        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView body = (TextView) v.findViewById(R.id.body);

        Comment comment = getItem(position);
        subject.setText(comment.getSubject());
        body.setText(comment.getBody());

        return v;
    }
}
