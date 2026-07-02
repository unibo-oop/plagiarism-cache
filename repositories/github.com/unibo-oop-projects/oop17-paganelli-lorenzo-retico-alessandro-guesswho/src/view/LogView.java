package view;

import java.awt.Dimension;
import javax.swing.*;
import utilities.*;
import view.viewposition.*;

/***/
public class LogView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH_PROPORTION = 3;
    private static final int HEIGHT_PROPORTION = 2;

    private final JPanel panel = new JPanel();

    /**
     * 
     */
    public LogView() {
        super();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        this.setMinimumSize(new Dimension(Utilities.getScreenDimension().width / WIDTH_PROPORTION, 
                Utilities.getScreenDimension().height / HEIGHT_PROPORTION));
        this.pack();
        this.setLocation(PositionManager.getSpecificPosition(this.getSize(), ViewPosition.CENTER));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Messages.TITLE);
        this.setVisible(true);
    }

    /**
     * @param message .
     */
    public void write(final String message) {
        panel.add(new JLabel(message));
    }

}
