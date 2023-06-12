package ru.academits.dashiev.temperature_unit;

public class Kelvin implements ITemperatureUnit {
    @Override
    public Double calculateCelsius(Double value) {
        return value - 273.15;
    }

    @Override
    public Double initFromCelsius(Double value) {
        return value + 273.15;
    }
}