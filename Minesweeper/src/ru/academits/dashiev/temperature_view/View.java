package ru.academits.dashiev.temperature_view;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JButton firstLevelButton;
    private JButton secondLevelButton;
    private JButton thirdLevelButton;
    private Image appIcon;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel chooseGameLevelLabel;

    public View(){
        String appName = "Minesweeper";

        // -- Create the principal frame --

        frame = new JFrame(appName);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // App open is center
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Layout manager
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // frame close on exit
        frame.setResizable(false); // not change frame size
        frame.setVisible(true);

        // -- Create UI elements --

        chooseGameLevelLabel = new JLabel("CHOOSE GAME LEVEL");

        appIcon = Toolkit.getDefaultToolkit().getImage("minesweeperAppImg.png"); // create app icon

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 500, 100);
        panel1.setBackground(new Color(245, 235, 224, 161));
        panel1.revalidate(); // to check valid
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel2 = new JPanel();
        panel2.setBounds(0, 0, 500, 100);
        panel2.setBackground(new Color(245, 235, 224, 161));
        panel2.revalidate(); // to check valid
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel3 = new JPanel();
        panel3.setBounds(0, 0, 500, 100);
        panel3.setBackground(new Color(245, 235, 224, 161));
        panel3.revalidate(); // to check valid
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel4 = new JPanel();
        panel4.setBounds(0, 0, 500, 100);
        panel4.setBackground(new Color(245, 235, 224, 161));
        panel4.revalidate(); // to check valid
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstLevelButton = new JButton("Beginner 9x9");
        firstLevelButton.setBounds(0,0, 500, 100);

        secondLevelButton = new JButton("Middle 16x16");
        secondLevelButton.setBounds(0,0, 500, 100);

        thirdLevelButton = new JButton("Master 16x30");
        firstLevelButton.setBounds(0,0, 500, 100);


        // -- Add to frame --

        frame.setIconImage(appIcon);

        frame.getContentPane().setBackground(new Color(197, 197, 199, 161));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);

        panel1.add(chooseGameLevelLabel);
        panel2.add(firstLevelButton);
        panel3.add(secondLevelButton);
        panel4.add(thirdLevelButton);
    }
}
