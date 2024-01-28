package ru.academits.dashiev.temperature.model;

import ru.academits.dashiev.temperature.model.scales.Scale;

public class Converter implements IConverter {
    private final Scale[] scales;

    public Converter(Scale ... scales) {
        this.scales = scales;
    }

    @Override
    public String[] getScalesString(){
        String[] stringScales = new String[scales.length];

        for(int i = 0; i< scales.length; i++){
            stringScales[i] = scales[i].toString();
        }

        return stringScales;
    }

    @Override
    public double getTemperatureFromInputToOutput(String inputTemperatureName, String outputTemperatureName, double inputTemperature) {
        double convertToCelsiusResult = 0.0;

        for (int i =0; i< scales.length; i++){
            if (inputTemperatureName.equals(scales[i].toString())){
                convertToCelsiusResult = scales[i].convertToCelsius(inputTemperature);
            }
        }

        double convertFromCelsiusResult = 0.0;

        for (int i =0; i< scales.length; i++){
            if (outputTemperatureName.equals(scales[i].toString())){
                convertFromCelsiusResult = scales[i].convertFromCelsius(convertToCelsiusResult);
            }
        }

        return (double) Math.round(convertFromCelsiusResult * 100) / 100;
    }
}