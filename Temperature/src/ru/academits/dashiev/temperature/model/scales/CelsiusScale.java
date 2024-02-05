package ru.academits.dashiev.temperature.model.scales;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double currentScaleTemperature) {
        return currentScaleTemperature;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}