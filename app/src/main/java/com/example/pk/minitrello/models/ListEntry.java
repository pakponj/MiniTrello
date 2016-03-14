package com.example.pk.minitrello.models;

import com.example.pk.minitrello.views.CardAdapter;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class ListEntry extends MiniTrelloContainers<Card> {
//    private CardRecycleViewAdapter adapter;
    private CardAdapter cardAdapter;
    public ListEntry(String name,String desc){
        super(name,desc);
    }

    public void setCardAdapter(CardAdapter cardAdapter) {
        this.cardAdapter = cardAdapter;
    }

    public CardAdapter getCardAdapter() {
        return cardAdapter;
    }
//   public void setAdapter(CardRecycleViewAdapter adapter) {this.adapter = adapter;}

    //public CardRecycleViewAdapter getAdapter() {return adapter;}
    //public void setRecyclerView(RecyclerView recyclerView) {this.recyclerView = recyclerView;}
    //public RecyclerView getRecyclerView() {return this.recyclerView;}
}
