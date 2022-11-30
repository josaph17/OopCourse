package ru.academits.dashiev.controller;

import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
    }
}
