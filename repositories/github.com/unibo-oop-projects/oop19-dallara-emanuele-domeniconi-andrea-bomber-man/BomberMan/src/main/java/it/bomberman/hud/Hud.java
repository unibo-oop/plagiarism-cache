package it.bomberman.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import it.bomberman.entities.Player;
import it.bomberman.gfx.Assets;

public class Hud {

	private Player p1;
	private Player p2;
	private Font hudFont;
	private Clock clock;

	public Hud(Player p1, Player p2, Clock clock) {
		this.p1 = p1;
		this.p2 = p2;
		this.init();
		this.clock= clock;
	}

	public void init() {
		try {
			hudFont = new Font("Century Gothic", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//(String GetTime
	public void update() {
		this.p1.getHealth();
		this.p2.getHealth();
		this.p1.getSpeed();
		this.p2.getSpeed();
		this.p1.getBombsNumber();
		this.p2.getBombsNumber();	
		this.p1.getBombExtension();
		this.p2.getBombExtension();	
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 1800, 70);
		g.setColor(Color.BLACK);
		g.setFont(hudFont);
		g.fillRect(0, 0, 1800, 70);
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 300, 70);
		g.fillRect(1550, 0, 500, 70);
		// g.fillRect(10,20,100,150);
		// P1
		g.setColor(Color.WHITE);
		g.drawImage(Assets.hud[0], 320, 0, 72, 70, null);
		g.drawString("P1 Stats: ", 400, 30);
		g.drawString("Bombs: "+ this.p1.getBombsNumber(), 650, 30);
		g.drawString("Extension: " + this.p1.getBombExtension(), 650, 60);
		g.drawString("Life: " + this.p1.getHealth(), 550, 30);
		g.drawString("Speed: " + this.p1.getSpeed(), 550, 60);

		// P2
		g.drawImage(Assets.hud[1], 980, 0, 72, 70, null);
		g.drawString("P2 Stats: ", 1100, 30);
		g.drawString("Bombs: "+ this.p2.getBombsNumber(), 1350, 30);
		g.drawString("Extension: " + this.p2.getBombExtension(), 1350, 60);
		g.drawString("Life: " + this.p2.getHealth(), 1250, 30);
		g.drawString("Speed: " + this.p2.getSpeed(), 1250, 60);
		//Clock
		g.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		g.drawString(clock.getTime()+"", 830, 45);
		g.drawRect(800, 0, 120, 70);
	}
}
