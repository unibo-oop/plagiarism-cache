package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.lang.Math;

public class GunDirection extends GameObject {
	
	public static final int RADIUS = 45;
	public static final int RADIALV = 3;
	
	public static final int COOLDOWN = 10;
	
	private int timer = 0;
	private double radians;
	private Point mouse;
	private GameObject player;

	public GunDirection(int x, int y, ID id) {
		super(x, y, id);
		mouse = new Point(0,0);
	}

	@Override
	public void tick() {

		if(player == null){
			player = Controller.getInstance().getObject(ID.Player).get(0);
		}
		
		if(isPointerAligned()){
			radians = isPointerUp() ? Math.PI/2 : Math.PI/2 + Math.PI;
		} else {
			radians = isPointerRight() ? Math.atan(getSlope()) : Math.atan(getSlope()) + Math.PI;
		}
		
		
		this.x = (player.x + 1 + (Player.PWIDTH /2)) + (int)(RADIUS * Math.cos(radians));
		this.y = (player.y + 1 + (Player.PHEIGHT /2)) + (int)(RADIUS * Math.sin(radians));
		
		if(timer > 0) timer--;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 10, 10);
	}

	@Override
	public void keyPressed(int k) {
	}

	@Override
	public void keyReleased(int k) {
	}
	
	private boolean isPointerRight() throws NullPointerException{
		if (mouse == null || player == null) throw new NullPointerException();
		else {
			return (mouse.getX() > player.getX());
		}
	}

	private boolean isPointerUp() throws NullPointerException{
		if (mouse == null || player == null) throw new NullPointerException();
		else {
			return (mouse.getY() > player.getY());
		}
	}	
	
	private boolean isPointerAligned() throws NullPointerException{
		if (mouse == null || player == null) throw new NullPointerException();
		else {
			return (mouse.getX() == player.getX());
		}
	}
	
	private double getSlope() throws NullPointerException{
		if (mouse == null || player == null) throw new NullPointerException();
		else {
			return ((mouse.getY() - player.getY()) / (mouse.getX() - player.getX()));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(timer <= 0){
			Controller.getInstance().addObject(new PlayerBullet(x, y, ID.Bullet, Math.cos(radians), Math.sin(radians)));
			timer = COOLDOWN;
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
