package com.exercises.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Elephant {
    static void main (){

        List<String> userIds = List.of("user1", "user2", "user3");

        //find all emails for user
        var emails = userIds.stream()
                .map(userId -> findEmail(userId))
                .filter(Optional::isPresent)
                .flatMap(Optional::stream)// put away the empty streams
                .collect(Collectors.joining(",  "));
        IO.println(emails);

        userIds.stream().map(Elephant::findAllEmails)
                .flatMap(List::stream)
                .forEach(IO::println);

        var result = userIds.stream().
                filter(userId -> userId.equals("user1"))
                .findAny();

        List<User> users = new ArrayList<>(List.of(
                new User("Martin", true),
                new User("Fred", true)));

        users.add(null);
        users.stream()
                .map(User-> Optional.ofNullable(User))
                .flatMap(Optional::stream)
                .map(User::name)
                .forEach(IO::println);

        User user = new User("Lisa", true);
        Random random = new Random();
        String displayName = Optional.ofNullable(user.name())
                .orElse("Guest" + random.nextInt(0, 100)); //If null return nothing.

        IO.println(displayName);

    }
    record User (String name, boolean enabled){}
    /**
     * @param userId
     * @return email adress for userId. null if no mail exists.
     */
    static Optional<String> findEmail(String userId) {
        if (userId.equals("user2")){
            return Optional.empty();
        }
        return Optional.of(userId + "@company.com");
    }

    static List<String> findAllEmails(String userId) {
        if (userId.equals("user1")) {
            return List.of(userId + "@company.com", "hotBaby@hotmail.com");
        }
        if (userId.equals("user2")) {
            return List.of();
        }

        return List.of(userId + "@comapny.com");
    }


}
