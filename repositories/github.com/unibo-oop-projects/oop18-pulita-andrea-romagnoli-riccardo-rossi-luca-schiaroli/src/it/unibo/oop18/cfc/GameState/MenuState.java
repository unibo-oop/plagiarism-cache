// The main menu GameState.

package it.unibo.oop18.cfc.GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import it.unibo.oop18.cfc.Manager.Content;
import it.unibo.oop18.cfc.Manager.GameStateManager;
import it.unibo.oop18.cfc.Util.JukeBox;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage food;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"OPTIONS",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm, GameStates.MENU);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		food = Content.FOOD[6][2];
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		//handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 350, 350);
		Content.drawString(g, options[1], 350, 410);
		Content.drawString(g, options[2], 350, 470);
		
		if(currentOption == 0) g.drawImage(food, 280, 350, null);
		else if(currentOption == 1) g.drawImage(food, 280, 410, null);
		else if(currentOption == 2) g.drawImage(food, 280, 470, null);
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gsm.newGame();
		}
		if(currentOption == 2) {
			System.exit(0);
		}
	}
	
	public void goUp() {
		if(currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
		}
	}
	
	public void goDown() {
		if(currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
	}
	
	public void select() {
		JukeBox.play("collect");
		selectOption();
	}
	
}
