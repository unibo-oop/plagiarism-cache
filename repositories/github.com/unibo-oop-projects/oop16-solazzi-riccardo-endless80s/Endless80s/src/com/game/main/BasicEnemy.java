package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.support.ID;


public class BasicEnemy extends GameObject implements Tickable{
	
	private Handler handler;
	

	public BasicEnemy(int x, int y, ID id, Handler handler, int lvl) {
		super(x, y, id);
		velX = 1 + (lvl);
		velY = 1 + (lvl);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.PLAYABLEHEIGHT-32) velY*= -1;
		if(x <= 0 || x >= Game.WIDTH-16) velX*= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.03f, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
		
		
	}

}

