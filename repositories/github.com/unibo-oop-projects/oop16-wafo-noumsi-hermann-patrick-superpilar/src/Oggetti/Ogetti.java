package Oggetti;

import java.awt.Image;

import javax.swing.ImageIcon;

import Gioco.Main;

public class Ogetti {
	
	private int l , h ; //dimensio
	private int x ,y; // posizione
	
	protected Image imgObj ;
	protected ImageIcon icoObj;
	
	public Ogetti(int X, int Y , int L , int H){
			
		this.x = X;
		this.y = Y;
		this.l = L; 
		this.h= H ;
	}


	// GETTERS
	public int getL() {
		return l;
	}

	public int getH() {
		return h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Image getImgObj() {
		return imgObj;
	}


		//SETTERS
	public void setL(int l) {
		this.l = l;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// metodi
	
	public void spostamenti (){
		
		if(Main.scene.getxPos() >= 0){
			this.x = this.x - Main.scene.getMov();
		}
	}
	
}
