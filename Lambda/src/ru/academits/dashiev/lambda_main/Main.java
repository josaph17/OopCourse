package ru.academits.dashiev.lambda_main;

import ru.academits.dashiev.person.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new LinkedList<>();
        people.add(new Person("Иван", 23));
        people.add(new Person("Сергей", 27));
        people.add(new Person("Петр", 16));
        people.add(new Person("Лиза", 20));
        people.add(new Person("Сергей", 15));
        people.add(new Person("Анна", 21));
        people.add(new Person("Джон", 23));
        people.add(new Person("Джон", 9)); // 8 человек

        // А) получить список униковльных имен
        List<String> uniqueNames = people.stream().map(x -> x.getName()).distinct().collect(
                Collectors.toList());

        // Б) вывести список уникальных имен
        System.out.println("Имена: " + uniqueNames.stream().collect(Collectors.joining(", ")));

        // В) получить список людей младше 18 и посчитать для них средний возраст
        List<Person> peopleYounger18 = people.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("Младше 18: " + peopleYounger18);

        peopleYounger18.stream()
                .mapToInt(x-> x.getAge())
                .average()
                .stream()
                .mapToInt(x->(int)x)
                .findFirst()
                .ifPresent(x->System.out.println("Средний возраст людей до 18 лет: " + x));

        // Г) при помощи группировки получить Map, в котором ключи - имена, а значения - средний возраст

        // Д) от 20 до 45
        Stream<String> peopleFrom18To45 = people.stream()
                        .filter(x-> x.getAge()>=20 && x.getAge()<=45 )
                        .sorted((p1,p2)->p2.getAge()-p1.getAge())
                        .map(x->x.getName());

        System.out.println("От 20 до 45: " + peopleFrom18To45.collect(Collectors.joining(", ")));
    }
}
