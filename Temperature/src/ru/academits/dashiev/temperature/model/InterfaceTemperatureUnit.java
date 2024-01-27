package ru.academits.dashiev.temperature.model;

public interface InterfaceTemperatureUnit {
    public double convertFromCurrentToBasic(double currentTemperature);

    public double convertFromBasicToCurrent(double basicTemperature);

    // return name of unit
    public String toString();
}