package view.menu.fair;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This panel is aimed at setting the fair capacity.
 */
public class CapacityPanel extends JPanel {

    private static final long serialVersionUID = -6701801878635285791L;
    private static final int TEXT_FIELD_WIDTH = 8;
    private final JLabel capacity = new JLabel("Fair capacity:");
    private final JTextField textCapacity = new JTextField("", TEXT_FIELD_WIDTH);

    public CapacityPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(capacity);
        this.add(textCapacity);
    }

    /**
     * @return the capacity chosen by the user
     */
    public int getCapacity() {
        return Integer.parseInt(textCapacity.getText());
    }

    /**
     * @param text to be written in the capacity text field
     */
    public void setCapacityText(final String text) {
        this.textCapacity.setText(text);
    }

}
