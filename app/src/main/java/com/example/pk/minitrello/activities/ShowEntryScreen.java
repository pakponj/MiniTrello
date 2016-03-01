package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.EntryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowEntryScreen extends AppCompatActivity {

    private List<ListEntry> entryList;
    private ListView entryListView;
    private EntryAdapter entryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entries_screen);
        initComponents();
    }


    private void initComponents()
    {

        Button deleteButton = (Button) findViewById(R.id.delete_all_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().clearListEntries();

            }
        });

        Button createNewBoardButton = (Button) findViewById(R.id.to_create_entry_button);
        createNewBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowEntryScreen.this, CreateEntryScreen.class);
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
                    public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
                        Intent intent = new Intent(ShowEntryScreen.this, ShowCardScreen.class);
                        intent.putExtra("entry", )
                    }
        });

        private void refreshEntries() {
        entryList.clear();
        for(ListEntry e: Storage.getInstance().getListEntries()) {
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
}
