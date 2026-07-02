package view;

import javafx.scene.shape.Rectangle;

public interface IdinamicObjView {
	
	/**
	 * settaggio larghezza
	 * @param width
	 */
	void setWidth(int width);
	
	/**
	 * settaggio altezza
	 * @param height
	 */
	void setHeight(int height);
	
	/**
	 * settaggio path immagine
	 * @param img
	 */
	void setImg(String img);
	
	/**
	 * 
	 * @return rettangolo
	 */
	Rectangle getRect();
	

}
