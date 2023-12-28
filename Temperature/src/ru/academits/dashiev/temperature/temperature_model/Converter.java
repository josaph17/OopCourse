package ru.academits.dashiev.temperature.temperature_model;

public class Converter {
    public static void convert(InterfaceTemperatureUnit inputTemperature, InterfaceTemperatureUnit outputTemperature) {
        outputTemperature.setBasicTemperature(outputTemperatureconvertFromBasicToCurrent(inputTemperature.getBasicTemperature()));
    }
}