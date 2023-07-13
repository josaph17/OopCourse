package ru.academits.dashiev.temperature_model;

public class Kelvin implements ITemperatureUnit {
    private Double value;

    Kelvin (Double value){
        this.value = value;
    }

    @Override
    public Double getCelsius() {
        return value - 273.15;
    }

    @Override
    public Double getInitValueFromCelsius(Double celsiusValue) {
        return celsiusValue + 273.15;
    }

    public Double getValue(){
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }
}