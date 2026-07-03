package it.unibo.apice.oop.machikoro.view;

import java.awt.Color;
import javax.swing.JRadioButton;

/**
 * Classe utilizzata per decorare i RadioButton usati all'interno del programma.
 */
public class RadioButtonChanged extends JRadioButton {

    /**
     * Global Variables
     */
    private static final long serialVersionUID = 1L;
    private static final int R = 0;
    private static final int G = 172;
    private static final int B = 220;

    /**
     * Costruttore senza argomenti.
     */
    public RadioButtonChanged() {
        super();
        // RadioButton avrà un background colore azzurro.
        this.setBackground(new Color(R, G, B));
    }
}
