package game;

import minigames.MiniGameFactoryImpl;
//import Junit.ControllerTest;

public class Game {

	private Controller controller;

	public Game (){
		controller = Controller.getInstance();
		/*
		ControllerTest test = new ControllerTest();
		test.testGetInstance();
		test.testFailState();
		test.testAddObject();
		test.testRemoveObject();
		test.testSuccessState();
		*/
		MiniGameFactoryImpl factory = new MiniGameFactoryImpl();
		controller.addFromList(factory.increasingRecipe(true));
	}



	public static void main(String args []){
		new Game();
	}
}
