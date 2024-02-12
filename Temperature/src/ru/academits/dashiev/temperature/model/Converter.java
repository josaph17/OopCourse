package ru.academits.dashiev.temperature.model;

public interface Converter {
    String[] getScalesNames();

    double convertTemperature(String inputScaleName, String outputScaleName, double inputTemperature);
}
