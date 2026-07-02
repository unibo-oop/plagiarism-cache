package net.pokemonbt.controller.view_controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.battle.BattleTurnController;
import net.pokemonbt.controller.battle.DamageUtils;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.controller.resources.LoadoutManager;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.PersonalMove;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;
import net.pokemonbt.model.pokemon.components.StatComponent;
import net.pokemonbt.utility.Refreshable;
import net.pokemonbt.utility.WorkRequest;

/**
 * Controller for the battle scene, handles the thread communication with the
 * battleTurnController.
 */
public final class BattleController implements Refreshable {
    private static final String SWITCH_SCENE_PATH = "/layouts/switch.fxml";
    private static final String DEFAULT_LOG_STRING = "Choose a Move";
    private static final int QUEUE_SIZE = 1;
    private Pokemon lastActive;
    private final BattleEnvironment be;
    private BlockingQueue<WorkRequest> requestQueue;
    // private final ResourceManager rm = new ResourceManager();

    @FXML
    private VBox opponentContainer;
    @FXML
    private VBox activePokemonContainer;
    @FXML
    private VBox optionsButtonWrapper;

    @FXML
    private GridPane moveContainer;

    @FXML
    private AnchorPane battleLog;

    @FXML
    private Button battleButton;
    @FXML
    private Button pokemonButton;
    @FXML
    private Button runButton;

    @FXML
    private Text battleButtonText;
    @FXML
    private Text opponentName;
    @FXML
    private Text activeName;
    @FXML
    private Text battleLogText;
    @FXML
    private Text moveName1;
    @FXML
    private Text moveName2;
    @FXML
    private Text moveName3;
    @FXML
    private Text moveName4;

    @FXML
    private Pane hpLineOpponent;
    @FXML
    private Pane hpLineActive;
    @FXML
    private Pane settingsPane;

    @FXML
    private ImageView settingsIcon;

    /**
     * Constructor for the BattleController class, sets the BattleEnviroment Object.
     * 
     * @param be the BattleController object to be set.
     */
    public BattleController(final BattleEnvironment be) {
        ResourceManager.initializeConditionList("json/conditions.json");
        this.be = be;
    }

    /**
     * Initializes menu controller.
     */
    @FXML
    public void initialize() {
        requestQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

        GameSession.clearTeam();
        final BattleTurnController btc = new BattleTurnController(requestQueue, be);

        DamageUtils.initialize(
                ResourceManager.getResourceAsJsonElement("json/type_mult.json")
                        .orElseThrow()
                        .getAsJsonObject());
        new Thread(btc).start();

        ViewUtilities.hideParent(moveContainer);
        ViewUtilities.addShadow(settingsPane);

        this.lastActive = be.getCurrentOwn(TeamType.PLAYER);

        battleLogText.setText("Battle started");
    }

    /**
     * Refresh method for the battle controller, execute every time the battle scene
     * loads.
     */
    @Override
    public void refresh() {
        this.battleLogText.setText(DEFAULT_LOG_STRING);
        this.updateActive();
        this.updateLife();
        if (!lastActive.equals(be.getCurrentOwn(TeamType.PLAYER)) && lastActive.getStatComponent().getHP() > 0) {
            this.handleUserInput(null, false);
            lastActive = be.getCurrentOwn(TeamType.PLAYER);
        }
        System.out.println("Battle Controller refreshed"); // NOPMD
    }

    /**
     * Updates the visuals for the current active pokemons, both player's and
     * enemy's.
     */
    private void updateActive() {
        final String activeId = be.getCurrentOwn(TeamType.PLAYER).getID();
        final ImageView activePokemon = new ImageView(
                ResourceManager.getResourceAsImage("img/back/".concat(activeId).concat(".png")));
        final String opponentId = be.getCurrentOther(TeamType.PLAYER).getID();
        final ImageView opponentPokemon = new ImageView(
                ResourceManager.getResourceAsImage("img/front/".concat(opponentId).concat(".png")));
        this.opponentContainer.getChildren().clear();
        this.activePokemonContainer.getChildren().clear();
        this.opponentContainer.getChildren().add(opponentPokemon);
        this.activePokemonContainer.getChildren().add(activePokemon);
    }

    /**
     * Updates every value in the hp pane: pokemon names and hp values.
     */
    private void updateLife() {
        opponentName.setText(
                GameSession.getEnemyName()
                        .concat("'s\n")
                        .concat(
                                be.getCurrentOwn(TeamType.ENEMY)
                                        .getDisplayName()));
        activeName.setText(
                GameSession.getPlayerName()
                        .concat("'s\n")
                        .concat(
                                be.getCurrentOwn(TeamType.PLAYER)
                                        .getDisplayName()));
        final double baseHpWidth = hpLineActive.getPrefWidth();

        final StatComponent activeStatComponent = be.getCurrentOwn(TeamType.PLAYER)
                .getStatComponent();
        double hpPercentage = (double) activeStatComponent.getHP()
                / activeStatComponent.getBaseStat(PokeStatType.HP_MAX);

        hpLineActive.setMaxWidth(baseHpWidth * hpPercentage);

        final StatComponent enemyStatComponent = be.getCurrentOwn(TeamType.ENEMY)
                .getStatComponent();
        hpPercentage = (double) enemyStatComponent.getHP() / enemyStatComponent.getBaseStat(PokeStatType.HP_MAX);
        final double value = baseHpWidth * hpPercentage;
        hpLineOpponent.setMaxWidth(value);

    }

    /**
     * Controls if the active pokemon should use a forced move and if so disables
     * the switching button.
     */
    private void forceMove() {
        if (be.getCurrentOwn(TeamType.PLAYER).getMoveComponent().isMoveChoosable()) {
            pokemonButton.setDisable(false);
        } else {
            pokemonButton.setDisable(true);
        }

    }

    /**
     * Handles the click on the battle button.
     */
    public void onBattle() {
        this.showMoves();
    }

    /**
     * Handles the click on the pokemon button.
     */
    public void onPokemon() {
        SceneManager.setSceneWithBE(SWITCH_SCENE_PATH, be);
    }

    /**
     * Hard loads the switching scene.
     */
    public void forceSwich() {
        SceneManager.setSceneWithBE(SWITCH_SCENE_PATH, be);
    }

    /**
     * Handles the click on the run button during battle.
     */
    @FXML
    public void onRun() {
        SceneManager.clearAllCache();
        GameSession.clearTeam();
        SceneManager.setScene("/layouts/menu.fxml");
    }

    /**
     * Handles the move selection during battle.
     * 
     * @param event the click on a move.
     */
    public void onMoveSelection(final ActionEvent event) {
        final Button moveButton = (Button) event.getSource();
        this.showMoves();
        final Move userMove = be.getCurrentOwn(TeamType.PLAYER).getMoveComponent()
                .getMoveMap().get(moveButton.getId());
        handleUserInput(userMove, true);
    }

    /**
     * Handles communication with the battle turn controller thread and elaborates
     * the arriving informations changing the GUI.
     * 
     * @param move       the selected move, may be null.
     * @param userChoice the choosen action by the player, true if battling, false
     *                   if switching.
     */
    private void handleUserInput(final Move move, final boolean userChoice) {
        moveContainer.setDisable(true);
        pokemonButton.setDisable(true);
        battleButton.setDisable(true);

        final WorkRequest request = new WorkRequest(move, userChoice);

        request.setOnComplete(() -> {
            playBattleLogs(request.getTurnLogs(), () -> {

                this.updateActive();
                this.updateLife();

                if (request.gameFinished()) {
                    this.battleLogText.setText("Battle finished! Returning to menu.");
                    final PauseTransition pause = new PauseTransition(Duration.seconds(4));
                    pause.setOnFinished(event -> {
                        GameSession.clearTeam();
                        SceneManager.setScene("/layouts/menu.fxml");
                        SceneManager.clearAllCache();
                    });
                    pause.play();
                } else if (request.isSomeoneKO()) {
                    handleKO(be.getCurrentOwn(TeamType.PLAYER),
                            be.getCurrentOwn(TeamType.ENEMY), request);
                } else {
                    this.battleLogText.setText(DEFAULT_LOG_STRING);
                }

                moveContainer.setDisable(false);
                battleButton.setDisable(false);
                this.forceMove();
            });
        });

        try {
            requestQueue.put(request);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Handles the visualization of moves in moveContainer.
     */
    private void showMoves() {
        if (pokemonButton.isVisible()) {
            ViewUtilities.showParent(moveContainer);
            ViewUtilities.hideParent(battleLog);
            ViewUtilities.hideParent(pokemonButton);
            ViewUtilities.hideParent(runButton);
            battleButtonText.setText("Back");
            this.setMoves();
        } else {
            ViewUtilities.showParent(battleLog);
            ViewUtilities.showParent(pokemonButton);
            ViewUtilities.showParent(runButton);
            ViewUtilities.hideParent(moveContainer);
            battleButtonText.setText("Battle");
        }

    }

    /**
     * Shows the moves name in moveContainer.
     */
    private void setMoves() {
        final var nameIterator = List.of(moveName1, moveName2, moveName3, moveName4).iterator();
        final Iterator<PersonalMove> moveIterator = be.getCurrentOwn(TeamType.PLAYER)
                .getMoveComponent()
                .getMoveSet().iterator();

        moveContainer.getChildren().forEach(moveNode -> {
            final Button moveButton = (Button) moveNode;

            if (moveIterator.hasNext()) {
                final PersonalMove move = moveIterator.next();
                moveButton.setDisable(false);
                moveButton.setVisible(true);

                moveButton.getStyleClass().clear();
                moveButton.getStyleClass()
                        .addAll(
                                "button",
                                "move-button",
                                move.getType()
                                        .displayAs()
                                        .toLowerCase(Locale.ROOT));
                nameIterator.next().setText(
                        move.getMaxPP() > 0
                                ? move.getDisplayName()
                                        .concat("  |  ")
                                        .concat(String.valueOf(move.currentPP()))
                                        .concat("/")
                                        .concat(String.valueOf(move.getMaxPP()))
                                : move.getDisplayName());
                moveButton.setId(move.getID());

                if (!move.isAvailable()) {
                    moveButton.setDisable(true);
                }
            } else {
                moveButton.setDisable(true);
                moveButton.setVisible(false);
            }
        });
    }

    /**
     * Used by a {@link Button} to open the menu.
     */
    @FXML
    public void openOptions() {
        optionsButtonWrapper.getChildren()
                .stream()
                .forEach(btn -> {
                    btn.setDisable(false);
                    btn.setVisible(true);
                });
        settingsIcon.setDisable(true);
        settingsIcon.setVisible(false);
    }

    /**
     * Used by a {@link Button} to save a game.
     */
    @FXML
    public void onSave() {
        if (LoadoutManager.saveGame(be)) {
            this.onRun();
        }
    }

    /**
     * Used by a {@link Button} to close the menu.
     */
    @FXML
    public void closeMenu() {
        optionsButtonWrapper.getChildren()
                .stream()
                .forEach(btn -> {
                    btn.setDisable(true);
                    btn.setVisible(false);
                });
        settingsIcon.setDisable(false);
        settingsIcon.setVisible(true);
    }

    /**
     * Used by a {@link Button} to open the Settings.
     */
    @FXML
    public void openSettings() {
        SceneManager.setScene("/layouts/settings.fxml");
    }

    private void handleKO(final Pokemon activePokemon, final Pokemon enemyPokemon, final WorkRequest request) {
        final PauseTransition pause = new PauseTransition(Duration.seconds(GameSession.getSpeed().timeSpan()));

        if (isKo(enemyPokemon) && isKo(activePokemon)) {
            this.battleLogText.setText("Both pokemons have been knocked out");
            pause.setOnFinished(event -> {
                request.resumeBackground();
                SceneManager.setSceneWithBE(SWITCH_SCENE_PATH, be);
            });

        } else if (isKo(enemyPokemon)) {
            this.battleLogText
                    .setText("The enemy's ".concat(enemyPokemon.getDisplayName()).concat(" has been knocked out!"));
            request.resumeBackground();
            pause.setOnFinished(event -> {

                this.battleLogText.setText(DEFAULT_LOG_STRING);
                this.updateActive();
                this.updateLife();
            });

        } else {
            this.battleLogText.setText("Your Pokemon has been knocked out");
            pause.setOnFinished(event -> {
                request.resumeBackground();
                forceSwich();
            });
        }
        pause.play();
    }

    /**
     * @param pokemon the pokemon you want to check.
     * @return if the {@link Pokemon} is KO.
     */
    private boolean isKo(final Pokemon pokemon) {
        return pokemon.getStatComponent().getHP() <= 0;
    }

    /**
     * @param logs       the list of logs that will be printed in the battle log.
     * @param onFinished the action to execute after all the logs have been written.
     */
    private void playBattleLogs(final List<String> logs, final Runnable onFinished) {
        if (logs == null || logs.isEmpty()) {
            onFinished.run();
            return;
        }

        final Queue<String> logQueue = new LinkedList<>(logs);

        final PauseTransition delay = new PauseTransition(
                Duration.seconds(GameSession.getSpeed().timeSpan()));
        delay.setOnFinished(e -> {
            if (!logQueue.isEmpty()) {
                battleLogText.setText(logQueue.poll());
                delay.playFromStart();
            } else {
                onFinished.run();
            }
        });

        ViewUtilities.showParent(battleLog);
        battleLogText.setText(logQueue.poll());
        delay.play();
    }
}
