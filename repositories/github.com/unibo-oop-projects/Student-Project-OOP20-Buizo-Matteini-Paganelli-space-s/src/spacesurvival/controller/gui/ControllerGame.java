package spacesurvival.controller.gui;

import spacesurvival.controller.CallerCommandShip;
import spacesurvival.controller.collision.CollisionController;
import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.game.Engine;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.model.worldevent.WorldEventListener;
import spacesurvival.model.Controller;
import spacesurvival.model.World;
import spacesurvival.model.commandship.MovementKeyListener;
import spacesurvival.model.common.Pair;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.CommandKey;
import spacesurvival.utilities.CommandType;
import spacesurvival.utilities.RoundUtils;
import spacesurvival.utilities.gameobject.LifeUtils;
import spacesurvival.view.GUI;
import spacesurvival.view.game.GUIGame;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * The controller of the game, impeìlementing the Controller and the Controller GUI.
 *
 */
public class ControllerGame implements ControllerGUI, Controller {
    private final Engine engine;
    private final GUIGame gui;
    private final SwitchGUI switchGUI;
    private final MovementKeyListener keyListener;
    private final CollisionController controlCollision;
    private final CallerCommandShip callerCommandShip;

    public ControllerGame(final Engine engine, final GUIGame gui) {
        this.engine = engine;
        this.gui = gui;
        this.switchGUI = new SwitchGUI(this.engine, this.gui);
        this.keyListener = new MovementKeyListener();
        this.controlCollision = new CollisionController();
        this.engine.setCollisionController(this.controlCollision);
        this.callerCommandShip = new CallerCommandShip(getShip());

        this.switchGUI.turn(this.engine.getVisibility());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignLinks() {
        this.gui.setMainAction(this.engine.getMainLink());
        this.gui.setBtnActions(this.engine.getMainLink(), this.engine.getLinks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void assignTexts() {
        this.gui.setMaxLifeBoss(LifeUtils.BOSS_LIFE);
        this.gui.setMaxLifeShip(LifeUtils.SPACESHIP_LIFE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void assignBounds() {
        this.gui.setBoundsGame(this.engine.getRectangle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LinkActionGUI getMainLink() {
        return this.engine.getMainLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GUI getGUI() {
        return this.gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EngineGUI getEngine() {
        return this.engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallerCommandShip getCallerShip() {
        return this.callerCommandShip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeOnShip(final CommandKey cmd) {
        this.callerCommandShip.execute(cmd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isVisibility() {
        return this.engine.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void turn(final Visibility visibility) {
        this.switchGUI.turn(visibility);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void changeVisibility() {
        this.switchGUI.changeVisibility();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void closeGUI() {
        this.gui.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionController getControllerCollision() {
        return this.controlCollision;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPauseAnimationAllObject(final boolean isPause) {
        this.engine.setPauseAnimationAllObject(isPause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore() {
        this.gui.setScore(this.engine.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRound() {
        this.gui.setRound(this.engine.getRound());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCountEnemies() {
        this.gui.setNEnemies(this.engine.getCountEnemies());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimer() {
        this.gui.setTextTimer(this.engine.getTimer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBulletHUD() {
        if (this.engine.getAmmoTypeHUD() != this.engine.getAmmoTypeShip()) {
            this.engine.assignBulletShipInHUD();
            this.gui.setBulletHUD(this.engine.getAmmoTypeHUD());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNHeart() {
        this.gui.setNHeart(this.engine.getLives());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initHUD() {
        this.updateScore();
        this.updateRound();
        this.updateCountEnemies();
        this.updateTimer();
        this.updateBulletHUD();
        this.checkIfPresentBoss();
        this.updateNHeart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHUD() {
        this.updateTimer();
        this.updateLifeShip();
        this.updateLifeBoss();
        this.updateBulletHUD();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLifeShip() {
        this.engine.setLifeShip(Math.max(this.engine.getLifeShip(), 0));
        this.gui.setLifeShip(this.engine.getLifeShip());
    }

    /**
     * {@inheritDoc}
     */
    public void checkIfPresentBoss() {
        this.gui.setVisibleLifeBarBoss(this.engine.getBoss().isPresent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLifeBoss() {
        this.engine.getBoss().ifPresent(boss -> this.gui.setLifeBoss(boss.getLife()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRoundState() {
        if (this.engine.getCountEnemies() == 0) {
            this.engine.incrRound();
            this.createNewEntities();
            this.gui.setVisibleLifeBarBoss(this.engine.getWorld().getBoss().isPresent());
            this.updateRound();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleLifeBarBoss(final boolean visible) {
        this.gui.setVisibleLifeBarBoss(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void createNewEntities() {
        int asteroidsNumber = this.engine.getRound() * RoundUtils.ASTEROID_INCREMENT_PER_ROUND;
        if (asteroidsNumber > RoundUtils.MAX_ASTEROID_PER_ROUND) {
            asteroidsNumber = RoundUtils.MAX_ASTEROID_PER_ROUND;
        }
        for (int i = 0; i < asteroidsNumber; i++) {
            this.getWorld().addAsteroid();
        }

        int chaseEnemiesNumber = this.engine.getRound() * RoundUtils.CHASE_ENEMY_INCREMENT_PER_ROUND;
        if (chaseEnemiesNumber > RoundUtils.MAX_CHASE_ENEMY_PER_ROUND) {
            chaseEnemiesNumber = RoundUtils.MAX_CHASE_ENEMY_PER_ROUND;
        }
        for (int i = 0; i < chaseEnemiesNumber; i++) {
            this.getWorld().addChaseEnemy();
        }

        if (this.engine.getRound() > RoundUtils.FIRE_ENEMY_MINIMUM_ROUND) {
            int fireEnemiesNumber = this.engine.getRound() * RoundUtils.FIRE_ENEMY_INCREMENT_PER_ROUND
                    - RoundUtils.FIRE_ENEMY_MINIMUM_ROUND;
            if (fireEnemiesNumber > RoundUtils.MAX_FIRE_ENEMY_PER_ROUND) {
                fireEnemiesNumber = RoundUtils.MAX_FIRE_ENEMY_PER_ROUND;
            }
            for (int i = 0; i < fireEnemiesNumber; i++) {
                this.getWorld().addFireEnemy();
            }
        }

        if (this.engine.getRound() % RoundUtils.ROUND_PER_BOSS == 0) {
            this.getWorld().addBoss();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void assignWorld() {
        this.gui.setWorld(this.engine.getWorld());
        this.engine.getWorld().getBoss().ifPresent(boss -> this.setVisibleLifeBarBoss(true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void startTimer() {
        this.engine.startTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stopTimer() {
        this.engine.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final World getWorld() {
        return this.engine.getWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final SpaceShipSingleton getShip() {
        return this.engine.getShip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setEventListenerInWorld(final WorldEventListener worldEventListener) {
        this.engine.setEventListenerInWorld(worldEventListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementKeyListener getMovementKeyListener() {
        return this.keyListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<CommandKey, CommandType>> getSpaceShipCommandList() {
        return this.keyListener.getSpaceShipCommandList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearSpaceShipCommandList() {
        this.keyListener.clearSpaceShipCommandList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void assignMovementListenerInShip() {
        this.addKeyListenerShip(this.getMovementKeyListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isGameOver() {
        return this.engine.isGameOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void restartGame() {
        this.engine.restartGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void decreaseLife(final int damage) {
        if (this.damageOverFlow(damage) && this.hasLivesShip()) {
            this.engine.resetLifeShip();
            this.engine.decreaseLives();
        } else {
            this.engine.decreaseLifeShip(damage);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void increaseLife(final int healAmount) {
        final int totalLife = this.getShip().getLife() + healAmount;
        int newLife = totalLife % LifeUtils.SPACESHIP_LIFE;
        int newLives = totalLife / LifeUtils.SPACESHIP_LIFE;
        if (newLife == 0) {
            newLife = LifeUtils.SPACESHIP_LIFE;
            newLives--;
        }
        this.getShip().setLife(newLife);
        increaseLives(newLives);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void increaseLives(final int amount) {
        this.engine.increaseLives(amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean damageOverFlow(final int damage) {
        return this.engine.getLifeShip() - damage <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLivesShip() {
        return this.engine.getLives() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void repaintWorld() {
        this.gui.repaintGameObjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void incrScore(final int score) {
        this.engine.incrScore(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStateWorld() {
        this.engine.updateStateWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKeyListenerShip(final KeyListener keyListener) {
        this.gui.addKeyListenerSpaceShip(keyListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkin(final SkinSpaceShip currentSkin) {
        this.engine.setSkin(currentSkin);
    }

    /**
     * Set the name of the player.
     * @param namePlayer
     */
    public void setNamePlayer(final String namePlayer) {
        this.gui.setNamePlayer(namePlayer);
    }

}
