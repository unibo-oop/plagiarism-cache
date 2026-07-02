package model;

import controller.Utility;

/**
 * Classe che implementa l'interfaccia della matrice di gioco.
 * 
 * @author Nicola Santolini
 */
public class Board implements IBoard {
	
	private final ICandy[][] mat = new Candy[Utility.DIM1][Utility.DIM2];

	private final IGame game;
	
	/**
	 * Costruttore.
	 */
	public Board() {
		
		for (int i = 0; i < Utility.DIM1; i++) {	
			for (int j = 0; j < Utility.DIM2; j++) {
				mat[i][j] = new Candy(ModelUtilities.generate());
			}
		}
		this.game = new Game();
		
		creation();
	}
	
	@Override
	public int getColor(final int i, final int j) {
		return this.mat[i][j].getColorNumber();
	}	
		
	@Override
	public int getTypeEl(final int i, final int j) {
		return this.mat[i][j].getType();
	}
	
	@Override
	public void setColor(final int x, final int y, final int c) {
		mat[x][y].setColorNumber(c);
	}
	
	@Override
	public void setType(final int x, final int y, final int t) {
		mat[x][y].setType(t);
	}
	
	@Override
	public void doExchange(final int x1, final int y1, final int x2, final int y2) {
		final Candy app = new Candy();

		app.setColorNumber(mat[x1][y1].getColorNumber());
		app.setType(mat[x1][y1].getType());
		
		mat[x1][y1].setColorNumber(mat[x2][y2].getColorNumber());
		mat[x1][y1].setType(mat[x2][y2].getType());
		
		mat[x2][y2].setColorNumber(app.getColorNumber());
		mat[x2][y2].setType(app.getType());
	}
	
	/**
	 * Metodo che dopo la prima inizializzazione della matrice di gioco la controlla 
	 * e rimescola finchè non si trova in una situazione corretta per iniziare a giocare: 
	 * nessun tris già in posizione ma almeno uno realizzabile in una mossa.
	 */
	private void creation() {
		
		while (!checkNextMove() || checkTris()) {
			ModelUtilities.shuffle(this.mat);
		}
	}
	
	@Override
	public int gameLoop() {
		return game.gameLoop(this.mat);
	}
	
	@Override
	public boolean checkTris() {
		return game.checkTris(this.mat);
	}
	
	@Override
	public boolean checkNextMove() {
		return game.checkNextMove(this.mat);
	}
	
	@Override
	public int doFive(final int c) {
		return game.doFive(this.mat, c);
	}
	
	@Override
	public void shuffle() {
		ModelUtilities.shuffle(this.mat);
	}
}
