package sharelist.view.login;

import sharelist.controller.ViewObserver;

/**
 * Interfaccia della View LoginView.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public interface LoginViewInterface {
	void loginResponse();
	void clearData();
	void commandFailed(String message);
	void commandRegisterOk(String message);
	void attachViewObserver(ViewObserver listener);
}