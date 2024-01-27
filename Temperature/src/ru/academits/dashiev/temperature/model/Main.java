package ru.academits.dashiev.temperature.model;

public class Main {
    public static void main(String[] args) {
        double inputValue = 99.14;

        Fahrenheit temperature1 = new Fahrenheit();
        Kelvin temperature2 = new Kelvin();

        double outputValue1 = temperature1.convertFromCurrentToBasic(inputValue);
        double outputValue2 = temperature2.convertFromBasicToCurrent(outputValue1);

        System.out.println("Convert " + inputValue + " Fahrenheit in Kelvin: " + outputValue2);
    }
}