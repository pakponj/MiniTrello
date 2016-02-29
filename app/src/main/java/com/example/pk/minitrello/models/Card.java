package com.example.pk.minitrello.models;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class Card extends MiniTrelloContainers<Comment> {
    public Card(String name,String desc){
        super(name,desc);
    }
}
