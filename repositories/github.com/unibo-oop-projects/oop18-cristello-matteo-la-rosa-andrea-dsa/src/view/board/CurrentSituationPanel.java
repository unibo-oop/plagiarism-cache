package view.board;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import view.BoardColorPalette;
import view.TextDrawer;
/**
 * This panel manage the player current stats.
 */
public class CurrentSituationPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Font fontForCurrPS = new Font(Font.SANS_SERIF, Font.BOLD, 11);
    /**
     * This is the constructor of the panel.
     * @param playersCurrentSituation The list of string for all the players that hold all the player game paramethers.
     */
    public CurrentSituationPanel(final List<String> playersCurrentSituation) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.updateCurrentSituation(playersCurrentSituation);
        this.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));

    }
    /**
     * Update the panel.
     * @param playersCurrentSituation The list of string for all the players that hold all the player game paramethers.
     */
    public final void updateCurrentSituation(final List<String> playersCurrentSituation) {
        this.removeAll();
        IntStream.range(0, playersCurrentSituation.size())
                .forEach(i -> this.add(new TextDrawer(playersCurrentSituation.get(i))));
        Arrays.asList(this.getComponents()).stream().forEach(x -> {
            x.setFont(this.fontForCurrPS);
            x.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
            x.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        });
        this.repaint();
    }

}

