package model;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

	@Override
	/**
	 * @param score1
	 * @param score2
	 * @return -1 if first score is greater than second
	 * @return 1 if second is greater than first
	 * @return 0 if are equals
	 */
	public int compare(Score score1, Score score2) {
		int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return 1;
        }else{
            return 0;
        }        
	}
}
