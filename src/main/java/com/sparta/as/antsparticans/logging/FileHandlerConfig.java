package com.sparta.as.antsparticans.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class FileHandlerConfig {

    public static FileHandler getFileHandler() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("src/main/resources.log", false);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IllegalArgumentException | SecurityException | IOException e) {
            e.printStackTrace();
        }
        return fileHandler;
    }

}


