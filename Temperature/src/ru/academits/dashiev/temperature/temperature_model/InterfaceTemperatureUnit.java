package ru.academits.dashiev.temperature.temperature_model;

public interface InterfaceTemperatureUnit {
    public double convertFromCurrentToBasic(double currentTemperature);

    public double convertFromBasicToCurrent(double basicTemperature);

    // return name of unit
    public String toString();
}