package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class PlayerBullet extends GameObject {
	
	public static final int BULLET_WIDTH = 8;
	public static final int BULLET_HEIGHT = 8;

	public PlayerBullet(int x, int y, ID id, double velX, double velY) {
		super(x, y, id);
		this.velX = 10*velX;
		this.velY = 10*velY;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		collision();
		Controller.getInstance().addObject(new Trail(x, y, 16, 16, Color.ORANGE, 0.1f));
	}
	
	private void collision() {

		
		if(Controller.getInstance().
					getCollisionable().
								stream().
									filter(o -> o.id != ID.Player).
										filter(o -> o.id != ID.Bullet).
											filter(o -> getBounds().intersects(o.getBounds())).
												count() != 0)
		{
			Controller.getInstance().
			getCollisionable().
			stream().
			filter(o -> o.id == ID.Enemy).
			filter(o -> getBounds().intersects(o.getBounds())).forEach(o -> o.die());
			Controller.getInstance().removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, BULLET_WIDTH, BULLET_HEIGHT);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
	}

}
