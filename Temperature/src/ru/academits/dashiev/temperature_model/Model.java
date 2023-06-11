package ru.academits.dashiev.temperature_model;

import ru.academits.dashiev.unit.Unit;

public class Model {
    private Double inputTemperatureValue;
    private Double outputTemperatureValue;
    private String inputTemperatureName;
    private String outputTemperatureName;

    public Model() {
    }

    public void setInputTemperatureValue(double inputTemperatureValue) {
        this.inputTemperatureValue = inputTemperatureValue;
    }

    public double getInputTemperatureValue() {
        return inputTemperatureValue;
    }

    public void setOutputTemperatureValue(double outputTemperatureValue) {
        this.outputTemperatureValue = outputTemperatureValue;
    }

    public double getOutputTemperatureValue() {
        return outputTemperatureValue;
    }

    public void setInputTemperatureName(String inputTemperatureName) {
        this.inputTemperatureName = inputTemperatureName;
    }

    public void setOutputTemperatureName(String outputTemperatureName) {
        this.outputTemperatureName = outputTemperatureName;
    }

    public Double calculateOutputTemperature() {
        if ((outputTemperatureName.equals("FAHRENHEIT"))) {
            return calculateOutputTemperatureToFahrenheit();
        }

        if (outputTemperatureName.equals("KELVIN")) {
            return calculateOutputTemperatureToKelvin();
        }

        return calculateOutputTemperatureToCelsius(); // default
    }

    private Double calculateOutputTemperatureToCelsius() {
        if (inputTemperatureName.equals("FAHRENHEIT")) {
            return (inputTemperatureValue - 32) * (5.0 / 9.0);
        }

        if (inputTemperatureName.equals("KELVIN")) {
            return inputTemperatureValue - 273.15;
        }

        return inputTemperatureValue;
    }

    private Double calculateOutputTemperatureToFahrenheit() {
        if (inputTemperatureName.equals("CELSIUS")) {
            return (inputTemperatureValue - 32) * (5.0 / 9.0);
        }

        if (inputTemperatureName.equals("KELVIN")) {
            return (inputTemperatureValue * (9.0 / 5.0)) + 32;
        }

        return inputTemperatureValue;
    }

    private Double calculateOutputTemperatureToKelvin() {
        if (inputTemperatureName.compareTo("CELSIUS") == 0) {
            return inputTemperatureValue + 273.15;
        }

        if (inputTemperatureName.compareTo("FAHRENHEIT") == 0) {
            return (inputTemperatureValue - 32) * (5.0 / 9.0) + 273.15;
        }

        return inputTemperatureValue;
    }
}