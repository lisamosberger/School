package com.exercises.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Uppgift2 {

    static void main (){
        //First and last element in stream
        getCountries().stream().findFirst().ifPresent(IO::println);

        getCountries().stream().reduce((t1, t2) ->t2).ifPresent(IO::println);

        getCountries().stream()
                .map(Country::countryName)
                .skip(getCountries().size())
                .forEach(IO::println);

        //Sorting increasing
        //Soting only the countries by name, not the whole object left
        getCountries().stream()
                .map(Country::countryName)
                .sorted()
                .forEach(IO::println);

        //Sorting objects after population
        getCountries().stream().sorted(Comparator.comparing(Country::population).reversed())
                .forEach(IO::println);

        //Max population
        getCountries().stream()
                .max(Comparator.comparing(Country::population))
                .map(c->String.format("Max population: %.0f",c.population * 1_000_000))
                .ifPresent(IO::println);

        //Mean area
        double average = getCountries().stream()
                .mapToInt(Country::area)
                .average()
                .orElse(0.0);
        IO.println(String.format("Average area: %,.2f", average));
        IO.println(getCountries().stream().filter(c->c.area() < average).count());

        getCountries().stream().filter(c->c.population < 5.0)
                .map(Country::countryName)
                .collect(Collectors.joining(" "));

        //Countries and capital starts with same letter
        getCountries().stream()
                .filter(c->c.countryName.charAt(0) == c.capital().charAt(0))
                .map(c->c.countryName() + " " + c.capital())
                .forEach(IO::println);

        //Countries with more letters in the name than in the capital
        getCountries().stream()
                .filter(c->c.countryName().length() > c.capital().length())
                .map(Country::countryName)
                .forEach(IO::println);

        //5 smallest population
        getCountries().stream()
                .sorted(Comparator.comparing(Country::population))
                .limit(5)
                .map(Country::countryName)
                .forEach(IO::println);

        //3 first countries with > 7 mil
        getCountries().stream()
                .filter(c -> c.population() > 7.0)
                .sorted(Comparator.comparing(Country::population))
                .limit(3)
                .map(Country::countryName)
                .forEach(IO::println);

        //Exercise 13
        getCountries().stream()
                .filter(c-> c.area() >= 500000)
                .sorted(Comparator.comparing(Country::area).reversed())
                .limit(3)
                .forEach(c-> IO.println(c.countryName + "," + c.area()));

        var result = getCountries().stream()
                .collect(Collectors.groupingBy(c -> c.countryName().charAt(0), Collectors.counting()));
        result.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(IO::println);

        //Exercise 15
        var pop = getCountries().stream()
                .collect(Collectors.groupingBy(c->Math.floor(c.population()),
                        TreeMap::new,
                        Collectors.toList() ));

        pop.forEach((key, value) -> IO.println(key + " miljoner: " + value.stream()
                .map(country -> country.countryName())
                .collect(Collectors.joining(", "))));

        //Exercise 16
        record  NameAndPopulation(String name, long population){}
        getCountries().stream()
                .map(c-> new NameAndPopulation(c.countryName(), Math.round(c.population() * 1_000_000)))
                .forEach(IO::println);

        //Exercise 17
        getCountries().stream()
                .sorted(Comparator.comparing(c->new StringBuilder(c.countryName()).reverse()))
                .forEach(IO::println);

        //Exercise 18
        var minTotal = getCountries().stream().sorted(Comparator.comparing(Country::population))
                .limit(6)
                .mapToDouble(Country::population)
                .sum();

        var maxTotal = getCountries().stream().sorted(Comparator.comparing(Country::population).reversed())
                .limit(3)
                .mapToDouble(Country::population)
                .sum();
        IO.println("Min: " + minTotal);
        IO.println("Max:" + maxTotal);

        //Exercise 19

        IO.println(getCountries().stream().filter(c->c.countryName.length() == 7).mapToDouble(Country::population)
                .sum());

        IO.println(getCountries().stream().anyMatch(c->c.population() >80.0));

        //Exercise 21:Mitten-flattning / Sammanslagning med flatMap (Om listan utökas)

        IO.println("Exercise 21:Mitten-flattning / Sammanslagning med flatMap (Om listan utökas)");
        record CountryCities(String countryName, List<String> cities) { //Eget record, Country har inga städer
        }
        List<CountryCities> landMedStader = List.of(
                new CountryCities("Sverige", List.of("Stockholm", "Göteborg", "Malmö")),
                new CountryCities("Norge", List.of("Oslo", "Bergen", "Trondheim")),
                new CountryCities("Danmark", List.of("Köpenhamn", "Aarhus", "Odense")),
                new CountryCities("Finland", List.of("Helsingfors", "Esbo", "Tammerfors")));

        List<String> allaStader = landMedStader.stream()
                .flatMap(country -> country.cities().stream()) //Varje land blir en stream av städer, flatMap slår ihop dem till en
                .toList();
        IO.println("Alla städer: " + allaStader);
    }








    private static List<Country> getCountries() {
        return List.of(
                new Country("Sverige", "Stockholm", 10.07, 450295),
                new Country("Norge", "Oslo", 5.27, 323802),
                new Country("Island", "Reykjavik", 0.33, 102775),
                new Country("Danmark", "Köpenhamn", 5.75, 42931),
                new Country("Finland", "Helsinki", 5.51, 338424),
                new Country("Belgien", "Bryssel", 11.30, 30528),
                new Country("Tyskland", "Berlin", 82.18, 357168),
                new Country("Frankrike", "Paris", 66.99, 640679),
                new Country("Storbritannien", "London", 60.80, 209331),
                new Country("Niue", "Alofi", 0.0016, 261),
                new Country("Mongoliet", "Ulan Batar", 3.08, 1566000),
                new Country("Polen", "Warszawa", 38.63, 312679),
                new Country("Spanien", "Madrid", 46.5, 505990),
                new Country("Portugal", "Lissabon", 10.31, 92212),
                new Country("Italien", "Rom", 60.59, 301338),
                new Country("Grekland", "Aten", 11.18, 131957),
                new Country("Luxemburg", "Luxemburg", 0.58, 2586),
                new Country("Liechtenstein", "Vaduz", 0.038, 160));
    }


    record Country(String countryName, String capital, double population, int area) {
    }

}
