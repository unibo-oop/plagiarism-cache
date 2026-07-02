package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.entity.Ball;

class BallTest {
	int x = 10;
	int y = 10;
	int height = 20;
	int width = 20;
	int color = 1;
	int index = 1;
	Ball ball = new Ball(x,y,height, width, color,index);
	
	
	@Test
	void testGetColor() {
		int colorBall = ball.getColor();
		assertEquals(color, colorBall);
	}
	
	@Test
	void testGetBall() {
		Ball ball1 = ball.getBall();
		assertEquals(ball, ball1);
	}
	@Test
	void testCoordinate() {
		Ball ball1 = ball.getBall();
		ball1.y = ball.getY();
		ball1.x = ball.getX();
		assertEquals(x,ball1.x);
		assertEquals(y,ball1.y);	
	}
	
	@Test
	void testEliminate() {
		ball.eliminate();
		assertNotEquals(height, ball.getHeight());
		assertNotEquals(width, ball.getWidth());
	}
	

}
