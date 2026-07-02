package model;

import controller.Utility;

/**
 * CLasse che contiene i comportamenti da applicare per gestire combinazioni
 * di quattro caramelle in verticale o in orizzontale.
 * 
 * @author Nicola Santolini
 */

public class PokerBehaviour extends AbstractLinearBehaviour {

	/**
	* Metodo per la gestione di una combinazione da quattro in verticale.
	* 
	* @param mat matrice degli elementi
	*/
	@Override
	public void makeVertical(final ICandy[][] mat) {
		
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 3; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()) {
				
					for (int k = 0; k < 4; k++) {
						switchOptions(mat, mat[i + k][j].getType(), i + k, j);				
					}
					mat[i][j].setType(Utility.STRIPED_V);
					mat[i + 1][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 2][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 3][j].setColorNumber(ModelUtilities.NOT_DEFINED);
				}
				descend(mat);
				resolve(mat);			
			}											
		}
	}
		
	/**
	 * Metodo per la gestione di una combinazione da quattro in orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 */
	@Override
	public void makeHorizontal(final ICandy[][] mat) {
		
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 3; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()) {	
					
					for (int k = 0; k < 4; k++) {
						switchOptions(mat, mat[i][j + k].getType(), i, j + k);
					}				
					mat[i][j + 1].setType(Utility.STRIPED_O);
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 2].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 3].setColorNumber(ModelUtilities.NOT_DEFINED);
				}				
				descend(mat);
				resolve(mat);
			}			
		}

	}

}
