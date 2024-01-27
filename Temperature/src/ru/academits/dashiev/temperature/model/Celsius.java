package ru.academits.dashiev.temperature.model;

public class Celsius implements InterfaceTemperatureUnit {
    @Override
    public double convertFromCurrentToBasic(double currentTemperature) {
        return currentTemperature;
    }

    @Override
    public double convertFromBasicToCurrent(double basicTemperature) {
        return basicTemperature;
    }

    @Override
    public String toString(){
        return "Celsius";
    }
}