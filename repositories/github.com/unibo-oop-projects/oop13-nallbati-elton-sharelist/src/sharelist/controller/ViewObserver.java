package sharelist.controller;

/**
 * Interfaccia del Controllore dell'applicazione.
 * La classe che vorra' sviluppare il controllore dell'applicazione dovra' implementare questa interfaccia.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public interface ViewObserver {
	void accessApplication();
	void commandLogin(String username, String password);
	void commandRegister(Object[] arg);
	void commandLoadList();
	void commandLoadList(String username, String password);
	void commandAddList(Object[] arg);
	void commandEditList(Object[] arg);
	void commandDeleteList(Object[] arg);
	void commandLoadAttivitaFromView(String idList);
	void commandLoadAttivita(String username, String password, String idList);
	void commandAddAttivita(Object[] arg);
	void commandEditAttivita(Object[] o);
	void commandDeleteAttivita(Object[] arg);
	void commandLoadMember(String idList);
	void commandAggiungiMember(String username_new_person, String id_list, String list);
	void commandDeleteMember(String id, String id_list);
	void commandLoadNotification();
	void commandAccettaListaNotification(String id, String id_list);
	void commandReadNotification();
	void commandQuit();
}
