package ru.academits.dashiev.controller;

import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;

        // код в скобках и есть Runnable, параметров передаю выражение вызывающую ф-ю convertTemperature
        view.initView(() -> convertTemperature());
    }

    public void convertTemperature() {
        try {
            model.setInputTemperature(Double.parseDouble((view.getInputTemperatureText())));
        } catch (NumberFormatException e) {
            view.showWrongInputError();

            return; // остановить функцию, чтобы дальше не пошла исполняться
        }

        model.setInputUnit(view.getInputTemperatureType());

        model.setOutputUnit(view.getOutputTemperatureType());

        model.setOutputTemperature(model.calculateOutputTemperature());

        view.setOutputTemperature(("" + model.getOutputTemperature()));
    }
}