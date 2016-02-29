package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;import com.example.pk.minitrello.R;import java.lang.Override;import java.lang.String;

public class CreateBoardScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_board_screen);
        initComponent();
    }

    private void initComponent()
    {
        Button createNewBoard = (Button) findViewById(R.id.create_board_button);
        createNewBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.board_name);
                String boardname = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.board_desc);
                String boarddescription = description.getText().toString();
                //TODO Code for putExtra

                Intent intent = new Intent(CreateBoardScreen.this , ShowBoardScreen.class);
                startActivity(intent);


            }
        });



    }
}
