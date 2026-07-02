package it.unibo.model.launchedgame.impl;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.api.StateOfLaunchedGame;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.world.impl.World;

/**
 * Implementation of the {@link LaunchedGame} interface.
 */
public class LaunchedGameImpl implements LaunchedGame {

    /**
     * The current state of the launched game, which determines the behavior of the
     * game loop.
     */
    private StateOfLaunchedGame state;

    /**
     * The world instance for the launched game.
     */
    private Optional<World> world;

    /**
     * A queue to hold running commands for the game.
     */
    private final Queue<RunningCommand> commands;

    /**
     * The menu for the launched game.
     */
    private final Menu menu;

    /**
     * Flag to indicate if the game is currently running.
     */
    private boolean running;

    /**
     * Constructs a new LaunchedGameImpl with the specified menu controller.
     * Initializes the command queue and sets the initial state.
     * 
     * @param menu the menu controller to be used in the launched game
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Menu is necessary because the state of the launched "
            + "game needs to interact with it.")
    public LaunchedGameImpl(final Menu menu) {
        this.commands = new ArrayBlockingQueue<>(100);
        this.running = false;
        this.menu = menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final StateOfLaunchedGame state) {
        this.state = state;
        state.onEnter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateOfLaunchedGame getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<World> getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorld(final World world) {
        this.world = Optional.of(world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommand(final RunningCommand command) {
        commands.add(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RunningCommand> pollCommand() {
        return Optional.ofNullable(commands.poll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return this.running;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRunning(final boolean running) {
        this.running = running;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Menu is necessary because the state of the launched "
            + "game needs to interact with it.")
    @Override
    public Menu getMenu() {
        return this.menu;
    }

}
