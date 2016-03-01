package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.Storage;

public class CreateBoardScreen extends AppCompatActivity {

    private String boardName;
    private String boardDesc;

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
                boardName = name.getText().toString();
                EditText desc = (EditText) findViewById(R.id.board_desc);
                boardDesc = desc.getText().toString();
                //TODO Code for putExtra

                //Intent intent = new Intent(CreateBoardScreen.this , ShowBoardScreen.class);
                //startActivity(intent);
                saveNewBoard();
                finish();
            }
        });

    }

    private void saveNewBoard() {
        Storage.getInstance().addBoard(new Board(boardName, boardDesc));
    }

}
