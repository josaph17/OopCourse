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
    private ButtonGroup inputButtonsGroup;

    private JRadioButton outputCelsiusButton;
    private JRadioButton outputFarenheitButton;
    private JRadioButton outputKelvinsButton;
    private ButtonGroup outputButtonsGroup;

    public View(String Name) {
        // TODO create main frame
        frame = new JFrame(Name); //  название приложения
        frame.setSize(500, 300);
        // frame.setLocationRelativeTo(null);
        //frame.setLayout(new GridLayout(5, 1)); // TODO главный менеджер компановки
        frame.setLayout(new BoxLayout(frame.getContentPane(),
                                      BoxLayout.Y_AXIS)); // TODO главный менеджер компановки
        frame.setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE); // окно закрывется при закрытии
        frame.setResizable(false); // не изменять размер
        frame.setVisible(true);

        panel1 = new JPanel();
        // panel1.setSize(500, 70);
        // panel1.setPreferredSize(new Dimension(500, 50));
        panel1.setBounds(0, 0, 500, 30);
        panel1.setBackground(new Color(255, 255, 255, 161));
        panel1.revalidate(); // обяз-но, Проверяет валидацию
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel2 = new JPanel();
        // panel2.setPreferredSize(new Dimension(500, 100));
        panel2.setBounds(0, 0, 500, 120);
        panel2.setBackground(new Color(255, 255, 255, 161));
        panel2.revalidate();
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel3 = new JPanel(); // не меняем
        // panel3.setSize(500, 200); // не меняем
        panel3.setBounds(0, 0, 500, 200);
        panel3.setBackground(new Color(255, 255, 255, 161));
        panel3.revalidate();
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel4 = new JPanel();
        // panel4.setSize(500, 50);
        panel4.setBounds(0, 0, 500, 30);
        panel4.setBackground(new Color(255, 255, 255, 161));
        panel4.revalidate();
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel5 = new JPanel();
        // panel5.setSize(500, 100);
        panel5.setBounds(0, 0, 500, 120);
        panel5.setBackground(new Color(255, 255, 255, 161));
        panel5.revalidate();
        panel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        Image img = Toolkit.getDefaultToolkit().getImage("appImg.png"); // create app icon
        ImageIcon convertButtonIcon = new ImageIcon("change.png"); // convertButtonIcon

        // TODO create UI elements
        inputField = new JTextField(20); // textField на 20 символов
        inputField.setBounds(2, 3, 200, 30);

        outputField = new JTextField(20); // textField на 20 символов
        outputField.setBounds(2, 3, 200, 30);
        outputField.setEditable(false);

        //button.addActionListener((e) -> button.setText("Ищу инструументы")); // button использовали замыкание
        inputTemperatureAndChooseUnitLabel = new JLabel();
        inputTemperatureAndChooseUnitLabel.setText("Enter input value and choose temperature unit");
        inputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        //inputTemperatureAndChooseUnitLabel.setBackground(Color.black); // поменять задний фон
        //inputTemperatureAndChooseUnitLabel.setOpaque(true); // применить задний фон

        outputTemperatureAndChooseUnitLabel = new JLabel();
        outputTemperatureAndChooseUnitLabel.setText("Choose temperature unit");
        outputTemperatureAndChooseUnitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        convertButton = new JButton();
        convertButton.setText("Convert");
        convertButton.setIcon(convertButtonIcon);

        inputCelsiusButton = new JRadioButton("Celsius");
        inputFarenheitButton = new JRadioButton("Farenheit");
        inputKelvinsButton = new JRadioButton("Kelvins");
        inputButtonsGroup = new ButtonGroup(); // объединяем в одну группу
        inputButtonsGroup.add(inputCelsiusButton);
        inputButtonsGroup.add(inputFarenheitButton);
        inputButtonsGroup.add(inputKelvinsButton);
        outputCelsiusButton = new JRadioButton("Celsius");
        outputFarenheitButton = new JRadioButton("Farenheit");
        outputKelvinsButton = new JRadioButton("Kelvins");
        outputButtonsGroup = new ButtonGroup(); // объединяем в одну группу
        outputButtonsGroup.add(outputCelsiusButton);
        outputButtonsGroup.add(outputFarenheitButton);
        outputButtonsGroup.add(outputKelvinsButton);

        // TODO add UI elements to Frame
        frame.setIconImage(img);
        // TODO задний фон на кнопках стал незаметен
        frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);


        // TODO add UI elements to Panels
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
}
