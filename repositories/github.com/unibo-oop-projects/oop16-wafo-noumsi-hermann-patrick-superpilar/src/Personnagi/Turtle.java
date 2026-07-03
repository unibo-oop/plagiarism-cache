package Personnagi;

import java.awt.Image;

import javax.swing.ImageIcon;

import Oggetti.Ogetti;

public class Turtle extends Personnagi implements Runnable {
	
	private Image imgTurtle;
	private ImageIcon icoTurtle;
	
	private final int PAUSE = 15;
	private int dxTurtle; // passo spostamento turtle
	
	public Turtle(int X, int Y) {
		super(X, Y,43 , 50);
		super.setVerso_destra(true);
		super.setIn_movimento(true);
		this.dxTurtle = 1;
		this.icoTurtle = new ImageIcon(getClass().getResource("/imagine/turtleAD.png"));
		this.imgTurtle = icoTurtle.getImage();
		
		Thread chronoTurtle = new Thread(this);
		chronoTurtle.start();
	}

	//getters
	public Image getImgTurtle() {
		return imgTurtle;
	}
	
	//metodi
	
		public void muoviti(){ //spostamento turle
			if(super.isVerso_destra() == true){this.dxTurtle =1;}
			else{this.dxTurtle= -1; }
			super.setX(super.getX()+this.dxTurtle);
			
		}
	
		@Override
		public void run() {
			try{Thread.sleep(20);}
			catch(InterruptedException e){}
			
			while(true){
				if(this.vivo == true){
					this.muoviti();
					try{Thread.sleep(PAUSE);}
					catch(InterruptedException e){}
					}
				}
		}
		
		public void contact (Ogetti obj){
			if(super.contactAvanti(obj) == true && this.isVerso_destra() == true){
				super.setVerso_destra(false);
				this.dxTurtle = -1;
			}else if (super.contactIndietro(obj)==true && this.isVerso_destra() ==false){
				super.setVerso_destra(true);
				this.dxTurtle = 1;
			}
		}
		
		public void contact (Personnagi pers){
			if(super.contactAvanti(pers) == true && this.isVerso_destra() == true){
				super.setVerso_destra(false);
				this.dxTurtle = -1;
			}else if (super.contactIndietro(pers)==true && this.isVerso_destra() ==false){
				super.setVerso_destra(true);
				this.dxTurtle = 1;
			}
		}
		 
		public Image muore(){		
				String str = "/imagine/turtleF.png";	
				ImageIcon ico;
				Image img;		
		        ico = new ImageIcon(getClass().getResource(str));
		        img = ico.getImage();
				return img;
		}
}
