package tests;

import java.util.Set;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import org.junit.Test;

import boxhead.model.entities.Player;
import boxhead.model.entities.Wall;
import boxhead.model.entities.utils.Collision;
import boxhead.model.entities.zombies.Zombie;
import boxhead.model.entities.zombies.ZombieModel;
import boxhead.model.entities.zombies.ZombieModelImpl;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;


public class ZombieTest {
	
	private final ZombieModel model;
	private final Player player;
	private final Wall wall;
	
	public ZombieTest() {
		final Set<Point2D> spawns;
		final Set<BoundingBox> walls;
		
		this.model = new ZombieModelImpl();
		spawns = new HashSet<>();
		spawns.add(new Point2D(10, 10));
		walls = new HashSet<>();
		wall = new Wall(new Point2D(50, 10));
		walls.add(wall.getBoundingBox());
		this.model.setSpawnPoints(spawns);
		this.model.setWalls(walls);
		this.player = new Player();
		this.player.setPosition(new Point2D(100, 10));
		this.model.setPlayer(player);
	}
	
	@Test
	public void testSpawn() {
		this.model.setZombiesToSpawn(1);
		this.model.update();
		assertTrue(Integer.valueOf(this.model.getZombies().size()).equals(1));
	}
	
	@Test
	public void testCollision() {
		this.model.setZombiesToSpawn(1);
		for(int i = 0; i < 40; i++) {
			this.model.update();
		}
		final Zombie zombie = this.model.getZombies().stream().findFirst().get();
		assertTrue(Collision.isColliding(zombie.getBoundingBox(), wall.getBoundingBox()));
	}


@Test
	public void testHitZombie() {
    	this.model.setZombiesToSpawn(1);
    	this.model.update();
    	final Zombie zombie = this.model.getZombies().stream().findAny().get();
    	final int zombieHealth = zombie.getHealth();
    	final int damage = zombieHealth / 2;
    	this.model.hitZombie(zombie, damage);
    	assertTrue(Integer.valueOf(zombie.getHealth()).equals(zombieHealth - damage));
	}

	/*
	 * Remove addKill (score) before
	 */
	@Test
	public void testKillZombie() {
		this.model.setZombiesToSpawn(1);
		this.model.update();
		final Zombie zombie = this.model.getZombies().stream().findAny().get();
		final int damage = zombie.getHealth();
		this.model.hitZombie(zombie, damage);
		this.model.update();
		assertTrue(this.model.getZombies().isEmpty());
	}
	
}