package ru.academits.dashiev.minesweeper_main;

import ru.academits.dashiev.controller.Controller;
import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception ignored) {

            }

            // Model m = new Model();
             View v = new View();
            // Controller c = new Controller(m, v);
        });
    }
}
