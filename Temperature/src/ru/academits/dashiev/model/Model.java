package ru.academits.dashiev.model;

public class Model {
    private double inputTemperature;
    private double outputTemperature;

    public Model(double inputTemperature) {
        this.inputTemperature = inputTemperature;
        outputTemperature = 0.0;
    }

    public double getInputTemperature() {
        return inputTemperature;
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
}
