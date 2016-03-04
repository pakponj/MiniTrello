package com.example.pk.minitrello.models;

import android.content.Context;
import android.util.Log;

import com.example.pk.minitrello.controllers.ListEntryController;
import com.example.pk.minitrello.controllers.BoardController;
import com.example.pk.minitrello.controllers.CommentController;
import com.example.pk.minitrello.controllers.CardController;
import com.example.pk.minitrello.views.CardRecycleViewAdapter;
import com.example.pk.minitrello.views.ListEntryRecyclerViewAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pipatpol on 2559-03-01.
 */
public class Storage {

    static final String filename = "miniTrelloData";

    private static Storage storage;

    private BoardController boards;
    private ListEntryController listEntries;
    private CommentController comments;
    private CardController cards;

    private List<Board> boardList;
    private List<ListEntry> listEntryList;
    private List<Card> cardList;
    private List<Comment> commentList;

    private ListEntryRecyclerViewAdapter listEntryRecyclerViewAdapter;
    private CardRecycleViewAdapter cardRecycleViewAdapter;

    private Storage(){
        this.boards = new BoardController();
        this.listEntries = new ListEntryController();
        this.cards = new CardController();
        this.comments = new CommentController();

    }

    public static Storage getInstance(){
        if(storage == null) storage = new Storage();
        return storage;
    }

    public void addBoard(Board board){
        this.boards.addChild(board);
    }

    public void addListEntry(ListEntry listEntry){
        this.listEntries.addChild(listEntry);
    }

    public void addCard(Card card) {
        this.cards.addChild(card);
    }

    public void addComment(Comment comment){
        this.comments.addChild(comment);
    }

    public void removeBoard(Board board){
        this.boards.removeChild(board);
    }

    public void removeListEntry(ListEntry listEntry){
        this.listEntries.removeChild(listEntry);
    }

    public void removeCard(Card card){
        this.cards.removeChild(card);
    }

    public void removeComment(Comment comment){
        this.comments.removeChild(comment);
    }

    public List<Board> getBoards(){
        return this.boards.getContainers();
    }

    public List<ListEntry> getListEntries(){
        return this.listEntries.getContainers();
    }

    public List<Card> getCards(){
        return this.cards.getContainers();
    }

    public List<Comment> getComments(){
        return this.comments.getContainers();
    }

    public void clearBoards(){
        this.boards.clearAll();
    }

    public void clearListEntries(){
        this.listEntries.clearAll();
    }

    public void clearCards(){
        this.cards.clearAll();
    }

    public void clearComments(){
        this.comments.clearAll();
    }

    public Board getBoard(int index){
        return this.getBoards().get(index);
    }

    public ListEntry getListEntry(int index){
        return this.getListEntries().get(index);
    }

    public Card getCard(int index){
        return this.getCards().get(index);
    }

    public Comment getComment(int index){
        return this.getComments().get(index);
    }

    public void saveData(Context context){
        FileOutputStream outputStream;
        ObjectOutputStream ous = null;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ous = new ObjectOutputStream(outputStream);
            ous.writeObject(boardList);
            ous.writeObject(listEntryList);
            ous.writeObject(cardList);
            ous.writeObject(commentList);
        } catch (Exception e) {
            Log.e("Save", "Got some problem");
        } finally {
            try {
                ous.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadData(Context context){
        if(boardList == null){
            boardList = new ArrayList<Board>();
        }
        if(listEntryList == null){
            listEntryList = new ArrayList<ListEntry>();
        }
        if(cardList == null){
            cardList = new ArrayList<Card>();
        }
        if(commentList == null){
            commentList = new ArrayList<Comment>();
        }
        FileInputStream inputStream;
        ObjectInputStream ois = null;
        try {
            inputStream = context.openFileInput(filename);
            ois = new ObjectInputStream(inputStream);
            boardList = (ArrayList<Board>)ois.readObject();
            listEntryList = (ArrayList<ListEntry>)ois.readObject();
            cardList = (ArrayList<Card>)ois.readObject();
            commentList = (ArrayList<Comment>)ois.readObject();

        } catch (ClassNotFoundException cnfe){
            Log.e("Load data","Serialization problem.");
        } catch (IOException ioe) {
            Log.e("Load data","No miniTrello file.");
        } finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void setListEntryRecyclerViewAdapter(ListEntryRecyclerViewAdapter adapter) {
        this.listEntryRecyclerViewAdapter = adapter;
    }

    public ListEntryRecyclerViewAdapter getListEntryRecyclerViewAdapter() {
        return this.listEntryRecyclerViewAdapter;
    }

    public void setCardRecycleViewAdapter(CardRecycleViewAdapter adapter) {
        this.cardRecycleViewAdapter = adapter;
    }

    public CardRecycleViewAdapter getCardRecycleViewAdapter() {
        return this.cardRecycleViewAdapter;
    }

}
