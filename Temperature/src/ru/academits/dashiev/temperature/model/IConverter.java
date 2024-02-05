package ru.academits.dashiev.temperature.model;

public interface IConverter {
    public String[] getScalesArray();

    public double convertTemperatureFromInputToOutput(String inputScaleName, String outputScaleName, double inputTemperature);
}
