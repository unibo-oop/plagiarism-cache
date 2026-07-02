package view.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This panel sets the welcome message, asking the user to set 
 * the parameters required to launch the simulation.
 */
public class WelcomePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JTextArea welcomeMsg = new JTextArea();

    public WelcomePanel() { 
        welcomeMsg.setBackground(Color.lightGray);
        welcomeMsg.setEditable(false);
        welcomeMsg.setText("  Welcome to LiveLand! To start the "
                + "simulation please set the environment below.");
        welcomeMsg.setForeground(Color.CYAN);
        final Font bigFont = welcomeMsg.getFont().deriveFont(Font.PLAIN, 18f);
        welcomeMsg.setFont(bigFont);
        this.add(welcomeMsg);

    }

    /**
     * @param msg to show when an exception regarding parameters 
     * insertion occurs
     */
    protected void setWelcomeText(final String msg) {
        this.welcomeMsg.setText(msg);
        this.welcomeMsg.setForeground(Color.RED);
    }

}
