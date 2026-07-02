package it.unibo.the100dayswar.view.map;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * View for rendering the game map with a background image and a grid overlay.
 */
public class MapView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int CELL_SIZE = 50;
    private static final int OPACITY = 64;
    private static final String MAP_IMAGE_PATH = "/map/map.png";
    private CellView selectedCell;

    /**
     * Constructor for MapView.
     */
    public MapView() {
        this.selectedCell = null;
        super.setLayout(null);

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                handleCellClick(e.getX(), e.getY());
            }
        });
    }

    /**
     * Loads an image from the given path.
     * @param path the image path.
     * @return the loaded Image.
     */
    private Image loadImage(final String path) {
        try {
            final URL imageUrl = MapView.class.getResource(path);
            if (imageUrl != null) {
                return ImageIO.read(imageUrl);
            } else {
                throw new IllegalStateException("Image not found: " + path);
            }
        } catch (IOException e) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, "Error loading image: " + path, e);
            return null;
        }
    }

    /**
     * Paints the map view.
     * @param g the graphics object.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final MapController mapController = The100DaysWar.CONTROLLER.getMapController();
        final int totalWidth = mapController.getMapWidth() * CELL_SIZE;
        final int totalHeight = mapController.getMapHeight() * CELL_SIZE;
        g.drawImage(loadImage(MAP_IMAGE_PATH), 0, 0, totalWidth, totalHeight, this);

        final List<CellView> cellsView = mapController.getCellsView();

        for (final CellView cellView : cellsView) {
            final int xPos = cellView.getX() * CELL_SIZE;
            final int yPos = cellView.getY() * CELL_SIZE;

            final Image cellImage = loadImage(cellView.getImagePath());
            if (cellImage != null) {
                g.drawImage(cellImage, xPos, yPos, CELL_SIZE, CELL_SIZE, this);
            }

            g.setColor(Color.BLACK);
            g.drawRect(xPos, yPos, CELL_SIZE, CELL_SIZE);

            if (cellView.equals(selectedCell)) {
                g.setColor(new Color(0, 0, 0, OPACITY));
                g.fillRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    /**
     * Returns the preferred size of the map view.
     * @return the preferred size.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
            The100DaysWar.CONTROLLER.getMapController().getMapWidth() * CELL_SIZE, 
            The100DaysWar.CONTROLLER.getMapController().getMapHeight() * CELL_SIZE
            );
    }

    /**
     * Handles cell click and retrieves the clicked cell data.
     *
     * @param mouseX the X coordinate of the mouse click
     * @param mouseY the Y coordinate of the mouse click
     */
    private void handleCellClick(final int mouseX, final int mouseY) {
        final int cellX = mouseX / CELL_SIZE;
        final int cellY = mouseY / CELL_SIZE;
        final Optional<CellView> clickedCell = The100DaysWar.CONTROLLER.getMapController().getCellsView().stream()
                .filter(cell -> cell.getX() == cellX && cell.getY() == cellY)
                .findFirst();

        clickedCell.ifPresent(cell -> {
            this.selectedCell = cell;
            repaint();
            The100DaysWar.CONTROLLER.getMapController().onCellClick(cellX, cellY);
        });
    }

    /**
     * Gets the selected cell.
     * @return the selected cell.
     */
    public CellView getSelectedCell() {
        return this.selectedCell;
    }

}
