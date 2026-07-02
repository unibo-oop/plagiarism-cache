package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
import model.CheckerBoardShadow.piecesType;

public class DamaBox extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1359251019723175129L;

	public DamaBox() {
		//richiama costruttore JButton
		super();
	}
/**
 * retrieve the img of the piece
 * @param  type of piece : whitepiece, blackpiece,  whiteDama, blackDama, Empty
 */
	public void SetPiece(piecesType type) {

		if(type==piecesType.WP)
		{
			resize("src/img/whitePiece.png");
		}
		else if(type==piecesType.BP){
			resize("src/img/blackPiece.png");	
		}
		else if(type==piecesType.WD){
			resize("src/img/whiteDama.png");	
		}
		else if(type==piecesType.BD){
			resize("src/img/blackDama.png");
		}else {
			this.setIcon(null);
		}


	}
/**
 * 
 * @param obj resize the img of the piece to adpat it on the checkerBoard
 */
	private void resize(String obj) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon(obj);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(newimg));
	}



}
