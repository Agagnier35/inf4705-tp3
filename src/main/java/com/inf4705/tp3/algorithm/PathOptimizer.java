package com.inf4705.tp3.algorithm;

import com.inf4705.tp3.Logger;
import com.inf4705.tp3.model.Destination;
import com.inf4705.tp3.model.DestinationLink;
import com.inf4705.tp3.model.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PathOptimizer {
    public Solution getBestSolution() {
        return bestSolution;
    }

    private Solution bestSolution;
    private int timeLimit;

    public Solution optimizePath(List<Destination> destination, int time) {

        Destination initialDestination = destination.get(0);
        List<Integer> initialPath = new ArrayList<>(0);
        initialPath.add(initialDestination.getId());

        Solution initialSolution = new Solution(initialDestination.getAppreciation(), initialPath);

        bestSolution = initialSolution;
        timeLimit = time;

        parcours(initialDestination, initialSolution);

        return bestSolution;
    }

    private void parcours(Destination destination, Solution currentSolution) {
        // if the current destination id is equal to the initialDestination id
        if(destination.getId() == currentSolution.getPath().get(0) && currentSolution.getPath().size() > 1 && currentSolution.getTime() < timeLimit) {
            // Its a full cycle
            // Log if appreciation is higher
            if(currentSolution.getAppreciation() > bestSolution.getAppreciation()) {
                bestSolution = currentSolution;
                Logger.logResult(bestSolution);
            }
        } else if(currentSolution.getTime() < timeLimit) {
            List<DestinationLink> currentDestinationsLinks = new ArrayList<>(destination.getLinks());
            // Go back to 0 first (Ensure an answer at each node of the tree)
            Solution firstSolution = new Solution(currentSolution, currentDestinationsLinks.get(0));
            if(currentDestinationsLinks.get(0).getDestination().getId() == 0) {
                parcours(currentDestinationsLinks.get(0).getDestination(), firstSolution);
                currentDestinationsLinks.remove(0);
            }

            // Sort by best appreciation/distance ratio
            Collections.sort(currentDestinationsLinks, (DestinationLink d1, DestinationLink d2) -> Double.compare(d2.getRatio(), d1.getRatio()));

            for(DestinationLink link : currentDestinationsLinks) {
                if(!currentSolution.getPath().contains(link.getDestination().getId()) || link.getDestination().getId() == 0) {
                    Solution alternateSolution = new Solution(currentSolution, link);
                    parcours(link.getDestination(), alternateSolution);
                }
            }
        }
    }

}

