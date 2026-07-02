package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.board.GameBoard;
import model.board.GameBoardImpl;
import model.board.Tile;
import model.board.TreasureGroupTileLogic;
import model.board.TreasureGroupTileLogicImpl;
import model.logic.MovementLogic;
import model.logic.MovementLogicImpl;
import model.logic.PlayerOptions;
import model.logic.PlayerOptionsImpl;
import model.logic.PlayersStatistic;
import model.logic.PlayersStatisticImpl;
import model.logic.ScoreLogic;
import model.logic.ScoreLogicImpl;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.players.PlayerImpl;
import model.utils.Boat;
import model.utils.BoatImpl;
import model.utils.Dice;
import model.utils.DiceImpl;
import model.utils.Direction;
import model.utils.OxygenReserve;
import model.utils.OxygenReserveImpl;
import model.utils.PossibleActionAfterMove;
import model.utils.TilePlayerPairImpl;
import sound.SoundManagerUtils;
import sound.SoundsAddress;
import view.ExceptionDialog;
import view.GameView;
import view.GameViewImpl;
import view.board.TemplateEnum;

/**
 * Implementation of DSAController.
 */

public class DSAControllerImpl implements DSAController { // NOPMD

    private static final int NGAMETURN = 3;
    private final MovementLogic movementLogic = new MovementLogicImpl();
    private final PlayersStatistic playerStatistic = new PlayersStatisticImpl();
    private final PlayerOptions playerOption = new PlayerOptionsImpl();
    private final ScoreLogic scoreLogic = new ScoreLogicImpl();
    private final PlayerCircularQueue playerCQ;
    private final List<Dice> dices = new ArrayList<Dice>();
    private final Boat<Player> boat = new BoatImpl<Player>();
    private final OxygenReserve oxygenReserve = new OxygenReserveImpl();
    private final GameBoard tileLine = new GameBoardImpl();
    private Integer turnGameCounter = 0;
    private GameView gameView;
    private Player playerInTurn;

    private final ScoreList scoreList = new ScoreList(ControllerUtils.getHighScore());

    /**
     * @param playerColorSetting
     *                               A map of names and color useful to create player at start.
     * @param templateEnum
     *                               The template scheme for coordinates of board tilesline.
     * @throws IOException
     *                         this exception happen when.......
     */
    public DSAControllerImpl(final Map<String, String> playerColorSetting, final String templateEnum)
            throws IOException {

        this.playerCQ = new PlayerCircularQueue(playerColorSetting.size());
        playerColorSetting.entrySet().stream()
                .forEach(x -> this.boat.add(new PlayerImpl(x.getKey(), PlayerColor.valueOf(x.getValue()))));
        this.gameView = new GameViewImpl(this, TemplateEnum.valueOf(templateEnum));
        this.playerInTurn = null;
        this.startGameTurn();

    }

    @Override
    public final Integer getGameTurnIndex() {
        return this.turnGameCounter;
    }

    @Override
    public final void setPlayers(final Map<String, String> nameColorMap) {
        nameColorMap.entrySet()
                .forEach(x -> this.boat.add(new PlayerImpl(x.getKey(), PlayerColor.valueOf(x.getValue()))));
    }

    @Override
    public final List<String> getMovementPossibleAction() {
        return this.playerOption.whereCanIMove(this.getPlayerInTurn(), this.tileLine, this.movementLogic);
    }

    @Override
    public final List<String> getPoassibleActionAfterMove() {
        return this.playerOption.whatICanDoAfterMovement(this.getPlayerInTurn(), this.tileLine);
    }

    @Override
    public final void gameMatchMessage(final String message) {
        this.gameView.updateMessageGameTurn(message);
    }

    @Override
    public final Player getPlayerInTurn() {
        return this.playerInTurn;
    }

    @Override
    public final void setPlayerInTurn() {
        this.playerInTurn = this.playerCQ.element();
    }

    @Override
    public final int getDicesValue() {
        this.soundLaunch(SoundsAddress.DICESROLL.getAddress());
        if (this.dices.isEmpty()) {
            this.dices.add(new DiceImpl());
            this.dices.add(new DiceImpl());
        }
        return this.dices.stream().mapToInt(x -> x.getValue()).sum();
    }

    @Override
    public final void endPlayerTurn() {
        this.playerCQ.remove();

    }

    @Override
    public final boolean testEndTurn() {
        return this.oxygenReserve.isEmpty() || this.playerCQ.isEmpty();
    }

    @Override
    public final boolean testEndGame() {
        return this.turnGameCounter >= DSAControllerImpl.NGAMETURN;
    }

    @Override
    public final boolean isLastTurn() {
        return this.turnGameCounter.equals(NGAMETURN);
    }

    @Override
    public final void play() {
        this.gameView.updateMovementButtons();

    }

    @Override
    public final Integer getRemainingOxigen() {
        return this.oxygenReserve.getOxygen();
    }

    @Override
    public final void manageAction(final PossibleActionAfterMove action) {
        this.manageAction(action, null);
    }

    @Override
    public final List<String> getPlayerStatistic() {
        return this.playerStatistic.getPlayersSituation(this.tileLine, this.boat, this.playerCQ);
    }

    @Override
    public final GameBoard getTileline() {
        return this.tileLine;
    }

    @Override
    public final void setView(final GameView gameFrame) {
        this.gameView = gameFrame;
    }

    @Override
    public final List<Tile> getPlayerInTurnTreasureTile() {
        return this.getPlayerInTurn().getPlayerTreasures().stream().collect(Collectors.toList());

    }

    @Override
    public final void startGameTurn() {
        this.soundLaunch(SoundsAddress.NEXTPLAYER.getAddress());
        this.turnGameCounter++;
        if (this.turnGameCounter.equals(1)) {
            this.playerCQ.addAll(this.boat);
            this.boat.clear();
        } else {
            Collections.reverse(this.boat);
            this.playerCQ.addAll(this.boat);
            this.boat.clear();
        }
        this.boat.clear();
        this.startNextPlayerTurn();
        this.gameView.updateMessageGameTurn("");
    }

    @Override
    public final void startNextPlayerTurn() {

        if (this.testEndTurn()) {
            this.endGameTurn();
        } else {
            this.soundLaunch(SoundsAddress.NEXTPLAYER.getAddress());
            this.endPlayerTurn();
            this.setPlayerInTurn();
            this.gameView.updateGameBoard();
            this.gameView.updatePlayerInTurn();
            this.oxygenReserve.decreaseOxygen(this.playerInTurn.getPlayerTreasures().size());
            this.gameView.updateOxygen();
            this.gameView.updateMovementButtons();
            this.gameView.updatePlayersSituation();
        }
    }

    @Override
    public final void manageAction(final PossibleActionAfterMove action, final Integer npickUp) {
        if (action.name().equals(PossibleActionAfterMove.PICK_UP.name())) {
            this.soundLaunch(SoundsAddress.PICKTREASURE.getAddress());
            this.scoreLogic.playerPickUpTreasure(this.tileLine, this.playerCQ.element());
            this.gameView.updateActionButtons();
            this.gameView.updateGameBoard();
            this.startNextPlayerTurn();
        } else if (action.name().equals(PossibleActionAfterMove.RELEASE.name())) {
            this.soundLaunch(SoundsAddress.RELEASETREASURE.getAddress());
            this.scoreLogic.playerReleaseTreasure(this.tileLine, this.playerInTurn, npickUp);
            this.gameView.updateActionButtons();
            this.gameView.updateGameBoard();
            this.startNextPlayerTurn();
        } else if (action.name().equals(PossibleActionAfterMove.PASS_TURN.name())) {
            this.gameView.updateGameBoard();
            this.startNextPlayerTurn();

        }
    }

    @Override
    public final void movePlayer(final Direction direction) {
        if (!this.playerCQ.element().getDirection().equals(direction)) {
            this.playerCQ.element().changeDirectionToBoat();
        }
        final Integer valueOfDice = this.getDicesValue();
        Integer totalValueMinusCarried = null;

        this.gameView.updateDices(valueOfDice);
        totalValueMinusCarried = valueOfDice - this.playerCQ.element().getPlayerTreasures().size();
        if (totalValueMinusCarried <= 0) {
            totalValueMinusCarried = 0;
        }
        this.movementLogic.movePlayer(this.tileLine, this.playerCQ.element(), totalValueMinusCarried, this.playerCQ,
                this.boat);
        this.gameView.updatePlayersSituation();
        this.gameView.updateGameBoard();
        this.gameView.updateDisableAllButtons();
        this.gameView.updateActionButtons();

    }

    @Override
    public final void endGameTurn() {
        this.oxygenReserve.resetOxygen();
        this.scoreLogic.giveScoreToPlayersOnBoat(this.boat);
        /*
         * next lines management of treasure lost by player still diving at the end of the turn
         */
        final TreasureGroupTileLogic tGTL = new TreasureGroupTileLogicImpl(this.playerCQ);
        final Integer tileLenght = this.tileLine.getTilesLine().size();

        IntStream.range(tileLenght, tileLenght + tGTL.getFallenTreasureGroupedList().size())
                .forEach(i -> this.tileLine.getTilesLine().put(i + 1,
                        new TilePlayerPairImpl(tGTL.getFallenTreasureGroupedList().get(i - tileLenght))));
        /* end of lost treasure management */

        this.scoreLogic.removeAllTreasureToPlayerOnTile(this.playerCQ);
        this.movementLogic.moveAllPlayerToBoat(this.tileLine, this.boat);
        this.playerCQ.clear();
        this.tileLine.recreateBoard(this.tileLine.getTilesLine());
        if (this.testEndGame()) {
            this.manageEndOfGame();
        } else {
            this.boat.stream().forEach(x -> x.setDirectionToDeep());
            this.startGameTurn();
        }
    }

    @Override
    public final void manageEndOfGame() {
        String message = "";
        /* questa cris potevamo farla in una riga sola ma viene bene uguale no? */
        this.soundLaunch(SoundsAddress.WINNER.getAddress());
        final Integer maxValue = this.boat.stream().map(x -> x.getPlayerScore())
                .max(Comparator.comparing(Integer::valueOf))
                .orElse(0);
        final List<Player> listWinner = this.boat.stream().filter(x -> x.getPlayerScore().equals(maxValue))
                .collect(Collectors.toList());
        if (listWinner.size() > 1) {
            message = message.concat("The match finish in a draw, between : ");
            final String names = listWinner.stream().map(x -> x.getPlayerName()).collect(Collectors.joining(", "));
            message = message.concat(names).concat(" with a total score of: " + maxValue);

        } else {

            message = message.concat(
                    "The Winner is " + listWinner.get(0).getPlayerName() + " with a total score of " + maxValue);
        }
        listWinner.stream().forEach(x -> scoreList.addScore(new Score(x.getPlayerName(), maxValue)));
        // scoreList.recreateList();
        this.gameView.updateEndGame();
        this.gameView.updateMessageForPlayer(message);
    }

    @Override
    public final void soundLaunch(final String soundAddr) {
        try {
            SoundManagerUtils.playSound(soundAddr);
        } catch (LineUnavailableException e ) {
            new ExceptionDialog(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            new ExceptionDialog(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            new ExceptionDialog(e.getMessage());
            e.printStackTrace();
        }
    }

}
