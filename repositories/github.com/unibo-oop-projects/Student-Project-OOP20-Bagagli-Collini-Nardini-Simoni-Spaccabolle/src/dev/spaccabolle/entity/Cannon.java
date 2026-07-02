package dev.spaccabolle.entity;

import java.awt.Graphics;
import dev.spaccabolle.gfx.Assets;
import dev.spaccabolle.input.KeyManager;
import dev.spaccabolle.states.StateGame;

/**
 * The Class Cannon.
 */
public class Cannon extends DynamicObject{
    
    /** The Constant SCARTO_X_BOLLA. */
    private static final int SCARTO_X_BOLLA=30;
    
    /** The Constant SCARTO_Y_BOLLA. */
    private static final int SCARTO_Y_BOLLA=15;
    
    /** The Constant EASY. */
    private static final float EASY = (float)3.5;
    
    /** The Constant NORMAL. */
    private static final float NORMAL = (float)6.5;
    
    /** The Constant HARD. */
    private static final float HARD = (float)11;
    
    /** The bounce. */
    private boolean ballPos;

    public boolean bounce;
    
    /** The ball. */
    private Ball ball;
    
    /** The collect ball. */
    public static CollectBall collectBall;
    
    /** The map color. */
    public static Ball[][] mapColor = CollectBall.getMapCollect();
    
    /** The angle. */
    private int angle = 0;
    
    /** The index. */
    public int difficult = 1,index;
    /** The firstShote */
    public static int firstShot=0;
    
    /**
     * Instantiates a new cannon.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param collectBall the collect ball
     */
    @SuppressWarnings("static-access")
	public Cannon(float x, float y, int width, int height, CollectBall collectBall) {
        super(x, y, width, height);
        this.collectBall = collectBall;
        this.setSpeed(50);
        this.ballPos = true;
        this.bounce = true;
        this.ball = new Ball(this.x + width / 2 - SCARTO_X_BOLLA,this.y + SCARTO_Y_BOLLA - 250,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,getColor(),Map.index++);
        while(this.ball.color == 0) {
        	this.ball = new Ball(this.x + width / 2 - SCARTO_X_BOLLA,this.y + SCARTO_Y_BOLLA - 250,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,getColor(),Map.index++);
        }
        collectBall.addBall(this.ball);
    }

    /**
     * Gets the collect ball.
     *
     * @return the collect ball
     */
    public CollectBall getCollectBall() {
        return collectBall;
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    private int getColor() {
    	  return CollectBall.randomColorCannon;
    }
       
    /**
     * New ball.
     */
    private void newBall() {
        if(!this.ball.isMove && !this.ballPos ) {
            this.ball = new Ball(this.x + width / 2 - SCARTO_X_BOLLA,this.y + SCARTO_Y_BOLLA - 250,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,getColor(),Map.index++);
            while(this.ball.color == 0) {
            	this.ball=new Ball(this.x + width / 2 - SCARTO_X_BOLLA,this.y + SCARTO_Y_BOLLA - 250,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,getColor(),Map.index++);
            }
            collectBall.addBall(this.ball);
            this.ballPos = true;
        }
     }
    
    /**
     * Shot.
     */
    private void shot() {
        if(this.ballPos && KeyManager.space && !StateGame.pause && !CollectBall.gameOver && !CollectBall.victory) {
            boolean iter = true;
            int i = 0;
            
            while(iter) {
            	if(this.ball.x >= Map.posX[i] && this.ball.x < Map.posX[i + 1]) {
            		this.ball.x = Map.posX[i];
            		iter = false;
            	}
            	
            	i++;
            }
    
            this.ball.directMove = (float) Math.toDegrees(Math.toRadians(this.angle - 90));
            this.ball.direct();
            this.ball.isMove = true;
            this.ballPos = false;   
            firstShot=1; //first shot now i can activate save
        }
    }
    
    /**
     * Check bounce.
     */
    public void checkBounce() {
    	if(this.x > Ball.RIGHT_BOUNCE + 5) {
    		this.bounce = false;
    	}else if(this.x < ( - Ball.LEFT_BOUNCE + 250)) {
    		this.bounce = true;
    	}
    }
    
    /**
     * Ball set X.
     */
    private void ballSetX() {
    	if(!this.ball.isMove) {
			this.ball.setX(this.x + (float)92);	    		
		}
    }
    
    /**
     * Speed cannon.
     *
     * @param x the x
     */
    private void speedCannon(float x) {
    	if(this.bounce) {
    		this.setX(this.x + (float)x);
    		ballSetX();
    	}else {
    		this.setX(this.x - (float)x);
    		ballSetX();
    	}
    }
    
    /**
     * Difficults.
     */
    private void difficults() {
    	if(!StateGame.pause && !CollectBall.gameOver && !CollectBall.victory) {
    		
    		switch(difficult) {
	    	case 1: 
	    		speedCannon(EASY);
	    		break;
	    	case 2:
	    		speedCannon(NORMAL);
		    	break;
	    	case 3: 
	    		speedCannon(HARD);
		    	break;
	    	default:
	            break;
    		}
    	}
    }
    
    /**
     * Cannon move.
     */
    public void cannonMove() {
    	checkBounce();
    	difficults();
    }
    
    /**
     * Tick.
     */
    public void tick() {
    	cannonMove();
        shot();
        newBall();
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g) {
        g.drawImage(Assets.cannon,(int)this.getX() - 50,(int)this.getY() - 280, this.getWidth(), this.getHeight(), null);
    }

}
