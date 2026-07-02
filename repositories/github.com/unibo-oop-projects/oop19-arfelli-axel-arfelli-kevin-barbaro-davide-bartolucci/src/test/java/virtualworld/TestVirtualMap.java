package virtualworld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Actor;
import entity.VirtualBody;
import entity.BodyImpl;
import entity.CollisionBoxInt;
import entity.Faction;
import entity.UUIDActor;
import entity.UUIDProjectile;

class TestVirtualMap {
    
    private static VirtualMap<UUIDActor,UUIDProjectile> map;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        map = new VirtualMapPrototype(100, 100);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void AddingMovingEntitis() {
        UUIDActor a1 = new TestActor();
        try {
            a1.getBody().setMotion(map.addActor( a1, 10, 10));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertTrue(map.getActors().get(a1)!= null);
        a1.update();
        assertFalse(a1.isAlive());
        assertTrue(a1.getBody().getCollisionBox().checkCollision(new CollisionBoxInt(20, 20, 10, 10) ));
        map.removeActor(a1);
        assertFalse(map.getActors().containsKey(a1));
    }
    
    @Test
    void TestException() {
        UUIDActor a1 = new TestActor();
        assertThrows(Exception.class, ()->a1.getBody().setMotion(map.addActor( a1, 110, 110)));
    }
    
    @Test
    void TestGetByFaction() {
        UUIDActor a1 = new TestActor();
        UUIDActor a2 = new TestActor(){
            
            @Override
            public Faction getFaction() {
                return Faction.ENEMY;
            }
        };
        
        try {
            map.addActor(a1,new CollisionBoxInt(20, 20, 10, 10));
            map.addActor(a2,new CollisionBoxInt(20, 20, 10, 10));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(2, map.getActors().size());
        assertEquals(1, map.getActors(Faction.NEUTRAL).size());        
    }
    
    
    private class TestActor extends UUIDActor{
        boolean alive=true;
        VirtualBody virtualBody = new BodyImpl(new CollisionBoxInt(0,0,10,10));
        private Faction f = Faction.NEUTRAL; 
        int life=0;
        VirtualMap map;
        
        @Override
        public void update() {
            if(virtualBody.move(20, 20)) {
                this.alive = false;
            }
            
        }
        
        @Override
        public boolean isAlive() {
            return this.alive;
        }
        
        @Override
        public String getType() {
            return "TEST-ACTOR";
        }
        
        @Override
        public VirtualBody getBody() {
            // TODO Auto-generated method stub
            return virtualBody;
        }
        
        @Override
        public void setMap(VirtualMap map) {
            this.map=map;
        }
        
        @Override
        public int getLife() {
            return this.life;
        }
        
        @Override
        public Faction getFaction() {
            return f;
        }
        
        @Override
        public void addToLife(int amount) {
            this.life=+amount;
        }

        @Override
        public int getScoreValue() {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}
