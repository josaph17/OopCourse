package ru.academits.dashiev.temperature.temperature_view;

import ru.academits.dashiev.temperature.temperature_model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class View {
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JTextField inputTemperatureField;
    private JTextField outputTemperatureField;
    private JLabel inputTemperatureAndChooseUnitLabel;
    private JLabel outputTemperatureAndChooseUnitLabel;
    private JButton convertButton;
    private JComboBox inputTemperatureComboBox;
    private JComboBox outputTemperatureComboBox;
    private ImageIcon convertButtonIcon;
    private Image appIcon;
    private ImageIcon warningIcon;

    public View(Model model) {
        SwingUtilities.invokeLater(() -> {
            try {
                // setLookAndFeel это Тема оформления
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            String appName = "My temperature app";

            frame = new JFrame(appName);
            frame.setSize(500, 300);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Layout manager
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // frame close on exit
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
            warningIcon = new ImageIcon("attention.png");

            inputTemperatureField = new JTextField();
            inputTemperatureField.setColumns(20); // textField 20 symbols
            inputTemperatureField.setBounds(2, 3, 200, 30);

            outputTemperatureField = new JTextField(20);
            outputTemperatureField.setBounds(2, 3, 200, 30);
            outputTemperatureField.setEditable(false);

            //button.addActionListener((e) -> button.setText("Ищу инструменты")); // button использовали замыкание
            inputTemperatureAndChooseUnitLabel = new JLabel();
            inputTemperatureAndChooseUnitLabel.setText("Enter input value and choose temperature unit");
            inputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            outputTemperatureAndChooseUnitLabel = new JLabel();
            outputTemperatureAndChooseUnitLabel.setText("Choose temperature unit");
            outputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            convertButton = new JButton();
            convertButton.setText("Convert");
            convertButton.setIcon(convertButtonIcon);

            inputTemperatureComboBox = new JComboBox(model.getUnits());
            outputTemperatureComboBox = new JComboBox(model.getUnits());

            // add UI elements to Frame
            frame.setIconImage(appIcon);

            frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
            frame.add(panel1);
            frame.add(panel2);
            frame.add(panel3);
            frame.add(panel4);
            frame.add(panel5);

            panel1.add(inputTemperatureAndChooseUnitLabel);
            panel2.add(inputTemperatureComboBox);
            panel2.add(inputTemperatureField);
            panel3.add(convertButton);
            panel4.add(outputTemperatureAndChooseUnitLabel);
            panel5.add(outputTemperatureComboBox);
            panel5.add(outputTemperatureField);

            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == convertButton) {
                        // Обновил значение температуры
                        try {
                            model.setInputTemperature(Double.parseDouble((inputTemperatureField.getText())));
                        } catch (NumberFormatException exception) {
                            showWrongInputError();
                            // остановить функцию, чтобы дальше не пошла исполняться
                        }

                        // TODO Until there done
                        try {
                            double calculatedTemperature = model.getTemperatureFromInputToOutput((String) inputTemperatureComboBox.getSelectedItem(), (String) outputTemperatureComboBox.getSelectedItem());

                            model.setOutputTemperature(calculatedTemperature);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                                 ClassNotFoundException | InstantiationException ex) {
                            throw new RuntimeException(ex);
                        }

                        outputTemperatureField.setText(String.valueOf(model.getOutputTemperature()));
                    }
                }
            });
        });
    }

    public String getInputTemperature() {
        return inputTemperatureField.getText();
    }

    public void showWrongInputError() {
        JOptionPane.showOptionDialog(null, "Input wrong value! Input number", "Input error",
                                     JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     warningIcon, null, 0);
    }
}