package com.example.pk.minitrello.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.CardRecycleViewAdapter;
import com.example.pk.minitrello.views.ListEntryRecyclerViewAdapter;

import java.util.List;

public class ShowRecycleEntryScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView cardRecyclerView;
    private ListEntryRecyclerViewAdapter adapter;
    private CardRecycleViewAdapter cardRecycleViewAdapter;
    private int boardIndex;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recycle_entry_screen);
        initComponents();
    }

    private void initComponents() {
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        board = Storage.getInstance().getBoards().get(boardIndex);
        //Entry's recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        List<ListEntry> entries = board.getChildren();
        adapter = new ListEntryRecyclerViewAdapter(entries, this , boardIndex  );
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

        final TextView entryBoardName = (TextView) findViewById(R.id.show_recycle_board_name);
        final TextView entryBoardDesc = (TextView) findViewById(R.id.show_recycle_board_desc);
        entryBoardName.setText(board.getName());
        entryBoardName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("LISTENING", "EntryBoardName is clicked.");
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShowRecycleEntryScreen.this);
                alertDialog.setTitle("Editing Board's Name");
                alertDialog.setMessage("Please enter a new board name: ");
                final EditText input = new EditText(ShowRecycleEntryScreen.this);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", null);

                final TextView thisEntryBoardName = entryBoardName;
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                final AlertDialog setEditDialog = alertDialog.create();
                setEditDialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button b = setEditDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (input.getText().toString().length() == 0) {
                                    Toast.makeText(getApplicationContext(), "Enter new name", Toast.LENGTH_SHORT).show();
                                } else {
                                    board.setName(input.getText().toString());
                                    thisEntryBoardName.setText(input.getText().toString());
                                    ShowRecycleEntryScreen.this.getWindow().getDecorView().postInvalidate();
                                    setEditDialog.dismiss();
                                }
                            }
                        });
                    }
                });
                setEditDialog.show();
                return false;
            }
        });
        entryBoardDesc.setText(board.getDesc());
        entryBoardDesc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("LISTENING", "EntryBoardDesc is clicked.");
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShowRecycleEntryScreen.this);
                alertDialog.setTitle("Editing Board's Description");
                alertDialog.setMessage("Please enter a new board description: ");
                final EditText input = new EditText(ShowRecycleEntryScreen.this);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", null);

                final TextView thisEntryBoardDesc = entryBoardDesc;
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                final AlertDialog setEditDialog = alertDialog.create();
                setEditDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button b = setEditDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(input.getText().toString().length() == 0) Toast.makeText(getApplicationContext(), "Enter new name", Toast.LENGTH_SHORT).show();
                                else {
                                    board.setDesc(input.getText().toString());
                                    thisEntryBoardDesc.setText(input.getText().toString());
                                    ShowRecycleEntryScreen.this.getWindow().getDecorView().postInvalidate();
                                    setEditDialog.dismiss();
                                }
                            }
                        });
                    }
                });
                setEditDialog.show();
                return false;
            }
        });
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
                refreshDeleteAll();
            }
        });
    }

    private void refreshDeleteAll() {
        Board board = Storage.getInstance().getBoard(boardIndex);
        board.clear();
        //Add each board to the list from main storage
        adapter.notifyDataSetChanged();
    }

}
