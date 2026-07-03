package sharelist.model;

import sharelist.exceptions.DataIncorrectException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.*;

/**
 * Incapsula i dati che formano lo stato dell'applicazione.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class Model implements ModelInterface {
	
	private static final String NO_RESPONSE = "Il server non risponde";
	private String message;
	private Integer successResponse;
	private String messageResponse;

	/**
	 * Costruttore del Modello
	 */
	public Model() {
		super();
		this.message = null;
		this.successResponse = null;
		this.messageResponse  = null;
	}
	
	/**
	 * Invia le credenziali al server per effettuare il login utente.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] login(String username, String password) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/login.php";
			String[] paramName = {"username", "password"};
			String[] paramVal = {username, password};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Invia i dati utente al server per effettuare una nuova registrazione.
	 * 
	 * @param name
	 * 				nome utente
	 * @param surname
	 * 				cognome utente
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] register(String name, String surname, String username, String password) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/register.php";
			String[] paramName = {"name", "surname", "username", "password"};
			String[] paramVal = {name, surname, username, password};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Carica tutte le liste dell'utente.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @return un array di tipo Object composto da
	 * 				[Integer] [String]	(esito, descrizione),
	 * 				[Integer] [String]	(id lista, descrizione lista),
	 * 				...
	 */
	@Override
	public Object[][] loadList(String username, String password) throws DataIncorrectException{
		Object[][] response = null;
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/list.php";
			String[] paramName = {"username", "password"};
			String[] paramVal = {username, password};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			final JSONArray arr = obj.getJSONArray("response");
			final int n = arr.length();
			response = new Object[n+1][2];
			
			/* Popolo 'response' con l'esito del server */
			response[0][0] = this.successResponse;
			response[0][1] = this.messageResponse;
			
			/* Continuo a popolare 'response' con le liste */
			for (int i = 1; i < n+1; i++) {
				final JSONObject list = arr.getJSONObject(i-1);
			      response[i][0] = list.getInt("id");
			      response[i][1] = list.getString("list");
			}
			
			/* Ritorno la risposta al Controllore */
			return response;
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Aggiunge una nuova lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param newList
	 * 				nome lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] addList(String username, String password, String newList) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/addlist.php";
			String[] paramName = {"username", "password", "newlist"};
			String[] paramVal = {username, password, newList};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Modifica il nome di una lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param id
	 * 				id lista
	 * @param editList
	 * 				nuovo nome lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] editList(String username, String password, String id, String editList) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/editlist.php";
			String[] paramName = {"username", "password", "id", "editlist"};
			String[] paramVal = {username, password, id, editList};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Elimina una lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param id
	 * 				id lista
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] deleteList(String username, String password, String id) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/deletelist.php";
			String[] paramName = {"username", "password", "id"};
			String[] paramVal = {username, password, id};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Aggiunge una nuova lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer] [String]	(esito, descrizione),
	 * 				[Integer] [Integer]	[String] [Integer] [String] (id, id lista, attivita, completo, note),
	 * 				...
	 */
	@Override
	public Object[][] loadAttivita(String username, String password, String idList) throws DataIncorrectException{
		Object[][] response = null;
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/attivita.php";
			String[] paramName = {"username", "password", "idlist"};
			String[] paramVal = {username, password, idList};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			final JSONArray arr = obj.getJSONArray("response");
			final int n = arr.length();
			response = new Object[n+1][5];
			
			/* Popolo 'response' con l'esito del server */
			response[0][0] = this.successResponse;
			response[0][1] = this.messageResponse;
			
			/* Continuo a popolare 'response' con le attivita' */
			for (int i = 1; i < n+1; i++) {
				final JSONObject attivita = arr.getJSONObject(i-1);
			      response[i][0] = attivita.getInt("id");
			      response[i][1] = attivita.getInt("idlist");
			      response[i][2] = attivita.getString("attivita");
			      response[i][3] = attivita.getInt("completo");
			      response[i][4] = attivita.getString("note");
			}
			/* Ritorno la risposta al Controllore */
			return response;
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Aggiunge un'attivita'.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 * @param newAttivita
	 * 				nome attivita'
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] addAttivita(String username, String password, String idList, String newAttivita) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/addattivita.php";
			String[] paramName = {"username", "password", "idlist", "newattivita"};
			String[] paramVal = {username, password, idList, newAttivita};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Modifica il nome di un'attivita'.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 * @param idAttivita
	 * 				id attivita'
	 * * @param attivita
	 * 				nome attivita'
	 * * @param completo
	 * 				completo
	 * @param note
	 * 				note
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] editAttivita(String username, String password, String idList, String idAttivita, String attivita, String completo, String note) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/editattivita.php";
			String[] paramName = {"username", "password", "idlist", "idattivita", "attivita", "completo", "note"};
			String[] paramVal = {username, password, idList, idAttivita, attivita, completo, note};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Elimina un'attivita'.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 * @param idAttivita
	 * 				id attivita'
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] deleteAttivita(String username, String password, String idList, String idAttivita) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/deleteattivita.php";
			String[] paramName = {"username", "password", "idlist", "idattivita"};
			String[] paramVal = {username, password, idList, idAttivita};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Carica i membri di una lista, compreso il proprietario.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer] [String]	(esito, descrizione),
	 * 				[Integer] [String] [String] [String] (id, username, id lista, stato),
	 * 				...
	 */
	@Override
	public Object[][] loadMember(String username, String password, String idList) throws DataIncorrectException{
		Object[][] response = null;
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/member.php";
			String[] paramName = {"username", "password", "idlist"};
			String[] paramVal = {username, password, idList};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			final JSONArray arr = obj.getJSONArray("response");
			final int n = arr.length();
			response = new Object[n+1][5];
			
			/* Popolo 'response' con l'esito del server */
			response[0][0] = this.successResponse;
			response[0][1] = this.messageResponse;
			
			/* Continuo a popolare 'response' con i membri */
			for (int i = 1; i < n+1; i++) {
				final JSONObject attivita = arr.getJSONObject(i-1);
			      response[i][0] = attivita.getInt("id");
			      response[i][1] = attivita.getString("username");
			      response[i][2] = attivita.getString("id_list");
			      if(attivita.getInt("state") == 1){
			    	  response[i][3] = "";
			      } else {
			    	  response[i][3] = "(in sospeso)";
			      }
			      if(attivita.getInt("owner") == 1){
			    	  response[i][4] = "(Proprietario)";
			      } else {
			    	  response[i][4] = "(Membro)";
			      }
			}
			
			/* Ritorno la risposta al Controllore */
			return response;
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Aggiunge un nuovo membro ad una lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param username_new_person
	 * 				username nuovo membro
	 * @param id_list
	 * 				id lista
	 * @param list
	 * 				nome lista
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] addMember(String username, String password, String username_new_person, String id_list, String list) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/addmember.php";
			String[] paramName = {"username", "password", "username_new_person", "id_list", "list"};
			String[] paramVal = {username, password, username_new_person, id_list, list};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Cancella un membro dalla lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param id
	 * 				id membro
	 * @param id_list
	 * 				id lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] deleteMember(String username, String password, String id, String id_list) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/deletemember.php";
			String[] paramName = {"username", "password", "id", "id_list"};
			String[] paramVal = {username, password, id, id_list};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Carica le notifiche.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer] [String]	(esito, descrizione),
	 * 				[Integer] [String] [Integer] [Integer] [Integer] [Integer] [String] (id, username del proprietario, letto, azione, id azione, id lista, descrizione),
	 * 				...
	 */
	@Override
	public Object[][] loadNotification(String username, String password) throws DataIncorrectException{
		Object[][] response = null;
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/notification.php";
			String[] paramName = {"username", "password"};
			String[] paramVal = {username, password};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			final JSONArray arr = obj.getJSONArray("response");
			final int n = arr.length();
			response = new Object[n+1][7];
			
			/* Popolo 'response' con l'esito del server */
			response[0][0] = this.successResponse;
			response[0][1] = this.messageResponse;
			
			/* Continuo a popolare 'response' con le notifiche */
			for (int i = 1; i < n+1; i++) {
				final JSONObject attivita = arr.getJSONObject(i-1);
			      response[i][0] = attivita.getInt("id");
			      response[i][1] = attivita.getString("username_owner");
			      response[i][2] = attivita.getInt("readed");
			      response[i][3] = attivita.getInt("action");
			      response[i][4] = attivita.getInt("id_action");
			      response[i][5] = attivita.getInt("id_list");
			      response[i][6] = attivita.getString("description");
			}
			
			/* Ritorno la risposta al Controllore */
			return response;
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Invia notifica al server che l'utente ha accettato a partecipare ad una lista.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param id
	 * 				id notifica
	 * @param id_list
	 * 				id lista
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] acceptListNotification(String username, String password, String id, String id_list) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/acceptlistnotification.php";
			String[] paramName = {"username", "password", "id", "id_list"};
			String[] paramVal = {username, password, id, id_list};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/**
	 * Invia notifica al server che l'utente ha letto le notifiche.
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * 
	 * @return un array di tipo Object composto da
	 * 				[Integer]	esito
	 * 				[String]	descrizione
	 */
	@Override
	public Object[] readNotification(String username, String password) throws DataIncorrectException{
		try{
			/* Invio un modulo http di tipo POST */
			String urlStr = "http://www.lionsapp.com/oop/readnotification.php";
			String[] paramName = {"username", "password"};
			String[] paramVal = {username, password};
			String responseHttp = httpPost(urlStr, paramName, paramVal);
			
			/* Decodifico la risposta JSON del server */
			final JSONObject obj = new JSONObject(responseHttp);
			this.successResponse = obj.getInt("success");
			this.messageResponse = obj.getString("message");
			
			/* Ritorno la risposta al Controllore */
			return new Object[]{this.successResponse,this.messageResponse};
		} catch (Exception e){
			/* Lancio eccezione se il server per qualsiasi motivo non risponde */
			this.message = NO_RESPONSE;
			throw new DataIncorrectException(this.message);
		}
	}
	
	/*
	 * Un ringraziamento speciale a Dr. M. Elkstein:
	 * http://rest.elkstein.org/2008/02/using-rest-in-java.html
	 * 
	 * @param paramName
	 * 				nome dei parametri POST
	 * @param paramVal
	 * 				valore dei parametri POST
	 * @return una String che contiene la risposta del server.
	 */
	private static String httpPost(String urlStr, String[] paramName, String[] paramVal) throws Exception {
			  URL url = new URL(urlStr);
			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("POST");
			  conn.setDoOutput(true);
			  conn.setDoInput(true);
			  conn.setUseCaches(false);
			  conn.setAllowUserInteraction(false);
			  conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			  // Create the form content
			  OutputStream out = conn.getOutputStream();
			  Writer writer = new OutputStreamWriter(out, "UTF-8");
			  for (int i = 0; i < paramName.length; i++) {
			    writer.write(paramName[i]);
			    writer.write("=");
			    writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			    writer.write("&");
			  }
			  writer.close();
			  out.close();

			  if (conn.getResponseCode() != 200) {
			    throw new IOException(conn.getResponseMessage());
			  }

			  // Buffer the result into a string
			  BufferedReader rd = new BufferedReader(
			      new InputStreamReader(conn.getInputStream()));
			  StringBuilder sb = new StringBuilder();
			  String line;
			  while ((line = rd.readLine()) != null) {
			    sb.append(line);
			  }
			  rd.close();

			  conn.disconnect();
			  return sb.toString();
	}
}
