package com.exercises.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExceptionDemo {

    static void main () throws IOException {
        try {
            divide(1, 0);
        } catch (Exception e) {
            switch (e) {
                case ArithmeticException _-> IO.println("ArithmeticException");
                default -> IO.println("Unexpected exception" + e.getMessage());
            }
        }
        finally {
            IO.println("Finally");
        }

    }

    public static int divide (int a, int b) {
        return a/b;
    }

    class Storage {
        private final String text;

        Storage(String text) {
            if (text == null) {
                throw new IllegalArgumentException("The text cant be null");
            }
            this.text = text;
        }

    }
    class StorageException extends RuntimeException {
        private final String text;
        public StorageException(String message, String text) {
            super(message);
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

}
