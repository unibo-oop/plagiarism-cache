package entity;
import virtualworld.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TestEntity {
	
	//Movment class just to test the entity movement
	public class EntityMovment implements Movment {

        private VirtualMap map;
        private CollisionBox hitbox;
		
        public EntityMovment(VirtualMap map, CollisionBox<Integer> hitbox) {
        	this.map = map;
            this.hitbox = hitbox;
        }

        @Override
        public VirtualMap getMap() {
        	return map;
        }

        @Override
        public boolean moveTo(int x, int y) {
        	CollisionBox tmp = new CollisionBoxInt(x, y, 64, 64);
        	this.hitbox = tmp;
            return true;
        }

		@Override
		public boolean isInside(int x, int y) {
			return false;
		}

		@Override
		public CollisionBox<Integer> getCollisionBox() {
			return this.hitbox;
		}
    }
	
	VirtualMap<UUIDActor, UUIDProjectile> testMap;
	
	@Test
	void testDrone() throws Exception {
		testMap = new VirtualMapPrototype<UUIDActor, UUIDProjectile>(1980, 1080);
		Actor entity = new Drone(1600, 800, 1, 1);
		assertEquals(1600, entity.getBody().getCollisionBox().getX());
		assertEquals(800, entity.getBody().getCollisionBox().getY());
		entity.getBody().setMotion(new EntityMovment(testMap, entity.getBody().getCollisionBox()));
		entity.update();
		assertEquals(1601, entity.getBody().getCollisionBox().getX());
		assertEquals(800, entity.getBody().getCollisionBox().getY());
		entity.addToLife(-1);
		assertFalse(entity.isAlive());
	}
	
	@Test
	void testMuncher() throws Exception {
		testMap = new VirtualMapPrototype<UUIDActor, UUIDProjectile>(1980, 1080);
		testMap.addActor(new PlayerShip(500, 10), 500, 10);
		Actor entity = new Muncher(1600, 800, 1, 1);
		assertEquals(entity.getBody().getCollisionBox().getX(), 1600);
		assertEquals(entity.getBody().getCollisionBox().getY(), 800);
		entity.getBody().setMotion(new EntityMovment(testMap, entity.getBody().getCollisionBox()));
		entity.update();
		assertEquals(1599, entity.getBody().getCollisionBox().getX());
		assertEquals(800, entity.getBody().getCollisionBox().getY());
		entity.addToLife(-1);
		assertFalse(entity.isAlive());
	}
	
	@Test
	void testTurret() throws Exception {
		testMap = new VirtualMapPrototype<UUIDActor, UUIDProjectile>(1980, 1080);
		Actor entity = new Turret(1600, 800, 1, 1);
		assertEquals(entity.getBody().getCollisionBox().getX(), 1600);
		assertEquals(entity.getBody().getCollisionBox().getY(), 800);
		entity.getBody().setMotion(new EntityMovment(testMap, entity.getBody().getCollisionBox()));
		entity.update();
		assertEquals(1600, entity.getBody().getCollisionBox().getX());
		assertEquals(799, entity.getBody().getCollisionBox().getY());
		entity.addToLife(-1);
		assertFalse(entity.isAlive());
		Actor entity2 = new Turret(1600, 1, 1, 1);
		entity2.getBody().setMotion(new EntityMovment(testMap, entity.getBody().getCollisionBox()));
		entity2.update();
		assertFalse(entity2.isAlive());
	}
	
	@Test
	void testBoss() throws Exception {
		testMap = new VirtualMapPrototype<UUIDActor, UUIDProjectile>(1980, 1080);
		Actor entity = new EnemyBoss(1600, 800, 1, 1);
		assertEquals(entity.getBody().getCollisionBox().getX(), 1600);
		assertEquals(entity.getBody().getCollisionBox().getY(), 800);
		entity.getBody().setMotion(new EntityMovment(testMap, entity.getBody().getCollisionBox()));
		entity.update();
		assertEquals(1601, entity.getBody().getCollisionBox().getX());
		assertEquals(800, entity.getBody().getCollisionBox().getY());
		entity.addToLife(-1);
		assertFalse(entity.isAlive());
	}
}
