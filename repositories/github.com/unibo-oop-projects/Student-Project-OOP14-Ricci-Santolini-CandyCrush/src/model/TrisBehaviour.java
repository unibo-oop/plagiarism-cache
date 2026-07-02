package model;

import controller.Utility;

/**
 * Classe che contiene i comportamenti da applicare per gestire tris
 * verticali e orizzontali.
 * 
 * @author Nicola Santolini
 *
 */
public class TrisBehaviour extends AbstractLinearBehaviour {

	/**
	 * Metodo per la gestione di un tris in verticale.
	 * 
	 * @param mat matrice degli elementi
	 */
	@Override
	public void makeVertical(final ICandy[][] mat) {
			
		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 2; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i + 1][j].getColorNumber() == mat[i + 2][j].getColorNumber()) {
				
					for (int k = 0; k < 3; k++) {
						switchOptions(mat, mat[i + k][j].getType(), i + k, j);				
					}		
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 1][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 2][j].setColorNumber(ModelUtilities.NOT_DEFINED);								
				}	
				descend(mat);
				resolve(mat);		
			}
		}					
	}
	
	/**
	 * Metodo per la gestione di un tris in orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 */
	@Override
	public void makeHorizontal(final ICandy[][] mat) {
		
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 2; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j + 1].getColorNumber() == mat[i][j + 2].getColorNumber()) {	
					
					for (int k = 0; k < 3; k++) {
						switchOptions(mat, mat[i][j + k].getType(), i, j + k);				
					}	
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 1].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 2].setColorNumber(ModelUtilities.NOT_DEFINED);
				}
				descend(mat);
				resolve(mat);		
			}
		}
	}
}
