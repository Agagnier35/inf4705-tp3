package com.inf4705.tp3.fileWriter;

import com.inf4705.tp3.ApplicationStartup;
import com.inf4705.tp3.model.Solution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class Writer {
    public void printSolution(Solution solution) throws UnsupportedEncodingException{
        File file =  new File(getJarPath() + "/resultats.txt");
        try (FileWriter fr = new FileWriter(file, false)) {
            file.createNewFile();
            fr.write(solution.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getJarPath() throws UnsupportedEncodingException {
        URL url = ApplicationStartup.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
}


