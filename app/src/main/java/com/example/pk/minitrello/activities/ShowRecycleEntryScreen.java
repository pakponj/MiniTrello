package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.ListEntryRecyclerViewAdapter;

import java.util.List;

public class ShowRecycleEntryScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListEntryRecyclerViewAdapter adapter;
    private int boardIndex;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recycle_entry_screen);
        initComponents();
    }

    private void initComponents() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        final List<ListEntry> entries = Storage.getInstance().getListEntries();
        adapter = new ListEntryRecyclerViewAdapter(entries);
        Storage.getInstance().setListEntryRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        };
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        TextView entryBoardName = (TextView) findViewById(R.id.show_recycle_board_name);
        TextView entryBoardDesc = (TextView) findViewById(R.id.show_recycle_board_desc);
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        board = Storage.getInstance().getBoards().get(boardIndex);
        entryBoardName.setText(board.getName());
        entryBoardDesc.setText(board.getDesc());
        Button delButton = (Button) findViewById(R.id.delete_current_button);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().removeBoard(board);
                finish();
            }
        });

        Button createNewEntryButton = (Button) findViewById(R.id.to_create_entry_button);
        createNewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowRecycleEntryScreen.this, CreateEntryScreen.class);
                intent.putExtra("add_in_this_board", boardIndex);
                startActivity(intent);
            }
        });
        Button delAllButton = (Button) findViewById(R.id.delete_all_button);
        delAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().getBoard(boardIndex).clear();
            }
        });

    }

    public interface OnRecycleItemClickListener<ListEntry> {
        public void onItemClick(View view, ListEntry entry);
    }

}
