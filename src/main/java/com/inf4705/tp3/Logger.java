package com.inf4705.tp3;

import com.inf4705.tp3.model.Destination;

import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    public static boolean p = false;

    public static void logLine(String text) {
        System.out.println(text);
    }


    public static int logResult(int bestSolutionScore, List<Destination> res) {
        int resScore = getSolutionScore(res);
        if (bestSolutionScore < resScore) {
            if (p) {
                logLine(res.stream().map(d -> String.valueOf(d.getId())).collect(Collectors.joining(" ")));
            } else {
                logLine(String.valueOf(resScore));
            }
            return resScore;
        }
        return bestSolutionScore;
    }

    private static int getSolutionScore(List<Destination> res) {
        return res.stream().mapToInt(Destination::getAppreciation).sum();
    }
}
