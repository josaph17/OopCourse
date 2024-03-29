package ru.academits.dashiev.temperature.main;

import ru.academits.dashiev.temperature.model.ApplicationConverter;
import ru.academits.dashiev.temperature.model.scales.CelsiusScale;
import ru.academits.dashiev.temperature.model.scales.FahrenheitScale;
import ru.academits.dashiev.temperature.model.scales.KelvinScale;
import ru.academits.dashiev.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        ApplicationConverter converter = new ApplicationConverter(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());
        new View(converter);
    }
}