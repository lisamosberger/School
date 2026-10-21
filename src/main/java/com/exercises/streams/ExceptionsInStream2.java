package com.exercises.streams;


import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NullMarked
public class ExceptionsInStream2 {

    static void main() {
        List<String> rawNumbers = List.of("10", "20", "foo", "30");

        Map<Boolean, List<Either<String, Integer>>> result = rawNumbers.stream()
                .map(s -> parseToInt(s))
                .collect(Collectors.partitioningBy(Either::isRight));

        //Right values
        result.get(true).stream()
                .map(Either::getRight)
                .flatMap(Optional::stream)
                .forEach(IO::println);

        //Left values
        result.get(false).stream()
                .map(Either::getLeft)
                .flatMap(Optional::stream)
                .forEach(IO::println);
    }

    static @NonNull Either<String, Integer> parseToInt(String s) {
        try {
            return new Either.Right<>(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return new Either.Left<>("Error when converting " + s);
        }
    }

    public sealed interface Either<L, R> permits Either.Left, Either.Right {
        record Left<L, R>(L value) implements Either<L, R> {
        }

        record Right<L, R>(R value) implements Either<L, R> {
        }

        default Optional<R> getRight(){
            return this instanceof Right<L,R> r ? Optional.of(r.value()) : Optional.empty();
        }
        default Optional<R> getLeft(){
            return this instanceof Right<L,R> l ? Optional.of(l.value()) : Optional.empty();
        }

        default boolean isRight() {
            return this instanceof Right;
        }
    }
}