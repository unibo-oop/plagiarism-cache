package view.board;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * The panel that show the dices image.
 */
public class DicesPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Dimension dimension;
    /**
     * Constructor of the class.
     * @param dimension the dimension of the panel.
     * 
     */
    public DicesPanel(final Dimension dimension) {
        super();
        this.dimension = dimension;
        this.setLayout(new BoxLayout(this, 1));
    }
    /**
     * This method update the dices panel.
     * @param value The value to show in the panel.
     */
    public void updateDicesPanel(final Integer value) {
        this.removeAll();
        final String imgAddr = "/Dices" + value + ".png";
        final DrawImagePanel dicesPanel = new DrawImagePanel(imgAddr, this.dimension);
        this.add(dicesPanel);
        this.revalidate();
    }

}
