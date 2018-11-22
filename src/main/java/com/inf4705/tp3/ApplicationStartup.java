package com.inf4705.tp3;

import com.inf4705.tp3.algorithm.PathOptimizer;
import com.inf4705.tp3.fileReader.DecryptedFile;
import com.inf4705.tp3.fileReader.FileReader;
import com.inf4705.tp3.model.Destination;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicationStartup {
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        setUpOptionalArguments(args, 1);

        DecryptedFile file = new FileReader(filePath).readFile();

        PathOptimizer optimizer = new PathOptimizer();
        int bestSolutionScore = 0;
        while (true) {//must run until manually stopped
            List<Destination> resultSet = optimizer.optimizePath(file.getDestination(), file.getTimeLimit());
            bestSolutionScore = Logger.logResult(bestSolutionScore, resultSet);
        }
    }

    private static void setUpOptionalArguments(String[] args, int index) {
        if (args.length > index) {
            if ("-p".equals(args[index])) {
                Logger.p = true;
            }
        }
    }
}
