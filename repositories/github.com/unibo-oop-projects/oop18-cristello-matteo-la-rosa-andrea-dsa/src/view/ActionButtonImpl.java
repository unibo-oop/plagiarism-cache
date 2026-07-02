package view;

import java.awt.Color;

import javax.swing.JButton;


/**
 * This is an action button implementation that extend Jbutton, created for project purposes.
 */
public class ActionButtonImpl extends JButton {

    /**
    * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is the constructor of ActionButtonImpl.
     * 
     * @param label
     *                        The label on button
     * @param commandName
     *                        The command name
     */
    public ActionButtonImpl(final String label, final String commandName) {
        super(label);
        this.setActionCommand(commandName);
        this.setBackground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.setForeground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
    }

}
