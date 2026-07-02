package game;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;



public class Timer extends GameObject {
	
	private final boolean lose;
	
	private int initial;
	private int time;
	private boolean stop;

	public Timer(int x, int y, ID id) {
		super(x, y, id);
		lose = true;
		time = 1000;
		initial = 1000;
	}
	
	public Timer(int x, int y, ID id, boolean lose) {
		super(x, y, id);
		this.lose = lose;
		time = 1000;
		initial = 1000;
	}
	
	public Timer(int x, int y, ID id, int time) {
		super(x, y, id);
		lose = true;
		this.time = time;
		initial = time;
	}	
	
	public Timer(int x, int y, ID id, boolean lose, int time) {
		super(x, y, id);
		this.lose = lose;
		this.time = time;
		initial = time;
	}

	@Override
	public void tick() {
		if(!stop){
			time--;
			if(time == 0 && lose) Controller.getInstance().FailState();
			else if (time == 0) Controller.getInstance().SuccessState();
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(lose) g.drawString("Time left : " + String.valueOf(time/100), x, y);
		else g.drawString("Survive for : " + String.valueOf(time/100), x, y);
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
		// TODO Auto-generated method stub
		return null;
	}

}
