package ru.academits.dashiev.lambda_person;

public record Person(String name, int age) {
    public Person {
        if (age <= 0) {
            throw new IllegalArgumentException("Wrong age value. Now age = " + age);
        }
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}