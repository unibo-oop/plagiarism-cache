package view.play;

import view.utility.IconUtility;
import controller.Utility;

/**
 * Classe il cui compito è quello di prendere i dati della matrice numerica del modello dal controller e 
 * di aggiornare la matrice che vede l'utente sulla base di quella che legge.
 * Questo è possibile controllando il tipo dell'elemento ed il suo colore.
 * 
 * @author Beatrice Ricci
 *
 */
public class Update implements IUpdate {
	private final GamePlayView pgm;
	
	/**
	 * Costruttore con passaggio di parametri.
	 * @param play schermata di gioco
	 */
	public Update(final GamePlayView play) {
		this.pgm = play;
	}
	
	@Override
	public void draw(final int color, final int type, final int i, final int j) {
		if (type == Utility.NORMAL) {
			if (color == Utility.BLUE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.BLUEIC);
			} else if (color == Utility.YELLOW) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.YELLOWIC);
			} else if (color == Utility.GREEN) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.GREENIC);
			} else if (color == Utility.VIOLET) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.VIOLETIC);
			} else if (color == Utility.ORANGE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.ORANGEIC);
			} else if (color == Utility.RED) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.REDIC);
			}
		} else if (type == Utility.STRIPED_V) {
			if (color == Utility.BLUE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.BLUEVIC);
			} else if (color == Utility.YELLOW) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.YELLOWSVIC);
			} else if (color == Utility.GREEN) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.GREENSVIC);
			} else if (color == Utility.VIOLET) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.VIOLETSVIC);
			} else if (color == Utility.ORANGE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.ORANGESVIC);
			} else if (color == Utility.RED) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.REDSVIC);
			}
		} else if (type == Utility.STRIPED_O) {
			if (color ==  Utility.BLUE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.BLUESOIC);
			} else if (color ==  Utility.YELLOW) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.YELLOWSOIC);
			} else if (color ==  Utility.GREEN) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.GREENSOIC);
			} else if (color ==  Utility.VIOLET) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.VIOLETSOIC);
			} else if (color ==  Utility.ORANGE) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.ORANGESOIC);
			} else if (color ==  Utility.RED) {
				this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.REDSOIC);
			}
		} else if (type == Utility.WRAPPED) {
				if (color == Utility.BLUE) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.BLUEWIC);
				} else if (color == Utility.YELLOW) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.YELLOWWIC);
				} else if (color == Utility.GREEN) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.GREENWIC);
				} else if (color == Utility.VIOLET) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.VIOLETWIC);
				} else if (color == Utility.ORANGE) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.ORANGEWIC);
				} else if (color == Utility.RED) {
					this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.REDWIC);
				}
		} else if (type == Utility.FIVE) {
			this.pgm.getAMatrixButt(i, j).setIcon(IconUtility.FIVEIC);
		}
	}
	
	@Override
	public void updateScoreAndMoves(final int moves, final int score) {
		this.pgm.updateScore(score);
		this.pgm.updateMoves(moves);
	}
}
