package powpaw.weapon.model.impl;

import javafx.geometry.Point2D;
import powpaw.map.model.impl.TransitionImpl;
import powpaw.player.model.api.PlayerStats;
import powpaw.weapon.model.api.Weapon;
import powpaw.weapon.model.api.WeaponHitbox;
import powpaw.world.controller.ScreenController;

/**
 * Class that implements the Weapon interface and defines the properties and
 * methods of a
 * weapon object in a game.
 * 
 * @author Giacomo Grassetti
 */
public final class WeaponImpl implements Weapon {

    /**
     * Width of the weapon.
     */
    public static final double WIDTH = ScreenController.SIZE_HD_W / 50;
    /**
     * Height of the weapon.
     */
    public static final double HEIGHT = ScreenController.SIZE_HD_H / 30;
    /**
     * Max durability of the weapon.
     */
    public static final int MAX_DURABILITY = 10;

    private final WeaponHitbox hitbox;
    private Point2D position;
    private double attack;
    private boolean isVisible = true;
    private final int id;
    private int durability = MAX_DURABILITY;
    private boolean isPicked;

    private final TransitionImpl transition = new TransitionImpl();

    /**
     * Constructor for the WeaponImpl class.
     * 
     * @param position
     * @param id
     */
    public WeaponImpl(final Point2D position, final int id) {
        this.position = position;
        this.hitbox = new WeaponHitboxImpl(position, WIDTH, HEIGHT);
        this.id = id;
    }

    @Override
    public WeaponHitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setAttack(final double attack) {
        this.attack = attack;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void decrementDurability() {
        this.durability--;
    }

    @Override
    public void resetDurability() {
        this.durability = MAX_DURABILITY;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isPicked() {
        return isPicked;
    }

    @Override
    public void setPicked(final boolean isPicked) {
        this.isPicked = isPicked;
    }

    @Override
    public void addAttack(final PlayerStats ps) {
        final double oldAttack = ps.getAttack();
        ps.setAttack(oldAttack + this.attack);
        if (this.durability == 0) {
            ps.setAttack(oldAttack);
        }
    }

    @Override
    public void setVisible(final boolean b) {
        this.isVisible = b;
        this.hitbox.getShape().setVisible(b);
    }

    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @Override
    public void update() {
        this.position = transition.fallTransition(position);
        hitbox.updateCenter(position);
    }

}
