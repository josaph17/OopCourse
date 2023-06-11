package ru.academits.dashiev.temperature_view;

public interface IView {
    // public void initView(Runnable convertAction);

    public String getInputTemperatureText();

    public void setOutputTemperature(String value);

    public String getInputTemperatureName();

    public String getOutputTemperatureName();

    public void showWrongInputError();
}
