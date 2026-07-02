package controller;

import java.io.IOException;

public interface IheroGoController {
	
	/**
	 * aggiunge un proiettile per la visualizzazione
	 */
	void addProiettile();
	
	/**
	 * 
	 * @return ObjController
	 */
	IobjectController getObjController();
	
	/**
	 * 
	 * @return heroController
	 */
	HeroController getHeroController();
	
	/**
	 * 
	 * @return EnemyController
	 */
	IenemyController getEnemyController();
	
	/**
	 * Controllo collisioni tra eroe e oggetti statici
	 * @param prevX
	 * @param prevY
	 * @throws IOException
	 */
	void checkCollision(int prevX, int prevY) throws IOException;
	
	/**
	 * Controllo collisioni tra proiettili e oggetti statici, 
	 * 						tra proiettili e nemici
	 * @return
	 */
	boolean checkCollisionProiettili();
	
	/**
	 * Controllo collisione tra nemici e oggetti statici
	 * 						tra nemici e eroe
	 * @return
	 */
	boolean checkCollisionEnemy();
	
	/**
	 * Controllo sui movimenti concessi all'eroe
	 * @param xHero
	 * @param yHero
	 * @param prevX
	 * @param prevY
	 */
	void chekDir(double xHero, double yHero, double prevX, double prevY);
	
	/**
	 * aggiornamento della vita dell'eroe
	 */
	void updateLife();
	
	/**
	 * Controllo sull'uscita dal livello
	 */
	void checkNextLevel();
	
	/**
	 * Controllo sulla vita
	 */
	void checkEnd();

}
