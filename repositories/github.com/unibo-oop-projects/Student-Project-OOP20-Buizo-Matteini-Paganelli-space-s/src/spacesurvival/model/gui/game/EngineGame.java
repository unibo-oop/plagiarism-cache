package spacesurvival.model.gui.game;

import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.model.worldevent.WorldEventListener;
import spacesurvival.controller.collision.CollisionController;
import spacesurvival.model.World;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.gameobject.LifeUtils;
import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EngineGame implements Engine {
    /**
     * Rect representing the full screen.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_FULLSCREEN;

    private final LinkActionGUI id;
    private final LinkActionGUI idPause;
    private final World world;
    private final EngineHUD hud;
    private Visibility visibility;

    public EngineGame() {
        this.id = LinkActionGUI.LINK_GAME;
        this.idPause = LinkActionGUI.LINK_PAUSE;
        this.world = new World(RECTANGLE);
        this.hud = new EngineHUD();
        this.visibility = Visibility.HIDDEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return this.id;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getRectangle() {
        return RECTANGLE;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibility(final Visibility state) {
        this.visibility = state;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return this.visibility.isVisible();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<LinkActionGUI> getLinks() {
        return List.of(this.idPause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollisionController(final CollisionController collisionController) {
        this.world.setCollisionController(collisionController);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPauseAnimationAllObject(final boolean isPause) {
        this.world.setPauseAnimationAllObject(isPause);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTimer() {
        return this.hud.getTimer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        this.hud.startTimer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        this.hud.stopTimer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.hud.getScore();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getRound() {
        return this.hud.getRound();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountEnemies() {
        return this.world.getCountEnemies();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.hud.getLives();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLifeShip() {
        return this.world.getLifeShip();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLifeBoss() {
        return this.world.getLifeBoss();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void incrScore(final int score) {
        this.hud.incrScore(score);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void incrRound() {
        this.hud.incrRound();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLifeShip(final int damage) {
        this.world.getShip().decreaseLife(damage);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLives() {
        this.hud.decreaseLives();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseLives(final int amount) {
        this.hud.increaseLives(amount);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setLifeShip(final int life) {
        this.world.getShip().setLife(life);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void resetLifeShip() {
        this.world.getShip().setLife(LifeUtils.SPACESHIP_LIFE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.getLives() <= 0 && this.getLifeShip() <= 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void restartGame() {
        this.resetLifeShip();
        this.hud.resetLives();
        this.hud.resetTimer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getAllObjects() {
        return this.world.getAllObjects();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public SpaceShipSingleton getShip() {
        return this.world.getShip();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setEventListenerInWorld(final WorldEventListener worldEventListener) {
        this.world.setEventListener(worldEventListener);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStateWorld() {
        this.world.updateState();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkin(final SkinSpaceShip currentSkin) {
        this.world.setSkin(currentSkin);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<FireableObject> getBoss() {
        return this.world.getBoss();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Weapon getWeaponShip() {
        return this.getShip().getWeapon();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AmmoType getAmmoTypeShip() {
        return this.getWeaponShip().getAmmoType();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void assignBulletShipInHUD() {
        this.hud.setAmmoType(this.getAmmoTypeShip());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AmmoType getAmmoTypeHUD() {
        return this.hud.getAmmoType();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAmmoType(final AmmoType ammoType) {
        this.hud.setAmmoType(ammoType);
    }

}
