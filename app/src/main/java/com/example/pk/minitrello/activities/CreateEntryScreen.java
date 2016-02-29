package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;import com.example.pk.minitrello.R;import java.lang.Override;import java.lang.String;

public class CreateEntryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry_screen);
        initComponent();
    }

    private void initComponent() {
        Button createNewEntry = (Button) findViewById(R.id.create_entry_button);
        createNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.entry_name);
                String entryname = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.entry_desc);
                String entrydescription = description.getText().toString();
                //TODO Code for putExtra

                //TODO fill entry
                Intent intent = new Intent(CreateEntryScreen.this, ShowEntryScreen.class);
                startActivity(intent);


            }
        });
    }
}
