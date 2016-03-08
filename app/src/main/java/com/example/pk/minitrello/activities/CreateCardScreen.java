package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Card;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.CardRecycleViewAdapter;

public class CreateCardScreen extends AppCompatActivity {

    private String cardName;
    private EditText name;
    private int entryIndex;
    private CardRecycleViewAdapter adapter;

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
                entryIndex = (Integer) getIntent().getSerializableExtra("createCard");
                adapter = Storage.getInstance().getCardRecycleViewAdapter();
                ListEntry le = Storage.getInstance().getListEntry(entryIndex);
                Card tmp = new Card(cardName,null);
                adapter.add(tmp,le.getChildren().size());
                finish();
            }
        });
    }

}
