package sharelist.view.application;

import sharelist.controller.ViewObserver;

/**
 * Interfaccia della ApplicationView dell'applicazione.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public interface ApplicationViewInterface {

	void loadList(Object[][] list);
	void loadMember(final Object[][] list);
	void loadAttivita(Object[][] attivita);
	void loadNotification(Object[][] notification);
	void attachViewObserver(ViewObserver listener);
	void commandFailed(String message);
	void clearData();
}
