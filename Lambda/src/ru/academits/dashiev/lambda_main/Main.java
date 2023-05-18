package ru.academits.dashiev.lambda_main;

import ru.academits.dashiev.lambda_person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Задача 1 --");

        List<Person> persons = Arrays.asList(
                new Person("Иван", 23),
                new Person("Сергей", 44),
                new Person("Джон", 16),
                new Person("Лиза", 20),
                new Person("Сергей", 15),
                new Person("Анна", 21),
                new Person("Джон", 33),
                new Person("Джон", 10)
        );

        System.out.println("Изначальный список:");
        persons.forEach(person -> System.out.print(person + " "));
        System.out.println();

        System.out.println();

        // Person::getName вместо x -> x.getName(), прошу не удалять важный комментарий
        // А) получить список уникальных имен, Б) вывести список уникальных имен
        String uniqueNames = persons.stream()
                                    .map(Person::name)
                                    .distinct()
                                    .collect(Collectors.joining(", "));

        System.out.println("А), Б) список уникальных имен: " + uniqueNames);

        // В) получить список людей младше 18 и посчитать для них средний возраст
        List<Person> younger18PersonsList = persons.stream()
                                                   .filter(x -> x.age() < 18)
                                                   .toList();

        if (younger18PersonsList.isEmpty()) {
            System.out.println("B) Can't calculate average!");
        } else {
            // 15. после получения среднего не нужно использовать stream
            younger18PersonsList.stream()
                                .mapToInt(Person::age)
                                .average()
                                .ifPresent(x -> System.out.println("В) Средний возраст людей младше 18: " + x));
        }

        // Г) при помощи группировки получить Map, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> namesAndAverageAges = persons.stream()
                                                         .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        System.out.println("Г) namesAndAverageAges map: " + namesAndAverageAges);

        // Д) от 20 до 45
        String personsFrom20To45 = persons.stream()
                                          .filter(x -> x.age() >= 20 && x.age() <= 45)
                                          .sorted((p1, p2) -> p2.age() - p1.age())
                                          .map(Person::name)
                                          .collect(Collectors.joining(", "));

        System.out.println("Д) Люди от 20 до 45: " + personsFrom20To45);

        System.out.println();

        System.out.println("-- Задача 2 --");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число сколько элементов нужно вычислить: ");
        int rootsCount = scanner.nextInt();

        // Код ниже выполняется в вертикальном порядке
        // Math::sqrt вместо result -> Math.sqrt(result), прошу не удалять важный комментарий

        IntStream.iterate(0, x -> x + 1)
                 .mapToDouble(Math::sqrt)
                 .limit(rootsCount)
                 .forEach(System.out::println);
    }
}