package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class AngryBall extends GameObject {
	
	/**
	 * @author RealTutsGML (youtube)
	**/
	public static final int ENEMY_WIDTH = 24;
	public static final int ENEMY_HEIGHT = 24;
	
	private static final double XSPEED = 1.5;
	private static final double YSPEED = 1.5;
	
	private Optional<GameObject> player;

	public AngryBall(int x, int y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		
		if (Controller.getInstance().getObject(ID.Player).stream().count() > 0){
			player = Optional.of(Controller.getInstance().getObject(ID.Player).get(0));
			if(player.isPresent()){ 
				homeIn();
			}
		}
		x += velX;
		y += velY;
		//if(velX > 0) velX--;
		//if(velX < 0) velX++;
		//if(velY > 0) velY--;
		//if(velY < 0) velY++;
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	@Override
	public void keyPressed(int k) {
	}
	
	public void keyReleased(int k) {
		
	}
	
	private void homeIn() {
		GameObject p = player.get();
		if(p.x > this.x) velX = XSPEED;
		else if (p.x < this.x) velX = XSPEED * -1;
		
		if(p.y > this.y) velY = YSPEED;
		else if (p.y < this.y) velY = YSPEED * -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}


}
