package ru.academits.dashiev.temperature.model.scales;

public interface Scale {
    public double convertToCelsius(double currentScaleTemperature);

    public double convertFromCelsius(double celsiusTemperature);
}