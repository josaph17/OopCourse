package ru.academits.dashiev.temperature_unit;

public class Celsius implements ITemperature {
    public static final String NAME = "CELSIUS";

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
        return value + 273.15;
    }

    @Override
    public Double calculateFahrenheit() {
        return (value * 9.0 / 5.0) + 32.0;
    }

    @Override
    public Double calculateCelsius() {
        return value;
    }
}