package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.entity.Ball;
import dev.spaccabolle.entity.CollectBall;
import dev.spaccabolle.entity.Map;

class MapTest {
	CollectBall collectBallMap = new CollectBall();
	CollectBall collectBallMaps = new CollectBall();
	Map map = new Map(0, 0, collectBallMap, null);
	

	
	@Test
	void testSetCollectBallMap() {
		Map.setCollectBallMap(collectBallMap);
		assertEquals( collectBallMap, Map.getCollectBallMap());
	}
	
	@Test
	void testGetCollectBallMap() {
		assertNotEquals(collectBallMaps, Map.getCollectBallMap());
	}
	@Test
	void testGetMapMatrix() {
		Ball[][] mapGame = Map.getMapmatrix();
		assertEquals(Map.mapMatrix, mapGame);
	}

}
