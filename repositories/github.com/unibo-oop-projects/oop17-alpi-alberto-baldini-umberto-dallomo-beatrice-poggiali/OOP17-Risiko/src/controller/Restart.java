package controller;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * A simple restart menu.
 */
public final class Restart {

    private Restart() { }

    /**
     * static method to start a new game.
     */
    public static void restart() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("ricominciare il gioco?"));
        JButton a = new JButton("SI");
        a.addActionListener(b -> {
            view.mainmenu.MenuIni.startMenu();
            System.exit(0);
            });
        a.setEnabled(true);
        //a.setVisible(true);
        JButton c = new JButton("NO");
        c.addActionListener(d -> System.exit(0));

        panel.add(a);
        panel.add(c);
        panel.setVisible(true);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
