package it.bomberman.menu;

import java.awt.Graphics;
import javax.swing.JFrame;

import it.bomberman.states.GameStateManager;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameStateManager gsm;

	public MenuView() {
		gsm= new GameStateManager(this);
		this.setName("BomberMan Menu");
		//this.setContentPane(new MenuPanel(gsm));
		this.gsm.setState(GameStateManager.MENUSTATE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		

		this.setVisible(true);
		//this.gsm = gsm;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw();
	}
	
	public void draw() {
		
	}

	public void update() {
		this.gsm.update();
	}
}
