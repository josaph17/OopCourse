package ru.academits.dashiev.view;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTextField inputField;
    private ButtonGroup levelButtonsGroup;
    private JRadioButton firstLevelButton;
    private JRadioButton secondLevelButton;
    private JRadioButton thirdLevelButton;
    private Image appIcon;

    public View(){
        String appName = "Minesweeper";

        JFrame frame = new JFrame(appName);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // App open is center
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Layout manager
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // frame close on exit
        frame.setResizable(false); // not change frame size
        frame.setVisible(true);

        inputField = new JTextField(20); // textField 20 symbols
        inputField.setBounds(2, 3, 200, 30);

        appIcon = Toolkit.getDefaultToolkit().getImage("minesweeperAppImg.png"); // create app icon

        frame.setIconImage(appIcon);
    }
}
