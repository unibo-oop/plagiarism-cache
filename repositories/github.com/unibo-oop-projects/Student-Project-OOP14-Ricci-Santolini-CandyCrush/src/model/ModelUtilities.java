package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import controller.Utility;

/**
 * Classe contenente costanti di utilità del modello.
 * 
 * @author Nicola Santolini
 *
 */
public final class ModelUtilities {

	/**
	 * Valore che esprime il non-colore, una caramella eliminata o una caramella non inizializzata.
	 */
	public static final int NOT_DEFINED = -1;
	
	/**
	 * Punti da attribuire quando si crea una caramella special.
	 */
	public static final int SPECIAL_POINTS = 750;

	/**
	 * Punti da attribuire quando si crea una caramella striped.
	 */
	public static final int STRIPED_POINTS = 250;

	/**
	 * Punti da attribuire quando si crea una caramella wrapped.
	 */
	public static final int WRAPPED_POINTS = 250;

	/**
	 * Punti da attribuire quando si crea un tris.
	 */
	public static final int TRIS_POINTS = 100;

	/**
	 * Numero dei diversi colori delle caramelle.
	 */
	public static final int COLORS_NUMBER = 6;
	
	/**
	 * Codice caramella wrapped di tipo 1.
	 */
	public static final int WRAPPED_CODE_1 = 1;
	
	/**
	 * Codice caramella wrapped di tipo 2.
	 */
	public static final int WRAPPED_CODE_2 = 2;
	
	/**
	 * Codice caramella wrapped di tipo 3.
	 */
	public static final int WRAPPED_CODE_3 = 3;
	
	/**
	 * Codice caramella wrapped di tipo 4.
	 */
	public static final int WRAPPED_CODE_4 = 4;
	
	/**
	 * Codice caramella wrapped di tipo 5.
	 */
	public static final int WRAPPED_CODE_5 = 5;
	
	/**
	 * Codice caramella wrapped di tipo 6.
	 */
	public static final int WRAPPED_CODE_6 = 6;
	
	/**
	 * Codice caramella wrapped di tipo 7.
	 */
	public static final int WRAPPED_CODE_7 = 7;
	
	/**
	 * Codice caramella wrapped di tipo 8.
	 */
	public static final int WRAPPED_CODE_8 = 8;
	/**
	 * Metodo che genera interi da zero a cinque, utilizzato per attribuire
	 * colori casuali alle caramelle quando vengono generate.
	 * 
	 * @return r numero del colore generato
	 */
	public static int generate() {
		final Random r = new Random();
		return r.nextInt(ModelUtilities.COLORS_NUMBER);
	}
	
	/**
	 * Metodo che effettua un mescolamento della matrice di gioco utilizzando il set
	 * di caramelle attuale, necessario se ci si viene a trovare in una situazione di
	 * stallo in cui la matrice di gioco non ammetterebbe nuove mosse.
	 * 
	 * @param mat matrice degli elementi
	 */
	public static void shuffle(final ICandy[][] mat) {
		final List<ICandy> list = new ArrayList<>();
		
		/*Si inseriscono gli elementi della matrice attuale in una lista*/
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				list.add(mat[i][j]);
			}
		}
		/*Si mescola la lista e poi, utilizzando un iteratore, si reinseriscono
		 * gli elementi mischiati nella matrice*/
		Collections.shuffle(list);		
		final Iterator<ICandy> it = list.iterator();
		
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				mat[i][j] = it.next();
			}
		}
	}

	private ModelUtilities() { }
}
