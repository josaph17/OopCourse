package ru.academits.dashiev.interface_view;

import javax.swing.*;

public interface interfaceView {
    public String getInputFieldText();

    public void setValueInOutputField(String value);

    public JButton getConvertButton();

    public Enum getSelectedInputTemperature();

    public Enum getSelectedOutputTemperature();

    public void showInputErrorMessage();

    public void defaultDoCLickInputCelsiusButton();

    public void defaultDOClickOutputCelsiusButton();
}
