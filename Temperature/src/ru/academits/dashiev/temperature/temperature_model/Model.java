package ru.academits.dashiev.temperature.temperature_model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Model {
    private final String[] units;
    private double inputTemperature;
    private double outputTemperature;

    public Model() {
        units = new String[]{new Celsius().toString(), new Fahrenheit().toString(), new Kelvin().toString()};
        inputTemperature = 1.7e-308 ;
        outputTemperature = 1.7e-308;
    }

    public String[] getUnits(){
        return units;
    }

    public double getInputTemperature() {
        return inputTemperature;
    }

    public double getOutputTemperature() {
        return outputTemperature;
    }

    public void setInputTemperature(double inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public double getTemperatureFromInputToOutput(String inputTemperatureName, String outputTemperatureName) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String path = "ru.academits.dashiev.temperature.temperature_model.";

        String path1 = path + inputTemperatureName;
        String path2 = path + outputTemperatureName;

        Class<?> inputTemperatureClass = Class.forName(path1);
        Class<?> outputTemperatureClass = Class.forName(path2);

        String inputMethodName = "convertFromCurrentToBasic";
        String outputMethodName = "convertFromBasicToCurrent";

        @SuppressWarnings("unchecked") Method inputMethod = Class.forName(path1).getDeclaredMethod("convertFromCurrentToBasic", double.class);
        @SuppressWarnings("unchecked") Method outputMethod = Class.forName(path2).getDeclaredMethod("convertFromBasicToCurrent", double.class);

        double convertFromCurrentToBasicResult = (double) inputMethod.invoke(inputTemperatureClass.newInstance(),inputTemperature);

        double convertFromBasicToCurrentResult = (double) outputMethod.invoke(outputTemperatureClass.newInstance(),convertFromCurrentToBasicResult);

        double roundedUpFinalTemperature = (double) Math.round(convertFromBasicToCurrentResult * 100) / 100;

        return roundedUpFinalTemperature;
    }
}