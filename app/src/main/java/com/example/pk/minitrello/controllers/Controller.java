package com.example.pk.minitrello.controllers;

import java.util.ArrayList;
import java.util.List;

public class Controller<T> {

    private List<T> children;

    public Controller() {
        children = new ArrayList<T>();
    }

    public void addChild(T t) {
        children.add(t);
    }

    public void removeChild(T t) {
        children.remove(t);
    }

    public void clearAll() {
        children.clear();
    }

    public List<T> getChildren(){
        return this.children;
    }

    public T getChild(T t){
        return this.children.get(children.indexOf(t));
    }

}
