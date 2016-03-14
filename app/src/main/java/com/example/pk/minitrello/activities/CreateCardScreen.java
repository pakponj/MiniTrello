package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.Card;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.CardAdapter;

public class CreateCardScreen extends AppCompatActivity {

    private EditText name;
    private String cardName;
    private int entryIndex , boardIndex;
    private CardAdapter cardAdapter;
    private Board board;
    private ListEntry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card_screen);
        initComponents();
    }

    private void initComponents() {
        Button createCardButton = (Button) findViewById(R.id.create_card_button);
        createCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.card_edittext_name);
                cardName = name.getText().toString();
                entryIndex = (Integer) getIntent().getSerializableExtra("entryIndex");
                boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
                board = Storage.getInstance().getBoard(boardIndex);
                entry = board.getChildren().get(entryIndex);
                Log.e("Add card to the entry", "Entry name: " + entry.getName());
                cardAdapter = entry.getCardAdapter();
                saveNewCard();
                cardAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void saveNewCard() {
        entry.add(new Card(cardName, null));
    }

}
