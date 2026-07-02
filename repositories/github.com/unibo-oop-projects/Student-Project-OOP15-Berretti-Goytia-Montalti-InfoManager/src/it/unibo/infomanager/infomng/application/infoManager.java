package it.unibo.infomanager.infomng.application;

import javax.swing.UIManager;

import it.unibo.infomanager.infomng.controller.ObserverInterfaceImpl;
import it.unibo.infomanager.infomng.view.ViewInterfaceImpl;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.interfaces.ViewInterface;

public class infoManager {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		ViewInterface view = new ViewInterfaceImpl();
		ObserverInterface controller = new ObserverInterfaceImpl(view);
		controller.start();
	}

}
