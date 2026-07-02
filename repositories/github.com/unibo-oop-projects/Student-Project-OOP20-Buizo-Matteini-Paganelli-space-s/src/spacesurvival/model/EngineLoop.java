package spacesurvival.model;

import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import spacesurvival.controller.gui.ManagerControllerGUI;
import spacesurvival.controller.gui.ControllerGame;
import spacesurvival.controller.gui.ControllerSound;
import spacesurvival.model.collision.event.DeadEvent;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.Pair;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.model.worldevent.WorldEventListener;
import spacesurvival.utilities.path.SoundPath;
import spacesurvival.utilities.CommandAudioType;
import spacesurvival.utilities.CommandKey;
import spacesurvival.utilities.CommandType;
import spacesurvival.utilities.ThreadUtils;

/**
 * Main loop used to process input, update elements and render them to the view.
 */
public class EngineLoop extends Thread implements WorldEventListener {
    /**
     * Frames per second of the game.
     */
    public static final int FPS = 30;

    private final ManagerControllerGUI controlGUI;
    private final ControllerGame controlGame;
    private final ControllerSound controlSound;
    private final List<WorldEvent> eventQueue;

    /**
     * Construction for engine loop initializing the control gui, the control sound and the control game.
     */
    public EngineLoop() {
        this.controlGUI = new ManagerControllerGUI();
        this.eventQueue = new LinkedList<>();
        this.controlGame = this.controlGUI.getCtrlGame();
        this.controlSound = this.controlGUI.getCtrlSound();
    }

    /**
     * Init the game and starts all loops and listeners.
     */
    public void initGame() {
        this.controlGUI.initGUI();
        this.controlGUI.assignSoundLoop();
        this.controlGame.assignMovementListenerInShip();
        this.controlGame.setEventListenerInWorld(this);
        this.controlGame.assignWorld();
        this.controlGame.initHUD();
        this.controlGUI.getCurrentGUI().ifPresent(link -> this.controlSound.setSoundLoop(link));
        this.controlSound.setCmdAudioLoop(CommandAudioType.AUDIO_ON);
        this.controlGUI.startGUI();
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        long lastTime = System.currentTimeMillis();
        long current = 0L;
        while (true) {
            if (!this.controlGame.isGameOver()) {
                current = System.currentTimeMillis();

                if (this.controlGUI.isInGame()) {
                    if (!this.controlGUI.isInPause()) {
                        this.processInput();
                        this.renderMovement();
                        this.updateGame();
                        this.waitForNextFrame(current);
                        lastTime = current;
                        this.render();
                    }
                } else if (this.controlGUI.isInGame() || !this.controlGUI.isInPause()) {
                    waitForNextFrame(current);
                }
            } else {
                renderGameOver();
                while (this.controlGUI.isInGameOver()) {
                    waitForNextFrame(current);
                }
            }
        }
    }

    /**
     * Wait for the next frame to line up game events and states.
     * 
     * @param current
     */
    protected void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < FPS) {
            ThreadUtils.sleep(FPS - dt);
        }
    }

    /**
     * Updates all events and game states.
     */
    protected final void updateGame() {
        this.controlGame.startTimer();
        this.controlGame.updateStateWorld();
        this.checkEvents(); 
        this.checkGameObjectsDead();
        this.checkSoundEffects();
        this.assignTargetToEnemies();
        this.checkScore();
        this.checkLife();
        this.controlGame.updateHUD();
    }

    /**
     * Process user input.
     */
    private void processInput() {
        final Iterator<Pair<CommandKey, CommandType>> inputIterator = this.controlGame.getSpaceShipCommandList().iterator();
        while (inputIterator.hasNext()) {
            this.controlGame.executeOnShip(inputIterator.next().getX());
        }
        this.controlGame.clearSpaceShipCommandList();
    }

    /**
     * Check current game objects and their dead.
     */
    private void checkGameObjectsDead() {
        final World world = this.controlGame.getWorld();
        world.getMainObjects().forEach(mainObject -> {
            if (mainObject.isDead()) {
                eventQueue.add(new DeadEvent(mainObject));
                this.controlGame.incrScore(mainObject.getScore());
                this.controlGame.updateScore();
            }
        });
    }

    /**
     * Check all sound effects and and starts them.
     */
    protected void checkSoundEffects() {
        this.controlGame.getWorld().getSoundQueue().addAll(this.controlGame.getShip().getSoundQueue());
        this.controlGame.getWorld().getSoundQueue().forEach(this::playEffect);
        this.controlGame.getWorld().getSoundQueue().clear();
        this.controlGame.getShip().getSoundQueue().clear();
    }

    /**
     * Check the current score and its changing.
     */
    protected void checkScore() {
        final List<Integer> scoreUpdate = this.controlGame.getWorld().getQueueScore();
        scoreUpdate.forEach(this.controlGame::incrScore);
        scoreUpdate.clear();
    }

    /**
     * Check the current life and its changing.
     */
    protected void checkLife() {
        final List<Integer> listIncreaseLife = this.controlGame.getWorld().getQueueIncreaseLife();
        final List<Integer> listDecreaseLife = this.controlGame.getWorld().getQueueDecreaseLife();

        listIncreaseLife.forEach(this.controlGame::increaseLife);
        listDecreaseLife.forEach(this.controlGame::decreaseLife);

        if (!listDecreaseLife.isEmpty() || !listIncreaseLife.isEmpty()) {
            this.controlGame.updateNHeart();
        }
        listIncreaseLife.clear();
        listDecreaseLife.clear();
    }


    /**
     * Check events of the game notified to the world.
     */
    protected void checkEvents() {
        final World world = this.controlGame.getWorld();
        eventQueue.forEach(ev -> {
            ev.manage(world);
            this.controlGame.updateCountEnemies();
            this.controlGame.updateRoundState();
        });
        eventQueue.clear();
    }

    /**
     * Repaint world and all game objects graphic.
     */
    protected final void render() {
        this.controlGame.repaintWorld();
    }

    /**
     * Render the movement of all MoveableObject of the world.
     */
    private void renderMovement() {
        this.controlGame.getWorld().getMovableObjects().forEach(MoveableObject::move);
    }

    /**
     * Method called for assign targets to enemies.
     */
    public void assignTargetToEnemies() {
        this.controlGame.getWorld().getAllEnemies().forEach(enemy -> {
            final SpaceShipSingleton ship = this.controlGame.getShip();
            final AffineTransform shipTransform = new AffineTransform();
            shipTransform.setTransform(ship.getTransform());
            shipTransform.translate(ship.getWidth() / 2, ship.getHeight() / 2);
            final P2d shipPosition = new P2d(shipTransform.getTranslateX(), shipTransform.getTranslateY());
            enemy.setTargetPosition(shipPosition);
        });
    }

    /**
     * Render game over screen.
     */
    protected void renderGameOver() {
        this.controlGUI.endGame();
        playEffect(SoundPath.GAME_OVER);
    }

    /**
     * Notify the event to the world.
     * 
     * @param ev the event of type WorldEvent
     */
    public void notifyEvent(final WorldEvent ev) {
        eventQueue.add(ev);
    }

    /**
     * Play a sound from world.
     * 
     * @param soundPath the SoundPath of the sound to play.
     */
    private void playEffect(final SoundPath soundPath) {
        this.controlSound.getCallerAudioEffectFromSoundPath(soundPath).get().execute(CommandAudioType.RESET_TIMING);
        this.controlSound.getCallerAudioEffectFromSoundPath(soundPath).get().execute(CommandAudioType.AUDIO_ON);
    }

}

