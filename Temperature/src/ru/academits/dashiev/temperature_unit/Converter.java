package ru.academits.dashiev.temperature_unit;

public class Converter {
    public static Double convert(ITemperatureUnit temperature1, ITemperatureUnit temperature2, Double value) {
        return temperature2.initFromCelsius(temperature1.calculateCelsius(value));
    }
}