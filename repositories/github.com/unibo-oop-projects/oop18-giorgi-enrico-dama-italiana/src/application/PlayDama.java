package application;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.CheckerBoardShadow;




public class PlayDama extends JFrame{
;
	
	
	
	/**
	 *  start the game
	 */
	private static final long serialVersionUID = -3152739538661070117L;

	public static void main(String[] args) {

		//Lancio la schermata iniziale
		Controller c = new Controller();
		c.startGame();
		



	}

}
