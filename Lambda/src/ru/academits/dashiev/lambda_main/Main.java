package ru.academits.dashiev.lambda_main;

import ru.academits.dashiev.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person("Иван", 23));
        persons.add(new Person("Сергей", 44));
        persons.add(new Person("Джон", 16));
        persons.add(new Person("Лиза", 20));
        persons.add(new Person("Сергей", 15));
        persons.add(new Person("Анна", 21));
        persons.add(new Person("Джон", 33));
        persons.add(new Person("Джон", 10)); // 8 человек

        System.out.println("Изначальный список: ");
        persons.forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("--------------");

        // А) получить список униковльных имен
        List<String> uniqueNamesPersonsList = persons.stream().map(x -> x.getName()).distinct().collect(
                Collectors.toList());

        // Б) вывести список уникальных имен
        System.out.println("Б) Имена: " + uniqueNamesPersonsList.stream().collect(Collectors.joining(", ")));

        // В) получить список людей младше 18 и посчитать для них средний возраст
        List<Person> younger18PersonsList = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("В) List людей младше 18: " + younger18PersonsList);

        younger18PersonsList.stream()
                .mapToInt(x-> x.getAge())
                .average()
                .stream()
                .mapToInt(x->(int)x)
                .findFirst()
                .ifPresent(x->System.out.println("Средний возраст людей младше 18: " + x));

        // Г) при помощи группировки получить Map, в котором ключи - имена, а значения - средний возраст

        /* Map<String, List<Person>> neoPeople = people.stream().collect(Collectors.groupingBy(p-> p.getName());
         -- прошу не считать за ошибку, Приготитсяздать Map, где ключ - имя, значение List<Person>, зо*/
        /* Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
                -- прошу не считать за ошибку, создать Map, где ключ - возраст*/

        Map<String, Double> personsMap = persons.stream()
               .collect(Collectors.groupingBy(p->p.getName(), Collectors.averagingInt(p->p.getAge())));

        System.out.println("Г) peopleMap: " + personsMap);

        // Д) от 20 до 45
        String from20To45Persons =  persons.stream()
                .filter(x-> x.getAge()>=20 && x.getAge()<=45 )
                .sorted((p1,p2)->p2.getAge()-p1.getAge())
                .map(x->x.getName())
                .collect(Collectors.joining(", "));

        System.out.println("Д) Люди от 20 до 45: " + from20To45Persons);
    }
}
