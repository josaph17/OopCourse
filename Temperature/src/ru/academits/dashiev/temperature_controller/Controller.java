package ru.academits.dashiev.temperature_controller;

import ru.academits.dashiev.temperature_model.Model;
import ru.academits.dashiev.temperature_view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
    }

    public void initController(){
        // код в скобках и есть Runnable, параметров передаю выражение вызывающую ф-ю convertTemperature
        // view.initView(() -> convertTemperature());
        outputCelsiusButton.doClick();
        inputCelsiusButton.doClick();
        view.getConvertButton().addActionListener(e -> convertTemperature());
    }

    private void convertTemperature() {
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