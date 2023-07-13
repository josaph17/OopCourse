package ru.academits.dashiev.temperature_model;

public class Fahrenheit implements ITemperatureUnit {
    private Double value;

    Fahrenheit (Double value){
        this.value = value;
    }

    @Override
    public Double getCelsius() {
        return (value - 32) * (5.0 / 9.0);
    }

    @Override
    public Double getInitValueFromCelsius(Double celsiusValue) {
        return celsiusValue * (9.0 / 5.0) + 32;
    }
    public Double getValue(){
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }
}