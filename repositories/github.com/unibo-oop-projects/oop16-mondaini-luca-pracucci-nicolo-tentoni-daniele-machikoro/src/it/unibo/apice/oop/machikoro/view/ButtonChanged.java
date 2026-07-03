package it.unibo.apice.oop.machikoro.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Classe che decora bottoni.
 */
public class ButtonChanged extends JButton {

    /**
     * Global Variables
     */
    private static final long serialVersionUID = 1L;
    private static final int RGB_WHITE = 255;
    private static final int R = 0;
    private static final int G = 172;
    private static final int B = 220;
    private static final int BUTTONMATCH = 128;
    private static final int FONT = 45;

    /**
     * Costruttore senza argomenti.
     */
    public ButtonChanged() {
        super();
        this.setBackground(new Color(R, G, B));
    }

    /**
     * Costruttore con 2 argomenti.
     * 
     * @param name
     *            Testo che avrà il bottone.
     * @param n
     *            Grandezza font del bottone.
     */
    public ButtonChanged(final String name, final int n) {
        super(name);
        // Button con background colore azzurro,foreground colore bianco, bordi
        // bianchi e Font personalizzato.
        this.setBackground(new Color(R, BUTTONMATCH, RGB_WHITE));
        this.setForeground(new Color(RGB_WHITE, RGB_WHITE, RGB_WHITE));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setFont(new Font("Segoe Print", Font.BOLD, n));
    }

    /**
     * Costruttore con 1 argomento.
     * 
     * @param name
     *            Testo che avrà il bottone.
     */
    public ButtonChanged(final String name) {
        super(name);
        this.setFont(new Font("Segoe Print", Font.BOLD, FONT));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setFocusable(false);
        this.setBackground(new Color(R, G, B));
        this.setForeground(new Color(RGB_WHITE, RGB_WHITE, RGB_WHITE));
    }
}
