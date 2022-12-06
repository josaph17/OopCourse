package ru.academits.dashiev.view;

import javax.swing.*;

public interface IView {
    public void initView();

    public String getInputFieldText();

    public void setValueInOutputField(String value);

    public JButton getConvertButton();

    public Enum getSelectedInputTemperature();

    public Enum getSelectedOutputTemperature();

    public void showInputErrorMessage();
}
