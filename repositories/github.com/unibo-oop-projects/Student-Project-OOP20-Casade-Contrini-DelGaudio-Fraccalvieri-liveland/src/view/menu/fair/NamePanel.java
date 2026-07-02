package view.menu.fair;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This panel asks the user to set the fair name.
 */
public class NamePanel extends JPanel {

    private static final long serialVersionUID = -2519848286506649556L; 
    private static final int TEXT_FIELD_WIDTH = 8;
    private final JLabel name = new JLabel("Choose a name:");
    private final JTextField textName = new JTextField("", TEXT_FIELD_WIDTH);

    public NamePanel() {
        this.add(name);
        this.add(textName);
    }

    /**
     * @return the name written by the user in the name text field
     */
    public String getName() {
        return this.textName.getText();
    }

    /**
     * @param msg to be written in the name text field
     */
    public void setTextName(final String msg) {
        this.textName.setText(msg);
    }

}
