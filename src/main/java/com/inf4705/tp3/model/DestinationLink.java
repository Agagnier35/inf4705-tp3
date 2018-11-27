package com.inf4705.tp3.model;

public class DestinationLink {
    private Destination destination;
    private int time;

    public DestinationLink(Destination destination, int time) {
        this.destination = destination;
        this.time = time;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }
}
