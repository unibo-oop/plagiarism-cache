package it.unibo.biscia.core;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * possible type of movement of a entity on the grid.
 *
 */
enum MovementType {

    /**
     * no movement.
     */
    NONE(false, false, true, MovementType.CrashPreventerMode.NONE),
    /**
     * all parts moved on direction.
     */
    TRANSLATE(true, false, false, MovementType.CrashPreventerMode.NONE),
    /**
     * all parts moved on direction if crash STOP.
     */
    TRANSLATE_STOP(true, false, false, MovementType.CrashPreventerMode.STOP),

    /**
     * all parts moved on direction.
     */
    TRANSLATE_BOUNCE(true, false, false, MovementType.CrashPreventerMode.BOUNCE),
    /**
     * all parts moved on direction if crash wait.
     */
    TRANSLATE_WAIT(true, false, false, MovementType.CrashPreventerMode.WAIT),

    /**
     * all parts moved on direction, if crash CASUAL.
     */
    TRANSLATE_CASUAL(true, false, false, MovementType.CrashPreventerMode.CASUAL),

    /**
     * the first part (cell) of entity move on direction and other substitute the
     * position of previous part.
     */
    SLITHER(true, true, false, MovementType.CrashPreventerMode.NONE),
    /**
     * the first part (cell) of entity move on direction and other substitute the
     * position of previous part.
     */
    SLITHER_ONE_STEP(true, true, true, MovementType.CrashPreventerMode.NONE);

    private enum CrashPreventerMode {
        NONE, STOP, BOUNCE, WAIT, CASUAL;
    }

    private final MovementStrategy strategy;
    private final boolean stopAfterMove;
    private final boolean move;
    private final boolean slither;
    private final MovementType.CrashPreventerMode crashPreventer;

    MovementType(final boolean move, final boolean slither, final boolean stopAfter,
            final MovementType.CrashPreventerMode crashPreventer) {
        this.move = move;
        this.crashPreventer = crashPreventer;
        this.slither = slither;
        this.stopAfterMove = stopAfter;
        if (this.move) {
            if (slither) {
                this.strategy = (Level level, List<Cell> cells, Direction direction) -> IntStream.range(0, cells.size())
                        .mapToObj(i -> (Cell) (i == 0 ? level.getCell(cells.get(0).getCol() + direction.getStepCol(),
                                cells.get(0).getRow() + direction.getStepRow()) : cells.get(i - 1)))
                        .collect(Collectors.toList());
            } else {
                this.strategy = (Level level, List<Cell> cells, Direction direction) -> cells.stream().map(cell -> level
                        .getCell(cell.getCol() + direction.getStepCol(), cell.getRow() + direction.getStepRow()))
                        .collect(Collectors.toList());
            }
        } else {
            this.strategy = (Level level, List<Cell> cells, Direction direction) -> cells;
        }
    }

    /**
     * apply the movement to list of cell.
     * 
     * @param level
     * @param cells     list of cells
     * @param direction
     * @return
     */
    MovementEffect move(final Entity.EntityManaged.Movable entity, final Direction direction) {
        final List<Cell> newCells = strategy.move(entity.getLevel(), entity.getCells(), direction);

        final boolean crash = entity.getLevel().getEntities().stream()
                // exclude itself
                .filter(e -> !entity.equals(e))
                // allow eater eat food
                .filter(e -> !(entity instanceof Entity.EntityManaged.Eater
                        && e instanceof Entity.EntityManaged.Eatable))
                // map to list o cells
                .flatMap(e -> e.getCells().stream()).distinct()
                // .filter(c -> !entity.getCells().contains(c))
                // any is present on new cells
                .filter(c -> newCells.contains(c)).findAny().isPresent();

        return new MovementEffect() {

            @Override
            public Optional<Direction> getDirection() {

                if (stopAfterMove) {
                    return Optional.empty();
                }
                Optional<Direction> directionAfter = Optional.of(direction);
                if (crash & !crashPreventer.equals(MovementType.CrashPreventerMode.NONE)) {
                    switch (crashPreventer) {
                    case STOP:
                        directionAfter = Optional.empty();
                        break;
                    case BOUNCE:
                        directionAfter = Optional.empty();
                        for (final Direction d : Direction.values()) {
                            if (d.getStepRow() == direction.getStepRow() * -1
                                    && d.getStepCol() == direction.getStepCol() * -1) {
                                directionAfter = Optional.of(d);
                                break;
                            }
                        }
                        break;
                    case WAIT:
                        break;
                    case CASUAL:
                        final Random random = new Random();
                        Direction dir;
                        do {
                            dir = Direction.values()[random.nextInt(Direction.values().length)];
                        } while (!MovementType.this.acceptDirection(entity, Optional.of(dir)));
                        directionAfter = Optional.of(dir);
                        break;
                    default:
                        directionAfter = Optional.empty();
                        break;
                    }
                }
                return directionAfter;

            }

            @Override
            public List<Cell> getCells() {
                if (crash & !crashPreventer.equals(MovementType.CrashPreventerMode.NONE)) {
                    return entity.getCells();
                }
                return newCells;
            }
        };
    }

    boolean acceptDirection(final Entity.EntityManaged.Movable entity, final Optional<Direction> direction) {
        if (!this.move) {
            return false;
        }
        if (direction.isEmpty()) {
            if (this.slither) {
                return false;
            }
        } else {
            if (this.slither && entity.getCells().size() > 1) {
                final Cell newCell = this.move(entity, direction.get()).getCells().get(0);
                if (newCell.equals(entity.getCells().get(1))) {
                    return false;
                }
            }
        }
        return true;
    }
}
