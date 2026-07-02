package model;

import controller.Utility;

/**
 * Classe che gestisce il comportamento da applicare quando si genera una
 * caramella SPECIAL, combinazione di 5 elementi dello stesso colore.
 * 
 * @author Nicola Santolini
 *
 */
public class FiveBehaviour extends AbstractLinearBehaviour {

	/**
	 * Metodo che individua una combinazione di 5 elementi in orizzontale, posiziona
	 * una caramella SPECIAL e elimina quelle adiacenti nel modo corretto.
	 * 
	 * @param mat matrice degli elementi
	 */
	@Override
	public void makeHorizontal(final ICandy[][] mat) {
		
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2 - 4; j++) {
				if (mat[i][j].getColorNumber() == mat[i][j + 1].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 2].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 3].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i][j + 4].getColorNumber()) {
					
					for (int k = 0; k < Utility.FIVE; k++) {
						switchOptions(mat, mat[i][j + k].getType(), i, j + k);
					}
					mat[i][j + 2].setColorNumber(Utility.SPECIAL);
					mat[i][j + 2].setType(Utility.FIVE);
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 1].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 3].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j + 4].setColorNumber(ModelUtilities.NOT_DEFINED);
					
					descend(mat);
					resolve(mat);
				}
			}
		}
	}
	
	
	/**
	 * Metodo che individua una combinazione di 5 elementi in verticale, posiziona
	 * una caramella SPECIAL e elimina quelle adiacenti nel modo corretto.
	 * 
	 * @param mat matrice degli elementi
	 */
	@Override
	public void makeVertical(final ICandy[][] mat) {

		for (int j = 0; j < Utility.DIM2; j++) {
			for (int i = 0; i < Utility.DIM1 - 4; i++) {
				if (mat[i][j].getColorNumber() == mat[i + 1][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 2][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 3][j].getColorNumber()
					&& mat[i][j].getColorNumber() == mat[i + 4][j].getColorNumber()) {
						
					for (int k = 0; k < Utility.FIVE; k++) {
						switchOptions(mat, mat[i + k][j].getType(), i + k, j);				
					}
					
					mat[i + 4][j].setColorNumber(Utility.SPECIAL);
					mat[i + 4][j].setType(Utility.FIVE);
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 1][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 2][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i + 3][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					
					descend(mat);
					resolve(mat);
				}
			}
		}
	}

	/**
	 * Metodo che gestisce il comportamento da applicare quando si attiva una caramella 
	 * SPECIAL, che quando associata a una caramella di un qualsiasi colore, elimina
	 * tutte le caramelle di quel colore; per ogni caramella eliminata in questo modo
	 * vengono attribuiti punti bonus.
	 * 
	 * @param color colore con cui è stata abbinata la caramella SPECIAL
	 * @param mat matrice degli elementi
	 * @return il numero di caramelle che sono state eliminate
	 */
	public int doFive(final ICandy[][] mat, final int color) {
		int n = 0;
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				if (mat[i][j].getColorNumber() == color) {
					mat[i][j].setColorNumber(ModelUtilities.NOT_DEFINED);
					mat[i][j].setType(Utility.NORMAL);
					n++;
				}
			}
		}
		descend(mat);									
		resolve(mat);
		
		return n;
	}
}
