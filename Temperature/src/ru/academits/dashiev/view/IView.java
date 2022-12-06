package ru.academits.dashiev.view;

public interface IView {
    public void initView(Runnable convertAction);

    public String getInputFieldText();

    public void setValueInOutputField(String value);

    public Enum getInputTemperatureType();

    public Enum getOutputTemperatureType();

    public void showWrongInputError();
}
