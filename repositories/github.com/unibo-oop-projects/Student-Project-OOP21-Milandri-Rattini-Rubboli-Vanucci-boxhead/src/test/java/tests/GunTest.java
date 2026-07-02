package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import boxhead.model.entities.Player;
import boxhead.model.entities.Wall;
import boxhead.model.entities.gun.Bullet;
import boxhead.model.entities.gun.Gun;
import boxhead.model.entities.gun.Gun.GunType;
import boxhead.model.entities.gun.GunFactory;
import boxhead.model.entities.gun.Shot;
import boxhead.model.entities.gun.ShotManager;
import boxhead.model.entities.gun.ShotManagerImpl;
import boxhead.model.entities.utils.Direction;
import boxhead.model.entities.zombies.Zombie;
import boxhead.model.entities.zombies.ZombieModel;
import boxhead.model.entities.zombies.ZombieModelImpl;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * Test for Gun and their use.
 */
public class GunTest {
	
	private static final int PISTOL_DAMAGE = 30;
	private static final int UZI_DAMAGE = 25;
	private static final int SHOTGUN_DAMAGE = 50;
	
	private final Player player;
	private final ShotManager manager;
	private final ZombieModel zombieModel;
	private final Set<Point2D> spawns;
	private final Point2D position;
	
	public GunTest() {
        final Point2D zombiePos = new Point2D(0, 20);
        this.position = new Point2D(0, 0);
        this.spawns = new HashSet<>();
        this.spawns.add(zombiePos);
        this.player = new Player();
        this.player.setPosition(position);
        this.zombieModel = new ZombieModelImpl();
        this.manager = new ShotManagerImpl(zombieModel);
        this.zombieModel.setPlayer(this.player);
        this.zombieModel.setSpawnPoints(spawns);
	}
	
	/**
	 * To test the collision between Bullets and Walls.
	 */
	@Test
	public void testShotManager() {
		Shot bullet1;
		Shot bullet2;
        int active = 0;
        assertTrue(Integer.valueOf(this.manager.getShotsActive().size()).equals(active));
        //Istanzio i bullet
        bullet1 = new Bullet(new Point2D(0,0), new Point2D(100, 0), 100);
        this.manager.addShot(bullet1);
        active++;
        assertTrue(Integer.valueOf(this.manager.getShotsActive().size()).equals(active));
        assertFalse(bullet1.hasEnded());
        
        bullet2 = new Bullet(new Point2D(0,0), new Point2D(0,60), 100);
        this.manager.addShot(bullet2);
        active++;
        assertTrue(Integer.valueOf(this.manager.getShotsActive().size()).equals(active));
        assertFalse(bullet2.hasEnded());
        //Piazzo nella traiettoria del Bullet un muro per vedere se si schianta
        final Set<BoundingBox> walls = new HashSet<>();
        final Wall wall1 = new Wall(new Point2D(55, 0));
        final Wall wall2 = new Wall(new Point2D(0, 55));
        walls.add(wall1.getBoundingBox());
        walls.add(wall2.getBoundingBox());
        this.manager.setWalls(walls);
        for (int i = 0; i < 4; i++) {
        	this.manager.update();
        }
        final Set<Shot> ended = this.manager.getShotsEnded();
        assertTrue(ended.contains(bullet1) && ended.contains(bullet2)); 
	}
	
	@Test
	public void testGunImpl() {
		final Gun uzi = new GunFactory().getGun(this.position, GunType.UZI);
		final Gun shotgun = new GunFactory().getGun(this.position, GunType.SHOTGUN);
		this.player.setPosition(this.position);
		this.zombieModel.setZombiesToSpawn(1);
		this.zombieModel.update();
		
		//PISTOL
		final Zombie z = this.zombieModel.getZombies().stream().findFirst().get();
		int zombieHP = z.getHealth();
		this.player.getCurrentGun().attack(position.subtract(Direction.SOUTH.getShotOffset()), Direction.SOUTH).forEach(s -> {
			this.manager.addShot(s.get());
		});
		this.manager.update();
		assertTrue(z.getHealth() == (zombieHP - PISTOL_DAMAGE));
		
		//UZI
		this.player.unlockGun(uzi);
		this.player.nextGun();
		assertTrue(this.player.getCurrentGun().equals(uzi));
		this.player.getCurrentGun().attack(position.subtract(Direction.SOUTH.getShotOffset()), Direction.SOUTH).forEach(s -> {
			this.manager.addShot(s.get());
		});
		this.manager.update();
		assertTrue(z.getHealth() == (zombieHP - PISTOL_DAMAGE - UZI_DAMAGE));
		
		//SHOTGUN
		this.player.unlockGun(shotgun);
		this.player.setCurrentGun(shotgun);
		assertTrue(this.player.getCurrentGun().equals(shotgun));
		this.player.getCurrentGun().attack(position.subtract(Direction.SOUTH.getShotOffset()), Direction.SOUTH).forEach(s -> {
			this.manager.addShot(s.get());
		});
		z.setHealth(200);
		zombieHP = z.getHealth();
		//The zombie gets hit by two out of the three bullets generated.
		this.manager.update();
		assertTrue(z.getHealth() == (zombieHP - 2*SHOTGUN_DAMAGE));
	}
}
