package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.entity.Ball;
import dev.spaccabolle.entity.Cannon;
import dev.spaccabolle.entity.CollectBall;

class CannonTest {
	
        int x = 1000;
        int y = 10;
        int height = 20;
        int width = 20;
        CollectBall collectBall = new CollectBall();
        Cannon cannon = new Cannon(x,y,height, width, collectBall);
    
        @Test
	void testGetCollectBall() {
		assertEquals(collectBall, cannon.getCollectBall());
	}
        
        @Test
        void testGetNumBall() {
            cannon.getCollectBall().addBall(new Ball(0,0,0,0,0,0));
            assertEquals(2,cannon.getCollectBall().numBolle());
            cannon.getCollectBall().addBall(new Ball(1,1,1,1,1,1));
            assertEquals(3,cannon.getCollectBall().numBolle());
        }
        
        @Test
        void testSpeedCannon() {
            cannon.setSpeed(30);
            assertEquals(30,cannon.getSpeed());
        }
        
        @Test
        void testCheckBounce() {
            cannon.checkBounce();
            assertEquals(false,cannon.bounce);
        }
}

