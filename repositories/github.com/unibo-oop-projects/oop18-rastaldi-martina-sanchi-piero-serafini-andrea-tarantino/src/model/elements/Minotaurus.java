package model.elements;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerImpl;
import javafx.util.Pair;
import model.board.Board;
import model.settings.SettingsManager;
import utilities.Colors;
import utilities.Directions;
import utilities.Elements;
import utilities.Status;

/**
 * Class implementing an element of minotaurus type.
 * Andrea Serafini.
 *
 */
public class Minotaurus implements Element {

    /**
     *
     */
    private static final long serialVersionUID = 3121605127066265449L;
    private final Elements type = Elements.MINOTAURO;
    private Pair<Integer, Integer> actualPosition;
    private final Colors color;
    private final Integer steps;
    private boolean eating;
    private final int limit;

    /**
     * Constructor.
     *
     * @param steps
     *            number of possible steps
     */
    public Minotaurus(final Integer steps) {
        this.color = Colors.Black;
        this.steps = steps;
        this.eating = false;
        this.limit = SettingsManager.getLog().getSettings().getBoardLimit();
        this.actualPosition = this.getMiddleRandom();
    }

    @Override
    public final Pair<Integer, Integer> getActualPosition() {
        return this.actualPosition;
    }

    @Override
    public final Colors getColor() {
        return this.color;
    }

    private Pair<Integer, Integer> getMiddleRandom() {
        Integer x = this.limit / 2;
        Integer y = this.limit / 2;

        if (new SecureRandom().nextBoolean()) {
            x += 1;
        }
        if (new SecureRandom().nextBoolean()) {
            y += 1;
        }

        return new Pair<>(x, y);
    }

    /**
     * Return the list of possible moves for the minotaurus.
     */
    private List<Pair<Integer, Integer>> getPossibleMoves() {
        final List<Pair<Integer, Integer>> moves = new ArrayList<>();
        final List<Pair<Integer, Integer>> provMoves = new ArrayList<>();
        final Integer startx = this.getActualPosition().getKey();
        final Integer starty = this.getActualPosition().getValue();
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, starty));
        provMoves.add(new Pair<>(startx, (starty + 1) > this.limit ? this.limit : starty + 1));
        provMoves.add(new Pair<>(startx, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1, starty));

        if (SettingsManager.getLog().getSettings().isMinotaurusHedgeJumping()
                && ControllerImpl.getLog().isNotLastStep()) {
            for (final Pair<Integer, Integer> n : provMoves) {

                if (!this.getActualPosition().equals(n) && !moves.contains(n)
                        && !Board.getLog().getWallMap().containsKey(n)
                        && (!Board.getLog().getOccupationList().containsKey(n)
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.ARRIVO)
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.SIEPE)
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.TEMPIO))
                        && (!Board.getLog().getHeroMap().containsKey(n)
                                || !Board.getLog().getHeroMap().get(n).isArrived())) {
                    moves.add(n);
                }
            }
        } else {
            for (final Pair<Integer, Integer> n : provMoves) {

                if (!this.getActualPosition().equals(n) && !moves.contains(n)
                        && !Board.getLog().getWallMap().containsKey(n)
                        && (!Board.getLog().getOccupationList().containsKey(n)
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.ARRIVO)
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.TEMPIO))
                        && (!Board.getLog().getHeroMap().containsKey(n)
                                || !Board.getLog().getHeroMap().get(n).isArrived())) {
                    moves.add(n);
                }
            }
        }
        return moves;
    }

    /**
     *
     * @return the number of possible steps
     */
    public final Integer getSteps() {
        return this.steps;
    }

    @Override
    public final Elements getType() {
        return this.type;
    }

    /**
     *
     * @return true if the minotaurus is eating
     */
    public final boolean isEating() {
        return this.eating;
    }

    /**
     * Move the minotaurus of one step in the selected direction.
     */
    @Override
    public boolean move(final Directions direction) {
        Pair<Integer, Integer> newPosition = null;
        switch (direction) {
        case UP:
            newPosition = new Pair<>(this.getActualPosition().getKey(), this.getActualPosition().getValue() - 1);
            break;
        case DOWN:
            newPosition = new Pair<>(this.getActualPosition().getKey(), this.getActualPosition().getValue() + 1);
            break;
        case LEFT:
            newPosition = new Pair<>(this.getActualPosition().getKey() - 1, this.getActualPosition().getValue());
            break;
        case RIGHT:
            newPosition = new Pair<>(this.getActualPosition().getKey() + 1, this.getActualPosition().getValue());
            break;
        default:
            break;
        }

        if (this.getPossibleMoves().contains(newPosition)) {
            this.setNewPosition(newPosition);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset minotaurus to its initial position.
     */
    public void restartMinotaurus() {
        this.setNewPosition(new Pair<>(this.limit / 2, this.limit / 2));
    }

    /**
     * Set if the minotaurus is eating or not.
     */
    public void setIsEating() {
        this.eating = !this.eating;
    }

    @Override
    public final void setNewPosition(final Pair<Integer, Integer> next) {
        this.actualPosition = next;
    }

}
