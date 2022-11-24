package ru.academits.dashiev.temperature_main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // чтобы весь код выполнился в потоке диспетчера событий
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            JFrame frame = new JFrame("My temperature application");
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(
                    WindowConstants.EXIT_ON_CLOSE); // коно закрывется при закрытии

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            Image img = Toolkit.getDefaultToolkit().getImage("appImg.jpg");
            frame.setIconImage(img);

            JPanel panel = new JPanel();
            frame.add(panel);

            JButton button = new JButton("Панель инструментов");
            frame.add(button, BorderLayout.PAGE_START);
            button.addActionListener((e) -> button.setText("Ищу инструументы")); // button использовали замыкание

            frame.setVisible(true);
        });
    }
}
