package test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Candy;
import model.Checks;
import model.NextMoveChecker;
import org.junit.Test;

import controller.Utility;

/**
 * Classe di test per i metodi Checks. 
 * 
 * @author Beatrice Ricci, Nicola Santolini
 *
 */
public class Testing {

	private final Candy[][] mat = new Candy[Utility.DIM1][Utility.DIM2];
	private final Checks c = new Checks();
	private final NextMoveChecker n = new NextMoveChecker();
	
	/**
	 * Questo test va a testare i risultati ottenuti dai Checker "lineari" cioè quelli che vanno a controllare le presenze
	 * di combinazioni di tre, quattro o cinque Elements uguali (sia verticalmente che orizzontalmente).
	 */
	@Test
	public void testChecksCombinations() {
		//genero una nuova matrice
		this.generateMatrix();
		
		//modifico la matrice generata aggiungento un tris orizzontale ed uno in verticale in altro a sinistra
		mat[0][0].setColorNumber(0);
		mat[1][0].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[0][1].setColorNumber(0);
		mat[0][2].setColorNumber(0);
		
		assertTrue(c.checkTrisVertical(mat));
		assertTrue(c.checkTrisHorizontal(mat));
		
		//aggiungo al tris verticale un elemento uguale in modo da ottenere un poker verticale
		mat[3][0].setColorNumber(0);
		assertTrue(c.checkPokerVertical(mat));
		
		//aggiungo al tris orizzontale un elemento uguale in modo da ottenere un poker orizzontale
		mat[0][3].setColorNumber(0);
		assertTrue(c.checkPokerHorizontal(mat));
		
		//aggiungo al poker verticale un elemento uguale in modo da ottenere una combinazione di 5 Element in verticale
		mat[4][0].setColorNumber(0);
		assertTrue(c.checkFiveVertical(mat));
		
		//aggiungo al poker orizzontale un elemento uguale in modo da ottenere una combinazione di 5 Element in orizzontale
		mat[0][4].setColorNumber(0);
		assertTrue(c.checkFiveHorizontal(mat));
	}
	
	/**
	 * Avendo fatto la scelta di fare un solo metodo checksWrapped per tutte le varie tipologie di "caramella Wrappata", 
	 * questo test va a controllare che il metodo utilizzato trovi effettivamente tutti i tipi di combinazione.
	 */
	@Test
	public void testChecksWrapped() {
		//tipologia 1 ->   |^
		//genero una nuova matrice
		this.generateMatrix();
		//tris orizzontale a sinistra e tris verticale in alto
		mat[0][0].setColorNumber(0);
		mat[1][0].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[0][1].setColorNumber(0);
		mat[0][2].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 2 ->   L
		//genero una nuova matrice
		this.generateMatrix();
		//tris verticale a sinistra e tris orizzontale in basso
		mat[0][0].setColorNumber(0);
		mat[1][0].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[2][1].setColorNumber(0);
		mat[2][2].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 3 ->   T
		this.generateMatrix();
		//tris verticale al centro e tris orizzontale in alto
		mat[0][0].setColorNumber(0);
		mat[0][1].setColorNumber(0);
		mat[0][2].setColorNumber(0);
		mat[1][1].setColorNumber(0);
		mat[2][1].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 4 ->   _|_
		this.generateMatrix();
		//tris verticale al centro e tris orizzontale in basso
		mat[0][1].setColorNumber(0);
		mat[1][1].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[2][1].setColorNumber(0);
		mat[2][2].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 5 ->   7
		//genero una nuova matrice
		this.generateMatrix();
		//tris verticale a destra e tris orizzontale in alto
		mat[0][0].setColorNumber(0);
		mat[0][1].setColorNumber(0);
		mat[0][2].setColorNumber(0);
		mat[1][2].setColorNumber(0);
		mat[2][2].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 6 ->   _|
		//genero una nuova matrice
		this.generateMatrix();
		//tris verticale a destra e tris orizzontale in basso
		mat[0][2].setColorNumber(0);
		mat[1][2].setColorNumber(0);
		mat[2][2].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[2][1].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 7 ->   |-
		//genero una nuova matrice
		this.generateMatrix();
		//tris verticale a sinistra e tris orizzontale al centro
		mat[0][0].setColorNumber(0);
		mat[1][0].setColorNumber(0);
		mat[2][0].setColorNumber(0);
		mat[1][1].setColorNumber(0);
		mat[1][2].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
		
		//tipologia 8 ->   -|
		//genero una nuova matrice
		this.generateMatrix();
		//tris verticale a destra e tris orizzontale al centro
		mat[0][2].setColorNumber(0);
		mat[1][2].setColorNumber(0);
		mat[2][2].setColorNumber(0);
		mat[1][0].setColorNumber(0);
		mat[1][1].setColorNumber(0);
		assertTrue(c.checkWrapped(mat));
	}
	
	/**
	 * Test che verifica che i metodi di check non vanno mai fuori range.
	 */
	@Test
	public void testRange() {
		generateMatrix();
		
		try {
			for (int i = 0; i < 1000; i++) {
				assertFalse(c.checkTrisVertical(mat));
				assertFalse(c.checkTrisHorizontal(mat));
				assertFalse(c.checkPokerVertical(mat));
				assertFalse(c.checkWrapped(mat));
				assertFalse(c.checkFiveVertical(mat));
				assertFalse(c.checkFiveHorizontal(mat));
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Out of RANGE!");
		}	
	}
	
	/**
	 * Metodo che testa i metodi di controllo di una possibile mossa successiva.
	 */
	@Test
	public void testNextMove() {
		generateMatrix();
		
		//test range
		try {
			for (int i = 0; i < 1000; i++) {
				assertFalse(n.checkNextMove(mat));
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Out of RANGE!");
		}
		
	}
	
	/**
	 * Metodo per generare una matrice senza nessuna combinazione degli Element.
	 */
	private void generateMatrix() {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				mat[i][j] = new Candy(i + j);
			}
		}
	}
	
}
