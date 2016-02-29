package com.example.pk.minitrello.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class MiniTrelloContainers<T> {
    private List<T> children;
    private String name;
    private String desc;

    public MiniTrelloContainers(String name, String desc){
        children = new ArrayList<T>();
        this.name = name;
        this.desc = desc;
    }

    public void add(T child){
        children.add(child);
    }

    public void removeChild(T child){
        children.remove(child);
    }

    public void clear(){
        children.clear();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
