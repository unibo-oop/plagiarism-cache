package it.unibo.memory.controller;

import it.unibo.memory.view.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(final String[] args) {
        // SwingUtilities ci assicura che la grafica venga caricata nel modo corretto
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            // Rendiamo visibile la finestr
            frame.setVisible(true);
        });
    }
}