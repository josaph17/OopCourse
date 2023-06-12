package ru.academits.dashiev.temperature_unit;

public class Fahrenheit implements ITemperatureUnit {
    @Override
    public Double calculateCelsius(Double value) {
        return (value - 32) * (5.0 / 9.0);
    }

    @Override
    public Double initFromCelsius(Double value) {
        return value * (9.0 / 5.0) + 32;
    }
}