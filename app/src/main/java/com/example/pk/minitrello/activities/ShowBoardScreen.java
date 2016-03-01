package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.BoardAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowBoardScreen extends AppCompatActivity {

    private List<Board> boardList;
    private ListView boardListView;
    private BoardAdapter boardAdapter;

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
                Storage.getInstance().clearBoards();
                refreshBoards();
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

        boardList = new ArrayList<Board>();
        boardListView = (ListView) findViewById(R.id.board_list);
        boardAdapter = new BoardAdapter(this,R.layout.board_cell, boardList);
        boardListView.setAdapter(boardAdapter);
        boardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShowBoardScreen.this, ShowEntryScreen.class);
                Board board = boardList.get(i);
                int boardIndex = Storage.getInstance().getBoards().indexOf(board);
                intent.putExtra("boardIndex", boardIndex);
                Log.e("Index #", boardIndex + "");
                startActivity(intent);
            }
        });
    }

    private void refreshBoards() {
        boardList.clear();
        //Add each board to the list from main storage
        for(Board b: Storage.getInstance().getBoards()) {
            boardList.add(b);
        }
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshBoards();
    }
}
