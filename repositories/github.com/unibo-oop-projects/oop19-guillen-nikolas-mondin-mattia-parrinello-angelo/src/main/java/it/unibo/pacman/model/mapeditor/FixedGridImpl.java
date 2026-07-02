package it.unibo.pacman.model.mapeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.pacman.model.utilities.EntityType;

/**
 * Implement a grid where some entities are fixed in the map and cannot be modified.
 */
public class FixedGridImpl implements FixedGrid {

    private final int rows;
    private final int columns;
    private final List<List<GridEntity>> map;

    /**
     * Create a fixed grid for the map.
     * 
     * @param rows of the map
     * @param columns of the map
     */
    public FixedGridImpl(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = this.createEmptyMap();
    }

    private List<List<GridEntity>> createEmptyMap() {
        final List<List<GridEntity>> tmpMap = new ArrayList<>();
        this.fillMap(tmpMap, EntityType.EMPTY);
        return tmpMap;
    }

    /**
     * Fill a map with the specified entity type.
     * 
     * @param mapToFill is the map to be filled
     * @param type is the entity that will fill the map
     */
    private void fillMap(final List<List<GridEntity>> mapToFill, final EntityType type) {
        for (int y = 0; y < this.rows; y++) {
            mapToFill.add(new ArrayList<GridEntity>());
            for (int x = 0; x < this.columns; x++) {
                mapToFill.get(y).add(new GridEntity(type, true));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillWithPills() {
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                if (this.getEntity(x, y).equals(EntityType.EMPTY)) {
                    this.setEntity(x, y, EntityType.PILL);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntity(final int x, final int y, final EntityType type) {
        if (this.isPositionFixed(x, y)) {
            // Always modifiable.
            this.map.get(y).set(x, new GridEntity(type, true));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFixedEntity(final int x, final int y, final EntityType type) {
        if (this.isPositionFixed(x, y)) {
            this.map.get(y).set(x, new GridEntity(type, false));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isPositionFixed(final int x, final int y) {
        return this.map.get(y).get(x).isFixed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntity(final int x, final int y) {
        return this.map.get(y).get(x).getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<List<GridEntity>> getMap() {
        return Collections.unmodifiableList(this.map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(30);
        sb.append("Columns= ")
          .append(this.columns)
          .append("\nRows= ")
          .append(this.rows)
          .append('\n');
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                sb.append('(')
                  .append(y)
                  .append(',')
                  .append(x)
                  .append(')')
                  .append(this.map.get(y).get(x).getType())
                  .append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Model an immutable grid entity, that can be fixed or not.
     */
    public static final class GridEntity {

        private final EntityType type;
        private final boolean fixed;

        /**
         * Create a grid entity.
         * 
         * @param type defines the entity type
         * @param fixed defines if it fixed or not
         */
        public GridEntity(final EntityType type, final boolean fixed) {
            this.type = type;
            this.fixed = fixed;
        }

        /**
         * 
         * @return the entity
         */
        public EntityType getType() {
            return type;
        }

        /**
         * 
         * @return true if it is fixed, false if it isn't
         */
        public boolean isFixed() {
            return fixed;
        }

    }

}
