package ru.academits.dashiev.lambda_person;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (age <= 0 || age > 86) {
            throw new IllegalArgumentException("Wrong age value. Now age = " + age);
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}