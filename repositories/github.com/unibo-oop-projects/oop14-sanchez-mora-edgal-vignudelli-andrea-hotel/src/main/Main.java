package main;

import controller.Controller;
import model.Hotel;
import vieww.View;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Hotel model = Hotel.getInstance();
		Hotel.saveInstance();
		@SuppressWarnings("unused")
		View view = View.getInstance();
		@SuppressWarnings("unused")
		Controller controller = Controller.getInstance();
		Controller.getInstance().computeBusyRooms();
	}
}
