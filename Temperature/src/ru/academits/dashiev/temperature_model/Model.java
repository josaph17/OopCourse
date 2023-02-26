package ru.academits.dashiev.temperature_model;

import ru.academits.dashiev.temperature_unit.Unit;

public class Model {
    private Double inputTemperature;
    private Double outputTemperature;
    private Enum inputUnit;
    private Enum outputUnit;

    public Model() {
    }

    public void setInputTemperature(double inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public double getOutputTemperature() {
        return outputTemperature;
    }

    public void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public void setInputUnit(Enum inputUnit) {
        this.inputUnit = inputUnit;
    }

    public void setOutputUnit(Enum outputUnit) {
        this.outputUnit = outputUnit;
    }

    public Double calculateOutputTemperature() {
        if ((outputUnit.compareTo(Unit.FAHRENHEIT) == 0)) {
            return calculateOutputTemperatureToFahrenheit();
        }

        if (outputUnit.compareTo(Unit.KELVIN) == 0) {
            return calculateOutputTemperatureToKelvin();
        }

        return calculateOutputTemperatureToCelsius(); // default
    }

    private Double calculateOutputTemperatureToCelsius() {
        if (inputUnit.compareTo(Unit.FAHRENHEIT) == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0);
        }

        if (inputUnit.compareTo(Unit.KELVIN) == 0) {
            return inputTemperature - 273.15;
        }

        return inputTemperature;
    }

    private Double calculateOutputTemperatureToFahrenheit() {
        if (inputUnit.compareTo(Unit.CELSIUS) == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0);
        }

        if (inputUnit.compareTo(Unit.KELVIN) == 0) {
            return (inputTemperature * (9.0 / 5.0)) + 32;
        }

        return inputTemperature;
    }

    private Double calculateOutputTemperatureToKelvin() {
        if (inputUnit.compareTo(Unit.CELSIUS) == 0) {
            return inputTemperature + 273.15;
        }

        if (inputUnit.compareTo(Unit.FAHRENHEIT) == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0) + 273.15;
        }

        return inputTemperature;
    }
}