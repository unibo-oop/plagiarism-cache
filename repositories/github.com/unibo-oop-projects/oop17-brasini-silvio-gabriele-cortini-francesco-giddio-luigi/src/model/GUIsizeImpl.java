package model;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Classe che implementa l'interfaccia GUIsize.
 * @author silviobrasini
 *
 */
public class GUIsizeImpl implements GUIsize {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int MIN_WIDTH = screenSize.width * 1 / 5;
    private static final int MIN_HEIGHT = screenSize.height * 1 / 4;
    private JFrame frame;

    /**
     * Costruttore di GUIsizeImpl.
     * 
     * @param frame frame per il quale settare la dimensione
     */
    public GUIsizeImpl(final JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void setSize() {
        this.frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    }
}
