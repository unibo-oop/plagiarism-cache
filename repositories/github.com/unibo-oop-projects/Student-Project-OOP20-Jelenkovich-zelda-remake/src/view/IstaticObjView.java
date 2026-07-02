package view;

import javafx.scene.shape.Rectangle;

public interface IstaticObjView {
	
	/**
	 * settaggio coordinate x e y
	 * @param x
	 * @param y
	 */
	void setObjPos(final int x, final int y);
	
	/**
	 * settaggio dimensioni
	 * @param width
	 * @param height
	 */
	void setObjDim(final int width, final int height);
	
	/**
	 * settaggio path immagine
	 * @param img
	 */
	void setImg(String img);
	
	/**
	 * 
	 * @return rettangolo
	 */
	Rectangle getObj();

}
