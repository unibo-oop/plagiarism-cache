package model;

import controller.Utility;

/**
 * Classe che si occupa di effettuare i controlli delle possibili combinazioni
 * sulla matrice degli elementi.
 * 
 * @author Nicola Santolini
 */
public class Checks implements IChecks {

	private final NextMoveChecker nextChecker;
	
	/**
	 * Costruttore.
	 */
	public Checks() {
		this.nextChecker = new NextMoveChecker();
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
	public boolean checkTris(final ICandy[][] mat) {	
		if (!checkTrisVertical(mat) && !checkTrisHorizontal(mat)) {
			return false;
		}
		return true;	
	}
	
	@Override
	public boolean checkTrisVertical(final ICandy[][] mat) {	
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 2; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i + 1][j].getColorNumber() == mat[i + 2][j].getColorNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean checkTrisHorizontal(final ICandy[][] mat) {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 2; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j + 1].getColorNumber() == mat[i][j + 2].getColorNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean checkPoker(final ICandy[][] mat) {	
		if (!checkPokerVertical(mat) && !checkPokerHorizontal(mat)) {
			return false;
		}
		return true;	
	}
	
	@Override
	public boolean checkPokerVertical(final ICandy[][] mat) {
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 3; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean checkPokerHorizontal(final ICandy[][] mat) {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 3; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean checkFive(final ICandy[][] mat) {	
		if (!checkFiveVertical(mat) && !checkFiveHorizontal(mat)) {
			return false;
		}
		return true;	
	}
	
	@Override
	public boolean checkFiveHorizontal(final ICandy[][] mat) {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 4; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 4].getColorNumber()) {
					return true;
				}
			}
		}
		return false;	
	}
	
	@Override
	public boolean checkFiveVertical(final ICandy[][] mat) {
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 4; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 4][j].getColorNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean checkWrapped(final ICandy[][] mat) {
		for (int i = 0; i < Utility.DIM1 - 2; i++) {
			for (int j = 0; j < Utility.DIM2 - 2; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
						&& mat[i][j + 1].getColorNumber() == mat[i][j + 2].getColorNumber()) {
					
					if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber() 
						&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()) {
						return true;
					}
					if (mat[i][j + 1].getColorNumber() == mat[i + 1][j + 1].getColorNumber() 
							&& mat[i][j + 1].getColorNumber() == mat[i + 2][j + 1].getColorNumber()) {
						return true;
					}
					if (mat[i][j + 2].getColorNumber() == mat[i + 1][j + 2].getColorNumber() 
							&& mat[i][j + 2].getColorNumber() == mat[i + 2][j + 2].getColorNumber()) {
						return true;
					}	
				}	
			}
		}
		for (int i = Utility.DIM1 - 1; i > 1; i--) {
			for (int j = Utility.DIM2 - 1; j > 1; j--) {
				if (mat[i][j].getColorNumber() == mat[i][j - 1].getColorNumber()
						&& mat[i][j - 1].getColorNumber() == mat[i][j - 2].getColorNumber()) {
					
					if (mat[i][j].getColorNumber() == mat[i - 1][j].getColorNumber() 
						&& mat[i][j].getColorNumber() == mat[i - 2][j].getColorNumber()) {
						return true;
					}
					if (mat[i][j - 1].getColorNumber() == mat[i - 1][j - 1].getColorNumber() 
							&& mat[i][j - 1].getColorNumber() == mat[i - 2][j - 1].getColorNumber()) {
						return true;
					}
					if (mat[i][j - 2].getColorNumber() == mat[i - 1][j - 2].getColorNumber() 
							&& mat[i][j - 2].getColorNumber() == mat[i - 2][j - 2].getColorNumber()) {
						return true;
					}	
				}	
			}
		}		
		for (int j = 0; j < Utility.DIM2 - 2; j++) {
			for (int i = 0; i < Utility.DIM1 - 2; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()) {
					
					if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber() 
						&& mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()) {
						return true;
					}
					if (mat[i + 1][j].getColorNumber() == mat[i + 1][j + 1].getColorNumber() 
							&& mat[i + 1][j].getColorNumber() == mat[i + 1][j + 2].getColorNumber()) {
						return true;
					}
					if (mat[i + 2][j].getColorNumber() == mat[i + 2][j + 1].getColorNumber() 
							&& mat[i + 2][j].getColorNumber() == mat[i + 2][j + 2].getColorNumber()) {
						return true;
					}	
				}	
			}
		}
		for (int j = Utility.DIM2 - 1; j > 1; j--) {
			for (int i = Utility.DIM1 - 1; i > 1; i--) {
				if (mat[i][j].getColorNumber() == mat[i - 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i - 2][j].getColorNumber()) {
					
					if (mat[i][j].getColorNumber() == mat[i][j - 1].getColorNumber() 
						&& mat[i][j].getColorNumber() == mat[i][j - 2].getColorNumber()) {
						return true;
					}
					if (mat[i - 1][j].getColorNumber() == mat[i - 1][j - 1].getColorNumber() 
							&& mat[i - 1][j].getColorNumber() == mat[i - 1][j - 2].getColorNumber()) {
						return true;				
					}
					if (mat[i - 2][j].getColorNumber() == mat[i - 2][j - 1].getColorNumber() 
							&& mat[i - 2][j].getColorNumber() == mat[i - 2][j - 2].getColorNumber()) {
						return true;
					}	
				}	
			}
		}
		return false;
	}
	
	@Override
	public boolean checkNextMove(final ICandy[][] mat) {
		return nextChecker.checkNextMove(mat);
	}
}