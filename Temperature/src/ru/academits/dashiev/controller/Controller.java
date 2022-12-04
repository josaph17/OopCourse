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
        view.getInputCelsiusButton().doClick();
        view.getOutputCelsiusButton().doClick();
    }

    public void initController() {
        view.getConvertButton().addActionListener(e -> convertTemperature());
    }

    public void convertTemperature() {
        try {
            model.setInputTemperature(Double.parseDouble((view.getInputField().getText())));
        } catch (NumberFormatException e) {
            view.showInputErrorMessage();
            return; // остановить функцию, чтобы дальше не пошла исполняться
        }

        model.setInputUnit(view.getSelectedInputTemperatureText());

        model.setOutputUnit(view.getSelectedOutputTemperatureText());

        model.setOutputTemperature(model.calculateOutputTemperature());

        view.getOutputField().setText("" + model.getOutputTemperature());
    }
}
