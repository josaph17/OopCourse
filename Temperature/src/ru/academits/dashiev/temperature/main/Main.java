package ru.academits.dashiev.temperature.main;

import ru.academits.dashiev.temperature.model.Model;
import ru.academits.dashiev.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m);
    }
}