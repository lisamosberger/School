package com.exercises.exceptions;

import java.util.stream.Stream;

public class Checked {
    static void main () {

        Stream.of("1","2","3","fyra")
                .map(Checked::convertAndCatch)
                .forEach(IO::println);
    }
    public static Integer convertAndCatch (String text) {
        try {
            return convert(text);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Integer convert(String text) throws Exception{
        return Integer.parseInt(text);
    }
}
