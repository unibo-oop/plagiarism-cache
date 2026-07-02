package controller;

import java.io.IOException;

public interface IgameLoop {
	
	/**
	 * loop del livello attraverso l'uso di un animationTimer
	 */
	void go();
	
	/**
	 * movimento nemici
	 * @param flag per collisioni
	 */
	void moveEnemy(boolean flag);
	
	/**
	 * movimento proiettili
	 * @param flag per collisioni
	 */
	void moveProiettile(boolean flag);
	
	/**
	 * avvio controllo delle collisioni
	 * @param prevX
	 * @param prevY
	 * @throws IOException
	 */
	void checkCollision(int prevX, int prevY) throws IOException;
	
	/**
	 * risposta all'evento click del mouse per sparare proiettile
	 * @param mousex
	 * @param mousey
	 * @throws IOException
	 */
	void clickEvent(double mousex, double mousey) throws IOException;
	
	/**
	 * risposta all'evento pressione tasto W
	 * @throws IOException
	 */
	void upEvent() throws IOException;
	
	/**
	 * risposta all'evento pressione tasto D
	 * @throws IOException
	 */
	void rightEvent() throws IOException;
	
	/**
	 * risposta all'evento pressione tasto A
	 * @throws IOException
	 */
	void leftEvent() throws IOException;
	
	/**
	 * risposta all'evento pressione tasto S
	 * @throws IOException
	 */
	void downEvent() throws IOException;

}
