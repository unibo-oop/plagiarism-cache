package view.menu.data.setting;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import data.Language;
import utilities.DimensionComponent;

/**
 * Class that implements a panel containing the description of the menu to set the parameters.
 *
 */
public class AddMenuDescriptrion extends JPanel implements AddElem {
    /**
     */
    private static final long serialVersionUID = -3347005479021142881L;
    public AddMenuDescriptrion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JSeparator());
        panel.add(text(Language.getkeyofbundle("MenuDescription")));
        panel.add(new JSeparator());
        panel.add(new JSeparator());
        this.add(panel);
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    private JPanel text(final String text) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(DimensionComponent.MENU_DESCRIPTION_PANEL.getDimension());
        panel.add(new JLabel(text));
        return panel;
    }

}
