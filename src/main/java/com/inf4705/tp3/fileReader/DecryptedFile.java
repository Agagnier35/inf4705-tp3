package com.inf4705.tp3.fileReader;

import com.inf4705.tp3.model.Destination;

import java.util.ArrayList;
import java.util.List;


public class DecryptedFile {
    private List<Destination> destination = new ArrayList<>();
    private int timeLimit = 0;

    public List<Destination> getDestination() {
        return destination;
    }

    public void setDestination(List<Destination> destination) {
        this.destination = destination;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}
