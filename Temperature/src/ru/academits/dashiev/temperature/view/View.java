package ru.academits.dashiev.temperature.view;

import ru.academits.dashiev.temperature.model.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JTextField inputTemperatureField;
    private JTextField outputTemperatureField;
    @SuppressWarnings("rawtypes")
    private JComboBox inputTemperatureComboBox;
    @SuppressWarnings("rawtypes")
    private JComboBox outputTemperatureComboBox;

    ImageIcon warningIcon;

    public View(Converter model) {
        SwingUtilities.invokeLater(() -> {
            try {
                // setLookAndFeel это Тема оформления
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            String appName = "My temperature app";

            JFrame frame = new JFrame(appName);
            frame.setSize(500, 300);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Layout manager
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // frame close on exit
            frame.setResizable(false); // not change frame size
            frame.setVisible(true);

            JPanel applicationPart1 = new JPanel();
            applicationPart1.setBounds(0, 0, 500, 30);
            applicationPart1.setBackground(new Color(255, 255, 255, 161));
            applicationPart1.revalidate(); // to check valid
            applicationPart1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel applicationPart2 = new JPanel();
            applicationPart2.setBounds(0, 0, 500, 120);
            applicationPart2.setBackground(new Color(255, 255, 255, 161));
            applicationPart2.revalidate();
            applicationPart2.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel applicationPart3 = new JPanel(); // не меняем
            applicationPart3.setBounds(0, 0, 500, 200);
            applicationPart3.setBackground(new Color(255, 255, 255, 161));
            applicationPart3.revalidate();
            applicationPart3.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel applicationPart4 = new JPanel();
            applicationPart4.setBounds(0, 0, 500, 30);
            applicationPart4.setBackground(new Color(255, 255, 255, 161));
            applicationPart4.revalidate();
            applicationPart4.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel applicationPart5 = new JPanel();
            applicationPart5.setBounds(0, 0, 500, 120);
            applicationPart5.setBackground(new Color(255, 255, 255, 161));
            applicationPart5.revalidate();
            applicationPart5.setAlignmentX(Component.CENTER_ALIGNMENT);

            Image appIcon = Toolkit.getDefaultToolkit().getImage("appImg.png"); // create app icon
            ImageIcon convertButtonIcon = new ImageIcon("change.png"); // convertButtonIcon
            warningIcon = new ImageIcon("attention.png");

            inputTemperatureField = new JTextField();
            inputTemperatureField.setColumns(20); // textField 20 symbols
            inputTemperatureField.setBounds(2, 3, 200, 30);

            outputTemperatureField = new JTextField(20);
            outputTemperatureField.setBounds(2, 3, 200, 30);
            outputTemperatureField.setEditable(false);

            //button.addActionListener((e) -> button.setText("Ищу инструменты")); // button использовали замыкание
            JLabel inputTemperatureAndChooseUnitLabel = new JLabel();
            inputTemperatureAndChooseUnitLabel.setText("Enter input value and choose temperature unit");
            inputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel outputTemperatureAndChooseUnitLabel = new JLabel();
            outputTemperatureAndChooseUnitLabel.setText("Choose temperature unit");
            outputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JButton convertButton = new JButton();
            convertButton.setText("Convert");
            convertButton.setIcon(convertButtonIcon);

            //noinspection rawtypes
            inputTemperatureComboBox = new JComboBox(model.getScalesString());
            //noinspection rawtypes
            outputTemperatureComboBox = new JComboBox(model.getScalesString());

            // add UI elements to Frame
            frame.setIconImage(appIcon);

            frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
            frame.add(applicationPart1);
            frame.add(applicationPart2);
            frame.add(applicationPart3);
            frame.add(applicationPart4);
            frame.add(applicationPart5);

            applicationPart1.add(inputTemperatureAndChooseUnitLabel);
            applicationPart2.add(inputTemperatureComboBox);
            applicationPart2.add(inputTemperatureField);
            applicationPart3.add(convertButton);
            applicationPart4.add(outputTemperatureAndChooseUnitLabel);
            applicationPart5.add(outputTemperatureComboBox);
            applicationPart5.add(outputTemperatureField);

            // add ActionListener on convertButton
            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == convertButton) {
                        try {
                            double inputTemperature = Double.parseDouble((inputTemperatureField.getText()));
                        } catch (NumberFormatException exception) {
                            showWrongInputError();
                        }

                        double calculatedTemperature = model.getTemperatureFromInputToOutput((String) inputTemperatureComboBox.getSelectedItem(), (String) outputTemperatureComboBox.getSelectedItem(), Double.parseDouble((inputTemperatureField.getText())));


                        outputTemperatureField.setText(Double.toString(calculatedTemperature));
                    }
                }
            });
        });
    }

    public void showWrongInputError() {
        JOptionPane.showOptionDialog(null, "Input wrong value! Input number", "Input error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, warningIcon, null, 0);
    }
}