package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import view.View;

public class Player extends GameObject {
	
	public static final int PWIDTH = 32;
	public static final int PHEIGHT = 32;
	public static final int MAX_HEALTH = 100;
	
	private int health;

	public Player(int x, int y, ID id) {
		super(x, y, id);
		health = MAX_HEALTH;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(velX > 0) velX--;
		if(velX < 0) velX++;
		if(velY > 0) velY--;
		if(velY < 0) velY++;
		if(y <= 0 || y > View.HEIGHT - PHEIGHT){
			y = y <= 0? 0 : View.HEIGHT - PHEIGHT; 
			velY *= -1;
		}
		if(x <= 0 || x > View.WIDTH - PWIDTH){
			x = x <= 0? 0 : View.WIDTH - PWIDTH;
			velX *= -1;
		}
		
		if(health <= 0) Controller.getInstance().FailState();
		
		collision();
	}

	private void collision() {
		for (int i = (int) Controller.getInstance().
								getCollisionable().
								stream().
								filter(o -> o.id != ID.Player).
								filter(o -> o.id != ID.Bullet).
								filter(o -> getBounds().intersects(o.getBounds())).
								count()
								;i > 0 ; i--){
			healthDecrease();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, PWIDTH, PHEIGHT);
		g.setColor(Color.green);
		g.fillRect(x,(int) (y - PHEIGHT * 1.1), (int)(PWIDTH * health / MAX_HEALTH) , PHEIGHT/4);
	}
	
	public void keyPressed(int k){
		switch(k){
		case 65: ;
		case 97: velX -= 10;
					break;
		case 68: ;
		case 100: velX += 10;
					break;
		case 83: ;
		case 115: velY += 10;
					break;
					
		case 87 :  ;
		case 119 : velY -= 10;
					break;
		
		}
	}

	public void keyReleased(int k) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	@Override
	public int getX(){
		return this.x + PWIDTH/2;
	}
	
	@Override
	public int getY(){
		return this.y + PHEIGHT/2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y, PWIDTH, PHEIGHT);
	}
	
	public void healthDecrease() {
		health--;
	}
}
