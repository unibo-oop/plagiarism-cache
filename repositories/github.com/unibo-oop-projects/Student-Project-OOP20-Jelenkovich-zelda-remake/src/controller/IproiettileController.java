package controller;

import java.util.List;

import javafx.scene.Node;
import view.ProiettileView;

public interface IproiettileController {
	
	/**
	 * creazione proiettile View
	 * @return
	 */
	List<ProiettileView> startProiettileView();
	
	/**
	 * creazione proiettili
	 * @param x
	 * @param y
	 * @param mousex
	 * @param mousey
	 */
	void createProiettile(double x, double y, double mousex, double mousey);

	/**
	 * rimozione proiettili dalla lista
	 * @param index
	 */
	void deleteProiettile(int index);
	
	/**
	 * creazione e ritorno della node list per la visualizzazione
	 * @return
	 */
	List<Node> getNodeList();
	
	/**
	 * implementazione del movimento automatico del proiettile 
	 * attraverso calcolo della traiettoria
	 */
	void move();
}
