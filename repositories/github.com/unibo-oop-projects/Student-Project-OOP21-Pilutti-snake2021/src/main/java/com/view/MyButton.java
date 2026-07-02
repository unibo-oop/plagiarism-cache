package main.java.com.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Extended {@link JButton} class for a personalized button look.
 *
 */
public class MyButton extends JButton {

    private static final long serialVersionUID = 5184638749083375107L;
    private static final Font FONT = new Font("Tahoma", Font.BOLD, 21);

    /**
     * Constructor that instantiates the button.
     * 
     * @param txt the string to be written on the button.
     */
    public MyButton(final String txt) {
        super(txt);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(FONT);
    }
}
