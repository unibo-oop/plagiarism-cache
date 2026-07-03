package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.main.GameObject;
import com.game.support.ID;

public class PlayerCustom extends GameObject implements Tickable{

	Handler handler;
	Color color;

	public PlayerCustom(float x, float y, ID id, Handler handler, Color color) {
		super(x, y, id);
		this.handler = handler;
			this.color = color;
				
	}

	
	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.PLAYABLEHEIGHT - 60);
		collision();
	}

	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					HUD.HEALTH -=2;
				}
			} else if(tempObject.getId() == ID.Coin) {
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH +=4;
					this.handler.removeObject(tempObject);
				}
			} else if(tempObject.getId() == ID.CoinBomb){
				if(getBounds().intersects(tempObject.getBounds())){
					this.handler.removeObject(tempObject);
					handler.clearEnemys();
					handler.addObject(new SmartEnemy(10,10, ID.SmartEnemy, handler));
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setColor(this.color);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
