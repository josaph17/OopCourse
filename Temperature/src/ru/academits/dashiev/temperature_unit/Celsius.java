package ru.academits.dashiev.temperature_unit;

public class Celsius implements ITemperatureUnit {
    @Override
    public Double calculateCelsius(Double value) {
        return value;
    }

    @Override
    public Double initFromCelsius(Double value) {
        return value;
    }
}