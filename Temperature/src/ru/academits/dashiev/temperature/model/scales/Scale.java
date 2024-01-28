package ru.academits.dashiev.temperature.model.scales;

public interface Scale {
    public double convertToCelsius(double currentTemperature);

    public double convertFromCelsius(double basicTemperature);
}