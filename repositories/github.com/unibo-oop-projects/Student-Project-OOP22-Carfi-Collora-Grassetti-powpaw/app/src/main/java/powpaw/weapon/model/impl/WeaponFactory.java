package powpaw.weapon.model.impl;

import java.util.Random;

import javafx.geometry.Point2D;
import powpaw.weapon.model.api.Weapon;
import powpaw.world.controller.ScreenController;

/**
 * Class creates different types of weapons with specific attack values based on
 * the
 * given ID.
 * 
 * @author Giacomo Grassetti
 */
public final class WeaponFactory {

    private static final double START_TERRAIN = 300;
    private static final double END_TERRAIN = ScreenController.SIZE_HD_W - 300;
    private static final double ATK_SWORD = 0.25;
    private static final double ATK_HAMMER = 0.4;

    private WeaponFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Static method that creates a weapon with a random position and type based on
     * the given id.
     * 
     * @param id The id parameter is an integer value that represents the type of
     *           weapon to be created (0 = sowrd, 1 = hammer)
     * 
     * @return Weapon object.
     */
    public static Weapon createWeapon(final int id) {
        final Random rand = new Random();
        final Point2D position = new Point2D(rand.nextDouble(START_TERRAIN, END_TERRAIN), 200);
        Weapon weapon = new WeaponImpl(position, id);
        switch (id) {
            case 0:
                weapon = createSword(position, id);
                break;
            default:
                weapon = createHammer(position, id);
                break;
        }
        return weapon;
    }

    private static Weapon createSword(final Point2D pos, final int id) {
        final Weapon sword = new WeaponImpl(pos, id);
        sword.setAttack(ATK_SWORD);
        return sword;
    }

    private static Weapon createHammer(final Point2D pos, final int id) {
        final Weapon hammer = new WeaponImpl(pos, id);
        hammer.setAttack(ATK_HAMMER);
        return hammer;
    }

}
