package zengine.core;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import zengine.constants.ZengineColor;
import zengine.interfaces.GameEngine;
import zengine.interfaces.GameFunctionsGUI;

final class ZengineGUI extends JPanel implements ZengineComponent, GameFunctionsGUI {

    /**
     * 
     */
    private static final long serialVersionUID = 7912416020090479532L;

    private static final int DEFAULT_WINDOW_WIDTH = 800;
    private static final int DEFAULT_WINDOW_HEIGHT = 600;
    private static final int POINT_SYMBOL_SIZE = 24;
    private static final int DEFAULT_FONT_SIZE = 14;

    private static final int INFLATION_RATIO_3D = 500;
    private static final int Z_SHIFT_3D = 10;
    private static final double Z_OFFSET_FACTOR_3D = 0.02;
    private static final double Z_POW_FACTOR_3D = 0.8;
    private static final double Z_EPS_FACTOR_3D = 0.0008;

    private int windowWidth = DEFAULT_WINDOW_WIDTH;
    private int windowHeight = DEFAULT_WINDOW_HEIGHT;
    private double viewX = windowWidth / 2;
    private double viewY = windowHeight / 2;
    private double requestedViewX = viewX;
    private double requestedViewY = viewY;
    // private int viewAngle=0;
    private double viewScale = 1;
    private double requestedViewScale = viewScale;
    private boolean viewLocked; // = false;

    private static ZengineGUI gui = new ZengineGUI();

    private ZengineUtilities utilities;
    private ZengineSystem system;
    private Graphics graphicHandler;

    private ZengineGUI() {
        final ZengineWindow window; // = null;

        link();

        setBackground(ZengineColor.C_BLACK.getValue());
        setFocusable(true);
        setPreferredSize(new Dimension(windowWidth, windowHeight));

        window = new ZengineWindow(this);

        window.setTitle("Zengine Game");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public static ZengineGUI getGUI() {
        return gui;
    }

    @Override
    public ZengineComponent getComponent() {
        return gui;
    }

    @Override
    public void initialize() {
        link();
        restoreDefaultValues();
    }

    @Override
    public void reinitialize() {
        restoreDefaultValues();
    }

    @Override
    public void link() {
        system = ZengineSystem.getSystem();
        utilities = ZengineUtilities.getUtilities();
    }

    @Override
    public void restoreDefaultValues() {
        windowWidth = DEFAULT_WINDOW_WIDTH;
        windowHeight = DEFAULT_WINDOW_HEIGHT;
        viewX = windowWidth / 2;
        viewY = windowHeight / 2;
        requestedViewX = viewX;
        requestedViewY = viewY;
        viewScale = 1;
        requestedViewScale = viewScale;
        viewLocked = false;
    }

    private class ZengineWindow extends JFrame {
        /**
         * 
         */
        private static final long serialVersionUID = 7663986287881739953L;

        ZengineWindow(final ZengineGUI creator) {
            this.add(creator); // adds zengine's gui to the frame
        }
    }

    @Override
    public void update() {
        draw();
    }

    private void draw() {
        repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        graphicHandler = g;
        super.paintComponent(graphicHandler);
        doDrawing();
    }

    private void doDrawing() {
        // draw event

        if (viewLocked) {
            // modify requestedViewX, requestedViewY consistently with room
            // borders
            lockViewInRoom();
        }

        viewX = requestedViewX;
        viewY = requestedViewY;
        viewScale = requestedViewScale;

        system.drawAll();

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void drawSpriteExt(final String sprite, final double subimage, final double x, final double y, final double xscale,
            final double yscale, final double angle, final double alpha) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            final double scale = 1 / viewScale;
            double actualX = 0;
            double actualY = 0;

            if (sprite != null) {
                // sorry pmd but i need the two statements separated because I
                // need to be super sure that sprite!=null and I may need the
                // else branch in future
                if (!sprite.equals("")) { // //
                    final GameSprite toDraw = ZengineAssets.getAssets().spriteGet(Zengine.USR_SPRITE_PATH + sprite);
                    if (toDraw != null) {
                        // rotate according to hoffset
                        actualX = utilities.pointXrotate(x - toDraw.getxOffset() * xscale, y - toDraw.getyOffset() * yscale, x, y,
                                angle);
                        actualY = utilities.pointYrotate(x - toDraw.getxOffset() * xscale, y - toDraw.getyOffset() * yscale, x, y,
                                angle);
                        final Image image = toDraw.getSprite(subimage);
                        final Graphics2D g2d = (Graphics2D) graphicHandler.create();
                        g2d.translate(
                                actualX - viewX
                                        + ((actualX - viewX - windowWidth / 2) * scale - (actualX - viewX - windowWidth / 2)),
                                actualY - viewY
                                        + ((actualY - viewY - windowHeight / 2) * scale - (actualY - viewY - windowHeight / 2)));
                        g2d.rotate(Math.toRadians(-angle));
                        g2d.scale(xscale * scale, yscale * scale);

                        // Color c = new Color(0.0f, 0.0f, 0.0f, 1f); // Red
                        // with
                        // alpha
                        // =
                        // 0.5
                        // g2d.setPaint(c);

                        final AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                        g2d.setComposite(alcom);
                        // Drawing the rotated image at the required drawing
                        // locations
                        g2d.drawImage(image, 0, 0, this);
                    }
                }
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw sprite " + sprite + " outside the draw event");
        }
    }

    @Override
    public void drawSprite(final String sprite, final double subimage, final double x, final double y) {
        drawSpriteExt(sprite, subimage, x, y, 1, 1, 0, 1);
    }

    @Override
    public void drawTextDebug(final String text, final int x, final int y) {

        if (ZengineSystem.getSystem().isDrawEvent()) {
            final Font small = new Font("Helvetica", Font.BOLD, DEFAULT_FONT_SIZE);
            // FontMetrics metr = getFontMetrics(small);
            graphicHandler.setColor(Color.white);
            graphicHandler.setFont(small);
            graphicHandler.drawString(text, x, y);

        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw debug text outside the draw event");
        }
    }

    @Override
    public void viewSetX(final double x) {
        requestedViewX = x - windowWidth / 2;
    }

    @Override
    public void viewSetY(final double y) {
        requestedViewY = y - windowHeight / 2;
    }

    @Override
    public void viewSetPosition(final double x, final double y) {
        viewSetX(x);
        viewSetY(y);
    }

    @Override
    public double viewGetX() {
        return requestedViewX + windowWidth / 2;
    }

    @Override
    public double viewGetY() {
        return requestedViewY + windowHeight / 2;
    }

    @Override
    public void viewSetScale(final double scale) {
        requestedViewScale = scale;
    }

    @Override
    public double viewGetScale() {
        return requestedViewScale;
    }

    @Override
    public double viewGetCornerX() {
        return viewGetX() - windowWidth / 2 * viewGetScale();
    }

    @Override
    public double viewGetCornerY() {
        return viewGetY() - windowHeight / 2 * viewGetScale();
    }

    @Override
    public double windowToWorldX(final int x) {
        return viewGetCornerX() + x * viewGetScale();
    }

    @Override
    public double windowToWorldY(final int y) {
        return viewGetCornerY() + y * viewGetScale();
    }

    @Override
    public int worldToWindowX(final double x) {
        final double scale = 1 / viewScale;
        return (int) (x - viewX + ((x - viewX - windowWidth / 2) * scale - (x - viewX - windowWidth / 2)));
    }

    @Override
    public int worldToWindowY(final double y) {
        final double scale = 1 / viewScale;
        return (int) (y - viewY + ((y - viewY - windowHeight / 2) * scale - (y - viewY - windowHeight / 2)));
    }

    @Override
    public int windowGetWidth() {
        return this.getWidth();
    }

    @Override
    public int windowGetHeight() {
        return this.getHeight();
    }

    @Override
    public void drawRectangle(final double x, final double y, final double width, final double height, final ZengineColor color,
            final boolean filled) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            graphicHandler.setColor(color.getValue());
            if (filled) {
                graphicHandler.fillRect(worldToWindowX(x), worldToWindowY(y), (int) (width / viewScale),
                        (int) (height / viewScale));
            } else {
                graphicHandler.drawRect(worldToWindowX(x), worldToWindowY(y), (int) (width / viewScale),
                        (int) (height / viewScale));
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw rectangle outside the draw event");
        }
    }

    @Override
    public void drawEllipse(final double x, final double y, final double width, final double height, final ZengineColor color,
            final boolean filled) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            graphicHandler.setColor(color.getValue());
            if (filled) {
                graphicHandler.fillOval(worldToWindowX(x), worldToWindowY(y), (int) (width / viewScale),
                        (int) (height / viewScale));
            } else {
                graphicHandler.drawOval(worldToWindowX(x), worldToWindowY(y), (int) (width / viewScale),
                        (int) (height / viewScale));
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw ellipse outside the draw event");
        }
    }

    @Override
    public void drawCircle(final double x, final double y, final double radius, final ZengineColor color, final boolean filled) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            drawEllipse(x - radius, y - radius, radius * 2, radius * 2, color, filled);

        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw circle outside the draw event");
        }
    }

    @Override
    public void drawLine(final double x1, final double y1, final double x2, final double y2, final ZengineColor color) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            graphicHandler.setColor(color.getValue());
            graphicHandler.drawLine(worldToWindowX(x1), worldToWindowY(y1), worldToWindowX(x2), worldToWindowY(y2));

        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw line outside the draw event");
        }
    }

    @Override
    public void drawPoint(final double x, final double y, final ZengineColor color) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            drawLine(x, y - POINT_SYMBOL_SIZE, x, y + POINT_SYMBOL_SIZE, color);
            drawLine(x - POINT_SYMBOL_SIZE, y, x + POINT_SYMBOL_SIZE, y, color);
            drawCircle(x, y, 3, color, false);

        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw point outside the draw event");
        }
    }

    @Override
    public void drawSpriteHUD(final String sprite, final double subimage, final int x, final int y, final double xscale,
            final double yscale, final double angle, final double alpha) {
        drawSpriteExt(sprite, subimage, windowToWorldX(x), windowToWorldY(y), viewGetScale() * xscale, viewGetScale() * yscale,
                angle, alpha);
    }

    @Override
    public void drawText(final String text, final double x, final double y, final String fontAlias, final ZengineColor color) {
        drawTextHUD(text, worldToWindowX(x), worldToWindowY(y), fontAlias, color);
    }

    @Override
    public void drawTextHUD(final String text, final int x, final int y, final String fontAlias, final ZengineColor color) {
        if (ZengineSystem.getSystem().isDrawEvent()) {
            if (ZengineAssets.getAssets().fontAliasExists(fontAlias)) {
                final GameFont font = ZengineAssets.getAssets().fontGet(fontAlias);
                if (font.isValid()) {
                    graphicHandler.setFont(font.getFont());
                    graphicHandler.setColor(color.getValue());
                    graphicHandler.drawString(text, x, y + (int) font.getHeight());
                } else {
                    ZengineLogger.getLogger().loggerError("tried to draw invalid font " + fontAlias);
                }
            } else {
                ZengineLogger.getLogger().loggerError("tried to draw undefined font " + fontAlias);
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to draw text outside the draw event");
        }
    }

    @Override
    public void viewSetLocked(final boolean locked) {
        this.viewLocked = locked;
    }

    @Override
    public boolean viewIsLocked() {
        return viewLocked;
    }

    private void lockViewInRoom() {
        // forces the camera to stay inside room borders
        final GameEngine ze = Zengine.getEngine();
        if (viewGetWidth() > ze.roomWidth()) {
            viewSetX(ze.roomWidth() * 0.5);
        } else {
            if (viewGetCornerX() < 0) {
                viewSetX(viewGetWidth() * 0.5);
            }
            if (viewGetCornerX() + viewGetWidth() > ze.roomWidth()) {
                viewSetX(ze.roomWidth() - viewGetWidth() * 0.5);
            }
        }
        if (viewGetHeight() > ze.roomHeight()) {
            viewSetY(ze.roomHeight() * 0.5);
        } else {
            if (viewGetCornerY() < 0) {
                viewSetY(viewGetHeight() * 0.5);
            }
            if (viewGetCornerY() + viewGetHeight() > ze.roomHeight()) {
                viewSetY(ze.roomHeight() - viewGetHeight() * 0.5);
            }
        }
    }

    @Override
    public double viewGetWidth() {
        return windowWidth * viewGetScale();
    }

    @Override
    public double viewGetHeight() {
        return windowHeight * viewGetScale();
    }

    @Override
    public void drawSprite3D(final String sprite, final double subimage, final double x, final double y, final double z,
            final double xscale, final double yscale, final double angle, final double alpha) {
        final double zoom = viewGetScale();
        final double tempZ = calculateZ3D(z / zoom);
        final double scale = (tempZ / INFLATION_RATIO_3D);
        final double hor = getHorizontal3D(x);
        final double ver = getVertical3D(y);

        drawSpriteExt(sprite, subimage, x - (tempZ * hor), y - (tempZ * ver), xscale + scale * xscale, yscale + scale * yscale,
                angle, alpha);
    }

    // 3d utilities
    private double calculateZ3D(final double z) {
        if (z <= 0) {
            return (-Z_SHIFT_3D * z) / (Z_OFFSET_FACTOR_3D * z - Z_SHIFT_3D);
        } else {
            return ZengineUtilities.getUtilities().power(Z_POW_FACTOR_3D * z, (Z_EPS_FACTOR_3D * z + 1));
        }
    }

    private double getVertical3D(final double y) {
        return (-(y - (viewGetY())) / INFLATION_RATIO_3D);
    }

    private double getHorizontal3D(final double x) {
        return (-(x - (viewGetX())) / INFLATION_RATIO_3D);
    }
}
