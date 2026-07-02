package controller;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import controller.audio.Audio;
import controller.audio.AudioManager;
import controller.audio.AudioManagerImpl;
import javafx.util.Pair;
import model.board.Board;
import model.board.BoardInterface;
import model.board.Initializator;
import model.board.manager.GameManager;
import model.board.manager.GameManagerInterface;
import model.dice.Dice;
import model.dice.DiceFactory;
import model.memento.Memento;
import model.memento.MementoImpl;
import model.movement.Movement;
import model.movement.MovementManager;
import model.players.Player;
import model.players.ranking.Ranking;
import model.players.ranking.RankingManager;
import model.settings.SettingsManager;
import model.settings.SettingsManagerInterface;
import utilities.Colors;
import utilities.Directions;
import utilities.Elements;
import utilities.Status;
import utilities.test.demo.DemoBoardView;
import view.board.BoardView;
import view.board.BoardViewInterface;
import view.board.WinDialog;
import view.board.WinDialogInterface;

/**
 *
 * Piero Sanchi. This class creates one instance of the controller.
 *
 */
public final class ControllerImpl implements Controller {

    private static final String MINOTAURO = "minotauro";
    private static final String MURO = "muro";
    private static final String EROE = "eroe";
    private static final String JUMP = "jump";

    private static ControllerImpl controller;

    private final Memento memento;
    private final DiceFactory diceFactory;
    private final AudioManager audio;
    private final Ranking rank;
    private final SettingsManagerInterface settings;
    private final GameManagerInterface gameManager;
    private BoardInterface gameBoard;
    private Movement movementManager;
    private BoardViewInterface boardView;
    private Pair<Integer, Integer> selected;
    private Pair<Integer, Integer> oldSelected;
    private Player actualPlayer;
    private Dice actualDice;
    private int steps;
    private int arrivedHero;
    private Elements actualElementType;

    /**
     * Constructor.
     */
    public ControllerImpl() {
        this.selected = null;
        this.oldSelected = null;
        this.arrivedHero = 0;
        this.memento = new MementoImpl();
        this.diceFactory = new DiceFactory();
        this.audio = new AudioManagerImpl();
        this.rank = new RankingManager();
        this.settings = SettingsManager.getLog();
        this.gameManager = GameManager.getLog();
    }

    @Override
    public void addPlayer(final Player player) {
        this.settings.getSettings().addNewPlayer(player);
    }

    private boolean arrivedEnoughHero() {
        this.gameBoard.getHeroMap().values().iterator().forEachRemaining(e -> {
            if (e.getColor().equals(this.actualPlayer.getColor()) && e.isArrived()) {
                this.arrivedHero++;
            }
        });
        if (this.settings.getSettings().getHeroForWin() == this.arrivedHero) {
            this.arrivedHero = 0;
            return true;
        } else {
            this.arrivedHero = 0;
            return false;
        }
    }

    private void deleteSavedGame() {
        this.settings.deleteSavedSettings();
        this.gameManager.deleteSavedGame();
    }

    private void endGame() {
        this.rank.addScore(this.actualPlayer);
        final WinDialogInterface win = new WinDialog();
        this.boardView.close();
        win.setRank(this.rank.getHighscoreString());
        this.audio.play(Audio.WIN);
    }

    private void endTurn() {
        this.selected = null;
        this.setActualPlayer();
        this.boardView.getSidePanel().repaintLabel(this.actualPlayer.getColor());
        this.boardView.waitDice();
        this.actualPlayer.incrementTurn();
        this.memento.resetMemento();
        this.settings.getSettings().setJumpEnabled(false);
    }

    @Override
    public boolean isAudioOn() {
        return this.settings.getSettings().isMusicOn();
    }

    @Override
    public int getCharactersForWin() {
        return this.settings.getSettings().getHeroForWin();
    }

    @Override
    public int getMinotaurusSteps() {
        return this.settings.getSettings().getMinotaurusSteps();
    }

    @Override
    public String getRanking() {
        return this.rank.getHighscoreString();
    }

    @Override
    public int getSelectedMaze() {
        return this.settings.getSettings().getMaze();
    }

    @Override
    public boolean isHedgeJumpingMode() {
        return this.settings.getSettings().isHedgeJumping();
    }

    @Override
    public boolean isMinotaurusHedgeJumpingMode() {
        return this.settings.getSettings().isMinotaurusHedgeJumping();
    }

    @Override
    public boolean isNotLastStep() {
        return this.steps != 1;
    }

    private void limitedMove(final Directions direction) {
        if (this.steps != 0) {
            this.move(direction);
        }
    }

    private void move(final Directions direction) {
        if (this.movementManager.move(this.selected, direction)) {
            this.redrawBoard();
            this.memento.lastStep(this.oldSelected);
            if (this.actualElementType != Elements.MURO) {
                this.audio.play(Audio.STEP);
                this.steps--;
                this.boardView.getSidePanel().setAvailableNStepsText(ControllerImpl.this.steps);
            } else {
                this.audio.play(Audio.WALL);
            }
            if ((this.actualElementType == Elements.EROE)
                    && (this.gameBoard.getHeroMap().get(this.selected).isArrived())) {
                this.audio.play(Audio.TADA);
                this.steps = 0;
                if (this.arrivedEnoughHero()) {
                    this.endGame();
                } else {
                    this.endTurn();
                }

            }
        }
    }

    @Override
    public void openBoardView() {
        this.deleteSavedGame();
        this.gameBoard = Board.getLog();
        this.boardView = new BoardView(this.settings.getSettings().getBoardLimit() + 1,
                this.settings.getSettings().getBoardLimit() + 1);
        this.prepareBoardView();
    }

    @Override
    public void openDemoBoardView() {
        this.gameBoard = Board.getLog();
        this.boardView = new DemoBoardView(this.settings.getSettings().getBoardLimit() + 1,
                this.settings.getSettings().getBoardLimit() + 1);
        this.prepareBoardView();
    }

    private void prepareBoardView() {
        this.movementManager = new MovementManager();
        this.actualDice = this.diceFactory.getDice(this.settings.getSettings().isHedgeJumping());
        this.setActualPlayer();
        this.boardView.waitDice();
        this.boardView.getSidePanel().repaintLabel(this.actualPlayer.getColor());
        this.actualPlayer.incrementTurn();
        this.updateAll();
        this.boardView.show();
    }

    private void pressedEnter() {
        if ((this.steps == 0) || (this.actualElementType == Elements.MURO)) {
            this.endTurn();
        }
    }

    private void redrawBoard() {
        if (this.gameBoard.getMinotaurus().isEating()) {
            this.audio.play(Audio.EVIL);
            this.updateOldBackground();
            this.movementManager.resetHero(this.selected);
            this.oldSelected = this.selected;
            this.updateOldBackground();
            this.gameBoard.getMinotaurus().restartMinotaurus();
            this.gameBoard.getMinotaurus().setIsEating();
            this.select(null);
            this.updateElements();
            this.endTurn();
        } else if (this.gameBoard.getWallMap().containsKey(this.selected)) {
            this.updateOneBackground(this.gameBoard.getWallMap().get(this.selected).getOldActualPosition());
            this.updateOneBackground(this.gameBoard.getWallMap().get(this.selected).getOldSecondPosition());
            this.updateOneElement();
            this.updateWall(this.gameBoard.getWallMap().get(this.selected).getSecondPosition());
        } else {
            this.updateOldBackground();
            this.updateOneElement();
        }
    }

    @Override
    public void reopenBoardView() {
        Board.restoreBoard();
        this.settings.loadSavedSettings();
        this.openBoardView();
    }

    @Override
    public void reset() {
        Initializator.getLog().restoreBoard();
        this.selected = null;
        Board.resetBoard();
        this.gameBoard.resetArrivedHero();
        this.settings.getSettings().resetTurn();
        this.openBoardView();
    }

    @Override
    public void rollDice() {
        this.audio.play(Audio.DICE);
        this.steps = this.actualDice.rollAndGetResult();
        this.boardView.getSidePanel().setDiceImage(this.actualDice.getResultFacePath());
        this.boardView.getSidePanel().redrawLabel(EROE);
        switch (this.steps) {
        case 1:
            this.actualElementType = Elements.MINOTAURO;
            this.boardView.getSidePanel().redrawLabel(MINOTAURO);
            this.boardView.getSidePanel().setAvailableNStepsText(this.settings.getSettings().getMinotaurusSteps());
            this.steps = this.settings.getSettings().getMinotaurusSteps();
            this.boardView.waitSelect(this.gameBoard.getMinotaurus().getActualPosition());
            break;
        case 2:
            this.actualElementType = Elements.MURO;
            this.boardView.getSidePanel().redrawLabel(MURO);
            this.boardView.getSidePanel().setAvailableNStepsText(null);
            this.gameBoard.getWallMap().keySet().stream().forEach(e -> {
                this.boardView.waitSelect(e);
            });
            break;
        case 3:
            if (this.settings.getSettings().isHedgeJumping()) {
                this.settings.getSettings().setJumpEnabled(true);
                this.boardView.getSidePanel().redrawLabel(JUMP);
            }
        default:
            this.actualElementType = Elements.EROE;
            this.boardView.getSidePanel().setAvailableNStepsText(this.steps);
            this.gameBoard.getHeroMap().keySet().stream().forEach(e -> {
                if ((this.gameBoard.getHeroMap().get(e).getColor() == this.actualPlayer.getColor())
                        && this.gameBoard.getHeroMap().get(e).isMovable()
                        && !this.gameBoard.getHeroMap().get(e).isArrived()) {
                    this.boardView.waitSelect(e);
                }
            });
            break;
        }
    }

    private void rotate() {
        if (this.movementManager.rotate(this.selected)) {
            this.updateOneBackground(this.gameBoard.getWallMap().get(this.selected).getOldActualPosition());
            this.updateOneElement();
            this.audio.play(Audio.WALL);
        }
    }

    @Override
    public boolean savedGamePresent() {
        return (this.settings.isSavedPresent() && this.gameManager.isPresent());
    }

    @Override
    public void saveGame() {
        if (this.actualElementType == Elements.MURO) {
            this.settings.getSettings().getNextPlayer();
        } else {
            while (this.memento.isStepPresent()) {
                this.undo();
            }
        }
        this.settings.saveSettings();
        this.gameManager.saveGame();
    }

    @Override
    public void select(final Pair<Integer, Integer> coord) {
        if (this.gameBoard.getElementAtPosition(coord) != null) {
            this.selected = coord;
            this.boardView.waitMove();
            if (this.gameBoard.getElementAtPosition(coord).getType() == Elements.MURO) {
                this.boardView.getSidePanel().waitSelect();
            } else if (this.gameBoard.getElementAtPosition(coord).getType() == Elements.EROE) {
                this.boardView.selectedIcon(coord);
            }
        }
    }

    private void setActualPlayer() {
        this.actualPlayer = this.settings.getSettings().getNextPlayer();
        this.boardView.getSidePanel().setTurnPlayerText(this.actualPlayer.toString());
        this.boardView.getSidePanel().setHeroArrived(this.gameBoard.arrivedHero(this.actualPlayer),
                this.settings.getSettings().getHeroForWin());
    }

    @Override
    public void setCharactersForWin(final int value) {
        this.settings.getSettings().setHeroForWin(value);
    }

    @Override
    public void setEdgeJumpingMode(final boolean isHedgeJumpingMode) {
        this.settings.getSettings().setHedgeJumping(isHedgeJumpingMode);
    }

    @Override
    public void setMaze(final int maze) {
        this.settings.getSettings().setMaze(maze);
    }

    @Override
    public void setMinotaurusEdgeJumpingMode(final boolean isMinotaurusHedgeJumpingMode) {
        this.settings.getSettings().setMinotaurusHedgeJumping(isMinotaurusHedgeJumpingMode);
    }

    @Override
    public void setMinotaurusSteps(final int minotaurusSteps) {
        this.settings.getSettings().setMinotaurusSteps(minotaurusSteps);
    }

    @Override
    public void setVolume(final int volume) {
        this.audio.setVolume(volume);
    }

    @Override
    public void startDemoKeyboardListener() {
        final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if ((e.getID() == KeyEvent.KEY_RELEASED) && (ControllerImpl.this.selected != null)) {
                ControllerImpl.this.oldSelected = ControllerImpl.this.selected;
                switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    ControllerImpl.this.move(Directions.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    ControllerImpl.this.move(Directions.UP);
                    break;
                case KeyEvent.VK_LEFT:
                    ControllerImpl.this.move(Directions.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    ControllerImpl.this.move(Directions.RIGHT);
                    break;
                case KeyEvent.VK_ENTER:
                    ControllerImpl.this.endTurn();
                    break;
                case KeyEvent.VK_R:
                    ControllerImpl.this.rotate();
                    break;
                default:
                    break;
                }

            }
            return true;
        });
    }

    @Override
    public void startKeyboardListener() {
        final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if ((e.getID() == KeyEvent.KEY_RELEASED) && (ControllerImpl.this.selected != null)) {
                ControllerImpl.this.oldSelected = ControllerImpl.this.selected;
                switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    ControllerImpl.this.limitedMove(Directions.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    ControllerImpl.this.limitedMove(Directions.UP);
                    break;
                case KeyEvent.VK_LEFT:
                    ControllerImpl.this.limitedMove(Directions.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    ControllerImpl.this.limitedMove(Directions.RIGHT);
                    break;
                case KeyEvent.VK_ENTER:
                    ControllerImpl.this.pressedEnter();
                    break;
                case KeyEvent.VK_R:
                    ControllerImpl.this.rotate();
                    break;
                default:
                    break;
                }

            }
            return true;
        });
    }

    @Override
    public void stopWin() {
        this.audio.stop(Audio.WIN);
    }

    @Override
    public void undo() {
        if (this.memento.isStepPresent()) {
            this.steps++;
            this.boardView.getSidePanel().setAvailableNStepsText(this.steps);
            this.oldSelected = this.selected;
            this.movementManager.moveTo(this.selected, this.memento.getLastStep());
            this.redrawBoard();
        }
    }

    private void updateAll() {
        this.updateBackground();
        this.updateElements();
    }

    private void updateBackground() {
        this.gameBoard.getOccupationList().keySet().stream().forEach(e -> {
            this.boardView.drawBackground(e, this.gameBoard.getOccupationList().get(e));
        });
    }

    @Override
    public void updateElements() {
        this.gameBoard.getHeroMap().keySet().stream().forEach(e -> {
            this.boardView.drawElement(e, this.gameBoard.getHeroMap().get(e));
        });
        this.gameBoard.getWallMap().keySet().stream().forEach(e -> {
            this.boardView.drawElement(e, this.gameBoard.getWallMap().get(e));
        });
        this.boardView.drawElement(this.gameBoard.getMinotaurus().getActualPosition(), this.gameBoard.getMinotaurus());
    }

    private void updateOldBackground() {
        if (this.gameBoard.getOccupationList().containsKey(this.oldSelected)) {
            this.boardView.drawBackground(this.oldSelected, this.gameBoard.getOccupationList().get(this.oldSelected));
        } else {
            this.boardView.drawBackground(this.oldSelected, new Pair<>(Status.VUOTO, Colors.Cyan));
        }
    }

    private void updateOneBackground(final Pair<Integer, Integer> coord) {
        this.boardView.drawBackground(coord, new Pair<>(Status.VUOTO, Colors.Cyan));
    }

    private void updateOneElement() {
        this.boardView.drawElement(this.selected, this.gameBoard.getElementAtPosition(this.selected));
    }

    private void updateWall(final Pair<Integer, Integer> coord) {
        this.boardView.drawElement(coord, this.gameBoard.getElementAtPosition(coord));
    }

    /**
     *
     * @return the single instance of the controller.
     */
    public static synchronized Controller getLog() {
        if (controller == null) {
            controller = new ControllerImpl();
        }
        return controller;
    }

}
