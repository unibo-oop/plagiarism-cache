package dev.spaccabolle.states;

import java.awt.Graphics;
import java.io.File;

import dev.spaccabolle.Handler;
import dev.spaccabolle.Launcher;
import dev.spaccabolle.display.Display;
//import dev.spaccabolle.entity.Ball;
import dev.spaccabolle.entity.Cannon;
import dev.spaccabolle.entity.CollectBall;
//import dev.spaccabolle.entity.Map;
import dev.spaccabolle.gfx.Assets;
import dev.spaccabolle.score.Score;
import dev.spaccabolle.ui.ClickListener;
import dev.spaccabolle.ui.UIImageButton;
import dev.spaccabolle.ui.UIManager;

// TODO: Auto-generated Javadoc
/**
 * The Class StateMenu.
 */
public class StateMenu extends State{
	
	/** The Constant yDragonLimits. */
	private static final int yLogoLimits = 30, xDragonLimits=345,yDragonLimits=445;
	
	/** The Constant LIMITS. */
	private static final int LIMITS = 15;
	
	/** The y move. */
	private int yMove=-1;
	
	/** The Y move button. */
	private int YMoveButton=840;
	
	/** The y movelogo. */
	public static int yMovelogo=-430;
	
	/** The dim dragon. */
	public static int dimDragon=0;
	
	/** The x dragon. */
	public static int xDragon=800;
	
	/** The y dragon. */
	public static int yDragon=445;
	
	/** The ui manager. */
	public static UIManager uiManager;
	
	/** The run. */
	public static boolean run = false;
	
	/** The load. */
	public static boolean load = false;
	
	/** The home. */
	public static boolean home = false;
	
	/** The is load game. */
	public static boolean isLoadGame = false;
	
	/** The load game. */
	public static  File loadGame;
	
	/** The file path. */
	static String filePath = new File("").getAbsolutePath();
	
	/** The save game. */
	public static  File saveGame = new File(filePath+"/../src/res/map/save.txt") ;
	
	/**
	 * Instantiates a new state menu.
	 *
	 * @param handler the handler
	 */
	public StateMenu(Handler handler) {
		
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(50, YMoveButton, 220, 150, Assets.btn_start, new ClickListener() {
			public void onClick() {
				//handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				run = true;
			}
		}));
		
		uiManager.addObject(new UIImageButton(40, 640, 200, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(run) {
					if(Cannon.firstShot==1) {
						Display.saveFile((StateMenu) handler.getGame().menuState);
					}
					
				}
			}
		}));
		uiManager.addObject(new UIImageButton(330, 640, 200, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(run) {
					StateGame.pause = true;
				}
			}
		}));
	
		uiManager.addObject(new UIImageButton(600, 640, 200, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(run) {
					StateGame.exit = true;
				}
			}
		}));
		uiManager.addObject(new UIImageButton(210, 510, 90, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(StateGame.pause) {
					State.setState(handler.getGame().menuState);
					StateGame.pause = false;
					Cannon.firstShot=0;  //restart of the game		
					CollectBall.point=0;
					Score.setZero(CollectBall.score);

					run = false;
					home = true;
				}
			}
		}));
		uiManager.addObject(new UIImageButton(353, 510, 90, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(StateGame.pause) {
					StateGame.pause = false;
				}
			}
		}));
		uiManager.addObject(new UIImageButton(495, 510, 90, 90, Assets.trasparent, new ClickListener() {
			public void onClick() {
				if(StateGame.pause) {
					StateGame.exit = true;
				}
			}
		}));
		uiManager.addObject(new UIImageButton(308, YMoveButton, 220, 150, Assets.btn_load, new ClickListener() {
                 
					public void onClick() {
                    	if(!run) {
                    		load = true;
                    		try{
                    			Thread.sleep(3000);
                    			load=false;
                    		}catch(Exception e){}
                    		
                    		//loadGame = Display.getFile();
                    		//StateGame game= (StateGame) handler.getGame().gameState;                    		
                    		//game.level=loadGame;
                    		//game.collectBallMap = new CollectBall();
                    		//game.map=new Map(0, Ball.LEFT_BOUNCE,game.collectBallMap,game.level);
                            //isLoadGame=true;  
                    	
                            
                            
                    	}
                    }
            }));
		uiManager.addObject(new UIImageButton(566, YMoveButton, 220, 150, Assets.btn_exit, new ClickListener() {
                    public void onClick() {
                    	if(!run) {
                            System.exit(0);
                    	}
                    }
            }));
	}
	
	/**
	 * Move logo.
	 */
	private void moveLogo() {
	    yMovelogo+=2;
	}
	
	/**
	 * Move dragon.
	 */
	private void moveDragon() {
	    if(xDragon > xDragonLimits) {
	        xDragon-=2;
	    }else {
	        if(yDragon < yDragonLimits-LIMITS || yDragon > yDragonLimits+LIMITS) {
	            yMove*=-1;
	        }
	        yDragon+=yMove;
	    }
	    if(dimDragon<150)
	        dimDragon++;
	}

	/**
	 * Tick.
	 */
	public void tick() {
		uiManager.tick();
		if(yMovelogo < yLogoLimits) {
		    moveLogo();
		}
		moveDragon();
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.dark_background, 0, 0, Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT, null);
		g.drawImage(Assets.logo, 70, StateMenu.yMovelogo, 650, 650, null);
		g.drawImage(Assets.dragon, StateMenu.xDragon, StateMenu.yDragon, StateMenu.dimDragon, StateMenu.dimDragon, null);
		StateMenu.uiManager.render(g);
		if(load) {
			 g.drawImage(Assets.demo, 0, 0, Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT, null);
		 }
	
	}
}
