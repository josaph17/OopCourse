package ru.academits.dashiev.abstract_view;

import javax.swing.*;

public abstract class AbstractView {
    public abstract String getInputFieldText();

    public abstract void setValueInOutputField(String value);

    public abstract JButton getConvertButton();

    public abstract Enum getSelectedInputTemperature();

    public abstract Enum getSelectedOutputTemperature();

    public abstract void showInputErrorMessage();

    public abstract void defaultDoCLickInputCelsiusButton();

    public abstract void defaultDOClickOutputCelsiusButton();
}
