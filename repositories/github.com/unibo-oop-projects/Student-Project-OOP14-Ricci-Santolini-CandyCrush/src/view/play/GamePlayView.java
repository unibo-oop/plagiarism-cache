package view.play;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AbstractMenu;
import view.utility.IconUtility;
import controller.Controller;
import controller.Utility;

/**
 * Classe che disegna la schermata principale del gioco con la matrice di caramelle, label con i punti, 
 * le mosse e l'obiettivo che devono aggiornarsi ad ogni mossa.
 * 
 * @author Beatrice Ricci
 */
public class GamePlayView extends AbstractMenu implements IGamePlay {

	private static final long serialVersionUID = 6751617017799096695L;
	
	private static final int WIDTH_DIMENSION = 250;
	private static final int HEIGHT_DIMENSION = 150;
	
	private final JPanel matrixPanel;
	
	private final Butt[][] matrix = new Butt[Utility.DIM1][Utility.DIM2];
	private final Controller c = new Controller(this);
	private final IUpdate up = new Update(this);
	
	private int initialTarget;
	private int initialMoves;
	
	private final JLabel step = new JLabel(" ");
	private final JLabel tot = new JLabel(" 0 punti"); 
	private final JLabel target = new JLabel(" ");
	private final JLabel level = new JLabel(" ");
	
	private final JPanel panel = new JPanel();
	
	/**
	 * Costruttore della classe contenente la schermata principale di gioco.
	 * @param iMoves mosse del livello
	 * @param iTarget obiettivo del livello
	 */
	public GamePlayView(final int iMoves, final int iTarget) {
		
		this.setSize(Utility.DIM1 * Utility.HBUTT + WIDTH_DIMENSION, Utility.DIM1 * Utility.LBUTT + HEIGHT_DIMENSION);
		this.setResizable(false);
		
		this.initialTarget = iTarget;
		this.initialMoves = iMoves;
		
		this.c.setInitialConditions(this.initialMoves, this.initialTarget);
		
		this.panel.setLayout(new BorderLayout());
		
		this.matrixPanel = new JPanel(new GridLayout(Utility.DIM1, Utility.DIM2));
		this.matrixPanel.setSize(Utility.DIM1 * Utility.HBUTT, Utility.DIM2 * Utility.LBUTT);
		
		new SetPlayPanels().updatePanel(panel, this.matrixPanel, this.level, this.step, this.target, this.tot);
		this.drawFirstMatrix();
		this.getContentPane().add(panel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Metodo per la creazione iniziale della schermata di gioco. In base agli interi che identificano il colore
	 * presenti nella matrice di Element vengono creati dei Butt (e aggiunti alla matrice matrix[][]) con le rispettive icone.
	 */
	private void drawFirstMatrix() {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				matrix[i][j] = new Butt(i, j);
				if (this.c.getModelNum(i, j) == Utility.BLUE) {
					matrix[i][j].setIcon(IconUtility.BLUEIC);
				} else if (this.c.getModelNum(i, j) == Utility.YELLOW) {
					matrix[i][j].setIcon(IconUtility.YELLOWIC);
				} else if (this.c.getModelNum(i, j) == Utility.GREEN) {
					matrix[i][j].setIcon(IconUtility.GREENIC);
				} else if (this.c.getModelNum(i, j) == Utility.VIOLET) {
					matrix[i][j].setIcon(IconUtility.VIOLETIC);
				} else if (this.c.getModelNum(i, j) == Utility.ORANGE) {
					matrix[i][j].setIcon(IconUtility.ORANGEIC);
				} else if (this.c.getModelNum(i, j) == Utility.RED) {
					matrix[i][j].setIcon(IconUtility.REDIC);
				}
				matrix[i][j].addActionListener(c.getObserver());
				matrixPanel.add(matrix[i][j]);
			}
		}
	}
	
	@Override
	public void draw(final int color, final int type, final int i, final int j) {
		up.draw(color, type, i, j);
	}
	
	@Override
	public void update(final int moves, final int score) {
		this.up.updateScoreAndMoves(moves, score);
	}
	
	@Override
	public void updateTarget(final int n) {
		this.target.setText(" target: " + n + " p ");
	}
	
	@Override
	public void setDiff(final String s) {
		this.level.setText(" " + s + " LEVEL ");
	}
	
	@Override
	public void updateMoves(final int n) {
		this.step.setText("  " + n + "  ");
	}
	
	@Override
	public void updateScore(final int n) {
		this.tot.setText(" " + n + " punti ");
	}
	
	@Override
	public Butt getAMatrixButt(final int i, final int j) {
		return this.matrix[i][j];
	}
}