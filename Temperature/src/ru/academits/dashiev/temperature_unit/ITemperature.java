package ru.academits.dashiev.temperature_unit;
public interface ITemperature {
    public abstract String getName();

    public abstract Double getValue();

    public abstract void setValue(Double value);

    public abstract Double calculateKelvin();

    public abstract Double calculateFahrenheit();

    public abstract Double calculateCelsius();
}