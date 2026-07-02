package controller;

import java.util.List;

import javafx.scene.Node;
import model.people.Enemy;
import view.EnemyView;

public interface IenemyController {
	
	/**
	 * crea una enemyView list a partire dalla Enemy
	 * @return enemyView list
	 */
	List<EnemyView> startEnemyView();
	
	/**
	 * Creazione enemy
	 * @return enemy list
	 */
	List<Enemy> createEnemy();
	
	/**
	 * A partire dalla enemyView list crea una node list
	 * @return node list
	 */
	List<Node> getNodeList();
	
	/**
	 * prende in ingresso la posizione attuale dell'eroe e permette il movimento nelle 4 direzioni
	 * @param xhero
	 * @param yhero
	 */
	void move(int xhero, int yhero);
	
	/**
	 * 
	 * @return enemy view list
	 */
	List<EnemyView> getlistEnemyView();
	
	/**
	 * elimina attraverso la ricerca per indice un nemico dalla lista
	 * @param index
	 */
	void deleteEnemy (int index);
	
	/**
	 * 
	 * @return enemy list
	 */
	List<Enemy> getlistEnemy();

}
