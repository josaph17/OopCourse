package ru.academits.dashiev.temperature.temperature_main;

import ru.academits.dashiev.temperature.temperature_controller.Controller;
import ru.academits.dashiev.temperature.temperature_model.Model;
import ru.academits.dashiev.temperature.temperature_view.View;

public class Main {
    public static void main(String[] args) {
        Model m = new Model();
        View v = new View();
        Controller c = new Controller(m, v);

        c.initController();
    }
}