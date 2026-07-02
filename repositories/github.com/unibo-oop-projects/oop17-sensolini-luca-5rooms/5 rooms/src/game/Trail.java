package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	
	
	
	private final Color color;
	private final int width;
	private final int height;

	private float life;
	private float alpha = 1;
	
	public Trail(int x, int y, int width, int height, Color color, float life) {
		super(x, y, ID.Particle);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if (alpha > life) alpha -= (life - 0.001f);
		else {
			Controller.getInstance().removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x, y, width, height);

	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type,  alpha));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
