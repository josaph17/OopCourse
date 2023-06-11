package ru.academits.dashiev.temperature_unit;

public class Kelvin implements ITemperature {
    public static final String NAME = "KELVIN";

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
        return value;
    }

    @Override
    public Double calculateFahrenheit() {
        return (value - 273.15) * (9.0 / 5.0) + 32;
    }

    @Override
    public Double calculateCelsius() {
        return value - 273.15;
    }
}