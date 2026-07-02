package it.unibo.the100dayswar.controller.gamecontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.gamecontroller.api.GameController;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.view.diceview.DiceAnimationFrame;
import it.unibo.the100dayswar.view.gameover.GameLoseView;
import it.unibo.the100dayswar.view.gameover.GameWinView;

/**
 * The implementation of the game controller of the game.
 */
public class GameControllerImpl implements GameController {
    /** 
     * {@inheritDoc}
     */
    @Override
    public void attack() {
        final var selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        if (selectedCell.getSecond().getUnit().isPresent()) {
            final var unit = selectedCell.getSecond().getUnit().get();
            if (unit instanceof Soldier) {
                final var soldier = (Soldier) unit;
                final var adjacentCells = The100DaysWar.CONTROLLER.getMapController().getAdjacentCells(selectedCell.getSecond());
                for (final var cell : adjacentCells) {
                    if (cell.getUnit().isPresent() && cell.getUnit().get() instanceof Combatant) {
                        final var defender = cell.getUnit().get();
                        DiceAnimationFrame.showDiceFrame();
                        soldier.performAttack(defender);
                    }
                }
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void skip() {
        The100DaysWar.CONTROLLER.getGameInstance().skipTurn();
        this.checkGameOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkGameOver() {
        if (The100DaysWar.CONTROLLER.getGameInstance().isOver()) {
            displayGameOver(The100DaysWar.CONTROLLER.getGameInstance().getWinner());
        }
    }

    /**
     * Display the game over message.
     * 
     * @param winner the winner of the game
     */
    private void displayGameOver(final Player winner) {
        if (winner == null) {
            throw new IllegalStateException("Game ended in a draw");
        } else if (winner.equals(The100DaysWar.CONTROLLER.getGameInstance().getHumanPlayer())) {
            new GameWinView().initialize();
        } else {
            new GameLoseView().initialize();
        }
    }
}
