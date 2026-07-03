package com.game.main;

import java.awt.Color;
import java.awt.Graphics;


public class HUD {
	
	public static int HEALTH = 300;
	private int greenValue = 255;
	private int score = 0;
	private int level = 1;
	private int wave = 1;
	
	public void tick(){
		HEALTH = (int)Game.clamp(HEALTH, 0, 300);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH*2;
		score++;
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 400, 600, 32);
		g.setColor(new Color(75, 255, 0));
		g.fillRect(15, 400, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 400, 600, 32);
		g.drawString("Score: " + score, 15, 16);
		g.drawString("Level: " + level, 15, 32);
		g.drawString("Current wave: " + wave, 15, 48);
	}

	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getWave(){
		return wave;
	}
	
	public void setWave(int wave){
		this.wave = wave;
	}
}
