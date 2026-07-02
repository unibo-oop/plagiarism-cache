package view.menus;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;

/**
 * Player config panel to set the number of player before decide names and color.
 */

public class PlayerConfigPanel {

    /**
     * 
     */
    private static final String[] NUMBEROFPLAYERARRAY = { "2", "3", "4", "5", "6" };
    /**
     * 
     */
    private static final String LABELSTRING = "Select numbers of players";
    /**
     * 
     */
    private final JComboBox<String> combobox = new JComboBox<String>(NUMBEROFPLAYERARRAY);
    /**
     * 
     */
    private final JLabel label = new JLabel(LABELSTRING);
    /**
     * 
     */
    private final JButton buttonOk = new JButton("ok");
    /**
     * 
     */
    private final JPanel panel = new JPanel();

    /**
     * 
     */

    public PlayerConfigPanel() {

        this.panel.setLayout(new FlowLayout());
        this.label.setForeground(Color.white);
        this.panel.add(label);
        this.panel.add(combobox);
        this.panel.add(buttonOk);
        this.panel.setVisible(true);

    }

    /**
     * @return The panel.
     */
    public JPanel getConfiguration() {

        return this.panel;
    }

    /**
     * @return The button for ok.
     */
    public final JButton getButton() {
        return this.buttonOk;
    }

    /**
     * @return The combobox with number of players.
     */
    public JComboBox<String> getComboBox() {

        return this.combobox;

    }

    /**
     * @return This Label.
     */
    public JLabel getLabel() {
        return this.label;
    }
}
