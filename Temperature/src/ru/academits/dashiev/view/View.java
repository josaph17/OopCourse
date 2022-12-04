package ru.academits.dashiev.view;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JTextField inputField;
    private JTextField outputField;
    private JLabel inputTemperatureAndChooseUnitLabel;
    private JLabel outputTemperatureAndChooseUnitLabel;
    private JButton convertButton;
    private JRadioButton inputCelsiusButton;
    private JRadioButton inputFarenheitButton;
    private JRadioButton inputKelvinsButton;
    private ButtonGroup inputTemperatureButtonsGroup;
    private JRadioButton outputCelsiusButton;
    private JRadioButton outputFarenheitButton;
    private JRadioButton outputKelvinsButton;
    private ButtonGroup outputTemperatureButtonsGroup;
    private ImageIcon convertButtonIcon;
    private Image appIcon;
    private ImageIcon warningIcon;

    public View() {
        String appName = "Me temperature app";

        frame = new JFrame(appName);
        frame.setSize(500, 300);
        frame.setLayout(new BoxLayout(frame.getContentPane(),
                                      BoxLayout.Y_AXIS)); // Layout manager
        frame.setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE); // frame close on exit
        frame.setResizable(false); // not change frame size
        frame.setVisible(true);

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 500, 30);
        panel1.setBackground(new Color(255, 255, 255, 161));
        panel1.revalidate(); // to check valid
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel2 = new JPanel();
        panel2.setBounds(0, 0, 500, 120);
        panel2.setBackground(new Color(255, 255, 255, 161));
        panel2.revalidate();
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel3 = new JPanel(); // не меняем
        panel3.setBounds(0, 0, 500, 200);
        panel3.setBackground(new Color(255, 255, 255, 161));
        panel3.revalidate();
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel4 = new JPanel();
        panel4.setBounds(0, 0, 500, 30);
        panel4.setBackground(new Color(255, 255, 255, 161));
        panel4.revalidate();
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel5 = new JPanel();
        panel5.setBounds(0, 0, 500, 120);
        panel5.setBackground(new Color(255, 255, 255, 161));
        panel5.revalidate();
        panel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        appIcon = Toolkit.getDefaultToolkit().getImage("appImg.png"); // create app icon
        convertButtonIcon = new ImageIcon("change.png"); // convertButtonIcon
        warningIcon =  new ImageIcon("attention.png");

        inputField = new JTextField(20); // textField 20 symbols
        inputField.setBounds(2, 3, 200, 30);

        outputField = new JTextField(20);
        outputField.setBounds(2, 3, 200, 30);
        outputField.setEditable(false);

        //button.addActionListener((e) -> button.setText("Ищу инструументы")); // button использовали замыкание
        inputTemperatureAndChooseUnitLabel = new JLabel();
        inputTemperatureAndChooseUnitLabel.setText("Enter input value and choose temperature unit");
        inputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        outputTemperatureAndChooseUnitLabel = new JLabel();
        outputTemperatureAndChooseUnitLabel.setText("Choose temperature unit");
        outputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        convertButton = new JButton();
        convertButton.setText("Convert");
        convertButton.setIcon(convertButtonIcon);

        inputCelsiusButton = new JRadioButton("Celsius");
        inputFarenheitButton = new JRadioButton("Farenheit");
        inputKelvinsButton = new JRadioButton("Kelvins");
        inputTemperatureButtonsGroup = new ButtonGroup();
        inputTemperatureButtonsGroup.add(inputCelsiusButton);
        inputTemperatureButtonsGroup.add(inputFarenheitButton);
        inputTemperatureButtonsGroup.add(inputKelvinsButton);
        outputCelsiusButton = new JRadioButton("Celsius");
        outputFarenheitButton = new JRadioButton("Farenheit");
        outputKelvinsButton = new JRadioButton("Kelvins");
        outputTemperatureButtonsGroup = new ButtonGroup();
        outputTemperatureButtonsGroup.add(outputCelsiusButton);
        outputTemperatureButtonsGroup.add(outputFarenheitButton);
        outputTemperatureButtonsGroup.add(outputKelvinsButton);

        // add UI elements to Frame
        frame.setIconImage(appIcon);

        frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);

        panel1.add(inputTemperatureAndChooseUnitLabel);
        panel2.add(inputField);
        panel2.add(inputCelsiusButton);
        panel2.add(inputFarenheitButton);
        panel2.add(inputKelvinsButton);
        panel3.add(convertButton);
        panel4.add(outputTemperatureAndChooseUnitLabel);
        panel5.add(outputField);
        panel5.add(outputCelsiusButton);
        panel5.add(outputFarenheitButton);
        panel5.add(outputKelvinsButton);
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JTextField getOutputField() {
        return outputField;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public String getSelectedInputTemperatureText() {
        if (inputKelvinsButton.isSelected()) {
            return inputKelvinsButton.getText();
        }

        if (inputFarenheitButton.isSelected()) {
            return inputFarenheitButton.getText();
        }

        return inputCelsiusButton.getText();
    }

    public String getSelectedOutputTemperatureText() {
        if (outputKelvinsButton.isSelected()) {
            return outputKelvinsButton.getText();
        }

        if (outputFarenheitButton.isSelected()) {
            return outputFarenheitButton.getText();
        }

        return outputCelsiusButton.getText();
    }

    public void showInputErrorMessage() {
         JOptionPane.showOptionDialog(null,
                                       "Input wrong value! Input number",
                                       "Input error",
                                       JOptionPane.DEFAULT_OPTION ,
                                       JOptionPane.PLAIN_MESSAGE,
                                       warningIcon,
                                       null,
                                       0);
    }

    public JRadioButton getInputCelsiusButton(){
        return inputCelsiusButton;
    }

    public JRadioButton getOutputCelsiusButton(){
        return outputCelsiusButton;
    }
}