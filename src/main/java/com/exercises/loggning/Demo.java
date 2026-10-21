package com.exercises.loggning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {


    static void main () {
Logger logger = Logger.getLogger(Demo.class.getName());
logger.setLevel(Level.SEVERE);

        IO.println("Starting application...");

    try {
        logger.info("Trying to read config");
        Files.readString(Path.of("config"));
        logger.info("config read");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading config", e);
        return;
    }

        IO.println("Closing aplication...");
    }

}
