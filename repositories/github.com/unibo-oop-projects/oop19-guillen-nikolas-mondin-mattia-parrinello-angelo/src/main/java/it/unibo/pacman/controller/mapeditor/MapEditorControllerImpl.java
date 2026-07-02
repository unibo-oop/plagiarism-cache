package it.unibo.pacman.controller.mapeditor;

import java.util.List;

import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.model.mapeditor.FixedGrid;
import it.unibo.pacman.model.mapeditor.FixedGridImpl.GridEntity;
import it.unibo.pacman.model.mapeditor.MapBuilder;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.GUIFactory;
import it.unibo.pacman.view.ViewableUI;
import it.unibo.pacman.view.mapeditor.EditorView;
import it.unibo.pacman.view.mapeditor.EditorViewImpl;
import it.unibo.pacman.view.mapeditor.GridView;
import it.unibo.pacman.view.mapeditor.Tile;

/**
 * Model a controller of the map editor.
 */
public class MapEditorControllerImpl implements MapEditorController {

    private final int height;
    private final int width;

    private Tile selectedTile;

    private final EditorView view;
    private final GridView gridView;
    private final FixedGrid gridModel;
    /**
     * Create a map editor controller.
     * 
     * @param mainMenu of the game.
     * @param gFactory is the GUI factory
     * @param height is the number of rows
     * @param width is the number of columns
     */
    public MapEditorControllerImpl(final ViewableUI mainMenu, final GUIFactory gFactory, final int height, final int width) {
        this.height = height;
        this.width = width;
        this.gridModel = new MapBuilder(height, width).addFixedBordersWithPortals()
                                .addFixedCenter()
                                .getGrid();
        this.gridView = new GridView(this);
        this.view = new EditorViewImpl(mainMenu, gFactory, this.gridView, this);
        final GridListener listener = new GridListener(this);
        this.gridView.addMouseListener(listener);
        this.gridView.addMouseMotionListener(listener);
        this.setSelectedTile(new Tile(EntityType.EMPTY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelectedTile(final Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile getSelectedTile() {
        return this.selectedTile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePosition(final int x, final int y) {
        if (this.gridModel.isPositionFixed(x, y)) {
            this.gridModel.setEntity(x, y, this.selectedTile.getType());
            this.gridView.updateGrid(x, y, this.selectedTile);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType(final int x, final int y) {
        return this.gridModel.getEntity(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showEditor() {
        this.view.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveMap(final String mapName) {
        this.gridModel.fillWithPills();
        MapIO.writeMap(mapName, this.getStringMap());
    }

    private String getStringMap() {
        final List<List<GridEntity>> tmpMap = this.gridModel.getMap();
        final StringBuilder sb = new StringBuilder();
        sb.append(this.width).append('\n').append(this.height).append('\n');
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                sb.append(tmpMap.get(y).get(x).getType().getValue());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

}
