package ru.academits.dashiev.temperature_unit;

public class Fahrenheit implements ITemperature {
    public static final String NAME = "FAHRENHEIT";

    private Double value;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double calculateKelvin() {
        return (value - 32) * (5.0 / 9.0) + 273.15;
    }

    @Override
    public Double calculateFahrenheit() {
        return value;
    }

    @Override
    public Double calculateCelsius() {
        return (value - 32) * (5.0 / 9.0);
    }
}