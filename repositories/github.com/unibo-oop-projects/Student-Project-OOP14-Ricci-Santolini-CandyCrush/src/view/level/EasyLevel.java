package view.level;

import controller.Utility;
import view.play.GamePlayView;
import view.play.IGamePlay;

/**
 * Classe che va a impostare la schermata di gioco in base alla caratteristiche del livello più semplice.
 * 
 * @author Beatrice Ricci
 *
 */
public class EasyLevel extends AbstractLevel {
	
	@Override
	public void setLevel() {
		setFeatures();
	}
	
	/**
	 * Metodo per settare le caratteristiche del livello di difficoltà.
	 * @param play schermata di gioco da impostare
	 */
	private void setFeatures() {
		IGamePlay play = new GamePlayView(Utility.NUM_STEP, Utility.EASY_TARGET);
		play.updateMoves(Utility.NUM_STEP);
		play.updateTarget(Utility.EASY_TARGET);
		play.setDiff("EASY");
	}

}
