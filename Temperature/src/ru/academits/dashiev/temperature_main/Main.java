package ru.academits.dashiev.temperature_main;

import ru.academits.dashiev.controller.Controller;
import ru.academits.dashiev.model.Model;
import ru.academits.dashiev.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*TODO  SwingUtilities.invokeLater(() чтобы весь код выполнился в потоке диспетчера. Но в
           архитектуре MVC куда вставлять эту функцию? Так как кажется, что много куда надо
           вставлять,точнее, абсолютно везде, где создаются UI элементы или вызываются ф-ии от них*/
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            Model m = new Model();
            View v = new View();
            Controller c = new Controller(m, v);
        });
    }
}