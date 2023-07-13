package ru.academits.dashiev.temperature_model;

public interface ITemperatureUnit {
    public Double getCelsius();
    public Double getInitValueFromCelsius(Double celsiusValue);

    public Double getValue();

    public void setValue(Double value);
}