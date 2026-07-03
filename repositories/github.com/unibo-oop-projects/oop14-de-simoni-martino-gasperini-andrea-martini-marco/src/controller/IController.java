package controller;


/**
 * 
 * 
 *
 * Interfaccia per i Controller.  
 *
 * @author Martino De Simoni
 *
 */

/*
 * Mi vengono in mente solo esempi di controller di elementi di view, quindi è difficile non estendere qui l'interfaccia runnable.
 * Una scelta da discutere.
 * 
 * Nelle classi che sicuramente devono disporre di un metodo run, runnable è stata estesa di nuovo. Nel caso l'estensione di questa
 * classe venga eliminata, il programma non risentirà di errori di compilazione.
 */

public interface IController extends Runnable {
	
	/**
	 *  Metodo richiamato sul controller per notificargli l'espletazione della funzione dell'oggetto controllato.
	 *  Può essere chiamato dall'oggetto controllato o dal controller stesso.
	 *  Il significato degli argomenti deve essere fornito a priori nella javadoc.
	 *  
	 *  @param msg
	 *  			messaggio utile all'esecuzione.
	 *  
	 */
	
	void notifyController(String msg);
	
}
