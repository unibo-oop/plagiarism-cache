package it.bomberman.states;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuState extends GameState implements KeyListener {
	private static final long serialVersionUID = 1L;
	// dimensions
	public final int WIDTH = 600;
	public final int HEIGHT = 300;
	public final int SCALE = 3;
	public static String NAME = "BOMBERMAN";
	private ImageIcon ii;
	JLabel imageLabel;
	private GameStateManager gsm;

	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Font font;
	private volatile int menuX;
	private volatile boolean directionFlag = false;

	public MenuState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
	}

	public void init() {
		imageLabel = new JLabel();
		// Prende la gif e aggiunge
		try {
			this.setLayout(new BorderLayout());
			ii = new ImageIcon(this.getClass().getResource("/textures/bg2.gif"));
			ii.setImage(ii.getImage().getScaledInstance(WIDTH * SCALE, HEIGHT * SCALE, Image.SCALE_DEFAULT));
			imageLabel.setIcon(ii);
			this.add(imageLabel, java.awt.BorderLayout.CENTER);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		try {
			titleColor = new Color(250, 250, 250);
			titleFont = new Font("Century Gothic", Font.PLAIN, 60);
			font = new Font("Arial", Font.PLAIN, 40);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void update() {
		if (directionFlag)
			menuX--;
		else
			menuX++;
		if (menuX == 70)
			directionFlag = true;
		if (menuX == 0)
			directionFlag = false;
		repaint();
	}

	private void select() {
		switch (currentChoice) {
		case 0:
			this.gsm.setState(GameStateManager.ARENA);
			break;
		case 1:
			this.gsm.setState(GameStateManager.HELP);
			break;
		case 2:
			System.exit(0);
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void draw(Graphics g2) {
		// gsm.draw(g);
		g2.drawImage(ii.getImage(), WIDTH * SCALE, HEIGHT * SCALE, null);

		g2.setColor(titleColor);
		g2.setFont(titleFont);
		g2.drawString(NAME, 650 + menuX, 200);
		Toolkit.getDefaultToolkit().sync();

		// draw menu options
		g2.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g2.setColor(Color.CYAN);
			} else {
				g2.setColor(Color.WHITE);
			}
			g2.drawString(options[i], 820, 300 + i * 55);
		}
		Toolkit.getDefaultToolkit().sync();
		// g2.dispose();
	}

	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_ENTER) {
			select();
		}
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}