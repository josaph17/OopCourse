package ru.academits.dashiev.temperature_main;

import ru.academits.dashiev.controller.Controller;
import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // чтобы весь код выполнился в потоке диспетчера событий
        SwingUtilities.invokeLater(() -> {
            try {
                // чтобы приложение подходило для Windows, Linux и Mac
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            Model m = new Model(40);
            View v = new View("My temperature app");
            Controller c = new Controller(m,v);
        });
    }
}
