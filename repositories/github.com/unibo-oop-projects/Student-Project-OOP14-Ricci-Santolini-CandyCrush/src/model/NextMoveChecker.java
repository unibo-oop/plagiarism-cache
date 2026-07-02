package model;

import controller.Utility;

/**
 * Classe che contiene i metodi necessari a eseguire i controlli sulla matrice
 * di gioco che garantiscano che sia sempre possibile realizzare almeno una combinazione 
 * in una sola mossa con il set di caramelle attuali, e evita quindi che la partita non
 * possa più proseguire.
 * 
 * @author Nicola Santolini
 *
 */
public class NextMoveChecker {
	
	/**
	 * Metodo che verif ica la possibilità di completare almeno una mossa 
	 * col set di caramelle attuale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è possibile realizzare almeno una combinazione in verticale e/o
	 * orizzontale in una sola mossa
	*/
	public boolean checkNextMove(final ICandy[][] mat) {
		if (checkNextMoveHorizontal(mat) || checkNextMoveVertical(mat)) {
			return true;
		}		
		return false;
	}
	
	/**
	 * Metodo che percorre più volte la matrice di gioco da tutte le angolazioni
	 * per valutare tutte le possibili situazioni che posso permettere di realizzare
	 * una combinazione in orizzontale con un solo spostamento.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è possibile una mossa con un solo scambio
	 */
	private boolean checkNextMoveHorizontal(final ICandy[][] mat) {
		
		//scorro partendo da 0,0
		for (int i = 0; i < Utility.DIM1 - 1; i++) {
			for (int j = 0; j < Utility.DIM2 - 2; j++) {
				if ((mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 1][j + 2].getColorNumber())) {
						return true;
					}
				
				if ((mat[i][j].getColorNumber() == mat[i + 1][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber() 
							|| mat[i][j].getColorNumber() == mat[i + 1][j + 2].getColorNumber())) {
						return true;
				}
				
			}
		}
		//scorro partendo da N,0		
		for (int i = Utility.DIM1 - 1; i > 0; i--) {
			for (int j = 0; j < Utility.DIM2 - 2; j++) {
				if ((mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 1][j + 2].getColorNumber())) {
						return true;
					
				}
				if ((mat[i][j].getColorNumber() == mat[i - 1][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i - 1][j + 2].getColorNumber())) {
						return true;
					
				}
			}
		}
		//scorro partendo da 0,M
		for (int i = 0; i < Utility.DIM1 - 1; i++) {
			for (int j = Utility.DIM2 - 1; j > 1; j--) {
				if ((mat[i][j].getColorNumber() == mat[i][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 1][j - 2].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i + 1][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i][j - 2].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i + 1][j - 2].getColorNumber())) {
						return true;
				}
			}
		}
		//scorro partendo da N,M
		for (int i = Utility.DIM1 - 1; i > 0; i--) {
			for (int j = Utility.DIM2 - 1; j > 1; j--) {
				if ((mat[i][j].getColorNumber() == mat[i][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 1][j - 2].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i - 1][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i][j - 2].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i - 1][j - 2].getColorNumber())) {
						return true;
				}
			}
		}	
		//tris lineari	
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 3; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber() 
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()) {
					return true;
				}
				if (mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo che percorre più volte la matrice di gioco da tutte le angolazioni
	 * per valutare tutte le possibili situazioni che posso permettere di realizzare
	 * una combinazione in verticale con un solo spostamento.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è possibile una mossa con un solo scambio
	 */
	private boolean checkNextMoveVertical(final ICandy[][] mat) {

		//scorro partendo da 0,0
		for (int j = 0; j < Utility.DIM2 - 1; j++) {
			for (int i = 0; i < Utility.DIM1 - 2; i++) {
				if ((mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 2][j + 1].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i + 1][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i + 2][j + 1].getColorNumber())) {
						return true;
				}
			}
		}
		//scorro partendo da 0,M
		for (int j = Utility.DIM2 - 1; j > 0; j--) {
			for (int i = 0; i < Utility.DIM1 - 2; i++) {
				if ((mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 2][j - 1].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i + 1][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber() 
						|| mat[i][j].getColorNumber() == mat[i + 2][j - 1].getColorNumber())) {
						return true;
				}
			}
		}
		//scorro partendo da N,0
		for (int j = 0; j < Utility.DIM2 - 1; j++) {
			for (int i = Utility.DIM1 - 1; i > 1; i--) {
				if ((mat[i][j].getColorNumber() == mat[i - 1][j].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 2][j + 1].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i - 1][j + 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 2][j].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i - 2][j + 1].getColorNumber())) {
						return true;
				}
			}
		}
		//scorro partendo da N,M
		for (int j = Utility.DIM2 - 1; j > 0; j--) {
			for (int i = Utility.DIM1 - 1; i > 1; i--) {
				if ((mat[i][j].getColorNumber() == mat[i - 1][j].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 2][j - 1].getColorNumber())) {
						return true;
				}
				if ((mat[i][j].getColorNumber() == mat[i - 1][j - 1].getColorNumber()) 
					&& (mat[i][j].getColorNumber() == mat[i - 2][j].getColorNumber()
						|| mat[i][j].getColorNumber() == mat[i - 2][j - 1].getColorNumber())) {
						return true;
				}
			}
		}
		//tris lineari	
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 3; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()) {
					return true;
				}
				if (mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()) {
					return true;
				}
			}
		}	
		return false;
	}
}
