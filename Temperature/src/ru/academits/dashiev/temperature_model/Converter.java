package ru.academits.dashiev.temperature_model;

public class Converter {
    public static void convert(ITemperatureUnit temperature1, ITemperatureUnit temperature2) {
        temperature2.setValue(temperature2.getInitValueFromCelsius(temperature1.getCelsius()));
    }
}