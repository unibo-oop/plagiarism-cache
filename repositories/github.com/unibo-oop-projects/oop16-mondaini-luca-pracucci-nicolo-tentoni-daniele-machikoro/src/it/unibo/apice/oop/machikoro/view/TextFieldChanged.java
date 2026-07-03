package it.unibo.apice.oop.machikoro.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

/**
 * Classe utilizzata per decorare TextFields.
 */
public class TextFieldChanged extends JTextField {

    /**
     * Global Variables
     */
    private static final long serialVersionUID = 1L;
    private static final int FONT = 40;
    private static final int RGB_WHITE = 255;
    private static final int R = 0;
    private static final int G = 172;
    private static final int B = 220;

    /**
     * Costruttore.
     * 
     * @param name
     *            Testo che dovrà possederà la TextField.
     * @param n
     *            Parametro che viene usato per impostare le textfield abilitate
     *            o no.
     */
    public TextFieldChanged(final String name, final int n) {
        super(name);
        // TextField avrà un background colore azzurro, foreground colore bianco
        // e un Font personalizzato.
        this.setFont(new Font("Segoe Print", Font.BOLD, FONT));
        this.setForeground(new Color(RGB_WHITE, RGB_WHITE, RGB_WHITE));
        this.setBackground(new Color(R, G, B));
        if (n == 0) {
            this.setVisible(false);
        }

    }
}
