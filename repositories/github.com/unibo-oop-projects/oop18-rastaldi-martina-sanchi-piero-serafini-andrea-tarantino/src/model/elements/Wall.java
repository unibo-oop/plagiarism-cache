package model.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import model.board.Board;
import model.settings.SettingsManager;
import utilities.Colors;
import utilities.Directions;
import utilities.Elements;

/**
 * Class implementing an element of wall type.
 * Andrea Serafini.
 *
 */
public class Wall implements Element {

    /**
     *
     */
    private static final long serialVersionUID = 5235127222171262534L;
    private Pair<Integer, Integer> actualPosition;
    private final Colors color;
    private final Elements type = Elements.MURO;
    private Pair<Integer, Integer> secondPosition;
    private Pair<Integer, Integer> oldActualPosition;
    private Pair<Integer, Integer> oldSecondPosition;
    private final int limit;

    /**
     * Constructor.
     *
     * @param actualPosition
     *            of the wall
     * @param secondPosition
     *            of the wall
     */
    public Wall(final Pair<Integer, Integer> actualPosition, final Pair<Integer, Integer> secondPosition) {
        this.actualPosition = actualPosition;
        this.secondPosition = secondPosition;
        this.color = Colors.Gray;
        this.limit = SettingsManager.getLog().getSettings().getBoardLimit();

    }

    @Override
    public final Pair<Integer, Integer> getActualPosition() {
        return this.actualPosition;
    }

    @Override
    public final Colors getColor() {
        return this.color;
    }

    /**
     *
     * @return the old actual position of the wall
     */
    public Pair<Integer, Integer> getOldActualPosition() {
        return this.oldActualPosition;
    }

    /**
     *
     * @return the old second position of the wall
     */
    public Pair<Integer, Integer> getOldSecondPosition() {
        return this.oldSecondPosition;
    }

    /**
     * Return the list of possible moves for the hero.
     */
    private List<Pair<Integer, Integer>> getPossibleMoves() {
        final List<Pair<Integer, Integer>> moves = new ArrayList<>();
        final List<Pair<Integer, Integer>> provMoves = new ArrayList<>();
        Integer startx = this.getActualPosition().getKey();
        Integer starty = this.getActualPosition().getValue();
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, starty));
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(
                new Pair<>((startx - 1) < 0 ? 0 : startx - 1, (starty + 1) > this.limit ? this.limit : starty + 1));
        provMoves.add(new Pair<>(startx, (starty + 1) > this.limit ? this.limit : starty + 1));
        provMoves.add(new Pair<>(startx, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1, starty));
        provMoves.add(
                new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1,
                (starty + 1) > this.limit ? this.limit : starty + 1));

        startx = this.getSecondPosition().getKey();
        starty = this.getSecondPosition().getValue();
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, starty));
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(
                new Pair<>((startx - 1) < 0 ? 0 : startx - 1, (starty + 1) > this.limit ? this.limit : starty + 1));
        provMoves.add(new Pair<>(startx, (starty + 1) > this.limit ? this.limit : starty + 1));
        provMoves.add(new Pair<>(startx, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1, starty));
        provMoves.add(
                new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves.add(new Pair<>((startx + 1) > this.limit ? this.limit : startx + 1,
                (starty + 1) > this.limit ? this.limit : starty + 1));

        for (final Pair<Integer, Integer> n : provMoves) {
            // System.out.println(cellStatus(n));
            if (!moves.contains(n)
                    && (!Board.getLog().getWallMap().containsKey(n) || Board.getLog().getWallMap().get(n).equals(this))
                    && !Board.getLog().getHeroMap().containsKey(n)
                    && !Board.getLog().getMinotaurus().getActualPosition().equals(n)
                    && !Board.getLog().cellStatus(n).isPresent()) {

                moves.add(n);
            }

        }

        return moves;
    }

    /**
     *
     * @return the position of the second part of the wall
     */
    public Pair<Integer, Integer> getSecondPosition() {
        return this.secondPosition;
    }

    @Override
    public final Elements getType() {
        return this.type;
    }

    /**
     * Move the wall of one step in the selected direction.
     */
    @Override
    public boolean move(final Directions direction) {
        Pair<Integer, Integer> newPosition = null;
        Pair<Integer, Integer> newSecondPosition = null;

        switch (direction) {
        case UP:
            newPosition = new Pair<>(this.getActualPosition().getKey(), this.getActualPosition().getValue() - 1);
            newSecondPosition = new Pair<>(this.secondPosition.getKey(), this.secondPosition.getValue() - 1);
            break;
        case DOWN:
            newPosition = new Pair<>(this.getActualPosition().getKey(), this.getActualPosition().getValue() + 1);
            newSecondPosition = new Pair<>(this.secondPosition.getKey(), this.secondPosition.getValue() + 1);
            break;
        case LEFT:
            newPosition = new Pair<>(this.getActualPosition().getKey() - 1, this.getActualPosition().getValue());
            newSecondPosition = new Pair<>(this.secondPosition.getKey() - 1, this.secondPosition.getValue());
            break;
        case RIGHT:
            newPosition = new Pair<>(this.getActualPosition().getKey() + 1, this.getActualPosition().getValue());
            newSecondPosition = new Pair<>(this.secondPosition.getKey() + 1, this.secondPosition.getValue());
            break;
        default:
            break;
        }

        final List<Pair<Integer, Integer>> moves = this.getPossibleMoves();
        if (moves.contains(newPosition) && moves.contains(newSecondPosition)) {
            this.oldActualPosition = this.getActualPosition();
            this.oldSecondPosition = this.getSecondPosition();
            this.setNewPosition(newPosition);
            this.setSecondPosition(newSecondPosition);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return true if the wall has rotated
     */
    public boolean rotate() {
        Pair<Integer, Integer> newPosition = null;

        if (this.getActualPosition().getKey() == this.getSecondPosition().getKey()) {
            newPosition = new Pair<>(this.getSecondPosition().getKey() - 1, this.getSecondPosition().getValue());
        } else {
            newPosition = new Pair<>(this.getSecondPosition().getKey(), this.getSecondPosition().getValue() - 1);
        }

        if (this.getPossibleMoves().contains(newPosition)) {
            this.oldActualPosition = this.getActualPosition();
            this.setNewPosition(newPosition);
            return true;
        }
        return false;

    }

    @Override
    public final void setNewPosition(final Pair<Integer, Integer> next) {
        this.actualPosition = next;
    }

    /**
     * @param next
     *            the new second position of the wall
     */
    public void setSecondPosition(final Pair<Integer, Integer> next) {
        this.secondPosition = next;
    }

}
