package view.menus;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * Class to manage combobox.
 *
 */
public class PlayersCharComboBox {

    private final JPanel panel = new JPanel();
    private static final String LABEL = "Name";
    private final JLabel labelName = new JLabel(LABEL);
    private final TextField textFieldPlayerName = new TextField(30);
    private final JComboBox<String> colorsComboBox;

    /**
     * Constructor of panel.
     * @param playerColorList List of color assigned to players.
     */
    public PlayersCharComboBox(final List<String> playerColorList) {
        String[] colorList = new String[playerColorList.size()]; //per togliere l'errore pmd
        colorList = playerColorList.toArray(colorList);
        colorsComboBox = new JComboBox<String>(colorList);
        panel.setLayout(new FlowLayout());
        this.labelName.setForeground(Color.white);
        panel.add(this.labelName);
        panel.add(this.textFieldPlayerName);
        panel.add(colorsComboBox);
        panel.setOpaque(false);
        panel.setVisible(true);

    }

    /**
     * @return il pannello completo
     */
    public JPanel getConfiguration() {
        return panel;
    }

    /**
     * @return la combobox
     */

    public JComboBox<String> getComboBox() {
        return this.colorsComboBox;
    }

    /**
     * @return la textField
     */
    public TextField getTextField() {
        return this.textFieldPlayerName;
    }
}