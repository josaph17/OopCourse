package ru.academits.dashiev.temperature.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double currentScaleTemperature) {
        return (currentScaleTemperature - 32) * (5.0 / 9.0);
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature * (9.0 / 5.0) + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}