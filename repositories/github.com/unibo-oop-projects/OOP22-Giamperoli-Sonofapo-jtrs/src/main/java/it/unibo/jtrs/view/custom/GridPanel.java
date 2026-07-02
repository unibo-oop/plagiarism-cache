package it.unibo.jtrs.view.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jtrs.utils.Pair;

/**
 * This class models a graphical grid panel.
 */
public class GridPanel extends JPanel {

    public static final long serialVersionUID = 4328743;

    @SuppressFBWarnings(justification = "This field need to be transient")
    private final transient Map<Pair<Integer, Integer>, Cell> cells = new HashMap<>();
    private final int rows;
    private final int cols;

    /**
     * Constructor.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param padding the amount of top padding
     */
    public GridPanel(final int rows, final int cols, final int padding) {

        this.rows = rows;
        this.cols = cols;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(padding, 0, 0, 0));

        this.initalize();
    }

    private void initalize() {
        for (int i = 0; i < this.rows; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            for (int j = 0; j < this.cols; j++) {
                final var cell = new Cell();
                panel.add(cell);
                this.cells.put(new Pair<>(i, j), cell);
            }
            this.add(panel);
        }
    }

    /**
     * Updates a map of coordinates, each with their specified color. Restore the
     * remaining cells with a default color.
     *
     * @param map the map to read from
     */
    public void setCells(final Map<Pair<Integer, Integer>, Color> map) {
        this.cells.forEach((k, v) -> {
            v.setBackground(map.keySet().contains(k) ? map.get(k)
                : Constants.GridPanel.DEFAULT_COLOR);
        });
    }

    /**
     * Blinks all the lines of the grid contained in the specified set.
     *
     * @param lines a set of line number
     */
    public void blinkLines(final Set<Integer> lines) {
        lines.stream().sorted().forEach(l -> {
            this.cells.entrySet().stream()
                .filter(e -> e.getKey().getX().equals(l))
                .map(Entry::getValue)
                .forEach(Cell::blink);
        });
    }

    /**
     * A class modelling a square cell. A cell behaves exactly as a JLabel and
     * has a fixed size and a thin border.
     */
    static class Cell extends JLabel {

        public static final long serialVersionUID = 4328743;

        /**
         * Constructor.
         */
        Cell() {
            this.setIcon(new Icon() {

                @Override
                public void paintIcon(final Component c, final Graphics g, final int x, final int y) { }

                @Override
                public int getIconWidth() {
                    return Constants.GridPanel.CELL_SIZE;
                }

                @Override
                public int getIconHeight() {
                    return Constants.GridPanel.CELL_SIZE;
                }
            });

            this.setOpaque(true);
            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createRaisedBevelBorder()));
        }

        /**
         * Makes the cell blink for a short period of time.
         */
        public void blink() {
            this.setBackground(Color.LIGHT_GRAY);
            try {
                Thread.sleep(Constants.GridPanel.BLINK_TIME);
            } catch (InterruptedException e) { }
            this.setBackground(Constants.GridPanel.DEFAULT_COLOR);
        }
    }

}
