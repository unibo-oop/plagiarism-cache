package it.bomberman.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import it.bomberman.hud.Hud;
import it.bomberman.input.KeyManager;

public class ArenaState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KeyManager keyManager;
	private final Hud hud;
	private ArenaModel arenaModel;
	private Color backgroundColor;
	// private Winner winner;

	public ArenaState(GameStateManager gsm) {
		this.gsm = gsm;
		this.keyManager = new KeyManager();
		this.arenaModel = new ArenaModelImpl(this.keyManager);
		// this.winner= new Winner(this.arenaModel);
		this.hud = new Hud(this.arenaModel.getP1(), this.arenaModel.getP2(), this.arenaModel.getClock());
	}

	public void init() {
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH * 3, HEIGHT * 3));
		setFocusable(true);
		requestFocus();
		backgroundColor = new Color(0, 153, 0);
	}

	@Override
	public void update() {
		this.keyManager.tick();
		this.arenaModel.update();
		this.hud.update(); // (String Model.getClock())
		repaint();

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 1800, 900);
		Toolkit.getDefaultToolkit().sync();

//		p1.render(g);
//		p2.render(g);
		if (arenaModel.gameOver()) {
			this.gsm.setWinner(this.arenaModel.getWinner());
			this.gsm.setState(GameStateManager.WINNER);
			//this.gsm.update();
		} else {
			this.arenaModel.getDrawables().stream().forEach(e -> e.render(g));
			Toolkit.getDefaultToolkit().sync();
			this.hud.render(g);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addKeyListener(this.keyManager);
	}
}
