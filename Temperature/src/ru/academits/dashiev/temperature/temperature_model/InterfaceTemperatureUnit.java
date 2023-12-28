package ru.academits.dashiev.temperature.temperature_model;

public interface InterfaceTemperatureUnit {
    public double convertFromCurrentToBasic(double currentTemperature);

    public double convertFromBasicToCurrent(double basicTemperature);

    // return name of unit
    public String toString();
}

//    public double getCurrentTemperature();
//
//    // Basic temperature is Celsius
//    public double getBasicTemperature();
//
//    public double setCurrentTemperature(double value);
//
//    public void setBasicTemperature(double value);