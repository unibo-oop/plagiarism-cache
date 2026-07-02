package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Station;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * moving up to a {@link Box}. It uses the actual position of the {@link Player}
 * 's {@link Pawn} and the number of steps that it has to take, or the specific
 * {@link Box} to reach or the nearest {@link Station}.
 *
 */
public final class MoveUpTo implements Action {

    private static final int FIRST_STATION = 5;
    private static final int SECOND_STATION = 15;
    private static final int THIRD_STATION = 25;
    private static final int FOURTH_STATION = 35;
    private static final int LAST_BOX = 39;

    private static final int FIRST_USEFUL_POSITION = 28;
    private static final int LAST_USEFUL_POSITION = 11;

    private final int stepsToTake;
    private final Box box;

    private MoveUpTo() {
        this.stepsToTake = 0;
        this.box = null;
    }

    private MoveUpTo(final int stepsToTake) {
        this.stepsToTake = stepsToTake;
        this.box = null;
    }

    private MoveUpTo(final Box box) {
        this.stepsToTake = 0;
        this.box = box;
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * number of the steps that the {@link Player}'s {@link Pawn} has to take.
     * This number can be positive (if it has to go forward) or negative (if it
     * has to go back).
     * 
     * @param stepsToTake
     *            - the number of the steps to take
     * @throws IllegalArgumentException
     *             - if the number in input is zero
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo takeSteps(final int stepsToTake) {
        if (stepsToTake == 0) {
            throw new IllegalArgumentException("The steps to take can't be zero");
        }
        return new MoveUpTo(stepsToTake);
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * {@link Box} to reach by the {@link Player}'s {@link Pawn}.
     * 
     * @param box
     *            - the {@link Box} to reach
     * @throws NullPointerException
     *             - if the {@link Box} in input is null
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo moveUpToBox(final Box box) {
        return new MoveUpTo(Objects.requireNonNull(box));
    }

    /**
     * Constructs an instance of this specific {@link Action}. It send the
     * {@link Player}'s {@link Pawn} to the nearest {@link Station}.
     * 
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo theNearestStation() {
        return new MoveUpTo();
    }

    private boolean isPassedFromStartBox(final Player player) {
        return player.getPawn().getPreviousPos() >= FIRST_USEFUL_POSITION
                && player.getPawn().getActualPos() <= LAST_USEFUL_POSITION;
    }

    @Override
    public void play(final Player player) {
        if (this.box == null && this.stepsToTake == 0) {
            final int i = player.getPawn().getActualPos();
            if ((i >= 0 && i < FIRST_STATION) || (i >= FOURTH_STATION && i <= LAST_BOX)) {
                player.getPawn().setPos(FIRST_STATION);
            }
            if (i >= FIRST_STATION && i < SECOND_STATION) {
                player.getPawn().setPos(SECOND_STATION);
            }
            if (i >= SECOND_STATION && i < THIRD_STATION) {
                player.getPawn().setPos(THIRD_STATION);
            }
            if (i >= THIRD_STATION && i < FOURTH_STATION) {
                player.getPawn().setPos(FOURTH_STATION);
            }
        } else if (this.box == null) {
            player.getPawn().setPos(player.getPawn().getActualPos() + this.stepsToTake);
        } else {
            player.getPawn().setPos(this.box.getID());
        }
        if (this.isPassedFromStartBox(player)) {
            new PassFromStar().play(player);
        }
    }

}
