package dev.spaccabolle.states;


import java.awt.Graphics;
import java.io.File;

import dev.spaccabolle.Handler;
import dev.spaccabolle.Launcher;
import dev.spaccabolle.display.Display;
import dev.spaccabolle.entity.Ball;
import dev.spaccabolle.entity.Cannon;
import dev.spaccabolle.entity.CollectBall;

import dev.spaccabolle.gfx.Assets;
import dev.spaccabolle.gfx.DrawImage;
import dev.spaccabolle.gfx.Pause;
import dev.spaccabolle.input.KeyManager;
import dev.spaccabolle.score.Score;
import dev.spaccabolle.entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class StateGame.
 */
public class StateGame extends State{
    
	    /** The Constant SCARTO. */
    	private static final int SCARTO = 100;
        
        /** The Constant CANNON_X. */
        private static final int CANNON_X = (Launcher.GAME_WIDTH/2)-(Assets.cannon.getWidth()/2);        
        
        /** The Constant CANNON_Y. */
        private static final int CANNON_Y = (Launcher.GAME_HEIGHT/2)+Assets.cannon.getHeight()+SCARTO;
        
        /** The Constant EASY. */
        private static final int EASY = 1;
        
        /** The Constant NORMAL. */
        private static final int NORMAL = 2;
        
        /** The Constant HARD. */
        private static final int HARD = 3;
        
        /** The Constant INITIAL_DRAGON. */
        private static final int INITIAL_DRAGON = 200;
        
        /** The Constant EASY_DRAGON. */
        private static final int EASY_DRAGON = 200;
        
        /** The Constant NORMAL_DRAGON. */
        private static final int NORMAL_DRAGON = 300;
        
        /** The Constant HARD_DRAGON. */
        private static final int HARD_DRAGON = 410;
        
        /** The Constant LIMITS. */
        private static final int LIMITS = 15;
        
        /** The y move. */
        public  static int xDragon = 500, yDragon = 200, yDragonVictory = 450, yMove = -1;
    	
	    /** The y dragon victory limits. */
	    public static int yDragonLimits = 200, yDragonVictoryLimits = 200;
    	
    	/** The exit. */
	    public static boolean exit = false;
        
        /** The pause. */
        public static boolean pause = false;
    	
    	/** The level. */
	    public File level;
    	
	    /** The map. */
	    public Map map;    
        
        /** The cannon. */
        public static Cannon cannon;
        
        /** The collect ball. */
        public static CollectBall collectBall;
        
        /** The collect ball map. */
        public static CollectBall collectBallMap;		    
        
        /** The paused. */
        public static Pause paused;
        
        /** The image draw. */
        private DrawImage imageDraw;
        
		/**
		 * Instantiates a new state game.
		 *
		 * @param handler the handler
		 */
		public StateGame(Handler handler) {
			super(handler);
			
			collectBall=new CollectBall();
			collectBallMap=new CollectBall();
			CollectBall.point=0;
			Score.tempFlyngPoint=0;
			CollectBall.flyngPoint=0;
			cannon = new Cannon(CANNON_X, CANNON_Y, Assets.cannon.getWidth(), Assets.cannon.getHeight(),collectBall);
			map = new Map(0, Ball.LEFT_BOUNCE,collectBallMap,level);			
			paused = new Pause();
			imageDraw = new DrawImage();
		}
		
		/**
		 * If pause.
		 */
		private void ifPause() {
		    if(KeyManager.pause && !CollectBall.gameOver) {
	    	 	pause = true;
		    }
			if (pause) {
			   if(KeyManager.easy) {
				   cannon.difficult = EASY;   		
				   yDragon = EASY_DRAGON;
				   yDragonLimits = EASY_DRAGON;
				}
			   if(KeyManager.normal) {
				   cannon.difficult = NORMAL;   		
				   yDragon = NORMAL_DRAGON;
				   yDragonLimits = NORMAL_DRAGON;
				}
			   if(KeyManager.hard) {
				   cannon.difficult = HARD;
				   yDragon = HARD_DRAGON;
				   yDragonLimits = HARD_DRAGON;
				}
		   }
		}
		
		/**
		 * If exit.
		 */
		private void ifExit() {
			if(KeyManager.exit && !CollectBall.gameOver && !CollectBall.victory) {
				   exit = true;
			}else if (KeyManager.exit){
				   System.exit(0);
			}			   
		    if(exit) {
			   if(KeyManager.yes) {
				   System.exit(0);
			   }else if(KeyManager.no) {
				   exit = false;
			   }
		   }
		}
		
		/**
		 * If game over.
		 */
		private void ifGameOver() {
			if (CollectBall.gameOver) {
			   if(KeyManager.restart) {
				   State.setState(handler.getGame().menuState);
					CollectBall.point=0;
					Cannon.firstShot=0;  //restart of the game		
					Score.setZero(CollectBall.score);
				   StateMenu.run = false;
			   }
		   	}
		}
	
		/**
		 * Gets the input.
		 *
		 * @return the input
		 */
		private void getInput() {
		   ifPause();   //pause game
		   ifExit();	//exit game
		   ifGameOver(); //if game over restart game
		   
		   if(KeyManager.save && Cannon.firstShot==1) {			   
			   Display.saveFile((StateMenu) handler.getGame().menuState);
		   }

		   if(KeyManager.enter) {
			   pause = false;
			   yDragon = INITIAL_DRAGON;
			   yDragonLimits = INITIAL_DRAGON;
		   }
		}
		 
		/**
		 * Move icon.
		 */
		private void moveIcon() {
			if((yDragon < yDragonLimits-LIMITS || yDragon > yDragonLimits)) {
		          yMove*=-1;
		     }
		     yDragon+=yMove;
		}
		
		/**
		 * Tick.
		 */
		public void tick() {
			getInput();
			moveIcon();
			cannon.tick();
		    collectBallMap.tick();
		    collectBall.tick();
    
		}

		/**
		 * Render.
		 *
		 * @param g the g
		 */
		public void render(Graphics g) {
			imageDraw.render(g);
		}
}