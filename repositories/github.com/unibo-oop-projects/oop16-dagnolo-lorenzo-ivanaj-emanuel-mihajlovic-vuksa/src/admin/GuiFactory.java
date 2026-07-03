package admin;

import java.awt.Color;

import javax.swing.JButton;

public class GuiFactory {

    public static JButton createButton(final String name) {
        JButton button1 = new JButton(name);
        button1.setBackground(Color.CYAN);
        return button1;
    }
}
