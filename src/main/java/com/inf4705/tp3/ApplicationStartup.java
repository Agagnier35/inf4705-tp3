package com.inf4705.tp3;

import com.inf4705.tp3.algorithm.PathOptimizer;
import com.inf4705.tp3.fileReader.DecryptedFile;
import com.inf4705.tp3.fileReader.FileReader;
import com.inf4705.tp3.fileWriter.Writer;
import com.inf4705.tp3.model.Solution;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class ApplicationStartup {
    private static String filePath = "";
    private static Boolean absolutePath = false;

    public static void main(String[] args) throws Exception {
        Writer writer = new Writer();

        setUpOptionalArguments(args);

        DecryptedFile file;
        if(absolutePath) {
            file = new FileReader(filePath).readFile();
        } else {
            file = new FileReader(getJarPath() + filePath).readFile();
        }

        PathOptimizer optimizer = new PathOptimizer();

        // Print best solution in file when closing
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Solution currentBestSolution = optimizer.getBestSolution();
                try {
                    writer.printSolution(currentBestSolution);
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });

        // Print the best solution when algorithm gets the exact answer
        Solution bestSolution = optimizer.optimizePath(file.getDestination(), file.getTimeLimit());
        try {
            writer.printSolution(bestSolution);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void setUpOptionalArguments(String[] args) {
        for (String arg: args) {
            if("-p".equals(arg)) {
                Logger.p = true;
            } else if("-e".equals(arg)) {
                absolutePath = true;
            } else if("".equals(filePath)) {
                filePath = arg;
            }
        }
    }

    private static String getJarPath() throws UnsupportedEncodingException {
        URL url = ApplicationStartup.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
}
