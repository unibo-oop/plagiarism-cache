package gameengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Actor;
import entity.VirtualBody;
import entity.BodyImpl;
import entity.CollisionBoxInt;
import entity.EntitySpawner;
import entity.Faction;
import entity.GenericProjectile;
import entity.Projectile;
import entity.UUIDActor;
import virtualworld.VirtualMap;

class TestSpwaner {
    private static EntitiesUpdater et;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        et = new EntitiesUpdater(2);
        Thread t = new Thread(et);
        t.start();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        et.stop();
    }

    @Test
    void testSpawnActor() {
        EntitySpawner spawner = et.getSpawner();
        TestActor a1 = new TestActor(1);
        spawner.spwanActor(a1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        assertTrue(spawner.getDespawnedActors().isEmpty());
        a1.addToLife(-1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Set<Actor> set = spawner.getDespawnedActors();
        assertFalse(set.isEmpty());

        assertTrue(set.contains(a1));
    }

    @Test
    void testSpawnerProjectile() {
        EntitySpawner spawner = et.getSpawner();
        TestActor a1 = new TestActor(1);
        Projectile p1 = new GenericProjectile(new CollisionBoxInt(0, 0, 2, 2), 0, a1);
        spawner.spwanProjectile(p1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        assertTrue(spawner.getDespawnedProjectiles().isEmpty());
        p1.hit();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Set<Projectile> set = spawner.getDespawnedProjectiles();
        assertFalse(set.isEmpty());

        assertTrue(set.contains(p1));
    }

    class TestActor extends UUIDActor {
        private int life = 1;
        private VirtualBody virtualBody;

        /**
         * @param life
         */
        TestActor(final int life) {
            this.life = life;
            this.virtualBody = new BodyImpl(new CollisionBoxInt(0, 0, 1, 1));
        }

        @Override
        public int getLife() {
            return life;
        }

        @Override
        public void addToLife(final int amount) {
            life += amount;
        }

        @Override
        public Faction getFaction() {
            return Faction.NEUTRAL;
        }

        @Override
        public void setMap(final VirtualMap map) {
        }

        @Override
        public void update() {
            System.out.println(String.format("I'm Alive %d", life));
        }

        @Override
        public boolean isAlive() {
            return life > 0;
        }

        @Override
        public String getType() {
            return "TEST-ACTOR";
        }

        @Override
        public VirtualBody getBody() {
            return this.virtualBody;
        }

        @Override
        public int getScoreValue() {
            // TODO Auto-generated method stub
            return 0;
        }
    }

}
