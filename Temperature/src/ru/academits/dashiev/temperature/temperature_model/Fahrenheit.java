package ru.academits.dashiev.temperature.temperature_model;

public class Fahrenheit implements InterfaceTemperatureUnit {
    @Override
    public double convertFromCurrentToBasic(double currentTemperature) {
        return (currentTemperature - 32) * (5.0 / 9.0);
    }

    @Override
    public double convertFromBasicToCurrent(double basicTemperature) {
        return basicTemperature * (9.0 / 5.0) + 32;
    }

    @Override
    public String toString(){
        return "Fahrenheit";
    };
}