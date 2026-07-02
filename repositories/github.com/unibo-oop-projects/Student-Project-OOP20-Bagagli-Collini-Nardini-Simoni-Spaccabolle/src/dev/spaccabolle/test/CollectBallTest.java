package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import dev.spaccabolle.entity.Ball;
import dev.spaccabolle.entity.CollectBall;

class CollectBallTest {
	CollectBall collectBall = new CollectBall();
	int x = 10;
	int y = 10;
	int height = 20;
	int width = 20;
	int color = 1;
	int index = 1;
	Ball ball = new Ball(x,y,height, width, color,index);
	Ball ball1 = new Ball(x,y,height, width, color,index);
	Ball ball2 = new Ball(x,y,height, width, color,index);
	int size = 3;
	
	@Before
	void init() {
		collectBall.addBall(ball);
		collectBall.addBall(ball1);
		collectBall.addBall(ball2);
	}
	
	@Test
	void testNumBall() {
		init();
		assertEquals(size,collectBall.numBolle());
	}
	
	@Test
	void testCordX() {
		assertEquals(ball.getX(), collectBall.cordX(ball));
	}
	
	@Test
	void testCordY() {
		assertEquals(ball.getY(), collectBall.cordY(ball));
	}
	
	@Test
	void testColor() {
		assertEquals(ball.getColor(),collectBall.color(ball));
	}
	
	@Test
	void testGetBall() {
		assertNotEquals(collectBall, collectBall.getBolle());
	}
	
	@Test
	void testAddBall() {
		init();
		Ball ball3 = new Ball(x,y,height, width, color,index);
		collectBall.addBall(ball3);
		assertNotEquals(size,collectBall.numBolle());
	}
}
