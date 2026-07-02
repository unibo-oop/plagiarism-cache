package todo.view.entities.level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Vector2;

import todo.utils.Checks;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.ResolutionManifest;
import todo.view.entities.BaseEntity;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.MemoryAreaBuilder;

/**
 * This class represents an implementation of the {@link MemoryArea} interface.
 */
public final class MemoryAreaImpl extends BaseEntity implements MemoryArea {
    public static final float TILE_BOX_DISTANCE_RATIO = 0.25f;
    private final List<List<Optional<ValueBox>>> matrix;
    private final int rowsCount;
    private final int colsCount;
    private final ResolutionManifest resolutionManifest;

    private MemoryAreaImpl(final int rows, final int cols) {
        super();
        this.rowsCount = rows;
        this.colsCount = cols;
        this.resolutionManifest = ResolutionManagerImpl.getInstance().getCurrentAspectRatio().getManifest();
        this.matrix = new ArrayList<>();
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int getHorizontalSlotsCount() {
        return this.colsCount;
    }

    @Override
    public int getVerticalSlotsCount() {
        return this.rowsCount;
    }

    @Override
    public int getSlotsCount() {
        return this.rowsCount * this.colsCount;
    }

    @Override
    public Optional<ValueBox> getValueBox(final int x, final int y) {
        return this.matrix.get(y).get(x);
    }

    @Override
    public Optional<ValueBox> getValueBox(final int index) {
        final int y = index / this.colsCount;
        final int x = index % this.colsCount;
        return getValueBox(x, y);
    }

    @Override
    public List<ValueBox> getValueBoxes() {
        return this.matrix.stream()
                          .flatMap(m -> m.stream())
                          .filter(o -> o.isPresent())
                          .map(o -> o.get())
                          .collect(Collectors.toList());
    }

    @Override
    public void setValueBox(final int x, final int y, final ValueBox valueBox) {
        this.matrix.get(y).set(x, Optional.of(valueBox));
        valueBox.setParent(this);
        valueBox.setRelativePosition(getCellRelativePosition(x, y));
    }

    @Override
    public void setValueBox(final int index, final ValueBox valueBox) {
        final int y = index / this.colsCount;
        final int x = index % this.colsCount;
        setValueBox(x, y, valueBox);
    }

    @Override
    public void removeValueBox(final int x, final int y) {
        this.matrix.get(y).set(x, Optional.empty());
    }

    @Override
    public void removeValueBox(final int index) {
        final int y = index / this.colsCount;
        final int x = index % this.colsCount;
        removeValueBox(x, y);
    }

    @Override
    public Vector2 getCellPosition(final int x, final int y) {
        final float tileSize = this.resolutionManifest.getScaledFloorTileSize();
        return getPosition().cpy()
                            .add(0f, tileSize * this.rowsCount)
                            .add(tileSize * x + TILE_BOX_DISTANCE_RATIO * tileSize,
                                    -tileSize * (y + 1) + TILE_BOX_DISTANCE_RATIO * tileSize);
    }

    @Override
    public Vector2 getCellPosition(final int index) {
        final int y = index / this.colsCount;
        final int x = index % this.colsCount;
        return getCellPosition(x, y);
    }

    private Vector2 getCellRelativePosition(final int x, final int y) {
        final float tileSize = this.resolutionManifest.getScaledFloorTileSize();
        final Vector2 topLeftCorner = new Vector2(0f, tileSize * this.rowsCount);
        return topLeftCorner.add(tileSize * x + TILE_BOX_DISTANCE_RATIO * tileSize,
                -tileSize * (y + 1) + TILE_BOX_DISTANCE_RATIO * tileSize);
    }

    /**
     * This class is responsible for building the memory area.
     */
    public static final class Builder extends BaseEntity.Builder<Builder, MemoryArea>
            implements MemoryAreaBuilder<Builder> {

        private static final int DEFAULT_ROWS = 3;
        private static final int DEFAULT_COLS = 3;

        private int rows = DEFAULT_ROWS;
        private int cols = DEFAULT_COLS;
        private Optional<List<Optional<ValueBox>>> currentInitialValues = Optional.empty();

        @Override
        public Builder size(final int rows, final int columns) {
            this.rows = rows;
            this.cols = columns;
            return this;
        }

        @Override
        public Builder initialValues(final List<Optional<ValueBox>> list) {
            this.currentInitialValues = Optional.of(list);
            return this;
        }

        @Override
        protected MemoryArea callConstructor() {
            Checks.require(this.rows > 0, IllegalArgumentException.class, "Rows can't be zero or negative");
            Checks.require(this.cols > 0, IllegalArgumentException.class, "Columns can't be zero or negative");
            return new MemoryAreaImpl(this.rows, this.cols);
        }

        @Override
        protected MemoryArea additionalBuild(final MemoryArea area) {
            // Cast to the implementation being built
            final MemoryAreaImpl areaImpl = (MemoryAreaImpl) area;
            if (this.currentInitialValues.isPresent()) {
                // Fill in the list of lists with all empty Optionals or with the specified
                // initial values
                final Optional<Iterator<Optional<ValueBox>>> initialValuesIterator = this.currentInitialValues.map(
                        i -> i.iterator());
                for (int y = 0; y < this.rows; y++) {
                    areaImpl.matrix.add(new ArrayList<>());
                    for (int x = 0; x < this.cols; x++) {
                        if (initialValuesIterator.isPresent() && initialValuesIterator.get().hasNext()) {
                            final Optional<ValueBox> box = initialValuesIterator.get().next();
                            areaImpl.matrix.get(y).add(box);
                            if (box.isPresent()) {
                                box.get().setParent(areaImpl);
                                box.get().setRelativePosition(areaImpl.getCellRelativePosition(x, y));
                            }
                        } else {
                            areaImpl.matrix.get(y).add(Optional.empty());
                        }
                    }
                }
            }
            return area;
        }
    }
}
