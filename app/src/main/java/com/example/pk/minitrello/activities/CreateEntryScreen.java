package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;

public class CreateEntryScreen extends AppCompatActivity {

    private String entryName;
    private String entryDesc;
    private Board board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry_screen);
        initComponents();
    }

    private void initComponents() {
        Button createNewEntry = (Button) findViewById(R.id.create_entry_button);
        createNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.entry_name);
                entryName = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.entry_desc);
                entryDesc = description.getText().toString();
                //TODO Code for putExtra
                board = (Board) getIntent().getSerializableExtra("add_in_this_board");
                //TODO fill entry
//                Intent intent = new Intent(CreateEntryScreen.this, ShowEntryScreen.class);
//                startActivity(intent);
                saveNewEntry();
                finish();
            }
        });
    }

    private void saveNewEntry() {
        ListEntry temp = new ListEntry(entryName, entryDesc);
        Log.e("SaveEntry", temp.getName());
        Storage.getInstance().addListEntry(temp);
        board.add(temp);
    }
}
