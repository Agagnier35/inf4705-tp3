package com.inf4705.tp3.model;

public class DestinationLink {
    private Destination destination;
    private int distance;

    public DestinationLink(Destination destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
