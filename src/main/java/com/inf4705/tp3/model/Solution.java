package com.inf4705.tp3.model;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int getTime() {
        return time;
    }

    public Solution(int appreciation, List<Integer> path) {
        this.appreciation = appreciation;
        this.path = path;
        this.time = 0;
    }

    public Solution(Solution solution, DestinationLink destinationLink) {
        Destination destination = destinationLink.getDestination();
        this.appreciation = solution.getAppreciation() + destination.getAppreciation();
        this.time = solution.time + destinationLink.getTime();
        this.path = new ArrayList<>(solution.getPath());
        this.path.add(destination.getId());
    }

    public String toString() {
        String value = "";
        for(Integer id : this.path) {
            value += id + " ";
        }
        return value;
    }

    public void addDestination(Destination destination) {
        this.appreciation += destination.getAppreciation();
        this.path.add(destination.getId());
    }

    public int getAppreciation() {
        return appreciation;
    }

    public List<Integer> getPath() {
        return path;
    }

    private int appreciation;
    private int time;
    private List<Integer> path;
}
