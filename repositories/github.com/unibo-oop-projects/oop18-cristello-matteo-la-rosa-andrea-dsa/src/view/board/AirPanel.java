package view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.BoardColorPalette;

/**
 * This class manage the oxygen panel representation.
 */
public class AirPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Font fontForAir = new Font(Font.SANS_SERIF, Font.BOLD, 36);
    private final JLabel oxValue = new JLabel("");
    private final JLabel oxLabel = new JLabel("Remaining Air");

    /**
     * Class constructor.
     * 
     * @param value
     *                  The integer value to put at the start of the gameplay.
     */
    public AirPanel(final Integer value) {
        super();
        this.oxValue.setText(value.toString());
        this.setLayout(new BorderLayout());
        this.oxValue.setFont(fontForAir);
        this.oxLabel.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.add(oxValue, BorderLayout.CENTER);
        this.add(oxLabel, BorderLayout.PAGE_START);
        this.oxLabel.setHorizontalAlignment(JLabel.CENTER);
        this.oxValue.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Update the oxygen panel.
     * 
     * @param value
     *                  The integer value to put while game is running.
     * @throws IOException
     */
    public void updateOxygenPanel(final Integer value) {
        this.oxValue.setText(value.toString());
        this.revalidate();

    }
}
