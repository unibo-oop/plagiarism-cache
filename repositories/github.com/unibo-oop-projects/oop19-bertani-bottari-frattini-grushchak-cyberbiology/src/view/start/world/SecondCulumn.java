package view.start.world;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.data.setting.AddElem;
import view.menu.data.setting.AddElemValue;

/**
 * Class that implements a graphical panel containing the clock, the world specifications, the button to stop the application.
 *
 */
public class SecondCulumn extends JPanel implements AddElem {

    /**
     */
    private static final long serialVersionUID = 2613526913179393503L;

    /**
     * Builder that takes the graphic elements and adds them by placing them in a single panel.
     * @param specific interface of the specific's world
     * @param clockPanel graphic interface of the clock
     */
    public SecondCulumn(final AddElemValue<JLabel> clockPanel, final AddElem specific) {
        JPanel panel = new JPanel();
        JPanel stoppanel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(clockPanel.getElem());
        panel.add(specific.getElem());
        panel.add(stoppanel);
        this.add(panel);
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

}
