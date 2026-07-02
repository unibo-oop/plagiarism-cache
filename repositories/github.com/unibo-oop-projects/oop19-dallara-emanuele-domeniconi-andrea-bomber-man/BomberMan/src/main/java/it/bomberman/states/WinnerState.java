package it.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import it.bomberman.entities.Player;
import it.bomberman.gfx.Assets;

public class WinnerState extends GameState implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameStateManager gsm;
	private Optional<Player> player;
	private Font fontTitle, fontPar;
	private int currentChoice = 0;
	private String[] options = { "Retry", "Quit" };

	public WinnerState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
		this.player = this.gsm.getWinner();
	}
	
	private void select() {
		switch (currentChoice) {
		case 0:
			this.gsm.setState(GameStateManager.ARENA);
			this.gsm.update();
			break;
		case 1:
			System.exit(0);
			break;
		}
	}

	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	@Override
	public void init() {
		try {
			fontTitle = new Font("Century Gothic", Font.PLAIN, 100);
			fontPar = new Font("Century Gothic", Font.PLAIN, 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 1800, 900);
		g.setFont(fontTitle);
		g.setColor(Color.black);
		g.drawRect(500, 200, 800, 500);
		g.fillRect(500, 200, 800, 500);
		if (player.isEmpty()) {
			g.setColor(Color.white);
			g.drawString("Draw", 750, 300);
		} else {
			Player p = player.get();
			g.setColor(Color.WHITE);
			g.drawString("Player " + p.getPlayerNumb() + " Win", 600, 300);
		}
		g.drawImage(Assets.player_d[1], 400, 300, 500, 500, null);
		g.drawImage(Assets.player_d2[0], 1150, 300, 500, 500, null);
	
		g.setFont(fontPar);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 820, 400 + i * 75);
		}
		Toolkit.getDefaultToolkit().sync();
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
