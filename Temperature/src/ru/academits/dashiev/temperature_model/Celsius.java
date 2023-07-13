package ru.academits.dashiev.temperature_model;

public class Celsius implements ITemperatureUnit {
    private Double value;

    Celsius (Double value){
        this.value = value;
    }

    @Override
    public Double getCelsius() {
        return value;
    }

    @Override
    public Double getInitValueFromCelsius(Double celsiusValue) {
        return celsiusValue;
    }

    public Double getValue(){
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }
}