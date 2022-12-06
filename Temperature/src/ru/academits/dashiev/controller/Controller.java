package ru.academits.dashiev.controller;

import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
        initView();
    }

    private void initView() {
        view.initView();
    }

    public void initController() {
        view.getConvertButton().addActionListener(e -> convertTemperature());
    }

    public void convertTemperature() {
        try {
            model.setInputTemperature(Double.parseDouble((view.getInputFieldText())));
        } catch (NumberFormatException e) {
            view.showInputErrorMessage();
            return; // остановить функцию, чтобы дальше не пошла исполняться
        }

        model.setInputUnit(view.getSelectedInputTemperature());

        model.setOutputUnit(view.getSelectedOutputTemperature());

        model.setOutputTemperature(model.calculateOutputTemperature());

        view.setValueInOutputField(("" + model.getOutputTemperature()));
    }
}
