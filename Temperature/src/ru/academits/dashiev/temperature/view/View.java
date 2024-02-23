package ru.academits.dashiev.temperature.view;

import ru.academits.dashiev.temperature.model.Converter;

import javax.swing.*;
import java.awt.*;

public class View {
    private JTextField inputTemperatureField;
    private JTextField outputTemperatureField;
    private JComboBox<String> inputScalesNamesComboBox;
    private JComboBox<String> outputScalesNamesComboBox;
    private ImageIcon warningIcon;

    private JFrame frame;

    public View(Converter model) {
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
            frame.setLocationRelativeTo(null);

            JPanel guide1Panel = new JPanel();
            guide1Panel.setBounds(0, 0, 500, 30);
            guide1Panel.setBackground(new Color(255, 255, 255, 161));
            guide1Panel.revalidate(); // to check valid
            guide1Panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel inputScaleAndTemperaturePanel = new JPanel();
            inputScaleAndTemperaturePanel.setBounds(0, 0, 500, 120);
            inputScaleAndTemperaturePanel.setBackground(new Color(255, 255, 255, 161));
            inputScaleAndTemperaturePanel.revalidate();
            inputScaleAndTemperaturePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel convertButtonPanel = new JPanel(); // не меняем
            convertButtonPanel.setBounds(0, 0, 500, 200);
            convertButtonPanel.setBackground(new Color(255, 255, 255, 161));
            convertButtonPanel.revalidate();
            convertButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel guide2Panel = new JPanel();
            guide2Panel.setBounds(0, 0, 500, 30);
            guide2Panel.setBackground(new Color(255, 255, 255, 161));
            guide2Panel.revalidate();
            guide2Panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel outputScaleAndTemperaturePanel = new JPanel();
            outputScaleAndTemperaturePanel.setBounds(0, 0, 500, 120);
            outputScaleAndTemperaturePanel.setBackground(new Color(255, 255, 255, 161));
            outputScaleAndTemperaturePanel.revalidate();
            outputScaleAndTemperaturePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // В этих путях лучше использовать / Это будет работать на всех платформах. Комментарий по путям.
            Image appIcon = Toolkit.getDefaultToolkit().getImage("Temperature/src/ru/academits/dashiev/temperature/resources/appImg.png"); // create app icon
            ImageIcon convertButtonIcon = new ImageIcon("Temperature/src/ru/academits/dashiev/temperature/resources/convertButton.png"); // convertButtonIcon
            warningIcon = new ImageIcon("Temperature/src/ru/academits/dashiev/temperature/resources/attention.png");

            inputTemperatureField = new JTextField();
            inputTemperatureField.setColumns(20); // textField 20 symbols
            inputTemperatureField.setBounds(2, 3, 200, 30);

            outputTemperatureField = new JTextField(20);
            outputTemperatureField.setBounds(2, 3, 200, 30);
            outputTemperatureField.setEditable(false);

            JLabel inputTemperatureAndChooseUnitLabel = new JLabel();
            inputTemperatureAndChooseUnitLabel.setText("Enter input value and choose temperature unit");
            inputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel outputTemperatureAndChooseUnitLabel = new JLabel();
            outputTemperatureAndChooseUnitLabel.setText("Choose temperature unit");
            outputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JButton convertButton = new JButton();
            convertButton.setText("Convert");
            convertButton.setIcon(convertButtonIcon);

            inputScalesNamesComboBox = new JComboBox<>(model.getScalesNames());
            outputScalesNamesComboBox = new JComboBox<>(model.getScalesNames());

            // add UI elements to Frame
            frame.setIconImage(appIcon);
            frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
            frame.add(guide1Panel);
            frame.add(inputScaleAndTemperaturePanel);
            frame.add(convertButtonPanel);
            frame.add(guide2Panel);
            frame.add(outputScaleAndTemperaturePanel);

            guide1Panel.add(inputTemperatureAndChooseUnitLabel);
            inputScaleAndTemperaturePanel.add(inputScalesNamesComboBox);
            inputScaleAndTemperaturePanel.add(inputTemperatureField);
            convertButtonPanel.add(convertButton);
            guide2Panel.add(outputTemperatureAndChooseUnitLabel);
            outputScaleAndTemperaturePanel.add(outputScalesNamesComboBox);
            outputScaleAndTemperaturePanel.add(outputTemperatureField);

            // add ActionListener on convertButton
            convertButton.addActionListener(e -> {
                if (e.getSource() == convertButton) {
                    try {
                        double inputTemperature = Double.parseDouble(inputTemperatureField.getText());

                        double outputTemperature = model.convertTemperature((String) inputScalesNamesComboBox.getSelectedItem(), (String) outputScalesNamesComboBox.getSelectedItem(), inputTemperature);

                        outputTemperatureField.setText(Double.toString((double) Math.round(outputTemperature * 100) / 100));
                    } catch (NumberFormatException exception) {
                        showWrongInputError();

                        outputTemperatureField.setText("");
                    } catch (IllegalArgumentException exception) {
                        JOptionPane.showMessageDialog(frame, exception.getMessage(), "Wrong scale name error", JOptionPane.PLAIN_MESSAGE, warningIcon);
                    }
                }
            });
        });
    }

    private void showWrongInputError() {
        JOptionPane.showMessageDialog(frame, "Input wrong value! Input number", "Input error",
                JOptionPane.PLAIN_MESSAGE, warningIcon);
    }
}