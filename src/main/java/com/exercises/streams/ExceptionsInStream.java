package com.exercises.streams;

import java.util.List;
import java.util.Optional;

public class ExceptionsInStream {

    static void main() {
        List<String> rawNumbers = List.of("10", "20", "foo", "30");

        List<Integer> numbers = rawNumbers.stream()
                .map(s -> parseToInt(s))//From string to integer object
                .flatMap(Optional::stream)
                .toList();

        numbers.forEach(IO::println);
    }
    static Optional<Integer> parseToInt(String s){ //method to manage errors
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}

