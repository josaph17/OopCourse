package ru.academits.dashiev.temperature.temperature_model;

public class Main {
    public static void main(String[] args) {
        Double value = 99.14;

        Fahrenheit temperature1 = new Fahrenheit(value);
        Kelvin temperature2 = new Kelvin(0.0);

        Converter.convert(temperature1, temperature2);

        System.out.println("Convert " + value + " Fahrenheit in Kelvin: " + temperature2.getCu2rrentTemperature());
    }
}