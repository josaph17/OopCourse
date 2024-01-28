package ru.academits.dashiev.temperature.model.scales;

public class Kelvin implements Scale {
    @Override
    public double convertToCelsius(double currentTemperature) {
        return currentTemperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double basicTemperature) {
        return basicTemperature + 273.15;
    }

    public String toString() {
        return "Kelvin";
    }
}