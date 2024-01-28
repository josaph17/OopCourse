package ru.academits.dashiev.temperature.model.scales;

public class Celsius implements Scale {
    @Override
    public double convertToCelsius(double currentTemperature) {
        return currentTemperature;
    }

    @Override
    public double convertFromCelsius(double basicTemperature) {
        return basicTemperature;
    }

    public String toString(){
        return "Celsius";
    }
}