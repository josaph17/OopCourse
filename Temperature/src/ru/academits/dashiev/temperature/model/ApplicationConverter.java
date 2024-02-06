package ru.academits.dashiev.temperature.model;

import ru.academits.dashiev.temperature.model.scales.Scale;

public class ApplicationConverter implements Converter {
    private final Scale[] scales;

    public ApplicationConverter(Scale... scales) {
        this.scales = scales;
    }

    @Override
    public String[] getScalesArray() {
        String[] scalesNamesArray = new String[scales.length];

        for (int i = 0; i < scales.length; i++) {
            scalesNamesArray[i] = scales[i].toString();
        }

        return scalesNamesArray;
    }

    @Override
    public double convertTemperatureFromInputToOutput(String inputScaleName, String outputScaleName, double inputTemperature) {
        double celsiusTemperature = 0.0;

        for (Scale scale : scales) {
            if (inputScaleName.equals(scale.toString())) {
                celsiusTemperature = scale.convertToCelsius(inputTemperature);

                break;
            }
        }

        double outputTemperature = 0.0;

        for (Scale scale : scales) {
            if (outputScaleName.equals(scale.toString())) {
                outputTemperature = scale.convertFromCelsius(celsiusTemperature);

                break;
            }
        }

        return outputTemperature;
    }
}