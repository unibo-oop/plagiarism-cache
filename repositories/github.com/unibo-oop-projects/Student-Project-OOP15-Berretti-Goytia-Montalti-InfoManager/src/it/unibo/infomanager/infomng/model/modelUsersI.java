package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per gestione degli untente
 * @author Juan Goytia
 *
 */
public interface modelUsersI {
	
	Integer getID();

	String getName();

	String getLastName();

	String getUsername();

	String getPassword();

	String getMail();
	
	void setName(String nome);
	
	void setLastName(String cognome);
	
	void setUsername(String username);
	
	void setPassword(String password);
	
	void setMail(String mail);
	/***
	 * aggiornamento(salvataggio o modifica dell'utente)
	 * @return
	 * true se andato a buon fine
	 */
	boolean update();
	
	/***
	 * metodo per l'eliminazione di un utente
	 * richiedo i dati per accettarsi che si tratti del proprio utente e non di un altro
	 * @param nome
	 * nome Utente
	 * @param cognome
	 * cognome dell'utente
	 * @param mail
	 * mail dell'utente
	 * @param username
	 * username dell'utente
	 * @param password
	 * pasword dell utente
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean deleteUser(String nome, String cognome, String mail, String username, String password);
	
	/***
	 * metodo che ritorna un elenco di tutti gli utento
	 * @return
	 * una lista contenenti tutti gli utenti esistenti
	 */
	public static List<modelUsersI> usersList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Utenti");
		try {
			return query.find().stream()
					.map(e -> new modelUsers(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelUsersI>();
		}
	}
	/***
	 * creazione di un nuovo utente
	 * @param nome
	 * nome dell'utente
	 * @param cognome
	 * cognome dell'utente
	 * @param mail
	 * mail dell'utente
	 * @param username
	 * username per queto utente
	 * @param password
	 * pasword per questo utente
	 * @return
	 * true se andato a buon fine o false se l'username esiste gia oppure errore nel salvataggio
	 */
	public static boolean builder(String nome, String cognome, String mail, String username, String password){
		modelUsersI nuovo = new modelUsers();
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		//contrllo che non si usi lo stesso username
		if(modelUsersI.usersList().stream()
			.filter(u -> u.getUsername().equals(username))
			.count() > 1){
			return false;
		}
		nuovo.setUsername(username);
		nuovo.setPassword(password);
		if(nuovo.update()){
			UserTmp.CurrentUser.setUtente(nuovo);
			return true;
		}
		return false;
	}
	/***
	 * controllo dell'accesso dell'utente
	 * @param username
	 * username dell'utente passato come string
	 * @param password
	 * la pasword dell'utente passato come string
	 * @return
	 * true se l'untente esiste, altrimenti False
	 */
	public static boolean usersLogin(String username, String password){
		Optional<modelUsersI> tmp = modelUsersI.usersList().stream()
				.filter(e -> e.getUsername().equals(username))
				.filter(e -> e.getPassword().equals(password))
				.findFirst();
		
		if(tmp.isPresent()){
			//metodo per tener traccia dell'utente che è stato loggato
			UserTmp.CurrentUser.setUtente(tmp.get());
			return true;
		}
		else{
			return false;
		}
	}
	/***
	 * metodo che controlla se l'utente corrente è logato
	 * @return
	 * true se l'utente è loggato
	 */
	public static Boolean isLogged(){
		return UserTmp.CurrentUser.isLogged();
	}
	/***
	 * metodo che ottiene l'utente corrente 
	 * @return
	 * l'utente corrente
	 * @throws NullPointerException
	 */
	public static modelUsersI getUtenteCorrente() throws NullPointerException {
		if(UserTmp.CurrentUser.isLogged()){
			return UserTmp.CurrentUser.getUtente();
		}
		else{
			throw new NullPointerException("Utente non loggato");
		}
	}

}