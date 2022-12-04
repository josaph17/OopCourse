package ru.academits.dashiev.model;

public class Model {
    private Double inputTemperature;
    private Double outputTemperature;
    private String inputUnit;
    private String outputUnit;

    public Model() {
        inputTemperature = null;
        outputTemperature = null;
        inputUnit = null;
        outputUnit = null;
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

    public void setInputUnit(String inputUnit) {
        this.inputUnit = inputUnit;
    }

    public void setOutputUnit(String outputUnit) {
        this.outputUnit = outputUnit;
    }

    public Double calculateOutputTemperature() {
        if ((outputUnit.compareTo("Farenheit") == 0)) {
            return calculateOutputTemperatureToFarenheit();
        }

        if (outputUnit.compareTo("Kelvins") == 0) {
            return calculateOutputTemperatureToKelvins();
        }

        return calculateOutputTemperatureToCelsius(); // default
    }

    private Double calculateOutputTemperatureToCelsius() {
        if (inputUnit.compareTo("Farenheit") == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0);
        }

        if (inputUnit.compareTo("Kelvins") == 0) {
            return inputTemperature - 273.15;
        }

        return inputTemperature;
    }

    private Double calculateOutputTemperatureToFarenheit() {
        if (inputUnit.compareTo("Celsius") == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0);
        }

        if (inputUnit.compareTo("Kelvins") == 0) {
            return (inputTemperature * (9.0 / 5.0)) + 32;
        }

        return inputTemperature;
    }

    private Double calculateOutputTemperatureToKelvins() {
        if (inputUnit.compareTo("Celsius") == 0) {
            return inputTemperature + 273.15;
        }

        if (inputUnit.compareTo("Farenheit") == 0) {
            return (inputTemperature - 32) * (5.0 / 9.0) + 273.15;
        }

        return inputTemperature;
    }
}