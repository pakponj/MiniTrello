package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.pk.minitrello.R;import java.lang.Override;

public class ShowEntryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entries_screen);
        initComponent();
    }


    private  void  initComponent()
    {

        Button deleteButton = (Button) findViewById(R.id.delete_all_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO delete all shit
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




    }
}
