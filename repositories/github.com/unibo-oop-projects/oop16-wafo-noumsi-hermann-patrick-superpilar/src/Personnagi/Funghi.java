package Personnagi;

import java.awt.Image;

import javax.swing.ImageIcon;

import Oggetti.Ogetti;

public class Funghi extends Personnagi implements Runnable{

	private Image imgFunghi;
	private ImageIcon icoFunghi;
	
	private final int PAUSE = 15;
	private int dxFunghi;
	
	public Funghi(int X, int Y) {
		super(X, Y,27 , 30);
		super.setVerso_destra(true);
		super.setIn_movimento(true);
		this.dxFunghi = 1;
		this.icoFunghi = new ImageIcon(getClass().getResource("/imagine/funghiAD.png"));
		this.imgFunghi = icoFunghi.getImage();
		
		Thread chronoFunghi = new Thread(this);
		chronoFunghi.start();
	}

	//getters
	public Image getImgFunghi() {
		return imgFunghi;
	}

	
	//setters
	
	
	//metodi
	
	public void muoviti(){
		if(super.isVerso_destra() == true){this.dxFunghi =1;}
		else{this.dxFunghi= -1; }
		super.setX(super.getX()+this.dxFunghi);
		
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
			this.dxFunghi = -1;
		}else if (super.contactIndietro(obj)==true && this.isVerso_destra() ==false){
			super.setVerso_destra(true);
			this.dxFunghi = 1;
		}
	}
	
	public void contact (Personnagi pers){
		if(super.contactAvanti(pers) == true && this.isVerso_destra() == true){
			super.setVerso_destra(false);
			this.dxFunghi = -1;
		}else if (super.contactIndietro(pers)==true && this.isVerso_destra() ==false){
			super.setVerso_destra(true);
			this.dxFunghi = 1;
		}
	}
	
	  public Image muore(){		
			String str;	
			ImageIcon ico;
			Image img;
	        if(this.isVerso_destra() == true){str = "/imagine/funghiED.png";}
	        else{str = "/imagine/funghiEG.png";}		
	        ico = new ImageIcon(getClass().getResource(str));
	        img = ico.getImage();
			return img;
		}
	

	
}
