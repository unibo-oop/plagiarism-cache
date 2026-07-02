package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.score.Score;

class ScoreTest {
	Score score = new Score(0,0,0,0);
	
	@Test
	void testAddPoint() {
		score.addPoint(370, 5);
		assertEquals(3, score.getNumber1());
		assertEquals(7, score.getNumber2());
		assertEquals(3, score.getNumber3());
		assertEquals(2, score.getNumber4());
	}

	@Test
	void testPower() {
	    int score = Score.power(2, 2);
		assertEquals(4, score);
	}

}
