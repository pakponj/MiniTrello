package com.example.pk.minitrello.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.minitrello.R;
import com.example.pk.minitrello.models.Card;

import java.util.List;

public class CardRecycleViewAdapter extends  RecyclerView.Adapter<CardRecycleViewAdapter.CardViewHolder> {

    private List<Card> cards;

    public CardRecycleViewAdapter(List<Card> cards) {
        if( cards == null ) {
            throw new IllegalArgumentException("Cards must not be null");
        }
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_cell, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.subject.setText(card.getName());
    }

    @Override
    public int getItemCount() { return cards.size(); }

    public final static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView subject;
        
        public CardViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
        }
    }

    public void add(Card card, int position) {
        cards.add(position, card);
        notifyItemInserted(position);

    }

    public void remove(Card card) {
        int position = cards.indexOf(card);
        cards.remove(position);
        notifyItemRemoved(position);
    }

}
