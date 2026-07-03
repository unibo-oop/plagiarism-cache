package main;

import view.navigator.Navigator;
import view.NavigatorImpl;

public class Main {
	public static void main(String[] args) {
		final Navigator navigator = new NavigatorImpl();
		navigator.home();
	}
}
