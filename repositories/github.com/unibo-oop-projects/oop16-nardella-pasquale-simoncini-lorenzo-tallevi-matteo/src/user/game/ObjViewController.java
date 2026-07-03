package user.game;

import user.game.ships.ObjShip;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents an object that controls the view.
 */
public class ObjViewController extends GameObject {

    private static final double DEFAULT_SCALE = 1.5;
    private static final double MAX_SCALE = 2.5;
    private static final double SIGHT_RADIUS = 350;
    private static final int VIEW_WIDENING_FACTOR = 20;
    private static final int SHAKE_MAX = 100;

    private ObjShip player;
    private GameObject playerTarget;
    private double distance;
    private double actualScale = MAX_SCALE;
    private double desiredScale = MAX_SCALE;
    private int shake;

    @Override
    public void create() {
        z().viewSetScale(MAX_SCALE);
        shake = 0;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        this.followPlayer();
        if (shake > 0) {
            shake = z().max(shake - 2, 0);
        }
    }

    @Override
    public void draw() {
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    /**
     * This method sets the player field to a valid instance of ObjPlayerShip.
     * 
     * @param player
     *            the instance of player to set
     */
    public void setTarget(final ObjShip player) {
        this.player = player;
    }

    /**
     * This method sets the shake that applies to the screen.
     * 
     * @param shake
     *            the shake's value to set
     */
    public void setShake(final int shake) {
        this.shake = z().clamp(shake, 0, SHAKE_MAX);
    }

    private void followPlayer() {
        if (z().instanceExists(player)) {
            z().viewSetPosition(this.player.getX() + z().randomRange(-shake, shake),
                    this.player.getY() + z().randomRange(-shake, shake));
            this.playerTarget = this.player.getTarget();
            if (z().instanceExists(this.playerTarget)) {
                this.distance = z().pointDistance(this.player.getX(), this.player.getY(), this.playerTarget.getX(),
                        this.playerTarget.getY());
                if (this.distance > SIGHT_RADIUS) {
                    this.desiredScale = z().max(this.distance / SIGHT_RADIUS, DEFAULT_SCALE);
                } else {
                    this.desiredScale = DEFAULT_SCALE;
                }
                this.actualScale += (this.desiredScale - this.actualScale) / VIEW_WIDENING_FACTOR;
                z().viewSetScale(z().min(this.actualScale, MAX_SCALE));
            }
        }
    }
}
