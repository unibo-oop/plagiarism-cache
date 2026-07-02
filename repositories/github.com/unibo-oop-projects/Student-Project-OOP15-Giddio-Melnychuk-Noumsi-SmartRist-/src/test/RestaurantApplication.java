package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View.*;
import View.cardlayout.MainWindow;
import controller.LoginPresenter;
import controller.MainWindowPresenter;
import controller.PrenotationPresenter;
import pro_ristorante_oop.authentication.TransientAuthenticationService;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.TransientPersistenceService;

public class RestaurantApplication
{

	public static void main(String[] args)
	{
		try
		{
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		} catch (IllegalAccessException ex)
		{
			ex.printStackTrace();
		} catch (InstantiationException ex)
		{
			ex.printStackTrace();
		} catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainWindowPresenter mainPresenter = new MainWindowPresenter(new FilePersistenceService());
				mainPresenter.presentSubView(MainWindow.LOGINPANEL);
			}
		});

	}
}
