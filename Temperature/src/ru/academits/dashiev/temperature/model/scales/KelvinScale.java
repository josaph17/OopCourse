package ru.academits.dashiev.temperature.model.scales;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double currentScaleTemperature) {
        return currentScaleTemperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}