package it.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.bomberman.gfx.Assets;

public class HelpState extends GameState implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameStateManager gsm;
	private int currentChoice = 0;
	private Font fontTitle, fontTitle2, fontPar, fontCh;

	private String[] options = { "Start Game", "Quit" };

	public HelpState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
	}

	@Override
	public void init() {
		try {
			fontTitle = new Font("Century Gothic", Font.PLAIN, 95);
			fontTitle2 = new Font("Century Gothic", Font.PLAIN, 65);
			fontPar = new Font("Century Gothic", Font.PLAIN, 18);
			fontCh = new Font("Century Gothic", Font.PLAIN, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFocusable(true);
		requestFocus();
	}

	private void select() {
		switch (currentChoice) {
		case 0:
			this.gsm.setState(GameStateManager.ARENA);
			break;
		case 1:
			System.exit(0);
			break;
		}
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 1800, 900);
		g.setFont(fontTitle);

		g.setColor(Color.BLACK);
		g.fillRect(600, 700, 500, 500);

		g.setColor(Color.WHITE);
		g.drawRect(600, 700, 500, 500);
		g.setColor(Color.WHITE);
		g.drawString("---BomberMan Rules---", 360, 100);

		g.setFont(fontTitle2);
		g.drawString("Players: ", 120, 170);
		g.drawString("Upgrades:", 1150, 170);
		g.drawString("Walls:", 120, 500);

		// PLAYER DRAW
		g.setFont(fontPar);
		g.drawString("PLAYER 1 KEYS: ", 40, 200);
		g.drawImage(Assets.player_d[1], 40, 180, 150, 150, null);
		g.drawString("WASD to move ", 40, 360);
		g.drawString("SPACE to drop the bomb", 40, 380);
		// Player2
		g.drawString("PLAYER 2 KEYS: ", 280, 200);
		g.drawImage(Assets.player_d2[1], 280, 180, 150, 150, null);
		g.drawString("ARROW to move", 280, 360);
		g.drawString("ENTER to drop the bomb", 280, 380);

		// WALLS
		g.drawImage(Assets.simpleWall, 40, 520, 150, 150, null);
		g.drawString("This is a Simple wall:", 20, 680);
		g.drawString("you can destroy it with", 20, 700);
		g.drawString("your bomb and sometimes", 20, 720);
		g.drawString("it will drop upgrades!", 20, 740);

		g.drawImage(Assets.wall, 280, 520, 150, 150, null);

		g.drawString("This is a Wall:", 280, 680);
		g.drawString("you can't destroy it...", 280, 700);
		g.drawString("It delimits the playing area", 280, 720);

		// UPGRADE
		g.drawImage(Assets.upgrade[0], 1150, 200, 150, 150, null);
		g.drawString("The BOMBERMAN SHOES: ", 1300, 220);
		g.drawString("Put your fantastic new sneakers on your feet ", 1300, 240);
		g.drawString("and run fast the the explosion!", 1300, 260);
		g.drawString("INCREASE YOUR SPEED BY 1", 1300, 300);

		g.drawImage(Assets.upgrade[1], 1150, 360, 150, 150, null);
		g.drawString("The BOMBERMAN BOMB: ", 1300, 380);
		g.drawString("Drop only one bomb is boring...", 1300, 400);
		g.drawString("Now your pocket is bigger!", 1300, 420);
		g.drawString("INCREASE YOUR BOMB NUMBER BY 1", 1300, 460);

		g.drawImage(Assets.upgrade[2], 1150, 520, 150, 150, null);
		g.drawString("The BOMBERMAN HEART: ", 1300, 540);
		g.drawString("playing with bombs is not safe...", 1300, 560);
		g.drawString("But now you can play more safety!", 1300, 580);
		g.drawString("INCREASE YOUR LIFE BY 1", 1300, 620);

		g.drawImage(Assets.upgrade[3], 1150, 680, 150, 150, null);
		g.drawString("The BOMBERMAN CANDLE STICK: ", 1300, 700);
		g.drawString("Does your enemy run faster than you?", 1300, 720);
		g.drawString("Now you will have no more problems!", 1300, 740);
		g.drawString("INCREASE YOUR EXPLOSION RANGE BY 1", 1300, 780);

		
		g.drawString("Bomberman is an arcade game in which two or more players compete",560, 200);
		g.drawString("against each other trying to make the opponent lose by blowing him",560, 220);
		g.drawString("up with bombs.",560, 240);
		g.drawString("The game ends when only one player is left with lives or when ",560, 260);
		g.drawString("the game timer expires.",560, 280);
		g.drawString("There are breakable and unbreakable wall.",560, 300);
		g.drawString("Breakable walls can be destroyed using bombs.",560, 320);
		g.drawString("A destoyed wall can drop upgrades.",560, 340);
		g.drawString("Each bomb explodes a few seconds after it was dropped ",560, 360);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 29));
		g.drawString("CHOOSE STARTGAME AND HAVE FUN!!!",560, 540);
		
		
		g.setFont(fontCh);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			if (i == 0) {
				g.drawString(options[i], 780, 800 + i * 75);
			} else {
				g.drawString(options[i], 820, 800 + i * 75);
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			select();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
