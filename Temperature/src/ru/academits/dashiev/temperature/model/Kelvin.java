package ru.academits.dashiev.temperature.model;

public class Kelvin implements InterfaceTemperatureUnit {
    @Override
    public double convertFromCurrentToBasic(double currentTemperature) {
        return currentTemperature - 273.15;
    }

    @Override
    public double convertFromBasicToCurrent(double basicTemperature) {
        return basicTemperature + 273.15;
    }

    @Override
    public String toString(){
        return "Kelvin";
    }
}