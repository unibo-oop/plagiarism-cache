 package Oggetti;

import javax.swing.ImageIcon;

public class Tunello extends Ogetti {

	public Tunello(int X, int Y) {
		
		super(X, Y, 43 ,65);
		super.icoObj = new ImageIcon(getClass().getResource("/imagine/tunello.png"));
		super.imgObj = super.icoObj.getImage();
	}

}
