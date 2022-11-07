package ru.academits.dashiev.lambda_main;

import ru.academits.dashiev.person.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new LinkedList<>();
        people.add(new Person("Иван", 33));
        people.add(new Person("Сергей", 27));
        people.add(new Person("Петр", 15));
        people.add(new Person("Лиза", 20));
        people.add(new Person("Сергей", 15));
        people.add(new Person("Анна", 21));
        people.add(new Person("Джон", 23));
        people.add(new Person("Джон", 9)); // 8 человек

        List<String> list1 = people.stream().map(x -> x.getName()).distinct().collect(
                Collectors.toList());

        System.out.println("Имена: " + list1.stream().collect(Collectors.joining(", ")));

        List<Person> list2 = people.stream().filter(x -> x.getAge() < 18).collect(Collectors.toList());
        System.out.println(list2);

        Stream<Integer> stream2 = list2.stream().reduce(0, (memo, e)-> memo+e);
    }
}
