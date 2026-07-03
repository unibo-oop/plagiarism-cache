package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * choosing panel.
 *
 */
public class ChoosingPanelImpl extends JPanel implements ChoosingPanel {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public ChoosingPanelImpl() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setVisible(true);
        this.setBackground(Colors.getBackgroundColor());

    }

    @Override
    public void setBorderName(final String name) {
        this.setBorder(new TitledBorder(name));
    }

}