package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.ListEntryRecyclerViewAdapter;

public class CreateEntryScreen extends AppCompatActivity {

    private String entryName;
    private String entryDesc;
    private int boardIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry_screen);
        initComponents();
    }

    private void initComponents() {
        Button createNewEntry = (Button) findViewById(R.id.create_entry_button);
        createNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.entry_name);
                entryName = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.entry_desc);
                entryDesc = description.getText().toString();
                //TODO Code for putExtra
                boardIndex = (Integer) getIntent().getSerializableExtra("add_in_this_board");
                Log.e("Index #", boardIndex + "");
//  Log.e("Board HashCode: ", board.hashCode()+"");
                //TODO fill entry
//                Intent intent = new Intent(CreateEntryScreen.this, ShowEntryScreen.class);
//                startActivity(intent);
                //saveNewEntry();
                ListEntryRecyclerViewAdapter adapter = Storage.getInstance().getListEntryRecyclerViewAdapter();
                Board b = Storage.getInstance().getBoard(boardIndex);
                ListEntry tmp = new ListEntry(entryName, entryDesc);
//                tmp.setAdapter(new CardRecycleViewAdapter(new ArrayList<Card>()));
//                RecyclerView rv = (RecyclerView) findViewById(R.id.)
                /*tmp.setAdapter(new CardRecycleViewAdapter(tmp.getChildren()));
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycle_view);
                recyclerView.setHasFixedSize(true);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(tmp.getAdapter());

                recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                        super.onDraw(c, parent, state);
                    }
                });
                recyclerView.setItemAnimator((new DefaultItemAnimator()));
                recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

                tmp.setRecyclerView(recyclerView);*/
                adapter.add(tmp,b.getChildren().size());
                adapter.notifyDataSetChanged();
                //Storage.getInstance().getBoards().get(boardIndex).add(tmp);
                finish();
            }
        });
    }

    private void saveNewEntry() {
        ListEntry temp = new ListEntry(entryName, entryDesc);
//        Log.e("SaveEntry", temp.getName());
        Storage.getInstance().getBoards().get(boardIndex).add(temp);
//        board.add(temp);
    }
}
