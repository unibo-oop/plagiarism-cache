package net.pokemonbt.controller.battle;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;
import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.utility.WorkRequest;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.model.battle.BattleEvent;
import net.pokemonbt.model.move.Move;
import javafx.application.Platform;

/**
 * Main thread class for battle. Handles communication between View and Model
 * during the battle state.
 */
public final class BattleTurnController implements Runnable {
    private final AbstractEnemyAI enemy;
    private final BlockingQueue<WorkRequest> requestQueue;
    private final BattleEnvironment be;

    /**
     * Constructor for {@link BattleTurnController}.
     * 
     * @param requestQueue the {@link WorkRequest} object used for synchronization.
     * @param battleEnv    the battle enviroment of the current battle.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "requestQueue is a Synchronization oriented object,"
            + "it has to be changed by multiple Threads and respective classes")
    public BattleTurnController(final BlockingQueue<WorkRequest> requestQueue, final BattleEnvironment battleEnv) {

        try {
            // Create the instance of the class selected in the settings.
            this.be = battleEnv;
            enemy = (AbstractEnemyAI) Class.forName(
                    AbstractEnemyAI.class.getPackageName()
                            .concat(".")
                            .concat(GameSession.getCurrentEnemyDifficulty())
                            .concat("AI"))
                    .getConstructor(BattleEnvironment.class)
                    .newInstance(this.be);
        } catch (final ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }

        this.requestQueue = requestQueue;
    }

    /**
     * Runs the battle Thread.
     */
    @Override
    public void run() {
        boolean gameIsgaming = true;
        while (gameIsgaming) {

            try {
                final String currentActive = be.getCurrentOwn(TeamType.PLAYER)
                        .getDisplayName();
                final WorkRequest request = requestQueue.take();

                if (request.isBattle()) {
                    this.handleBattle(request.getMove(), request);
                } else {
                    if (!currentActive
                            .equals(be.getCurrentOwn(TeamType.PLAYER).getDisplayName())) {
                        request.addLog("You withdrew your " + currentActive + ", Go "
                                + be.getCurrentOwn(TeamType.PLAYER).getDisplayName()
                                + "!");
                        this.afterSwitch(request);
                    }
                }

                if (this.someoneKo()) {
                    if (battleFinished()) {
                        request.gameIsFinished();
                        Thread.currentThread().interrupt();
                        gameIsgaming = false;
                    }
                    request.setKo(true);

                    final WorkRequest finalRequest = request;
                    Platform.runLater(finalRequest::complete);

                    request.waitForUI();

                    if (!battleFinished() && be.getCurrentOwn(TeamType.ENEMY)
                            .getStatComponent().getHP() <= 0) {
                        be.swapPokemon(TeamType.ENEMY, enemy.swapPokemon());

                        Platform.runLater(() -> {
                        });
                    }

                    continue;
                }

                final WorkRequest finalRequest = request;
                Platform.runLater(finalRequest::complete);

            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                gameIsgaming = false;
            }
        }
    }

    /**
     * Handles the damage calculation.
     * 
     * @param selectedMove The {@link Move} selected by the user.
     * @param request      The {@link WorkRequest} used for synchronization.
     */
    private void handleBattle(final Move selectedMove, final WorkRequest request) {
        final Pokemon activePokemon = be.getCurrentOwn(TeamType.PLAYER);
        Pokemon enemyPokemon = be.getCurrentOwn(TeamType.ENEMY);
        boolean enemyMissed = true;
        boolean playerMissed = true;
        triggerCondition(TeamType.ENEMY, BattleEvent.TURN_START);
        triggerCondition(TeamType.PLAYER, BattleEvent.TURN_START);

        if (enemy.turnChoice()) {
            final Optional<Move> enemyMove = enemy.chooseMove();

            if (calculatePriority(selectedMove, enemyMove.get())) {

                this.composeBattleLog(TeamType.PLAYER, activePokemon.getDisplayName(), selectedMove, request);
                playerMissed = activePokemon.getMoveComponent().useMove(selectedMove.getID(), enemyPokemon);

                triggerCondition(TeamType.ENEMY, BattleEvent.AFTER_DAMAGE);
                triggerCondition(TeamType.PLAYER, BattleEvent.BEFORE_DAMAGE);
                if (!someoneKo()) {

                    this.composeBattleLog(TeamType.ENEMY, enemyPokemon.getDisplayName(), enemyMove.get(), request);
                    enemyMissed = enemyPokemon.getMoveComponent().useMove(enemyMove.get().getID(), activePokemon);
                    triggerCondition(TeamType.PLAYER, BattleEvent.AFTER_DAMAGE);
                    triggerCondition(TeamType.ENEMY, BattleEvent.BEFORE_DAMAGE);
                }

            } else {

                this.composeBattleLog(TeamType.ENEMY, enemyPokemon.getDisplayName(), enemyMove.get(), request);
                enemyMissed = enemyPokemon.getMoveComponent().useMove(enemyMove.get().getID(), activePokemon);
                triggerCondition(TeamType.PLAYER, BattleEvent.AFTER_DAMAGE);
                    triggerCondition(TeamType.ENEMY, BattleEvent.BEFORE_DAMAGE);
                if (!someoneKo()) {
                    this.composeBattleLog(TeamType.PLAYER, activePokemon.getDisplayName(), selectedMove, request);
                    playerMissed = activePokemon.getMoveComponent().useMove(selectedMove.getID(), enemyPokemon);
                    triggerCondition(TeamType.ENEMY, BattleEvent.AFTER_DAMAGE);
                    triggerCondition(TeamType.PLAYER, BattleEvent.BEFORE_DAMAGE);
                    triggerCondition(TeamType.ENEMY, BattleEvent.BEFORE_DAMAGE);
                }

            }

        } else {
            request.addLog("The enemy withdraws their pokemon!");
            be.swapPokemon(TeamType.ENEMY, enemy.swapPokemon());
            enemyPokemon = be.getCurrentOwn(TeamType.ENEMY);
            this.composeBattleLog(TeamType.PLAYER, activePokemon.getDisplayName(), selectedMove, request);
            playerMissed = activePokemon.getMoveComponent().useMove(selectedMove.getID(), enemyPokemon);

            triggerCondition(TeamType.ENEMY, BattleEvent.AFTER_DAMAGE);
            triggerCondition(TeamType.PLAYER, BattleEvent.AFTER_DAMAGE);
            triggerCondition(TeamType.PLAYER, BattleEvent.BEFORE_DAMAGE);
        }

        triggerCondition(TeamType.PLAYER, BattleEvent.TURN_END);
        triggerCondition(TeamType.ENEMY, BattleEvent.TURN_END);

        if (!enemyMissed) {
            request.addLog(enemyPokemon.getDisplayName().concat(" missed his attack!"));
        }
        if (!playerMissed) {
            request.addLog(activePokemon.getDisplayName().concat(" missed his attack!"));
        }
        if (activePokemon.getConditionComponent().hasPermanentCondition()) {
            request.addLog("Your pokemon is ".concat(activePokemon.getConditionComponent()
            .getPermanentCondition().getName()));
        }
        if (enemyPokemon.getConditionComponent().hasPermanentCondition()) {
            request.addLog("The enemy pokemon is ".concat(enemyPokemon.getConditionComponent()
            .getPermanentCondition().getName()));
        }
        this.killPokemon();

    }

    /**
     * Handles what happens after the player switched pokemon.
     * 
     * @param request The {@link WorkRequest} used for synchronization.
     */
    private void afterSwitch(final WorkRequest request) {

        if (enemy.turnChoice()) {
            final Move enemyMove = enemy.chooseMove().get();
            this.composeBattleLog(TeamType.ENEMY, be.getCurrentOwn(TeamType.ENEMY).getDisplayName(), enemyMove,
                    request);
            be.getCurrentOwn(TeamType.ENEMY).getMoveComponent()
                    .useMove(enemyMove.getID(), be.getCurrentOwn(TeamType.PLAYER));
        } else {
            request.addLog("The enemy withdraw their pokemon!");
            be.swapPokemon(TeamType.ENEMY, enemy.swapPokemon());
        }

    }

    /**
     * Flaggs eventual KO pokemon as defeated.
     */
    private void killPokemon() {
        if (be.getCurrentOwn(TeamType.PLAYER).getStatComponent().getHP() <= 0) {
            be.flagPokemonAsDead(TeamType.PLAYER,
                    be.getCurrentOwn(TeamType.PLAYER).getID());
        }
        if (be.getCurrentOwn(TeamType.ENEMY).getStatComponent().getHP() <= 0) {
            be.flagPokemonAsDead(TeamType.ENEMY,
                    be.getCurrentOwn(TeamType.ENEMY).getID());
        }
    }

    /**
     * @return true if at least a pokemon was defeated
     */
    private boolean someoneKo() {
        return be.getCurrentOwn(TeamType.ENEMY).getStatComponent().getHP() <= 0
                || be.getCurrentOwn(TeamType.PLAYER).getStatComponent().getHP() <= 0;
    }

    /**
     * @return true if the battle is finished.
     */
    private boolean battleFinished() {
        return be.getAlivePokemons(TeamType.PLAYER).isEmpty()
                || be.getAlivePokemons(TeamType.ENEMY).isEmpty();
    }

    /**
     * @param playerMove the {@link Move} choosed by the player.
     * @param enemyMove  the {@link Move} choosed by the enemy.
     * 
     * @return if the player goes first.
     */
    private boolean calculatePriority(final Move playerMove, final Move enemyMove) {
        final Pokemon playerPokemon = be.getCurrentOwn(TeamType.PLAYER);
        final Pokemon enemyPokemon = be.getCurrentOther(TeamType.PLAYER);
        final int priorityDifference = playerMove.getPriority() - enemyMove.getPriority();
        final int speedDifference = playerPokemon.getStatComponent().getStat(PokeStatType.SPE)
                - enemyPokemon.getStatComponent().getStat(PokeStatType.SPE);

        if (priorityDifference != 0) {
            return priorityDifference > 0;
        }

        if (speedDifference != 0) {
            return speedDifference > 0;
        }

        return RandomUtility.check(0.50F);
    }

    /**
     * @param user the pokemon on which the conditions need to be triggered 
     * @param moment the BattleEvent in which it needs to happen
     */
    private void triggerCondition(final TeamType user, final BattleEvent moment) {
        if (user == TeamType.ENEMY) {
            be.getCurrentOwn(user).getConditionComponent().triggerCondition(moment, be.getCurrentOther(user));
        } else if (user == TeamType.PLAYER) {
            be.getCurrentOwn(user).getConditionComponent().triggerCondition(moment, be.getCurrentOther(user));
        }
    }

    private void composeBattleLog(final TeamType owner, final String subject, final Move move,
            final WorkRequest request) {
        if (owner == TeamType.PLAYER) {
            request.addLog(subject + " used " + move.getDisplayName());
        } else {
            request.addLog("The enemy's " + subject + " used " + move.getDisplayName());
        }
    }
}
