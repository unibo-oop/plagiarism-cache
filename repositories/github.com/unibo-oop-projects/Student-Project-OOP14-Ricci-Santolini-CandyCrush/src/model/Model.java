package model;

import controller.Utility;

/**
 * Classe princpale del model, gestisce elementi fondamentali per la partita come mosse e punteggi.
 * 
 * @author Nicola Santolini
 *
 */
public class Model implements IModel {
	
	private final IBoard board;
	
	private int target;
	private int moves;
	private int score;
	
	/**
	 * Costruttore.
	 */
	public Model() {	
		this.board = new Board();
	}
	
	@Override
	public void decMoves() {
		this.moves--;
	}
	
	@Override
	public int getScore() {
		return this.score;
	}
	
	@Override
	public int getMoves() {
		return this.moves;
	}
		
	@Override
	public int getTarget() {
		return this.target;
	}
			
	@Override
	public int getColor(final int i, final int j) {
		return board.getColor(i, j);
	}	
	
	@Override
	public int getTypeEl(final int i, final int j) {
		return board.getTypeEl(i, j);
	}
	
	@Override
	public void setMoves(final int num) {
		this.moves = num;
	}
	
	@Override
	public void incScore(final int num) {
		this.score = this.getScore() + num;
	}
	
	@Override
	public void setTarget(final int num) {
		this.target = num;
	}
	
	@Override
	public  boolean checkExchange(final int x1, final int y1, final int x2, final int y2) {
		if (x2 == x1 - 1 && y1 == y2 
			|| x1 == x2 && y2 == y1 - 1 
			|| x1 == x2 && y2 == y1 + 1 
			|| x2 == x1 + 1 && y1 == y2) { 
			return true;
		}
		return false;
	}
	
	@Override
	public void doExchange(final int x1, final int y1, final int x2, final int y2) {
		board.doExchange(x1, y1, x2, y2);
	}
		
	@Override
	public boolean goOn() {
		return board.checkTris(); 	
	}
	
	@Override
	public boolean checkNextMove() {
		return board.checkNextMove();
	}
	
	@Override
	public void gameLoop() {
		incScore(board.gameLoop());
		
		while (!board.checkNextMove()) {
			board.shuffle();
		}
	}
	
	@Override
	public void shuffle() {
		board.shuffle();
	}
	
	@Override
	public boolean isUsingSpecial(final int x1, final int y1, final int x2, final int y2) {
		
		if (board.getTypeEl(x1, y1) == Utility.FIVE
				|| board.getTypeEl(x2, y2) == Utility.FIVE) {
			return true;
		}
		return false;
	}
	
	@Override
	public void makeSpecial(final int x1, final int y1, final int x2, final int y2) {
		
		if (board.getTypeEl(x1, y1) == Utility.FIVE) {
			board.setColor(x1, y1, ModelUtilities.generate());
			board.setType(x1, y1, Utility.NORMAL);
			/*salvo in una variabile il numero di caramelle dello stesso colore di quella che è stata accoppiata,
			che viene ritornato dal metodo doFive(), e per ognuna attribuisco punti bonus*/
			final int n = board.doFive(board.getColor(x2, y2));
			this.incScore(n * Utility.BONUS_POINTS);
		}
		if (board.getTypeEl(x2, y2) == Utility.FIVE) {
			board.setColor(x2, y2, ModelUtilities.generate());
			board.setType(x2, y2, Utility.NORMAL);
			final int n = board.doFive(board.getColor(x1, y1));
			this.incScore(n * Utility.BONUS_POINTS);
		}
		
	}	
}