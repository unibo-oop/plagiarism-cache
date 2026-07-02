package controller;

/**
 * interfaccia per i controller delle view che rispettano il layout di
 * anagrafica, con i 5 tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public interface IAnagraficaViewObserver extends IViewObserver {
	/**
	 * aggiorna la tabella
	 */
	void refresh();

	/**
	 * programma il tasto che di default è "filtra"/"cerca"
	 */
	void tasto0();

	/**
	 * programma il tasto che di default è "aggiungi"
	 */
	void tasto1();

	/**
	 * programma il tasto che di default è "modifica"
	 */
	void tasto2();

	/**
	 * programma il tasto che di default è "cancella"
	 */
	void tasto3();
}
