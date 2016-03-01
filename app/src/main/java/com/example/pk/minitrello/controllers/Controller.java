package com.example.pk.minitrello.controllers;

import java.util.ArrayList;
import java.util.List;

public class Controller<T> {

    private List<T> containers;

    public Controller() {
        containers = new ArrayList<T>();
    }

    public void addChild(T t) {
        containers.add(t);
    }

    public void removeChild(T t) {
        containers.remove(t);
    }

    public void clearAll() {
        containers.clear();
    }

    public List<T> getContainers(){
        return this.containers;
    }

    public T getContainer(T t){
        return this.containers.get(containers.indexOf(t));
    }

}
