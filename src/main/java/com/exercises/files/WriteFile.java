package com.exercises.files;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteFile {
    static void main () throws IOException {
        String homeFolder = System.getProperty("user.home");
        Path homePath = Path.of(homeFolder);
        IO.println(homePath);

        Path tempFile = Files.createTempFile("prefix-", ".tmp");
        IO.println(tempFile);
        Path savePath = homePath.resolve("java26");

        Files.createDirectories(savePath);
        IO.println("Is directory: " + Files.isDirectory(savePath));
        IO.println("Is file: " + Files.isRegularFile(savePath));
        Path filepath = savePath.resolve("file.txt");

        try {
            Files.createFile(filepath);
        } catch (FileAlreadyExistsException e) {
            IO.println("File " + filepath + "already exists");
        }
        Files.writeString(filepath, "I am Fine");

    }
}
