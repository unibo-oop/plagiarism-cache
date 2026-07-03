package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;

/**Questa classe è un contenitore che tiene insieme lo StatoPanel e il GamePanel */

public class GameView extends JPanel {

    public GameView(GamePanel gamePanel, JPanel statoPanel) {
        // Usiamo il BorderLayout: usando le i punti cardinali per posizionarli (Nord, Sud, Centro...)
        setLayout(new BorderLayout());

        // Posizionamento del pannello informativo in alto (Nord)
        if (statoPanel != null) {
            add(statoPanel, BorderLayout.NORTH);
        }

        // Posiziono il GamePanel al centro
        if (gamePanel != null) {
            add(gamePanel, BorderLayout.CENTER);
        }
    }
}