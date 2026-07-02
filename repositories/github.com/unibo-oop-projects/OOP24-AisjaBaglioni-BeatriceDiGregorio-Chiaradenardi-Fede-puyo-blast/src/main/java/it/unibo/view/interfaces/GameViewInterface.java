package it.unibo.view.interfaces;

import java.awt.Graphics;

public interface GameViewInterface {
    /**
     * Disegna il contenuto della vista di gioco.
     *
     * @param g      l'oggetto Graphics utilizzato per il rendering
     * @param width  la larghezza dell'area da disegnare
     * @param height l'altezza dell'area da disegnare
     */
    void render(Graphics g, int width, int height);


}
