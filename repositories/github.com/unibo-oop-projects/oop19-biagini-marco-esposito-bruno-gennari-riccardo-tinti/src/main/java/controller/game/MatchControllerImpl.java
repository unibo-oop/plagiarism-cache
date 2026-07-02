package controller.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import application.Battleships;

import java.util.Map.Entry;

import model.enums.PlayerNumber;
import model.enums.PlayerType;
import model.enums.ShipType;
import model.match.CellAlreadyShotException;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.match.Ship;
import model.util.Pair;
import view.match.BattleView;
import view.scene.SceneName;

/**
 * 
 * Possible implementation of match's controller.
 * 
 */
public class MatchControllerImpl implements MatchController {

    /*
     * For now i put grid size here
     */
    private static final int LINE = 10;
    private static final int COLUMN = 10;
    private static final int SHIPS_NUMBER = 5;

    private BattleView battleView;
    private final Map<PlayerNumber, PlaygroundBattle> playgrounds;
    private PlayerNumber currentVillain;
    private int shotAvailable;

    public MatchControllerImpl() {
        this.playgrounds = new HashMap<>();
        this.playgrounds.put(PlayerNumber.PLAYER_ONE,
                new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN));
        this.playgrounds.put(PlayerNumber.PLAYER_TWO,
                new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN));
        this.currentVillain = PlayerNumber.PLAYER_TWO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void positionShip(final ShipType shipType, final Pair<Integer, Integer> firstCell) {
        try {
            this.playgrounds.get(Battleships.getController().getCurrentPlayer().get()).positionShip(new Ship(shipType),
                    firstCell);
        } catch (CellsFilledException e) {
            this.battleView.showCellAlreadyUsedAlert(e.getCellsUsed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeShip(final Pair<Integer, Integer> cell) {
        this.playgrounds.get(Battleships.getController().getCurrentPlayer().get()).removeShipWithCell(cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shot(final int line, final int col) {
        try {
            final Optional<Entry<List<Pair<Integer, Integer>>, Ship>> v = this.playgrounds.get(this.currentVillain)
                    .shipHitted(new Pair<>(line, col));

            // If optional is present a ship is hitted.
            if (v.isPresent()) {
                if (this.playgrounds.get(this.currentVillain).shipSunk(v.get().getKey()).get()) {
                    this.battleView.drawSunkShip(v.get().getKey(), this.currentVillain);
                } else {
                    this.battleView.drawHit(new Pair<>(line, col), this.currentVillain);
                }
                this.checkWin();
            } else {
                this.battleView.drawMissed(new Pair<>(line, col), this.currentVillain);
            }

            this.battleView.setShotAvailable(this.playgrounds.get(this.currentVillain).getNumberOfAliveShip());
            this.battleView.setPoints(this.playgrounds.get(this.currentVillain).getDamage());

            if (this.shotAvailable <= 1) {
                this.changePlayer();
            } else {
                this.shotAvailable--;
                if (this.isAITurn()) {
                    this.turnAI();
                }
            }

        } catch (CellAlreadyShotException e) {
            if (this.isAITurn()) {
                this.turnAI();
            } else {
                this.battleView.showCellAlreadyShottedAlert(new Pair<>(line, col));
            }
        }
    }

    private void startBattle() {
        this.currentVillain = PlayerNumber.PLAYER_TWO;
        this.shotAvailable = this.playgrounds.get(PlayerNumber.PLAYER_ONE).getNumberOfAliveShip();
        this.battleView.drawShip(this.shipCells(PlayerNumber.PLAYER_ONE), PlayerNumber.PLAYER_ONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final BattleView battleView) {
        this.battleView = battleView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextToPosition() {

        final PlayerType secondPlayerType = Battleships.getController().getMatchInfo().get()
                .getPlayerInfo(PlayerNumber.PLAYER_TWO).getType();

        if (secondPlayerType == PlayerType.ARTIFICIAL) {
            Battleships.getController().nextPlayer();
            Battleships.getController().setAI();
            Battleships.getController().changeScene(SceneName.MATCH_BATTLE);
            this.startBattle();
        } else {
            if (Battleships.getController().getCurrentPlayer().get().equals(PlayerNumber.PLAYER_ONE)) {
                Battleships.getController().changeScene(SceneName.SHIP_DEPLOYMENT);
            } else {
                Battleships.getController().changeScene(SceneName.MATCH_BATTLE);
                this.startBattle();
            }
        }

        Battleships.getController().nextPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayground(final PlaygroundBattle playgroundBattle) {
        this.playgrounds.put(Battleships.getController().getCurrentPlayer().get(), playgroundBattle);
        playgroundBattle.resetLogicGrid();
    }

    public static int getShipNumberOfGame() {
        return MatchControllerImpl.SHIPS_NUMBER;
    }

    private void checkWin() {
        if (Battleships.getController().isMatchOver(this.playgrounds.get(this.currentVillain).getDamage(),
                this.playgrounds.get(this.currentVillain).getNumberOfAliveShip())) {
            this.setStatistic();
            this.battleView.showWinDialog();
        }
    }

    private void setStatistic() {
        final PlayerNumber currentPlayer = Battleships.getController().getCurrentPlayer().get();

        if (Battleships.getController().getMatchInfo().get().getPlayerInfo(currentPlayer).getType().equals(PlayerType.HUMAN)) {
            Battleships.getController().getAccountManager().setWinner(
                    Battleships.getController().getMatchInfo().get().getPlayerInfo(currentPlayer).getUsername(),
                    Double.valueOf(this.playgrounds.get(this.currentVillain).getDamage()));
        }

        if (Battleships.getController().getMatchInfo().get().getPlayerInfo(this.currentVillain).getType().equals(PlayerType.HUMAN)) {
            Battleships.getController().getAccountManager().setLoser(
                    Battleships.getController().getMatchInfo().get().getPlayerInfo(this.currentVillain).getUsername(),
                    Double.valueOf(this.playgrounds.get(currentPlayer).getDamage()));
        }
    }

    private void changePlayer() {
        this.shotAvailable = this.playgrounds.get(this.currentVillain).getNumberOfAliveShip();
        this.battleView.hideShip(this.shipCells(Battleships.getController().getCurrentPlayer().get()),
                Battleships.getController().getCurrentPlayer().get());
        this.battleView.changePlayer();
        this.currentVillain = Battleships.getController().getCurrentPlayer().get();
        Battleships.getController().nextPlayer();

        if (this.isAITurn()) {
            this.turnAI();
        }
    }

    private boolean isAITurn() {
        final PlayerType secondPlayerType = Battleships.getController().getMatchInfo().get()
                .getPlayerInfo(PlayerNumber.PLAYER_TWO).getType();

        // return
        // Battleships.getController().getMatchInfo().get().getPlayerInfo(Battleships.getController().getCurrentPlayer().get()).getType().equals(PlayerType.ARTIFICIAL);
        return !this.currentVillain.equals(PlayerNumber.PLAYER_TWO) && secondPlayerType.equals(PlayerType.ARTIFICIAL);
    }

    private void turnAI() {
        Battleships.getController().shotAI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showShip() {
        this.battleView.drawShip(this.shipCells(Battleships.getController().getCurrentPlayer().get()),
                Battleships.getController().getCurrentPlayer().get());
    }

    private List<Pair<Integer, Integer>> shipCells(final PlayerNumber playerNumber) {
        final List<Pair<Integer, Integer>> listOfCells = new ArrayList<>();
        final Set<List<Pair<Integer, Integer>>> keySet = this.playgrounds.get(playerNumber).getShips().keySet();

        for (final List<Pair<Integer, Integer>> list : keySet) {
            listOfCells.addAll(list);
        }

        return listOfCells;
    }
}
