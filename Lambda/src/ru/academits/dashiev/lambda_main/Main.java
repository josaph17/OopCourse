package ru.academits.dashiev.lambda_main;

import ru.academits.dashiev.lambda_person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Задача 1 --");

        List<Person> persons = Arrays.asList(new Person("Иван", 23), new Person("Сергей", 44),
                                             new Person("Джон", 16), new Person("Лиза", 20),
                                             new Person("Сергей", 15), new Person("Анна", 21),
                                             new Person("Джон", 33), new Person("Джон", 10));

        System.out.println("Изначальный список: ");
        persons.forEach(person -> System.out.print(person + " "));
        System.out.println();

        System.out.println();

        // А) получить список уникальных имен, Б) вывести список уникальных имен
        String uniqueNames = persons.stream()
                        .map(x -> x.getName())
                        .distinct()
                        .collect(Collectors.joining(", "));

        System.out.println("А), Б) список уникальных имен: " + uniqueNames);

        // В) получить список людей младше 18 и посчитать для них средний возраст
        List<Person> younger18PersonsList = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        if(younger18PersonsList.isEmpty()){
            System.out.println("B) Can't calculate average!");
        } else{
            // 15. после получения среднего не нужно использовать stream
            younger18PersonsList.stream()
                    .mapToInt(x -> x.getAge())
                    .average()
                    .ifPresent(x -> System.out.println("В) Средний возраст людей младше 18: " + x));
        }

        // Г) при помощи группировки получить Map, в котором ключи - имена, а значения - средний возраст

        /* Map<String, List<Person>> neoPeople = people.stream().collect(Collectors.groupingBy(p-> p.getName());
         -- прошу не считать за ошибку, пригодится создать Map, где ключ - имя, значение List<Person>, зо*/
        /* Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
                -- прошу не считать за ошибку, создать Map, где ключ - возраст*/

        Map<String, Double> nameAndAverageAge = persons.stream().collect(Collectors.groupingBy(p -> p.getName(), Collectors.averagingInt(p -> p.getAge())));

        System.out.println("Г) nameAndAverageAge map: " + nameAndAverageAge);

        // Д) от 20 до 45
        String from20To45Persons = persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(x -> x.getName())
                .collect(Collectors.joining(", "));

        System.out.println("Д) Люди от 20 до 45:" + from20To45Persons);

        System.out.println();

        System.out.println("-- Задача 2 --");

        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.print("Введите число для которого следует вычислить корни: ");
        double number = scanner1.nextDouble();

        System.out.print("Введите сколько элементов надо вычислить: ");
        int elementsCount = scanner2.nextInt();

        // Код ниже выполняется в вертикальном порядке
        DoubleStream numberRoots = DoubleStream.iterate(number, result -> Math.sqrt(result))
                .map(result -> Math.sqrt(result))
                .limit(elementsCount);

        numberRoots.forEach(System.out::println);
    }
}