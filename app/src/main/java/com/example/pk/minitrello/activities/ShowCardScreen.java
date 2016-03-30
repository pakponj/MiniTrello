package com.example.pk.minitrello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Board;
import com.example.pk.minitrello.models.Card;
import com.example.pk.minitrello.models.Comment;
import com.example.pk.minitrello.models.ListEntry;
import com.example.pk.minitrello.models.Storage;
import com.example.pk.minitrello.views.CardAdapter;
import com.example.pk.minitrello.views.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowCardScreen extends AppCompatActivity {

    private List<Card> cardList;
    private ListView cardListView;
    private CardAdapter cardAdapter;
    private List<Comment> commentList;
    private ListView commentListView;
    private CommentAdapter commentAdapter;
    private Board board;
    private ListEntry listEntry;
    private Card card;

    private EditText editText;

    private Button addCommentButton;
    private Button deleteCurrentCardButton;

    int boardIndex,entryIndex,cardIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_screen);
        initComponents();
    }

    public void initComponents(){
        commentList = new ArrayList<Comment>();
        commentAdapter = new CommentAdapter(ShowCardScreen.this, R.layout.comment_cell, commentList);
        cardListView = (ListView) findViewById(R.id.board_list);
        cardListView.setAdapter(commentAdapter);
        final TextView commentBoardName = (TextView) findViewById(R.id.card_name);
        final TextView commentBoardDesc = (TextView) findViewById(R.id.card_desc);
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        Log.e("ShowCardScreen BIndex",boardIndex+"");
        entryIndex = (Integer) getIntent().getSerializableExtra("entryIndex");
        Log.e("ShowCardScreen EIndex",entryIndex+"");
        cardIndex = (Integer) getIntent().getSerializableExtra("cardIndex");
        Log.e("ShowCardScreen CIndex",cardIndex+"");
        board = Storage.getInstance().getBoard(boardIndex);
        listEntry = board.getChildren().get(entryIndex);
        card = listEntry.getChildren().get(cardIndex);
        commentBoardName.setText(card.getName());
        commentBoardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("LISTENING", "Card's name: " + commentBoardName.getText().toString() + " is clicked.");
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShowCardScreen.this);
                alertDialog.setTitle("Editing Card's name");
                alertDialog.setMessage("Please enter an new card description: ");
                final EditText input = new EditText(ShowCardScreen.this);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", null);

                final TextView thisCommentBoardname = commentBoardName;
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
                                    card.setName(input.getText().toString());
                                    thisCommentBoardname.setText(input.getText().toString());
                                    ShowCardScreen.this.getWindow().getDecorView().postInvalidate();
                                    setEditDialog.dismiss();
                                }
                            }
                        });
                    }
                });
                setEditDialog.show();
            }
        });
        commentBoardDesc.setText(card.getDesc());
        addCommentButton = (Button) findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.add_comment_edittext);
                if(!editText.getText().toString().equals("")) {
                    card.add(new Comment(editText.getText().toString(),null ));
                    editText.setText("");
                    refreshCard();
                    Log.e("Add coment buton", "card list size " + card.getChildren().size());
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please type some text.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteCurrentCardButton = (Button) findViewById(R.id.delete_current_button);
        deleteCurrentCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Storage.getInstance().removeCard(card);
                listEntry.getChildren().remove(cardIndex);
                listEntry.getCardAdapter().notifyDataSetChanged();
                finish();
            }
        });
    }

    public void refreshCard(){
        commentList.clear();
        card = listEntry.getChildren().get(cardIndex);
        for(Comment c: card.getChildren()) {
            commentList.add(c);
        }
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart(){
        super.onStart();
        refreshCard();
    }

}
