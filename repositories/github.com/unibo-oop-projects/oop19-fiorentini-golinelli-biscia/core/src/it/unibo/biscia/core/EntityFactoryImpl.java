package it.unibo.biscia.core;

import it.unibo.biscia.core.Level.LevelManaged;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class EntityFactoryImpl implements EntityFactory {

    private final Level.LevelManaged level;
    private final LevelAnalyzer analyzer;

    private abstract class Entity implements it.unibo.biscia.core.Entity.EntityManaged {
        private final List<Cell> cells;
        private final EntityType type;

        /**
         * the list of cells (required not empty) do not contains duplicate, else throw
         * IllegalArgumentException.
         * 
         * @param cells list of cell that compose entity
         */
        private Entity(final List<Cell> cells, final EntityType type) {
            this.cells = validateCells(cells);
            this.type = type;
        }

        private List<Cell> validateCells(final List<Cell> cells) {
            Objects.requireNonNull(cells);
            if (cells.isEmpty()) {
                throw new IllegalArgumentException("cells needed");
            }
            if (cells.size() > 1 && cells.size() > cells.stream().distinct().count()) {
                throw new IllegalArgumentException("duplicate cells not allowed");
            }
            return cells.stream().collect(Collectors.toList());

        }

        @Override
        public List<Cell> getCells() {
            return Collections.unmodifiableList(this.cells);
        }

        @Override
        public EntityType getType() {
            return this.type;
        }

        @Override
        public LevelManaged getLevel() {
            return EntityFactoryImpl.this.level;
        }

        protected boolean addCell(final Cell cell) {
            if (!this.cells.contains(cell)) {
                this.cells.add(cell);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeCell(final int index) {
            if (index >= 0 && index < this.getCells().size()) {
                this.cells.remove(index);
                return true;
            }
            return false;
        }

        @Override
        public int removeFromCell(final int index) {
            int ret = 0;
            while (this.getCells().size() > index) {
                this.removeCell(this.getCells().size() - 1);
                ret++;
            }
            return ret;
        }

    }

    /**
     * extends entity for add movements.
     *
     */
    private abstract class Movable extends EntityFactoryImpl.Entity
            implements it.unibo.biscia.core.Entity.EntityManaged.Movable {
        private Optional<Direction> direction;
        private final int interval;
        private final MovementType movement;
        private int skipped;

        /**
         * 
         * @param entity
         * @param direction
         * @param movementInterval
         */
        private Movable(final List<Cell> cells, final EntityType type, final Optional<Direction> direction,
                final int movementInterval, final MovementType movement) {
            super(cells, type);
            this.direction = direction;
            this.interval = movementInterval;
            this.movement = movement;
            this.skipped = 0;
        }

        @Override
        public boolean move() {
            if (!this.direction.isPresent()) {
                return false;
            }
            skipped++;
            if (skipped < this.interval) {
                return false;
            }
            skipped = 0;
            final var movRes = this.movement.move(this, this.direction.get());
            final List<Cell> newCells = movRes.getCells();
            this.direction = movRes.getDirection();
            if (newCells.size() == this.getCells().size()) {
                boolean changed = false;
                for (int i = 0; i < newCells.size() && !changed; i++) {
                    if (!this.getCells().get(i).equals(newCells.get(i))) {
                        changed = true;
                    }
                }
                if (!changed) {
                    return false;
                }
            }
            super.cells.clear();
            newCells.forEach(c -> super.cells.add(c));
            return true;
        }

        @Override
        public final Optional<Direction> getDirection() {
            return this.direction;
        }

    }

    private abstract class Directable extends EntityFactoryImpl.Movable
            implements it.unibo.biscia.core.Entity.EntityManaged.Movable.Directable {
        private Directable(final List<Cell> cells, final EntityType type, final Optional<Direction> direction,
                final int movementInterval, final MovementType movementType) {
            super(cells, type, direction, movementInterval, movementType);
        }

        @Override
        public void setDirection(final Optional<Direction> direction) {
            // allow direction only if the movement in this direction not crush on second
            // cell of entity.
            if (!super.movement.acceptDirection(this, direction)) {
                return;
            }
            super.direction = direction;
        }
    }

    private abstract class Eatable extends Entity implements it.unibo.biscia.core.Entity.EntityManaged.Eatable {
        private final int energy;

        private Eatable(final List<Cell> cells, final EntityType type, final int energy) {
            super(cells, type);
            this.energy = energy;
        }

        @Override
        public int getEnergy() {
            return this.energy;
        }
    }

    private final class Snake extends EntityFactoryImpl.Directable
            implements it.unibo.biscia.core.Entity.EntityManaged.Eater,
            it.unibo.biscia.core.Entity.EntityManaged.Movable.Directable {
        private static final int INITIAL_SIZE = 2;
        private int increaseLength;
        private Cell lastCellBeforeMovement;

        private Snake(final List<Cell> cells, final Optional<Direction> direction, final MovementType movementType,
                final int startEnergy) {
            super(cells, EntityType.SNAKE, direction, 1, movementType);
            this.increaseLength = startEnergy * INCREMENT_FOR_ENERGY;
            if (this.getCells().size() + this.increaseLength < INITIAL_SIZE) {
                this.increaseLength = INITIAL_SIZE - this.getCells().size();
            }
            lastCellBeforeMovement = this.lastCell();
        }

        private Cell lastCell() {
            return this.getCells().get(this.getCells().size() - 1);
        }

        @Override
        public EntityType getType() {
            return EntityType.SNAKE;
        }

        @Override
        public boolean move() {
            lastCellBeforeMovement = this.lastCell();
            return super.move();
        }

        @Override
        public void eat(final int energy) {
            this.increaseLength = this.increaseLength + energy * INCREMENT_FOR_ENERGY;
        }

        @Override
        public boolean grow() {
            if (this.increaseLength > 0 && !this.getCells().contains(this.lastCellBeforeMovement)
                    && super.addCell(this.lastCellBeforeMovement)) {
                this.increaseLength--;
                this.lastCellBeforeMovement = this.lastCell();
                return true;
            }
            return false;
        }

    }

    private final class Food extends Eatable {
        private Food(final List<Cell> cells, final int energy) {
            super(cells, EntityType.FOOD, energy);
        }
    }

    private final class Wall extends Entity {
        Wall(final List<Cell> cells) {
            super(cells, EntityType.WALL);
        }
    }

    private final class WallMobile extends Movable {
        WallMobile(final List<Cell> cells, final Optional<Direction> direction, final int movementInterval,
                final MovementType movementType) {
            super(cells, EntityType.WALL, direction, movementInterval, movementType);
        }
    }

    private Cell getCasualFreeCell() {
        final List<Cell> occ = level.getAnalyzer().getOccupiedCells();
        if (occ.size() == this.level.getCols() * this.level.getRows()) {
            throw new IllegalStateException();
        }
        final Random random = new Random();
        Cell cell = this.level.getCell(0, 0);
        do {
            cell = this.level.getCell(random.nextInt(level.getCols()), random.nextInt(level.getRows()));
        } while (occ.contains(cell));
        return cell;
    }

    EntityFactoryImpl(final Level.LevelManaged level) {
        this.level = level;
        this.analyzer = level.getAnalyzer();

    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged.Movable.Directable makeBabySnake(final boolean runEver) {
        Direction direction;
        switch (this.level.getAnalyzer().getDirectables().size()) {
        case 0:
            direction = Direction.RIGHT;
            break;
        case 1:
            direction = Direction.LEFT;
            break;
        case 2:
            direction = Direction.UP;
            break;
        case 3:
            direction = Direction.DOWN;
            break;
        default:
            direction = Direction.values()[new Random().nextInt(Direction.values().length)];
        }
        return makeAdultSnake(Arrays.asList(this.analyzer.getNewSnakePosition()), 0,
                runEver ? Optional.of(direction) : Optional.empty(), runEver);
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged.Movable.Directable makeAdultSnake(final List<Cell> cells,
            final int energy, final Optional<Direction> direction, final boolean runEver) {
        return new Snake(cells, direction, runEver ? MovementType.SLITHER : MovementType.SLITHER_ONE_STEP, 0);
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged.Eatable makeCasualFood(final int energy) {

        final List<Cell> cells = Arrays.asList(this.getCasualFreeCell());
        return new Food(cells, energy);
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged makeEdge() {
        return makeWall(
                this.level
                        .getCells().stream().filter(c -> c.getCol() == 0 || c.getCol() == this.level.getCols() - 1
                                || c.getRow() == 0 || c.getRow() == this.level.getRows() - 1)
                        .collect(Collectors.toList()));
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged makeLinearWall(final Cell start, final int length,
            final Direction direction) {
        return makeWall(Stream.iterate(start, cell -> this.level.getSideCell(cell, direction)).limit(length)
                .collect(Collectors.toList()));
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged makeWall(final List<Cell> cells) {
        return new Wall(cells);
    }

    @Override
    public it.unibo.biscia.core.Entity.EntityManaged.Movable makeMovableWall(final List<Cell> cells,
            final Direction direction, final MovementType movementType, final int movementInterval) {
        return new WallMobile(cells, Optional.of(direction), movementInterval, movementType);
    }

}
