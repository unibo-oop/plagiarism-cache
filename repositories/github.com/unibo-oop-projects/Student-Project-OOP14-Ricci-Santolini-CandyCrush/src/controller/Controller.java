package controller;

import javax.swing.SwingUtilities;

import view.ending.GameOver;
import view.play.IGamePlay;
import view.play.Shuffle;
import view.ending.Win;
import model.IModel;
import model.Model;

/**
 * Classe che contiene il Controller dell'applicazione, che fa quindi da intermediario fra model e view come da pattern MVC.
 * 
 * @author Beatrice Ricci, Nicola Santolini
 *
 */
public class Controller implements IController {
	
	private final IModel model;
	private final IGamePlay view;
	private final Listener observer;
	
	/**
	 * Costruttore del controller.
	 * 
	 * @param v schermata principale di gioco
	 */
	public Controller(final IGamePlay v) {
		this.model = new Model();
		this.view = v;
		this.observer = new Listener();
		this.observer.addObserver(this);
	}
	
	
	@Override
	public int getModelNum(final int i, final int j) {
		return model.getColor(i, j);
	}
	
	@Override
	public int getModelType(final int i, final int j) {
		return model.getTypeEl(i, j);
	}
	
	@Override
	public int getModelMoves() {
		return model.getMoves();
	}
	
	@Override
	public int getModelScore() {
		return model.getScore();
	}
	
	@Override
	public void setInitialConditions(final int moves, final int targetScore) {
		model.setMoves(moves);
		model.setTarget(targetScore);
	}
	
	@Override
	public Listener getObserver() {
		return this.observer;
	}
	
	@Override
	public void makeMove(final int x1, final int y1, final int x2, final int y2) {
		if (model.checkExchange(x1, y1, x2, y2)) {
			model.doExchange(x1, y1, x2, y2);
			drawUpdates();
			view.update(this.model.getMoves(), this.model.getScore());
			//attivata una caramella special 
			if (model.isUsingSpecial(x1, y1, x2, y2)) {
				
				model.makeSpecial(x1, y1, x2, y2);
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Thread.sleep(Utility.TIME);
						} catch (InterruptedException e) {
							System.out.println(e);	
						}
						drawUpdates();
						view.update(model.getMoves(), model.getScore());
						finalControl();
					}
				});
			}
			//hai fatto tris -> do exchange
			if (model.goOn()) {
				model.decMoves();
				SwingUtilities.invokeLater(new Runnable() {		
					public void run() {	
						while (model.goOn()) {
							try {
								Thread.sleep(Utility.TIME);
							} catch (InterruptedException e) {
								System.out.println(e);
							}		
							model.gameLoop();
							drawUpdates();
							view.update(model.getMoves(), model.getScore());			
						}
						finalControl();
						while (!model.checkNextMove()) {
							model.shuffle();
							new Shuffle();
							drawUpdates();
							view.update(getModelMoves(), getModelScore());
						}
					}
				});
			} else { 
				//non hai fatto tris -> undo exchange
				SwingUtilities.invokeLater(new Runnable() {	
					public void run() {
						try {
							Thread.sleep(Utility.TIME);
						} catch (InterruptedException e) {
							System.out.println(e);
						}
						model.doExchange(x1, y1, x2, y2);
						drawUpdates();
						view.update(model.getMoves(), model.getScore());
					}
				});
				
			}
			view.update(this.model.getMoves(), this.model.getScore());
		}
	}
	/**
	 * Metodo che controlla se il gioco è finito o meno.
	 */
	private void finalControl() {
		//controllo vittoria: se viene raggiunto l'obiettivo (anche all'ultima mossa)
		if (this.model.getMoves() >= 0 && this.model.getScore() >= this.model.getTarget()) {
			this.view.closePage();
			new Win();
		}
		//controllo sconfitta: se al termine delle mosse il punteggio è minore dell'obiettivo richiesto 
		if (this.model.getMoves() == 0 && this.model.getScore() < this.model.getTarget()) {
			this.view.closePage();
			new GameOver();
		}
	}
	
	/**
	 * Metodo che fornisce alla view i dati per disegnare la matrice aggiornata. 
	 * La view a sua volta li passerà alla classe Update.
	 */
	private void drawUpdates() {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				this.view.draw(this.model.getColor(i, j), this.model.getTypeEl(i, j), i, j);
			}
		}
	}
}