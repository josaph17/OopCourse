package ru.academits.dashiev.temperature.temperature_model;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model implements ComboBoxModel {
    // private List<String> data = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private int selected = 0;
    private Double inputTemperatureValue;
    private Double outputTemperatureValue;
    private String inputTemperatureName;
    private String outputTemperatureName;

    private InterfaceTemperatureUnit inputTemperature;

    private InterfaceTemperatureUnit outputTemperature;

    public Model() {
        data = Arrays.asList("Celsius", "Fahrenheit", "Kelvin");
    }

    public void setSelectedItem(Object o)
    {
        selected = data.indexOf(o);
    }

    public Object getSelectedItem()
    {
        return data.get(selected);
    }

    public int getSize()
    {
        return data.size();
    }

    public Object getElementAt(int i)
    {
        return data.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

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