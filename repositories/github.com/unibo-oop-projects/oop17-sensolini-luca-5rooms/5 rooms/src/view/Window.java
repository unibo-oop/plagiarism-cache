package view;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.Game;

public class Window extends Canvas {

	/**
	 * A simple class for creating windows.
	 */
	private static final long serialVersionUID = -7652399833422083103L;
	

	public Window (int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.add(game);
		frame.setVisible(true);
	}
	
	public Window (int width, int height, String title, View view){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(view);
		frame.setVisible(true);
		view.start();
	}

}
