package ru.academits.dashiev.temperature.temperature_controller;

import ru.academits.dashiev.temperature.temperature_model.Model;
import ru.academits.dashiev.temperature.temperature_view.View;

public class Controller {
    private static Model model;
    private static View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
    }

    public void initView(Runnable convertAction) {
//        view.пуе
    }

    public static void saveInputTemperature() {
        // код в скобках и есть Runnable, параметров передаю выражение вызывающую ф-ю convertTemperature
        // view.initView(() -> convertTemperature());
        try {
            model.setInputTemperatureValue(Double.parseDouble((view.getInputTemperatureText())));
        } catch (NumberFormatException e) {
            view.showWrongInputError();

            return; // остановить функцию, чтобы дальше не пошла исполняться
        }    }

    public void initController(){
        // прописать инициалзацию контроллева церпез модель

        view.initInputTemperatureComboBox(new Model());
        view.initOutputTemperatureComboBox(new Model());

         view.getConvertButton().addActionListener(e -> convertTemperature());
    }

    private void convertTemperature() {
        model.setInputTemperatureName(view.getInputTemperatureName());

        model.setOutputTemperatureName(view.getOutputTemperatureName());

        model.setOutputTemperatureValue(model.calculateOutputTemperature());

        view.setOutputTemperature(("" + model.getOutputTemperatureValue()));
    }
}