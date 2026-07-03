package sharelist.model;

import sharelist.exceptions.DataIncorrectException;

/**
 * Interfaccia del Modello dell'applicazione.
 * La classe che vorra' sviluppare la struttura dati dell'applicazione dovra' implementare questa interfaccia.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public interface ModelInterface{
	
	Object[] login(String username, String password) throws DataIncorrectException;
	Object[] register(String name, String surname, String username, String password) throws DataIncorrectException;
	Object[][] loadList(String username, String password) throws DataIncorrectException;
	Object[] addList(String username, String password, String newList) throws DataIncorrectException;
	Object[] editList(String username, String password, String id, String editList) throws DataIncorrectException;
	Object[] deleteList(String username, String password, String id) throws DataIncorrectException;
	Object[][] loadAttivita(String username, String password, String idList) throws DataIncorrectException;
	Object[] addAttivita(String username, String password, String idList, String newAttivita) throws DataIncorrectException;
	Object[] editAttivita(String username, String password, String idList, String idAttivita, String attivita, String completo, String note) throws DataIncorrectException;
	Object[] deleteAttivita(String username, String password, String idList, String idAttivita) throws DataIncorrectException;
	Object[][] loadMember(String username, String password, String idList) throws DataIncorrectException;
	Object[] addMember(String username, String password, String username_new_person, String id_list, String list) throws DataIncorrectException;
	Object[] deleteMember(String username, String password, String id, String id_list) throws DataIncorrectException;
	Object[][] loadNotification(String username, String password) throws DataIncorrectException;
	Object[] acceptListNotification(String username, String password, String id, String id_list) throws DataIncorrectException;
	Object[] readNotification(String username, String password) throws DataIncorrectException;
}