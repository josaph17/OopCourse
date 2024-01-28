package ru.academits.dashiev.temperature.model.scales;

public class Fahrenheit implements Scale {
    @Override
    public double convertToCelsius(double currentTemperature) {
        return (currentTemperature - 32) * (5.0 / 9.0);
    }

    @Override
    public double convertFromCelsius(double basicTemperature) {
        return basicTemperature * (9.0 / 5.0) + 32;
    }

    public String toString() {
        return "Fahrenheit";
    }
}