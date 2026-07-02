package it.unibo.infomanager.infomng.view.interfaces;
/**
 * Interfaccia per le classi view.
 * @author Alessandro
 *
 */
public interface ViewInterface {
	/**
	 * Metodo che crea viewClient.
	 */
	void viewClienti();

	/**
	 * Metodo che crea viewFornitori.
	 */
	void viewFornitori();

	/**
	 * Metodo che crea viewFatture.
	 */
	void viewFatture();

	/**
	 * Metodo che crea la view Start .
	 */
	void viewStart();

	/**
	 * Metodo che crea viewMenu.
	 */
	void viewMenu();

	/**
	 * Metodo che crea viewMagazzino.
	 */
	void viewMagazzino();

	/**
	 * Metodo che crea viewRegistiIva.
	 */
	void viewRegistiIva();

	/**
	 * Metodo che crea viewReportVendite.
	 */
	void viewReportVendite();

	/**
	 * Metodo che crea viewRiunioni.
	 */
	void viewRiunioni();

	/**
	 * Metodo che crea viewScontrini.
	 */
	void viewScontrini();

	/**
	 * Metodo che crea viewDialogCampoObbligatorio.
	 */
	void viewDialogCampoObbligatorio();

	/**
	 * Metodo che crea viewDialogCerca.
	 */
	void viewDialogCerca();

	/**
	 * Metodo che crea viewDialogNuovo.
	 */
	void viewDialogNuovo();

	/**
	 * Metodo che crea viewDialogRegistrati.
	 */
	void viewDialogRegistrati();

	/**
	 * Metodo che crea viewDialogWrongPass.
	 */
	void viewDialogWrongPass();

	/**
	 * Metodo che crea viewDialogWrongUser.
	 */
	void viewDialogWrongUser();

	/**
	 * Metodo che setta l' Oggetto ObserverInterface.
	 * 
	 * @param o
	 *            OggettoObserverInterface
	 */
	void setOggettoController(ObserverInterface o);
}
