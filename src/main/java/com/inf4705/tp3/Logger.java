package com.inf4705.tp3;

import com.inf4705.tp3.model.Destination;
import com.inf4705.tp3.model.Solution;

import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    public static boolean p = false;


    public static void logResult(Solution solution) {
        if (p) {
            System.out.println(solution.toString());
        } else {
            System.out.print(solution.getAppreciation());
        }
    }
}
