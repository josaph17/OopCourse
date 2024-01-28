package ru.academits.dashiev.temperature.model;

public interface IConverter {
    public String[] getScalesString();

    public double getTemperatureFromInputToOutput(String inputTemperatureName, String outputTemperatureName, double inputTemperature);
}
