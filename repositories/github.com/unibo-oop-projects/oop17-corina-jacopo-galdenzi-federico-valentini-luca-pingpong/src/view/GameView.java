package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.GameController;
import utilities.Utility;

public class GameView extends JPanel {
	private GameController controllerGame;
	private JLabel time=new JLabel("");
	private JLabel score = new JLabel("");
	private Rectangle racket1;
	int width;
	int height;
	public GameView() {
		this.add(time);
		this.add(score);
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);	
		width = (int) Utility.getFrameDimension(this).getWidth();
		height = (int) Utility.getFrameDimension(this).getHeight();
		this.racket1 = DrawRect.drawRacket((width*2)/100, (height*30)/100,(width*1)/100 , (height*30)/100);
		g2.fill(racket1);
	}

	
	public void moveRacket(){
		
	}
	public void showRemainingTime(int n) {
		time.setText(String.valueOf(n));
	}
	public void setScore(int p1, int p2) {
		score.setText(String.valueOf(p1)+" - "+String.valueOf(p2));
	}
}

