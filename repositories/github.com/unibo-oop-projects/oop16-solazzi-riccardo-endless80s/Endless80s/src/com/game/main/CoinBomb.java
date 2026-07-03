package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.support.ID;

public class CoinBomb extends GameObject{
	
	
	public CoinBomb(float x, float y, ID id, Handler handler) {
		super(x, y, id);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 8, 8);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,8,8);
	}

}
