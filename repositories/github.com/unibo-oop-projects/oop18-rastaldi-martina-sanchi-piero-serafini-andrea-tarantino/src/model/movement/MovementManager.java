package model.movement;

import controller.Controller;
import controller.ControllerImpl;
import javafx.util.Pair;
import model.board.Board;
import model.elements.Element;
import model.elements.Hero;
import model.elements.Minotaurus;
import model.elements.Wall;
import utilities.Directions;
import utilities.Status;

/**
 *
 * Rastaldi Martina.
 *
 */
public final class MovementManager implements Movement {

    private final Board gameBoard;
    private Element selectedElement;
    private Pair<Integer, Integer> selected;
    private Pair<Integer, Integer> secondSelected;
    private final Controller controller = ControllerImpl.getLog();

    /**
     * Constructor.
     */
    public MovementManager() {
        super();
        this.gameBoard = Board.getLog();
    }

    @Override
    public boolean move(final Pair<Integer, Integer> selected, final Directions direction) {
        this.selected = selected;
        this.selectedElement = this.gameBoard.getSelected(selected);
        switch (this.selectedElement.getType()) {
        case EROE:
            return this.moveHero(direction);
        case MURO:
            this.selected = ((Wall) this.selectedElement).getActualPosition();
            this.secondSelected = ((Wall) this.selectedElement).getSecondPosition();
            this.controller.select(this.selected);
            return this.moveWall(direction);
        case MINOTAURO:
            return this.moveMinotaurus(direction);
        default:
            return false;
        }
    }

    @Override
    public void moveTo(final Pair<Integer, Integer> selected, final Pair<Integer, Integer> newPosition) {
        this.selected = selected;
        this.selectedElement = this.gameBoard.getSelected(selected);
        switch (this.selectedElement.getType()) {
        case EROE:
            this.selectedElement.setNewPosition(newPosition);
            final Hero hero = this.gameBoard.getHeroMap().remove(selected);
            this.gameBoard.getHeroMap().put(hero.getActualPosition(), hero);
            this.controller.select(hero.getActualPosition());
            break;
        case MINOTAURO:
            this.selectedElement.setNewPosition(newPosition);
            this.controller.select(this.gameBoard.getMinotaurus().getActualPosition());
            break;
        default:
            break;
        }
    }

    @Override
    public void resetHero(final Pair<Integer, Integer> coord) {
        final Hero hero = this.gameBoard.getHeroMap().remove(coord);
        hero.resetPosition();
        this.gameBoard.getHeroMap().put(hero.getActualPosition(), hero);
    }

    @Override
    public boolean rotate(final Pair<Integer, Integer> selected) {
        if (this.gameBoard.getWallMap().containsKey(selected) && this.gameBoard.getWallMap().get(selected).rotate()) {
            final Wall muro = this.gameBoard.getWallMap()
                    .remove(this.gameBoard.getWallMap().get(selected).getOldActualPosition());
            this.gameBoard.getWallMap().put(muro.getActualPosition(), muro);
            this.controller.select(muro.getActualPosition());
            return true;
        }
        return false;
    }

    private boolean moveHero(final Directions direction) {
        if (this.selectedElement.move(direction)) {
            final Hero hero = this.gameBoard.getHeroMap().remove(this.selected);
            if ((this.gameBoard.cellStatus(hero.getActualPosition()).isPresent()) && (this.gameBoard
                    .cellStatus(hero.getActualPosition()).get().getKey().equals(Status.ARRIVO)
                    && this.gameBoard.cellStatus(hero.getActualPosition()).get().getValue().equals(hero.getColor()))) {
                hero.setArrived(true);
            }
            this.gameBoard.getHeroMap().put(hero.getActualPosition(), hero);
            this.controller.select(hero.getActualPosition());
            return true;
        }
        return false;
    }

    private boolean moveMinotaurus(final Directions direction) {
        if (this.selectedElement.move(direction)) {
            Board.getLog().getHeroMap().keySet().stream().forEach(e -> {
                if (e.equals(this.selectedElement.getActualPosition())
                        && !Board.getLog().getHeroMap().get(e).isArrived()) {
                    ((Minotaurus) this.selectedElement).setIsEating();
                }
            });
            this.controller.select(this.gameBoard.getMinotaurus().getActualPosition());
            return true;
        }
        return false;
    }

    private boolean moveWall(final Directions direction) {
        if (this.selectedElement.move(direction)) {
            final Wall muro = this.gameBoard.getWallMap().remove(this.selected);
            this.gameBoard.getWallMap().remove(this.secondSelected);
            this.gameBoard.getWallMap().put(muro.getActualPosition(), muro);
            this.gameBoard.getWallMap().put(muro.getSecondPosition(), muro);
            this.controller.select(muro.getActualPosition());
            return true;
        }
        return false;
    }

}
