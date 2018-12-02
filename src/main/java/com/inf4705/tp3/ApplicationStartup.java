package com.inf4705.tp3;

import com.inf4705.tp3.algorithm.PathOptimizer;
import com.inf4705.tp3.fileReader.DecryptedFile;
import com.inf4705.tp3.fileReader.FileReader;
import com.inf4705.tp3.fileWriter.Writer;
import com.inf4705.tp3.model.Solution;

import java.io.UnsupportedEncodingException;

public class ApplicationStartup {
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        setUpOptionalArguments(args, 1);

        DecryptedFile file = new FileReader(filePath).readFile();

        PathOptimizer optimizer = new PathOptimizer();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Solution bestSolution = optimizer.getBestSolution();
                Writer writer = new Writer();
                try {
                    writer.printSolution(bestSolution);
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });

        optimizer.optimizePath(file.getDestination(), file.getTimeLimit());
    }

    private static void setUpOptionalArguments(String[] args, int index) {
        if (args.length > index) {
            if ("-p".equals(args[index])) {
                Logger.p = true;
            }
        }
    }
}
