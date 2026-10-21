package com.exercises.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWith {
    static void main () {
        try (Scanner scanner = new Scanner(new File("text.txt"));
                CloseableDemo closeable = new CloseableDemo()){
            while (scanner.hasNext()) {
                IO.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            IO.println(e.getMessage());
        }

    }
}
class CloseableDemo implements AutoCloseable {
    @Override
    public void close() throws RuntimeException {
        IO.println("Closing...");
    }
}
