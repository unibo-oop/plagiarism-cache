package it.unibo.scat.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Class for customizable button, used both from MenuPanel and GamePanel.
 */
public final class CustomButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color HOVER_COLOR = new Color(14, 158, 2, 125);
    private static final Color SELECTED_COLOR = new Color(14, 158, 2);
    private Color actualColor = DEFAULT_COLOR;
    private final transient Image image;
    private boolean selected;

    /**
     * Custom button constructor.
     * 
     * @param imagePath the image's path.
     */
    public CustomButton(final String imagePath) {

        image = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(imagePath))).getImage();

        setBackground(actualColor);
        setBorder(new LineBorder(Color.BLACK, 2));
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                actualColor = HOVER_COLOR;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                actualColor = DEFAULT_COLOR;
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (selected) {
            actualColor = SELECTED_COLOR;
        }
        setBackground(actualColor);
        final int space = 10;

        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.drawImage(image, space / 2, space * 2, getWidth() - space, getHeight() - space, this);
        g2.dispose();

    }

    /**
     * Sets the selection.
     * 
     * @param bool boolean that is true if it's selected.
     */
    public void setSelection(final boolean bool) {
        actualColor = DEFAULT_COLOR;
        this.selected = bool;
    }
}
