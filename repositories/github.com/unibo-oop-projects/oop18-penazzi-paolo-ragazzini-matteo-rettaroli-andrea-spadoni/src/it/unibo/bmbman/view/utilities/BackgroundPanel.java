package it.unibo.bmbman.view.utilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * We use this class to set a background image in a JFrame.
 * We didn't implement this Class but we took it from "https://tips4java.wordpress.com/2008/10/12/background-panel/".
 * We just fixed the bugs and the code style error. (add final to variable, add javadoc...)
 */

/**
 *  Support custom painting on a panel in the form of
 *
 *  a) images - that can be scaled, tiled or painted at original size
 *  b) non solid painting - that can be done by using a Paint object
 *
 *  Also, any component added directly to this panel will be made
 *  non-opaque so that the custom painting can show through.
 */
public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = -5024329227139713057L;
    /**
     * the integer associated to a scaled image.
     */
    public static final int SCALED = 0;
    /**
     * the integer associated to a tiled image.
     */
    public static final int TILED = 1;
    /**
     * the integer associated to a actual image.
     */
    public static final int ACTUAL = 2;
    private Paint painter;
    private Image image;
    private int style = SCALED;
    private float alignmentX = 0.5f;
    private float alignmentY = 0.5f;
    private boolean isTransparentAdd = true;

    /**
     * Set image as the background with the SCALED style.
     * @param image the background picture.
     */
    public BackgroundPanel(final Image image) {
        this(image, ACTUAL);
    }
    /**
     *  Set image as the background with the specified style.
     *  @param image the background picture.
     *  @param style the way the image will be placed.
     */
    public BackgroundPanel(final Image image, final int style) {
        super();
        setImage(image);
        setStyle(style);
        setLayout(new BorderLayout());
    }
    /**
     * Set image as the background with the specified style and alignment.
     *  @param image the background picture.
     *  @param style the way the image will be placed.
     *  @param alignmentX the x coordinate.
     *  @param alignmentY the y coordinate.
     */
    public BackgroundPanel(final Image image, final int style, final float alignmentX, final float alignmentY) {
        super();
        setImage(image);
        setStyle(style);
        setImageAlignmentX(alignmentX);
        setImageAlignmentY(alignmentY);
        setLayout(new BorderLayout());
    }
    /**
     *  Use the Paint interface to paint a background.
     *  @param painter the Paint
     */
    public BackgroundPanel(final Paint painter) {
        super();
        setPaint(painter);
        setLayout(new BorderLayout());
    }
    /**
     * Set the image used as the background.
     * @param image the image.
     */
    public final void setImage(final Image image) {
        this.image = image;
        repaint();
    }

    /**
     * Set the style used to paint the background image.
     * @param style the integer representing the style.
     */
    public void setStyle(final int style) {
        this.style = style;
        repaint();
    }
    /**
     * Set the Paint object used to paint the background.
     * @param painter the Paint we want to set.
     */
    public final void setPaint(final Paint painter) {
        this.painter = painter;
        repaint();
    }
    /**
     * Specify the horizontal alignment of the image when using ACTUAL style.
     * @param alignmentX the x coordinate.
     */
    public void setImageAlignmentX(final float alignmentX) {
        this.alignmentX = alignmentX > 1.0f ? 1.0f : alignmentX < 0.0f ? 0.0f : alignmentX;
        repaint();
    }
    /**
     *  Specify the horizontal alignment of the image when using ACTUAL style.
     *  @param alignmentY the y coordinate.
     */
    public void setImageAlignmentY(final float alignmentY) {
        this.alignmentY = alignmentY > 1.0f ? 1.0f : alignmentY < 0.0f ? 0.0f : alignmentY;
        repaint();
    }
    /**
     *  Override method so we can make the component transparent.
     *  @param component the component we want to make transparent.
     */
    public void add(final JComponent component) {
        add(component, null);
    }
    /**
     *  Override to provide a preferred size equal to the image size.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image == null) {
            return super.getPreferredSize();
        } else {
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }
    /**
     *  Override method so we can make the component transparent.
     *  @param component {@link JComponent}
     *  @param constraints an object expressing layout constraints for this component.
     */
    public void add(final JComponent component, final Object constraints) {
        if (isTransparentAdd) {
            makeComponentTransparent(component);
        }
        super.add(component, constraints);
    }
    /**
     *  Controls whether components added to this panel should automatically
     *  be made transparent. That is, setOpaque(false) will be invoked.
     *  The default is set to true.
     *  @param isTransparentAdd whether it should be transparent or not.
     */
    public void setTransparentAdd(final boolean isTransparentAdd) {
        this.isTransparentAdd = isTransparentAdd;
    }
    /**
     *  Try to make the component transparent.
     *  For components that use render, like JTable, you will also need to
     *  change the renderer to be transparent. An easy way to do this it to
     *  set the background of the table to a Color using an alpha value of 0.
     */
    private void makeComponentTransparent(final JComponent component) {
        component.setOpaque(false);
        if (component instanceof JScrollPane) {
            final JScrollPane scrollPane = (JScrollPane) component;
            final JViewport viewport = scrollPane.getViewport();
            viewport.setOpaque(false);
            final Component c = viewport.getView();
            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(false);
            }
        }
    }
    /**
     *  Add custom painting.
     *  @param g the Graphics object to protect.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        //  Invoke the painter for the background
        if (painter != null) {
            final Dimension d = getSize();
            final Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(painter);
            g2.fill(new Rectangle(0, 0, d.width, d.height));
        }
        //  Draw the image
        if (image == null) {
            return;
        }
        switch (style) {
            case SCALED :
                drawScaled(g);
                break;

            case TILED  :
                drawTiled(g);
                break;

            case ACTUAL :
                drawActual(g);
                break;

            default:
                drawScaled(g);
        }
    }
    /**
     *  Custom painting code for drawing a SCALED image as the background.
     *  @param g the Graphics object.
     */
    private void drawScaled(final Graphics g) {
        final Dimension d = getSize();
        g.drawImage(image, 0, 0, d.width, d.height, null);
    }
    /**
     *  Custom painting code for drawing TILED images as the background.
     *  @param g the Graphic object.
     */
    private void drawTiled(final Graphics g) {
        final Dimension d = getSize();
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        for (int x = 0; x < d.width; x += width) {
            for (int y = 0; y < d.height; y += height) {
                g.drawImage(image, x, y, null, null);
            }
        }
    }

    /**
     *  Custom painting code for drawing the ACTUAL image as the background.
     *  The image is positioned in the panel based on the horizontal and
     *  vertical alignments specified.
     *  @param g the Graphic component.
     */
    private void drawActual(final Graphics g) {
        final Dimension d = getSize();
        final Insets insets = getInsets();
        final int width = d.width - insets.left - insets.right;
        final int height = d.height - insets.top - insets.left;
        final float x = (width - image.getWidth(null)) * alignmentX;
        final float y = (height - image.getHeight(null)) * alignmentY;
        g.drawImage(image, (int) x + insets.left, (int) y + insets.top, this);
    }
}
