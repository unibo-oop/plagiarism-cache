package Oggetti;

import javax.swing.ImageIcon;

public class Blocco extends Ogetti{
	
	public Blocco(int X, int Y) {
		
		super(X, Y, 30 ,30);
		super.icoObj = new ImageIcon(getClass().getResource("/imagine/Blocco.png"));
		super.imgObj = super.icoObj.getImage();
	}

	
}
