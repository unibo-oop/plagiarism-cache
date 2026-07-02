package it.unibo.pacman.controller.mapeditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.mapeditor.Tile;
/**
 * A mouse listener for the grid.
 *
 */
public class GridListener implements MouseListener, MouseMotionListener {

    private final MapEditorController controller;
    private boolean entered;

    /**
     * Create a grid mouse listener.
     * 
     * @param controller is the controller of the map editor
     */
    public GridListener(final MapEditorController controller) {
        super();
        this.controller = controller;
        this.entered = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent e) {
        this.mouseClicked(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        final int x = e.getX() / Tile.getSize();
        final int y = e.getY() / Tile.getSize();
        if (this.entered) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                this.controller.updatePosition(x, y);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                final Tile oldTile = this.controller.getSelectedTile();
                this.controller.setSelectedTile(new Tile(EntityType.EMPTY));
                this.controller.updatePosition(x, y);
                this.controller.setSelectedTile(oldTile);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
        this.entered = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent e) {
        this.entered = false;
    }
}
