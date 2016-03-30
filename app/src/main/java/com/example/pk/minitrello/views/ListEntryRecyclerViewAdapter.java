package com.example.pk.minitrello.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.activities.CreateCardScreen;
import com.example.pk.minitrello.activities.ShowCardScreen;
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

    public class ListEntryViewHolder extends RecyclerView.ViewHolder{

        TextView subject;
        TextView body;
        Button button;
        int entryIndex = -1;
        ListView listView;

        public ListEntryViewHolder(View itemView, final Activity activity , final int boardIndex ) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("LISTENING", "ListEntry '" + subject.getText().toString() + "' is clicked");
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setTitle("Editing Entry's Name");
                    alertDialog.setMessage("Please enter a new board description");
                    final EditText input = new EditText(activity);
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", null);

                    final TextView thisEntrySubject = subject;
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT

                    );
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);

                    final AlertDialog setEditDialog = alertDialog.create();
                    setEditDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            Button b = setEditDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (input.getText().toString().length() == 0)
                                        Toast.makeText(activity.getApplicationContext(), "Enter new name", Toast.LENGTH_SHORT).show();
                                    else {
                                        entryIndex = getLayoutPosition();
                                        Board temp = Storage.getInstance().getBoard(boardIndex);
                                        ListEntry entry = temp.getChildren().get(entryIndex);
                                        entry.setName(input.getText().toString());
                                        thisEntrySubject.setText(input.getText().toString());
                                        activity.getWindow().getDecorView().postInvalidate();
                                        setEditDialog.dismiss();
                                    }
                                }
                            });
                        }
                    });
                    setEditDialog.show();
                }
            });
            body = (TextView) itemView.findViewById(R.id.body);
            body.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("LISTENING", "ListEntry '" + body.getText().toString() + "' is clicked");
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setTitle("Editing Entry's Description");
                    alertDialog.setMessage("Please enter a new entr description");
                    final EditText input = new EditText(activity);
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", null);

                    final TextView thisEntryDesc = body;
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    );
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);

                    final AlertDialog setEditDialog = alertDialog.create();
                    setEditDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            Button b = setEditDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(input.getText().toString().length() == 0) Toast.makeText(activity.getApplicationContext(), "Enter new description", Toast.LENGTH_SHORT ).show();
                                    else {
                                        Board temp = Storage.getInstance().getBoard(boardIndex);
                                        entryIndex = getLayoutPosition();
                                        ListEntry entry = temp.getChildren().get(entryIndex);
                                        entry.setDesc(input.getText().toString());
                                        thisEntryDesc.setText(input.getText().toString());
                                        setEditDialog.dismiss();
                                    }
                                }
                            });
                        }
                    });
                    setEditDialog.show();
                }
            });
            listView = (ListView) itemView.findViewById(R.id.card_list_view);
            button = (Button) itemView.findViewById(R.id.add_card_button);

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    entryIndex = getLayoutPosition();
                    Board temp = Storage.getInstance().getBoard(boardIndex);
                    ListEntry le = temp.getChildren().get(entryIndex);
                    if(le.getCardAdapter() == null) le.setCardAdapter(new CardAdapter(activity, R.layout.card_cell,le.getChildren()));
                    listView.setAdapter(le.getCardAdapter());
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(activity,ShowCardScreen.class);
                            intent.putExtra("boardIndex", boardIndex);
                            intent.putExtra("entryIndex", entryIndex);
                            intent.putExtra("cardIndex", position);
                            activity.startActivity(intent);
                        }
                    });
                    Log.e("Add card", "Add button clicked on entry@" + entryIndex);
                    Intent intent = new Intent(activity, CreateCardScreen.class);
                    intent.putExtra("boardIndex",boardIndex);
                    intent.putExtra("entryIndex",entryIndex);
                    activity.startActivity(intent);
                }
            });

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

    public void refreshCards() {

    }
}
