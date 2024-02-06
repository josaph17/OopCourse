package ru.academits.dashiev.temperature.model;

public interface Converter {
    String[] getScalesNamesStringsArray();

    double convertTemperatureFromInputToOutput(String inputScaleName, String outputScaleName, double inputTemperature);
}
