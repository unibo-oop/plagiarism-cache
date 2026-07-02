package model.entitiesutil;

/**
 * The class that manage all the constants of the entities
 */

public class EntityConstants { 
	
	public class PlayerBullet{
		public final static int INITIAL_MU_X = 0;
		public final static int  INITIALWIDTH = 5;
		public final static int  INITIALHEIGHT = 15;
		public final static double  INITIAL_MU_Y = 5;
	}
		
	public class Player{
		public final static int INITIAL_WIDTH = 150;
		public final static int INITIAL_HEIGHT = 90;
		public final static double INITIAL_MU_X = 10;
		public final static int MAX_HITS = 3;
		public final static int CYCLES_TO_SHOOT = 15; 
	}
	
	public class Alien{
		public final static int INITIAL_WIDTH = 60;
		public final static int INITIAL_HEIGHT = 30;
		public final static double INITIAL_MU_X = 1;
		public final static double INITIAL_MU_Y = 10;
		public final static int MAX_HIT = 1;
		public final static int CYCLES_TO_SHOOT = 100; 
	}

	public class Boss1 {
		public final static int INITIAL_WIDTH = 200;
		public final static int INITIAL_HEIGHT = 120;
		public final static double INITIAL_MU_X = 1.2;
		public final static double INITIAL_MU_Y = 20;
		public final static int MAX_HITS = 20;
		public final static int CYCLES_TO_SHOOT = 72; 
	}

	public class Boss2 {
		public final static int INITIAL_WIDTH = 300;
		public final static int INITIAL_HEIGHT = 200;
		public final static double INITIAL_MU_Y = 0.6;
		public final static int MAX_HITS = 25;
		public final static int HITS_2ND_PHASE = MAX_HITS/2;
		public final static int CYCLES_TO_SHOOT = 80;
	}

	public class Boss3 {
		public final static int INITIAL_WIDTH = 200;
		public final static int INITIAL_HEIGHT = 120;
		public final static double INITIAL_MU_X = 1.5;
		public final static double INITIAL_MU_Y = 20;
		public final static int MAX_HITS = 15;
		public final static int MAX_SPEED = 3;
		public final static int HITS_2ND_PHASE = MAX_HITS/2;
		public final static int CYCLES_TO_SHOOT_1ST_PHASE = 150;
		public final static int CYCLES_TO_SHOOT_2ND_PHASE = 70;
	}

	public class MonoDirectionEnemyBullet {
		public final static int INITIAL_WIDTH = 5;
		public final static int INITIAL_HEIGHT = 15;
		public final static double MAX_MU_Y = 3;
	}

	public class MultiDirectionEnemyBullet{
		public final static int INITIAL_WIDTH = 5;
		public final static int INITIAL_HEIGHT = 15;
		public final static double INITIAL_MU_X = 0.7;
		public final static double MAX_MU_Y = 2;
		public final static int POSSIBLE_DIRECTIONS = 3;
	}
}
