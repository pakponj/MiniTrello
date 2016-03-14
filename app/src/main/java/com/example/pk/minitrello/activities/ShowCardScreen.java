package com.example.pk.minitrello.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        TextView commentBoardName = (TextView) findViewById(R.id.card_name);
        TextView commentBoardDesc = (TextView) findViewById(R.id.card_desc);
        boardIndex = (Integer) getIntent().getSerializableExtra("boardIndex");
        entryIndex = (Integer) getIntent().getSerializableExtra("entryIndex");
        cardIndex = (Integer) getIntent().getSerializableExtra("cardIndex");
        //board = Storage.getInstance().getBoards().get(boardIndex);
        board = Storage.getInstance().getBoard(boardIndex);
        listEntry = board.getChildren().get(entryIndex);
        card = listEntry.getChildren().get(cardIndex);
        commentBoardName.setText(listEntry.getName());
        commentBoardDesc.setText(listEntry.getDesc());
        addCommentButton = (Button) findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.add_comment_edittext);
                Storage.getInstance().addComment(new Comment(editText.toString(),null));
            }
        });
        deleteCurrentCardButton = (Button) findViewById(R.id.delete_current_button);
        deleteCurrentCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().removeCard(card);
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
