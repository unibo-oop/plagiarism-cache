package test.matteini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.shootinglogic.implementation.NoFiringLogic;
import spacesurvival.model.gameobject.fireable.weapon.Magazine;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.moveable.movement.implementation.FixedMovementLogic;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.gameobject.BulletUtils;
import spacesurvival.utilities.path.animation.AnimationShip;

public class TestWeapon {
  //impact damage, set status, FACTORY

    private static final P2d STARTING_POSITION = new P2d(1, 1);
    private static final int BULLETS_SHOOTED = 100;
    private static final int SHOOTS = 20;
    private static final int REMAINING_MUNITIONS = 5;

    private final FireableObject fireableObject = new FireableObject(new EngineImage(ScaleOf.GAME_OBJECT, Screen.WIDTH_FULLSCREEN, AnimationShip.NORMAL0),
            STARTING_POSITION, new RectBoundingBox(), null, new V2d(10, 10), 0, new FixedMovementLogic(),
            500, 50, 70, null, new Weapon(), new NoFiringLogic()) {
        @Override
        public void collided(final World world, final WorldEvent worldEvent) {
        }
    };

    @Test
    public void testBulletShooted() {
        final Weapon weapon = new Weapon();
        assertNull(weapon.getOwner());
        weapon.setOwner(fireableObject);
        assertNotNull(weapon.getOwner());
        for (int i = 0; i < BULLETS_SHOOTED; i++) {
            weapon.shoot();
        }
        assertEquals(weapon.getShootedBullets().size(), BULLETS_SHOOTED);
    }

    @Test
    public void testAmmoType() {
        final Weapon weapon = new Weapon(AmmoType.NORMAL, fireableObject);
        assertEquals(weapon.getAmmoType(), AmmoType.NORMAL);

        weapon.setAmmoType(AmmoType.ICE);
        assertEquals(weapon.getAmmoType(), AmmoType.ICE);
        weapon.setAmmoType(AmmoType.ELECTRIC);
        assertEquals(weapon.getAmmoType(), AmmoType.ELECTRIC);
        weapon.setAmmoType(AmmoType.NORMAL);
        assertEquals(weapon.getAmmoType(), AmmoType.NORMAL);
    }

    @Test
    public void testMagazine() {
        final Weapon weapon = new Weapon(AmmoType.NORMAL, fireableObject);
        assertEquals(weapon.getMagazine(), Magazine.UNLIMITED);

        weapon.setAmmoType(AmmoType.FIRE);
        assertEquals(weapon.getMagazine(), Magazine.LIMITED);
        weapon.setAmmoType(AmmoType.ELECTRIC);
        assertEquals(weapon.getMagazine(), Magazine.LIMITED);
        weapon.setAmmoType(AmmoType.ICE);
        assertEquals(weapon.getMagazine(), Magazine.LIMITED);
    }

    @Test
    public void testMunitions() {
        final Weapon weapon = new Weapon(AmmoType.NORMAL, fireableObject);
        for (int i = 0; i < SHOOTS; i++) {
            weapon.shoot();
        }
        assertEquals(weapon.getMunitions(), BulletUtils.INFINITY);
        weapon.setAmmoType(AmmoType.FIRE);
        for (int i = 0; i < SHOOTS; i++) {
            weapon.shoot();
        }
        assertEquals(weapon.getMunitions(), REMAINING_MUNITIONS);
    }
}
