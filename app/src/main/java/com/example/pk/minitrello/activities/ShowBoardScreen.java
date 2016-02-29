package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;import com.example.pk.minitrello.R;import java.lang.Override;

public class ShowBoardScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_board_screen);
        initComponent();
    }

    private void initComponent()
    {
        Button deleteButton = (Button) findViewById(R.id.delete_all_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO delete all shit
            }
        });

        Button createNewBoardButton = (Button) findViewById(R.id.to_create_board_button);
        createNewBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ShowBoardScreen.this , CreateBoardScreen.class);
                startActivity(intent);
            }
        });
/*
        ListView listView = (ListView) findViewById(R.id.board_list);
        //TODO add adapter
        listView.setAdapter(null);

*/


    }


}
