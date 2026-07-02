package controller;

import java.io.IOException;

import controller.anaAziende.AnaAziendeControllerImpl;
import controller.dbController.DBLoader;

/**
 * 
 */

/**
 * 
 * classe per l'avvio del programma
 * 
 * @author Pentolo
 */
public final class ApplicationStart {

	/**
	 * @param args
	 *            argomento necessario
	 */
	public static void main(final String[] args) {
		try {
			new AnaAziendeControllerImpl(DBLoader.loadCompanys());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
