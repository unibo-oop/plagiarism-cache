package model.elements;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerImpl;
import javafx.util.Pair;
import model.board.Board;
import model.players.User;
import model.settings.SettingsManager;
import utilities.Colors;
import utilities.Directions;
import utilities.Elements;
import utilities.Status;

/**
 * Class implementing an element of hero type.
 * Andrea Serafini.
 *
 */
public class Hero implements Element {

    /**
     *
     */
    private static final long serialVersionUID = -4890606217330667315L;
    private final Elements type = Elements.EROE;
    private Pair<Integer, Integer> actualPosition;
    private final Colors color;
    private final User player;
    private final Pair<Integer, Integer> initialPosition;
    private final Integer id;
    private boolean arrived;
    private final int limit;

    /**
     * Constructor.
     *
     * @param position
     *            where the hero starts
     * @param player
     *            controlling the hero
     * @param id
     *            of the hero
     */
    public Hero(final Pair<Integer, Integer> position, final User player, final Integer id) {
        this.actualPosition = position;
        this.color = player.getColor();
        this.player = player;
        this.initialPosition = position;
        this.id = id;
        this.arrived = false;
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
     * @return the id number of the hero
     */
    public String getId() {
        return this.id.toString();
    }

    /**
     *
     * @return the player owning this hero
     */
    public User getPlayer() {
        return this.player;
    }

    /**
     * Return the list of possible moves for the hero.
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

        if (SettingsManager.getLog().getSettings().isJumpEnabled() && ControllerImpl.getLog().isNotLastStep()) {
            for (final Pair<Integer, Integer> n : provMoves) {
                if (!this.getActualPosition().equals(n) && !moves.contains(n)
                        && !Board.getLog().getWallMap().containsKey(n) && !Board.getLog().getHeroMap().containsKey(n)
                        && !Board.getLog().getMinotaurus().getActualPosition().equals(n)
                        && (!Board.getLog().cellStatus(n).isPresent()
                                || (Board.getLog().getOccupationList().get(n).getValue() == this.getColor())
                                || (Board.getLog().getOccupationList().get(n).getKey() == Status.SIEPE))) {
                    moves.add(n);
                }
            }
        } else {
            for (final Pair<Integer, Integer> n : provMoves) {
                if (!this.getActualPosition().equals(n) && !moves.contains(n)
                        && !Board.getLog().getWallMap().containsKey(n) && !Board.getLog().getHeroMap().containsKey(n)
                        && !Board.getLog().getMinotaurus().getActualPosition().equals(n)
                        && (!Board.getLog().cellStatus(n).isPresent()
                                || (Board.getLog().getOccupationList().get(n).getValue() == this.getColor()))) {
                    moves.add(n);
                }
            }
        }
        return moves;
    }

    @Override
    public final Elements getType() {
        return this.type;
    }

    /**
     *
     * @return true if the hero is arrived
     */
    public boolean isArrived() {
        return this.arrived;
    }

    /**
     *
     * @return false if there are no possible moves the hero can makes
     */
    public boolean isMovable() {
        return !this.getPossibleMoves().isEmpty();
    }

    /**
     * Move the hero of one step in the selected direction.
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
     * Reset the hero position to his starting position.
     */
    public final void resetPosition() {
        this.setNewPosition(this.initialPosition);
    }

    /**
     * Set true if the hero is arrived at destination.
     *
     * @param arrived
     *            at destination
     */
    public void setArrived(final boolean arrived) {
        this.arrived = arrived;
    }

    @Override
    public final void setNewPosition(final Pair<Integer, Integer> next) {
        this.actualPosition = next;
    }
}
