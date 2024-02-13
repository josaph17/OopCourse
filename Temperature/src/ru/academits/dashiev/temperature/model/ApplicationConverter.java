package ru.academits.dashiev.temperature.model;

import ru.academits.dashiev.temperature.model.scales.Scale;

public class ApplicationConverter implements Converter {
    private final Scale[] scales;

    public ApplicationConverter(Scale... scales) {
        this.scales = scales;
    }

    @Override
    public String[] getScalesNames() {
        String[] scalesNames = new String[scales.length];

        for (int i = 0; i < scales.length; i++) {
            scalesNames[i] = scales[i].toString();
        }

        return scalesNames;
    }

    @Override
    public double convertTemperature(String inputScaleName, String outputScaleName, double inputTemperature) {
        double celsiusTemperature = 0.0;
        boolean isInputScaleNameFound = false;

        for (Scale scale : scales) {
            if (inputScaleName.equals(scale.toString())) {
                celsiusTemperature = scale.convertToCelsius(inputTemperature);

                isInputScaleNameFound = true;

                break;
            }
        }

        if (!isInputScaleNameFound){
            throw new IllegalArgumentException("There is no such scale name in all scales list!");
        }

        double outputTemperature = 0.0;
        boolean isOutputScaleNameFound = false;

        for (Scale scale : scales) {
            if (outputScaleName.equals(scale.toString())) {
                outputTemperature = scale.convertFromCelsius(celsiusTemperature);

                isOutputScaleNameFound = true;

                break;
            }
        }

        if (!isOutputScaleNameFound){
            throw new IllegalArgumentException("There is no such scale name in all scales list!");
        }

        return outputTemperature;
    }
}