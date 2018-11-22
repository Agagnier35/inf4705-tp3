package com.inf4705.tp3.model;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private int id;
    private int appreciation;
    private List<DestinationLink> links = new ArrayList<>();

    public Destination(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public int getAppreciation() {
        return appreciation;
    }

    public void addLink(Destination d, int distance){
        links.add(new DestinationLink(d, distance));
    }

    public List<DestinationLink> getLinks(){
        return links;
    }

    public void setAppreciation(int appreciation) {
        this.appreciation = appreciation;
    }
}
