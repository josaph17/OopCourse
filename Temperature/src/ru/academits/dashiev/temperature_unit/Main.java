package ru.academits.dashiev.temperature_unit;

public class Main {
    public static void main(String[] args) {
        Double value = 99.14;

        Fahrenheit temperature1 = new Fahrenheit();
        Kelvin temperature2 = new Kelvin();

        Double finishTemperature = Converter.convert(temperature1, temperature2, value);

        System.out.println("Convert " + value + " Fahrenheit in Kelvin: " + finishTemperature);
    }
}
