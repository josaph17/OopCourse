package ru.academits.dashiev.view;

public interface IView {
    public void initView(Runnable convertAction);

    public String getInputTemperatureText();

    public void setOutputTemperature(String value);

    public Enum getInputTemperatureType();

    public Enum getOutputTemperatureType();

    public void showWrongInputError();
}
