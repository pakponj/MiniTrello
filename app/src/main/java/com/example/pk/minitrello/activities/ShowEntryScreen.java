package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.EntryAdapter;

import java.util.ArrayList;
import java.util.List;

public class    ShowEntryScreen extends AppCompatActivity {

    private List<ListEntry> entryList;
    private ListView entryListView;
    private EntryAdapter entryAdapter;
    private Board board;
    int boardIndex,cardIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("On Create", "On Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entries_screen);
        initComponents();
    }

    private void initComponents()
    {
        TextView entryBoardName = (TextView) findViewById(R.id.entry_board_name);
        TextView entryBoardDesc = (TextView) findViewById(R.id.entry_board_desc);
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        board = Storage.getInstance().getBoards().get(boardIndex);
        entryBoardName.setText(board.getName());
        entryBoardDesc.setText(board.getDesc());
        Button deleteButton = (Button) findViewById(R.id.delete_current_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board board = Storage.getInstance().getBoards().get(boardIndex);
                Storage.getInstance().removeBoard(board);
                finish();
            }
        });

//        ABC

        Button createNewEntryButton = (Button) findViewById(R.id.to_create_entry_button);
        createNewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowEntryScreen.this, CreateEntryScreen.class);
//                Log.e("Index #", boardIndex+"");
                intent.putExtra("add_in_this_board",boardIndex);
                startActivity(intent);
            }
        });

        entryList = new ArrayList<ListEntry>();
        entryListView = (ListView) findViewById(R.id.entry_list);
        entryAdapter = new EntryAdapter(this, R.layout.entry_cell, entryList);
        entryListView.setAdapter(entryAdapter);
        entryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ShowEntryScreen.this, ShowCardScreen.class);
                        ListEntry listEntry = entryList.get(i);
                        int listEntryIndex =
                                Storage.getInstance()
                                        .getBoards()
                                        .get(boardIndex)
                                        .getChildren()
                                        .indexOf(listEntry);
                        intent.putExtra("boardIndex",boardIndex);
                        intent.putExtra("entryIndex", listEntryIndex);
                        startActivity(intent);
                    }
                });

        Button deleteAllButton = (Button) findViewById(R.id.delete_all_button);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().getBoards().get(boardIndex).clear();
                refreshEntries();
            }
        });
    }

    private void refreshEntries() {
        entryList.clear();
//        Log.e("Empty Board", board.getChildren().isEmpty()+"");
        for(ListEntry e : Storage.getInstance().getBoards().get(boardIndex).getChildren()) {
//            Log.e("ListEntry", e.getName());
            entryList.add(e);
        }
        entryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshEntries();
    }
}
