package Oggetti;

import java.awt.Image;
import java.awt.image.ImagingOpException;

import javax.swing.ImageIcon;

public class Piece extends Ogetti implements Runnable{

	private int contattore ;
	private final int PAUSE = 10;
	public Piece(int X, int Y) {
		super(X, Y, 30 , 30);
		super.icoObj = new ImageIcon(this.getClass().getResource("/imagine/piece1.png"));
		super.imgObj = this.icoObj.getImage();
	}
	
	public Image muoviti(){
		
		ImageIcon ico ;
		Image img;
		String str ;
		this.contattore++;
		if(this.contattore / 100 == 0){
			str="/imagine/piece1.png";
		}else str = "/imagine/piec.png";
		if(this.contattore == 200 ) {this.contattore = 0;}
		ico = new ImageIcon(getClass().getResource(str));
		img =ico.getImage();
		return img;
	}

	@Override
	public void run() {
		
		try{Thread.sleep(10);}
	catch(InterruptedException e){}
			
		while(true){
			this.muoviti();
			try{Thread.sleep(PAUSE);}
			catch(InterruptedException e){}
		}
		
	}

}
