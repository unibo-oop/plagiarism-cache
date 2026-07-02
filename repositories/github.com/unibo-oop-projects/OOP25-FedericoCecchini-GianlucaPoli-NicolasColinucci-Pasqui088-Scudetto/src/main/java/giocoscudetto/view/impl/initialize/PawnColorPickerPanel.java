package giocoscudetto.view.impl.initialize;

import giocoscudetto.view.api.initialize.PawnColorPicker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Implementation of PawnColorPicker interface, to make possible the pawn's color selection.
 */
public final class PawnColorPickerPanel extends JPanel implements PawnColorPicker {

    static final Color[] AVAILABLE_COLORS = {
        new Color(231, 76, 60),   //Red Color
        new Color(52, 152, 219),  //Blue Color
        new Color(46, 204, 113),  //Green Color
        new Color(241, 196, 15),  //Yellow Color
    };

    private static final long serialVersionUID = 1L;
    private static final int BTN_SIZE = 32;

    private Color selectedColor;
    private final List<JButton> buttons = new ArrayList<>();
    private transient Consumer<Color> onColorChanged;

    /**
     * Implementing the logic to make work great the club's pawn color selection.
     */
    public PawnColorPickerPanel() {
        //CHECKSTYLE: MagicNumber OFF
        // Turning off magic number checkstyle as it is useless to create dedicated fields
        // for each magic number in this situation 
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 0));
        this.setOpaque(false);

        for (final Color c: AVAILABLE_COLORS) {

            final JButton btn = new JButton() {
                @Override
                protected void paintComponent(final Graphics g) {
                    final Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                        RenderingHints.VALUE_ANTIALIAS_ON);

                    //If a color is already selected it gets darker cause it cannot be choosed form other club
                    g2.setColor(isEnabled() ? c : c.darker().darker());
                    g2.fillOval(3, 3, getWidth() - 6, getHeight() - 6);

                    //And the border become balck
                    if (c.equals(selectedColor)) {
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(3));
                    } else {
                        g2.setColor(isEnabled() ? Color.GRAY : new Color(160, 160, 160));
                        g2.setStroke(new BasicStroke(1));
                    }
                    g2.drawOval(3, 3, getWidth() - 6, getHeight() - 6);

                    //And a cross is displayed in the center of it
                    if (!isEnabled()) {
                        g2.setColor(new Color(255, 255, 255, 180));
                        g2.setStroke(new BasicStroke(2.5f));
                        final int p = 10;
                        g2.drawLine(p, p, getWidth() - p, getHeight() - p);
                        g2.drawLine(getWidth() - p, p, p, getHeight() - p);
                    }

                    g2.dispose();
                }
            };
            //CHECKSTYLE: MagicNumber ON

            //Changing button size and properties when going on it
            btn.setPreferredSize(new Dimension(BTN_SIZE, BTN_SIZE));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> {
                if (btn.isEnabled()) {
                    selectedColor = c;
                    buttons.forEach(JButton::repaint);

                    if (onColorChanged != null) {
                        onColorChanged.accept(c);
                    } 
                }
            });

            buttons.add(btn);
            this.add(btn);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTakenColors(final Set<Color> takenByOthers) {
        for (int i = 0; i < AVAILABLE_COLORS.length; i++) {
            final boolean isMine = AVAILABLE_COLORS[i].equals(selectedColor);
            buttons.get(i).setEnabled(!takenByOthers.contains(AVAILABLE_COLORS[i]) || isMine);
        }
        repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getSelectedColor() {
        return selectedColor; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColorSelected() {
        return this.getSelectedColor() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        selectedColor = null;
        buttons.forEach(btn -> btn.setEnabled(true));
        repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnColorChanged(final Consumer<Color> callback) {
        this.onColorChanged = callback;
    }
}
