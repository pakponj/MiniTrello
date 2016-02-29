package models;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class Board extends MiniTrelloContainers<ListEntry> {
    public Board(String name,String desc){
        super(name,desc);
    }
}
