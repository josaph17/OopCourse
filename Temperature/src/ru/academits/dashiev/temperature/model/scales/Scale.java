package ru.academits.dashiev.temperature.model.scales;

public interface Scale {
    double convertToCelsius(double currentScaleTemperature);

    double convertFromCelsius(double celsiusTemperature);
}