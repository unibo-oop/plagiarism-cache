package talisman.controller.character;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import talisman.Controllers;

import talisman.controller.battle.BattleController;
import talisman.controller.battle.BattleControllerImpl;

import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleModelImpl;
import talisman.model.battle.BattleState;
import talisman.model.battle.EnemyInfos;
import talisman.model.battle.EnemyModel;
import talisman.model.battle.StrengthEnemy;

import talisman.model.cards.Card;
import talisman.model.cards.CardType;

import talisman.model.character.CharacterModel;
import talisman.model.character.PlayerModel;

import talisman.util.DiceType;
import talisman.util.DiceUtils;

import talisman.view.BattleWindow;
import talisman.view.CurrentPlayerChoicesWindow;
import talisman.view.OpponentChoiceWindow;

/**
 * Implementation that controls the current player's choices.
 * 
 * @author Alice Girolomini
 * @author Alberto Arduini
 *
 */
public class CurrentPlayerChoicesControllerImpl implements CurrentPlayerChoicesController {
    private final List<Integer> opponents;
    private int currentPlayerIndex;
    private int rollDice;
    private CurrentPlayerChoicesWindow window;
    private BattleController battleController;

    /**
     * Creates the controller for the current player's choices.
     * 
     * @param index - current player's index
     */
    public CurrentPlayerChoicesControllerImpl(final int index) {
        Controllers.getCharactersController().setCurrentPlayer(index);
        this.opponents = new ArrayList<>();
        this.initializeTurn();
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkRoll() {
        return this.rollDice != 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkOpponents() {
        return !this.opponents.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDiceRoll() {
        this.rollDice = DiceUtils.rollDice(DiceType.SIX);
        return this.rollDice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePawn() {
        final int currentPosition = Controllers.getBoardController().getCharacterPawn(this.currentPlayerIndex)
                .getPositionCell();
        if (checkRoll()) {
            Controllers.getBoardController().moveCharacterCell(this.currentPlayerIndex,
                    currentPosition + this.rollDice);
            this.opponents.addAll(Controllers.getBoardController().getCurrentCharacterOpponents());
            this.challengeEnemy();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void passTurn() {
        if (checkRoll()) {
            this.advanceTurn();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cellEvent() {
        if (checkRoll()) {
            Controllers.getBoardController().setActionEndedListener(() -> this.getView().setInteractible(true));
            Controllers.getBoardController().applyCurrentPlayerCellActions();
            Controllers.getBoardController().setActionEndedListener(null);
            // Since the action could place an enemy on the cell, i check i there is one
            // after applying the action
            this.challengeEnemy();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void challengeCharacter() {
        if (checkRoll() && checkOpponents()) {
            OpponentChoiceWindow.show(this.opponents, () -> this.getView().setInteractible(true));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void challengeEnemy() {
        final Optional<Card> card = Controllers.getBoardController().getCurrentCellCard();
        if (checkRoll() && card.isPresent() && card.get().getType() == CardType.ENEMY) {
            final BattleModel battleModel;
            final CharacterModel characterModel = Controllers.getCharactersController().getCurrentPlayer()
                    .getCurrentCharacter();
            final EnemyModel enemyModel = EnemyInfos.getEnemyByName(card.get().getName());
            if (enemyModel instanceof StrengthEnemy) {
                battleModel = new BattleModelImpl(characterModel.getStrength(), enemyModel.getStrength());
            } else {
                battleModel = new BattleModelImpl(characterModel.getCraft(), enemyModel.getCraft());
            }

            this.battleController = new BattleControllerImpl(characterModel, enemyModel, battleModel);

            this.getView().setInteractible(true);
            final BattleWindow window = new BattleWindow(battleController);
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(final WindowEvent e) {
                    CurrentPlayerChoicesControllerImpl.this.checkChallengeEnemyReuslt();
                }
            });
        } else {
            this.getView().setInteractible(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void skipTurn() {
        this.advanceTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrentPlayerChoicesWindow getView() {
        return this.window;
    }

    private void checkChallengeEnemyReuslt() {
        if (this.battleController.getResult() == BattleState.FIRST) {
            Controllers.getBoardController().removeCurrentCellCard();
        }
        this.getView().setInteractible(true);
    }

    private void initializeTurn() {
        this.currentPlayerIndex = Controllers.getCharactersController().getCurrentPlayer().getIndex();
        this.rollDice = 0;
        this.opponents.clear();
        this.window = CurrentPlayerChoicesWindow.show(this);
    }

    /**
     * Passes the current player's turn. 
     */
    private void advanceTurn() {
        this.getView().closeWindow();
        final PlayerModel player = Controllers.getCharactersController().getCurrentPlayer();
        if (player.hasQuest()) {
            player.resolveActiveQuest();
        }
        Controllers.getCharactersController().setCurrentPlayer(player.getIndex() + 1);
        this.initializeTurn();
    }

    @Override
    public List<Card> getCurrentCharacterCards() {
        return List.copyOf(Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getInventory().listCards());
    }
}
