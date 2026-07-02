package controller.gameplay;

import java.util.List;
import java.util.stream.Collectors;

import controller.AbstractController;
import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import model.navy.Navy;
import model.ship.Ship;

/**
 * Implementation of {@link GameplayControllerImpl}.
 * This class is declared as final because is not designed for extension
 */
public final class GameplayControllerImpl extends AbstractController implements GameplayController {

    @Override
    public String getPlayerName() {
       return getModel().getMatch().getPlayer().getPlayer().get().getName();
    }

    @Override
    public String getEnemyName() {
        return getModel().getMatch().getEnemy().getPlayer().get().getName();
    }

    @Override
    public int getGridSize() {
        return getModel().getMatch().getGridSize();
    }

    @Override
    public Navy getPlayerNavy() {
        return getModel().getMatch().getPlayer().getBattleGround().get().getNavy();
    }

    @Override
    public List<Cell> getPlayerHittedSpotSet() {
        return getModel().getMatch().getPlayer().getBattleGround().get().getShootsHistory();
    }

    @Override
    public List<Cell> getEnemyHittedSpotSet() {
        return getModel().getMatch().getEnemy().getBattleGround().get().getShootsHistory();
    }

    @Override
    public void shootEnemy(final StaticPoint2D coordinate) throws IllegalArgumentException, IllegalStateException {
        getModel().getMatch().getEnemy().getBattleGround().get().shoot(coordinate);
        getMasterController().shootUndergo();
    }

    @Override
    public String getPlayerPassword() {
        return getModel().getMatch().getPlayer().getPassword().get();
    }

    @Override
    public List<Ship> getEnemeySunkenShip() {
        return getModel().getMatch().getEnemy().getBattleGround().get().getNavy().getShips().stream()
                .filter(s -> s.getStatus().equals(Ship.Status.SUNK))
                .collect(Collectors.toList());
    }
}
