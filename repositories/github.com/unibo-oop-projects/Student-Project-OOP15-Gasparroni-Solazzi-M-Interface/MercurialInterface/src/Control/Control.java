package Control;

/**
 * Classe utilizzata l'intercetto degli eventi
 * ed il controllo delle varie informazioni
 *
 * @author Filippo Solazzi, Silvia Gasparroni
 *
 */
public interface Control {
	
	/**
	* Questo medoto è il cuore del progetto.
	* Esso, una volta ricevuta la Stringa contenente l'operazione da svolgere,
	* tramite uno switch effettua vari controlli e in caso va a modificare il model
	*
	* @param opera
	*          Stringa per definire l'operazione da eseguire
	*/
	void operazioni(String opera);
	
	/**
	* Questo metodo va a richiamare il model per inserire l'username
	* che è stato intercettato dal control uno volta che è stato
	* inserito nella View
	*
	* @param username
	*          Stringa contenente l'utente
	*/
	void username(String username);
	
	/**
	* set_Repository richiama il model che va a clonare la repository remota,
	* inserita nella stringa URL, in quella locale
	*
	* @param url
	*          String utilizzata per il clone
	*/
	void set_Repository(String url);
	
	/**
	* Il seguente metodo invocare il model passandogli il commento per effettuare il commit
	*
	* @param com
	*          Stringa contenente il commento
	*/
	void commit(String com);
	
	/**
	* Metodo utilizzato, dopo l'interfaccia di benvenuto, per richiamare
	* il model per controllare l'esistenza di mercurial
	* 
	* @return true se Mercurial è installato
	* 		  false invece se è da Installare
	*/
	boolean hg_Existence();
	
	/**
	* Viene invocato questo metodo per effettuare il login
	* quindi viene aperta la View del login
	*/
	void login();
	
	/**
	* Metodo che genera una View per facilitare l'utente ad installare Mercurial
	* con tanto di link e finchè ciò non accade non sarà possibile continuare con il programma
	*/
	void exe();
	
	/**
	* Controlla se nel momento in cui si va a scegliere il progetto da aggiungere al repository
	* la directory non sia la stessa della repository
	*
	* @param s
	*          Directory selezionata
	*          
	* @return true se la directory selezionata è corretta
	*/
	boolean dir_Diff(String s);
}