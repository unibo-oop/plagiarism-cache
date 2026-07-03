package model;

import java.util.Arrays;
import java.util.Random;

import controller.Controller;
import lands.Lands.MyJButton;

/**
 * 
 * this class creates the arrays full of the randomic numbers 
 * to simulate a throw of dices and applies the consequences of the battle .
 */
public class RandomNumbers {

	private static int[] NumsAtk = new int[3];
	private static int[] NumsDef = new int[3];
	private static final int MAXDICE = 6;
	private static final int MINDICE = 1;
	private static int NumAtkWin = 0;
	private static int NumDefWin = 0;
	
	/**
	 * 
	 * @param A is the attacker country .
	 * @param B is the attacked country .
	 * @param att is the number of armies with A attack .
	 * @param def is the number of armies with B defend .
	 */
	public static void RNG(MyJButton A, MyJButton B, int att, int def) {

		NumAtkWin = 0;
		NumDefWin = 0;
		for (int i = 0; i < 3; i++) {
			NumsAtk[i] = 0;
			NumsDef[i] = 0;
		}
		Random rand = new Random();
		for (int i = 0; i < att; i++) {
			NumsAtk[i] = rand.nextInt(MAXDICE) + MINDICE;
		}
		for (int i = 0; i < def; i++) {
			NumsDef[i] = rand.nextInt(MAXDICE) + MINDICE;
		}
		Arrays.sort(NumsAtk);
		Arrays.sort(NumsDef);
		Controller.updateDices(NumsAtk, NumsDef);
		if (att > def) {
			// caso in cui att sia maggiore di def
			for (int i = 2; i >= 3 - def; i--) {
				if (NumsAtk[i] > NumsDef[i]) {
					NumAtkWin++;
				} else {
					NumDefWin++;
				}
			}
		} else {
			// caso in cui def sia maggiore di att
			for (int i = 2; i >= 3 - att; i--) {
				if (NumsAtk[i] > NumsDef[i]) {
					NumAtkWin++;
				} else {
					NumDefWin++;
				}
			}
		} 
		A.setArmies(A.getArmies() - NumDefWin);
		B.setArmies(B.getArmies() - NumAtkWin);
	}

}
