package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.cardlayout.ViewLogin;
import View.cardlayout.MainWindow;
import pro_ristorante_oop.authentication.AuthenticationService;
import pro_ristorante_oop.authentication.AuthenticationState;
import pro_ristorante_oop.persistence.FilePersistenceService;


public class LoginPresenter implements ActionListener
{

	private final MainWindowPresenter mainWindowPresenter;
	private ViewLogin view;
	private final AuthenticationService model;

	public LoginPresenter(MainWindowPresenter mainWindowPresenter, ViewLogin view, AuthenticationService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.model = model;
		this.view = view;
		this.view.addButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		String username = this.view.getUsername();
		String password = this.view.getPassword();
		if (AuthenticationState.SUCCESS == this.model.authenticate(username, password))
		{
			this.mainWindowPresenter.initializeMainWindowsControllerLayer();
			this.mainWindowPresenter.presentSubView(MainWindow.PRENOTATIONPANEL);
		} else
		{
			//back to login screen
		}
	}

}
