package model.elements;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerImpl;
import javafx.util.Pair;
import model.board.Board;
import model.players.Player;
import model.settings.SettingsManager;
import utilities.Directions;
import utilities.Status;

/**
 * This class extends the hero element implementing an almost random way of moving.
 * Andrea Serafini.
 *
 */
public class DrunkHero extends Hero {

    /**
     *
     */
    private static final long serialVersionUID = -4236321278425832332L;
    private final SecureRandom random;
    private final int limit;

    /**
     * Constructor.
     *
     * @param position
     *            where the hero starts
     * @param palyer
     *            controlling the hero
     * @param id
     *            of the hero
     */
    public DrunkHero(final Pair<Integer, Integer> position, final Player palyer, final Integer id) {
        super(position, palyer, id);
        this.random = new SecureRandom();
        this.limit = SettingsManager.getLog().getSettings().getBoardLimit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean move(final Directions direction) {
        Pair<Integer, Integer> newPosition = null;
        if (this.random.nextBoolean()) {
            switch (Directions.values()[this.random.nextInt(Directions.values().length)]) {
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
        } else {
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
        }

        if (this.getPossibleMoves().contains(newPosition)) {
            this.setNewPosition(newPosition);
            return true;
        } else {
            return false;
        }

    }

}
