package dev.spaccabolle.entity;

import java.awt.Graphics;
import java.io.IOException;

import dev.spaccabolle.Launcher;
import dev.spaccabolle.gfx.Assets;

/**
 * The Class Ball.
 */
public class Ball extends DynamicObject{
    
    /** The Constant LEFT_BOUNCE. */
    public static final int LEFT_BOUNCE = Launcher.GAME_WIDTH/2-200;
    
    /** The Constant RIGHT_BOUNCE. */
    public static final int RIGHT_BOUNCE = Launcher.GAME_WIDTH/2+200;
    
    /** The Constant BOBBLE_SIZE. */
    public static final int BOBBLE_SIZE = 70;
   
    /** The direct move. */
    public float directMove;
    
    /** The top connected. */
    public int color,index,topConnected;
   

    /** The is move. */
    public boolean isMove;
    

    /**
     * Instantiates a new ball.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     * @param index the index
     */
    public Ball(float x, float y, int width, int height, int color,int index) {
        super(x, y, width, height);
        this.color = color;
        this.index = index;
        this.directMove = 0;
        this.isMove = false;
    }
    
    /**
     * Gets the color.
     *
     * @return the color
     */
    public int getColor() {
    	return this.color;
    }
    
    /**
     * Direct.
     */
    public void direct() {
        this.setxMove(Math.cos(Math.toRadians(directMove)) * this.speed);
        this.setyMove(Math.sin(Math.toRadians(directMove)) * this.speed);
    }
    
    /**
     * Destroy.
     */
    @SuppressWarnings("unused")
	private void destroy() {
        if(this.y < 0) {
            this.isMove = false;
        }
    }
    
    /**
     * Eliminate.
     */
    public void eliminate() {
    	this.setHeight(0);
    	this.setWidth(0);
    }
    
    /**
     * Ball status.
     */
    public void ballStatus() {
    	Ball b = new Ball(this.x, this.y, this.height, this.width, this.color,Map.index);
    	this.index = Map.index;
    	Map.collectBallMap.addBall(b);	// Aggiungo la bolla alla mappa
    }
    
    /**
     * Tick.
     */
    public void tick() {
    	if(isMove) {
    		if(this.x < 0|| this.x > 840) {
    			this.xMove = this.xMove * -1;
        	}
    		try {
    			if( Map.collectBallMap.roof(this.x,this.y,getBall())) {
    				this.isMove = false; 
				    ballStatus();
				    if(Map.collectBallMap.tris()) {
				    	eliminate();
				    }
    			}else {
    				if(Map.collectBallMap.check(this.x,this.y,getBall()) ) {
    					this.isMove = false;
					    ballStatus();
					    eliminate();  
					    if(Map.collectBallMap.tris()) {
					    	eliminate();
					    }
    				}
            	}
    		} catch (IOException e) {
				e.printStackTrace();
    		}
            move();
        }
    }
    
    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g) {
		g.drawImage(Assets.ballGroup[color], (int)x, (int)y, width, height, null);
    }
    
    /**
     * Gets the ball.
     *
     * @return the ball
     */
    public Ball getBall() {
        return this;
    }

}