package ru.academits.dashiev.temperature.main;

import ru.academits.dashiev.temperature.model.Converter;
import ru.academits.dashiev.temperature.model.scales.Celsius;
import ru.academits.dashiev.temperature.model.scales.Fahrenheit;
import ru.academits.dashiev.temperature.model.scales.Kelvin;
import ru.academits.dashiev.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter(new Celsius(), new Fahrenheit(), new Kelvin());
        View view = new View(converter);
    }
}