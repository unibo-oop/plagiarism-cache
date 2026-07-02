package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;
import zombieversity.model.entities.weapon.Attack;
import zombieversity.model.entities.weapon.AttackManager;
import zombieversity.model.entities.weapon.AttackManagerImpl;
import zombieversity.model.entities.weapon.Bullet;
import zombieversity.model.entities.weapon.KnifeAttack;
import zombieversity.model.entities.weapon.LongRangeWeapon;
import zombieversity.model.entities.weapon.ShortRangeWeapon;
import zombieversity.model.entities.weapon.WeaponFactory;
import zombieversity.model.entities.weapon.WeaponFactory.WeaponName;
import zombieversity.model.entities.zombie.Zombie;
import zombieversity.model.entities.zombie.ZombieModel;
import zombieversity.model.entities.zombie.ZombieModelImpl;

/**
 * Tests for weapons and their attacks.
 */
public class WeaponTest {

    private static final int GUN_MAGAZINE_SIZE = 10;
    private static final long MAX_TIME_KNIFE_ATTACK = 1500;

    private final Player player;
    private final AttackManager manager;
    private final ZombieModel zombieModel;
    private final Set<Point2D> spawnPoint;
    private final Point2D position;
    private final Random rnd;

    /**
     * Initializes common fields to the tests.
     */
    public WeaponTest() {
        this.rnd = new Random();
        final Set<BoundingBox> obstacles = new HashSet<>();
        final Point2D zombiePos = this.getRandomPoint();
        this.position = new Point2D(1, 1);
        this.spawnPoint = new HashSet<>();
        this.spawnPoint.add(zombiePos);
        this.player = new PlayerImpl();
        this.zombieModel = new ZombieModelImpl();
        this.manager = new AttackManagerImpl(zombieModel);
        this.manager.setObstacles(obstacles);
        this.zombieModel.setObstacles(obstacles);
        this.zombieModel.setPlayer(this.player);
        this.zombieModel.setSpawnPoints(spawnPoint);
    }

    /**
     * Tests for the global management of the attacks through AttackManager.
     */
    @Test
    public void testAttackManager() {
        Attack a;
        int active = 0;
        int ended = 0;
        assertTrue(Integer.valueOf(this.manager.getAttackActive().size()).equals(active));
        assertTrue(Integer.valueOf(this.manager.getAttacksEnded().size()).equals(ended));

        a = new Bullet(this.getRandomPoint(), this.getRandomPoint(), 0);
        this.manager.addAttack(a);
        active++;
        assertTrue(Integer.valueOf(this.manager.getAttackActive().size()).equals(active));

        a = new KnifeAttack(this.getRandomPoint(), this.getRandomPoint(), 0);
        this.manager.addAttack(a);
        active++;
        assertTrue(Integer.valueOf(this.manager.getAttackActive().size()).equals(active));
        assertFalse(a.hasEnded());

        final long t = System.currentTimeMillis();

        while (System.currentTimeMillis() - t < MAX_TIME_KNIFE_ATTACK) {
            this.manager.update();
        }

        assertTrue(a.hasEnded());
        ended++;
        active--;
        assertTrue(Integer.valueOf(this.manager.getAttackActive().size()).equals(active));
        assertTrue(Integer.valueOf(this.manager.getAttacksEnded().size()).equals(ended));
    }

    /**
     * Test on bullets generation through LongRangeWeapon and zombie kills.
     */
    @Test
    public void testLongRangeWeapon() {
        final LongRangeWeapon gun = new WeaponFactory().getLongRangeWeapon(this.position, WeaponName.GUN);
        this.player.setPosition(position);
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        assertTrue(Integer.valueOf(zombieModel.getZombies().size()).equals(1));

        final Zombie z = this.zombieModel.getZombies().stream().findFirst().get();
        final int zHp = z.getLifeManager().getHP();
        final int ammo = gun.getActualAmmo();
        final int ammoNeeded = zHp / gun.getDamage() + 1;
        Optional<Attack> a;
        while (gun.getActualAmmo() > ammo - ammoNeeded) {
            a = gun.attack(z.getPosition());
            if (a.isPresent()) {
                this.manager.addAttack(a.get());
            }
            this.manager.update();
        }

        this.zombieModel.update();
        assertTrue(Integer.valueOf(zombieModel.getZombies().size()).equals(0));
        assertFalse(gun.isRecharging());
        assertTrue(gun.getActualAmmo() < GUN_MAGAZINE_SIZE);
        gun.startRecharging();
        assertTrue(gun.isRecharging());
        while (gun.isRecharging()) {
            gun.update();
        }
        assertTrue(Integer.valueOf(gun.getActualAmmo()).equals(GUN_MAGAZINE_SIZE));
    }

    /**
     * Tests on KnifeAttack generation through Knife and zombie kill.
     * Note: the positions are not random because Knife must be near Zombie to hit it.
     */
    @Test
    public void testShortRangeWeapon() {
        final ShortRangeWeapon weapon = new WeaponFactory().getShortRangeWeapon(this.position, WeaponName.KNIFE);
        final int nearX = 20;
        final int nearY = 10;
        this.spawnPoint.clear();
        this.spawnPoint.add(new Point2D(nearX, nearY));
        this.zombieModel.setSpawnPoints(this.spawnPoint);
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        final Zombie z = this.zombieModel.getZombies().stream().findFirst().get();
        assertTrue(Integer.valueOf(zombieModel.getZombies().size()).equals(1));

        final int zHp = z.getLifeManager().getHP();
        final int attacksNeeded = zHp / weapon.getDamage() + 1;

        Optional<Attack> a;
        int i = 0;
        while (i < attacksNeeded) {
            a = weapon.attack(z.getPosition());
            if (a.isPresent()) {
                this.manager.addAttack(a.get());
                i++;
            }
            this.manager.update();
        }

        this.zombieModel.update();
        assertTrue(Integer.valueOf(zombieModel.getZombies().size()).equals(0));
    }

    private Point2D getRandomPoint() {
        return new Point2D(rnd.nextInt(100), rnd.nextInt(100));
    }

}
