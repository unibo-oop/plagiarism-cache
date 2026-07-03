package model;

import java.util.LinkedList;
import java.util.Random;

import lands.Lands.MyJButton;

/**
 * 
 * this class is used at the beginning of the game to randomly assign its states to each player .
 */
public class RandomicAssignmentCountry {

	private static final int TOTCOUNTRY = 42;

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param totplayers is the size of players list .
	 */
	public static void RAC(final LinkedList<MyJButton> terr, final int totplayers) { 

		int each = TOTCOUNTRY / totplayers;
		int rest = TOTCOUNTRY % totplayers;
		int check = 0;

		Random rand = new Random();
		for (int i = 1; i <= totplayers; i++) { 
			for (int j = 0; j < each; j++) { 
				check = rand.nextInt(TOTCOUNTRY) + 0;
				if (terr.get(check).getOwner() != 0) {
					j--;
				} else {
					terr.get(check).setOwner(i);
				}
			}
		}
		each = 0;
		for (MyJButton a: terr) { 
			if (a.getOwner() == 0) {
				break;
			}
			each++;
		}
		if (each != TOTCOUNTRY) {

			check = rand.nextInt(totplayers) + 1;
			terr.get(each).setOwner(check);
			for (each += 1; terr.get(each).getOwner() != 0; each++) { }
			do {
				rest = rand.nextInt(totplayers) + 1; 
			} while (rest == check);
			terr.get(each).setOwner(rest);
		}
	}
}
