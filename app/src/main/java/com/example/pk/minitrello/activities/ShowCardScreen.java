package com.example.pk.minitrello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.Card;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.CardAdapter;

import java.lang.Override;
import java.util.List;

public class ShowCardScreen extends AppCompatActivity {

    private List<Card> cardList;
    private ListView cardListView;
    private CardAdapter cardAdapter;
    private Board board;
    private ListEntry listEntry;
    int boardIndex,entryIndex;

    @Override
    public void onStart(){
        super.onStart();
        refreshCards();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_screen);
        initComponents();
    }

    public void initComponents(){
        TextView commentBoardName = (TextView) findViewById(R.id.card_name);
        TextView commentBoardDesc = (TextView) findViewById(R.id.card_desc);
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        entryIndex = (Integer) getIntent().getSerializableExtra("entryIndex");
        board = Storage.getInstance().getBoards().get(boardIndex);
        listEntry = board.getChildren().get(entryIndex);
        commentBoardName.setText(listEntry.getName());
        commentBoardDesc.setText(listEntry.getDesc());



    }

    public void refreshCards(){
    }
}
