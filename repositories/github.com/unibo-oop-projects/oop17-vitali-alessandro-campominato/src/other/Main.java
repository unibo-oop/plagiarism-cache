package other;

import panel.BoardGameView;

/**
 * classe avviata allo start dell'applicazione
 * 
 * @author Alessandro
 *
 */
public class Main {
	
	public static BoardGameView boardGameView;
	
	/**
	 * viene creato un elemento statico, il frame dell'applicazione
	 * 
	 * @param args
	 *     non utilizzato
	 */
	public static void main(String[] args) {
		boardGameView = new BoardGameView();	
	}

}
