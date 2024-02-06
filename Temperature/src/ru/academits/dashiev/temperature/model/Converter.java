package ru.academits.dashiev.temperature.model;

public interface Converter {
    String[] getScalesArray();

    double convertTemperatureFromInputToOutput(String inputScaleName, String outputScaleName, double inputTemperature);
}
