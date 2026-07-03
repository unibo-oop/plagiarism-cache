package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;
import com.game.support.Fonts;
import com.game.support.ID;

public class Menu extends MouseAdapter implements Tickable{
	
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private boolean colored = false;
	Fonts fontsInstance = new Fonts();
	public Menu(Game game, Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(Game.gameState == STATE.Menu){
			hud.setLevel(1);
			hud.setScore(0);
			hud.setWave(1);
		//play button
		if(mouseOver(mx, my, Game.WIDTH/2-100, 150, 200, 64)){
			Game.gameState = STATE.Game;
			if(colored){
				handler.addObject(new PlayerCustom((Game.WIDTH/2-32),(Game.HEIGHT/2-32),ID.Player, handler, Game.colorPlayer));	
			} else {
				handler.addObject(new PlayerCustom((Game.WIDTH/2-32),(Game.HEIGHT/2-32),ID.Player, handler, Color.white));
			}
			handler.clearEnemys();
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32),r.nextInt(Game.PLAYABLEHEIGHT-32), ID.SmartEnemy, handler));
		}
		//help button
		if(mouseOver(mx, my, Game.WIDTH/2-100, 250, 200, 64)){
			Game.gameState = STATE.Help;
		}
		//quit button
		if(Game.gameState == STATE.Menu){
		if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64)){
			System.exit(1);
		}}}
		//Color selection
		if(Game.gameState == STATE.Help){
			if(mouseOver(mx, my, 200, 275, 32, 32)){
				Game.colorPlayer = Color.white;
				colored = true;
			}
			if(mouseOver(mx, my, 250, 275, 32, 32)){
				Game.colorPlayer = Color.cyan;
				colored = true;
			}
			if(mouseOver(mx, my, 300, 275, 32, 32)){
				Game.colorPlayer = Color.green;
				colored = true;
			}
			if(mouseOver(mx, my, 350, 275, 32, 32)){
				Game.colorPlayer = Color.red;
				colored = true;
			}
			if(mouseOver(mx, my, 400, 275, 32, 32)){
				Game.colorPlayer = Color.blue;
				colored = true;
			}
		}
		//back button from help
		if(Game.gameState == STATE.Help){
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64)){
				Game.gameState = STATE.Menu;
			}
		}
		if(Game.gameState == STATE.End){
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64)){
				Game.gameState = STATE.Menu;
				}
		}
	}
	
	public void mouseReleased(MouseEvent e){	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick(){	}
	
	public void  render(Graphics g){
		if(Game.gameState == STATE.Menu){
		g.setColor(Color.WHITE);
		Font fnt = fontsInstance.newFont(36);
		g.setFont(fnt);
		g.drawString("ENDLESS 80s", 145, 90);
		Font fnt2 = fontsInstance.newFont(24);
		g.setFont(fnt2);
		g.drawString("Play", 280, 195);
		g.drawString("Help", 280, 295);
		g.drawString("Quit", 280, 395);
		g.drawRect(Game.WIDTH/2-100, 150, 200, 64);
		g.drawRect(Game.WIDTH/2-100, 250, 200, 64);
		g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
		} else if (Game.gameState == STATE.Help){
			g.setColor(Color.WHITE);
			Font fnt = fontsInstance.newFont(36);
			g.setFont(fnt);
			g.drawString("HELP", 245, 90);
			Font fnt2 = fontsInstance.newFont(24);
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
			g.drawString("Back", 280, 395);
			g.drawString("Use WASD to dodge enemies!", 50, 200);
			g.drawString("Select Color", 200, 250);
			g.setColor(Color.white);
			g.fillRect(200, 275, 32, 32);
			g.setColor(Color.CYAN);
			g.fillRect(250, 275, 32, 32);
			g.setColor(Color.green);
			g.fillRect(300, 275, 32, 32);
			g.setColor(Color.red);
			g.fillRect(350, 275, 32, 32);
			g.setColor(Color.blue);
			g.fillRect(400, 275, 32, 32);
			g.setColor(Color.white);
		}else if (Game.gameState == STATE.End){
			g.setColor(Color.WHITE);
			Font fnt = fontsInstance.newFont(36);
			g.setFont(fnt);
			g.drawString("Game Over", 180, 90);
			Font fnt2 = fontsInstance.newFont(24);
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-150, 350, 300, 64);
			g.drawString("Back to Menu", 195, 395);
			g.drawString("You lost with a score of: " + hud.getScore(), 35, 200);
			g.drawString("You lost against wave : " + hud.getWave(), 35, 250);
		} else if (Game.gameState == STATE.WaveEnd){
			g.setColor(Color.white);
			Font fnt = fontsInstance.newFont(24);
			g.setFont(fnt);
			g.drawString("End Wave " + (hud.getWave()-1), 210, 120);
			Font fnt2 = fontsInstance.newFont(18);
			g.setFont(fnt2);
			g.drawString("Press space to continue", 130, 250);
			}
	}

}
