package model;

public class ScoreImpl implements Score{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3930055289816320974L;
	public static final int NOT_SET_MAX = -1;
	public static final String DEFAULT_P1_NAME = "P1";
	public static final String DEFAULT_P2_NAME = "P2";
	private String player1Name;
	private String player2Name;
	private int score1;
	private int score2;
	private int max;
	
	public ScoreImpl() {
		max = NOT_SET_MAX;
		player1Name = DEFAULT_P1_NAME;
		player2Name = DEFAULT_P2_NAME;
	}
	public ScoreImpl(String n1,String n2) {
		this();
		player1Name = new String(n1);
		player2Name = new String(n2);
	}
	public ScoreImpl(String n1,String n2,int n) {
		this(n1,n2);
		max = n;
	}
	public ScoreImpl(int n) {
		this();
		max = n;
	}
	public int getScore1() {
		return score1;
	}
	public int getScore2() {
		return score2;
	}
	@Override
	public void addScorePlayer1() {
		score1++;
	}

	@Override
	public void addScorePlayer2() {
		score2++;
	}

	@Override
	public int getWinner() {
		if(score1 > score2) {
			return 1;
		}
		else if(score1 < score2) {
			return 2;
		}
		else {
			return 0;
		}
	}

	@Override
	/**
	 * if maximum score limit is set,checks if somebody has reached it
	 */
	public int getWinnerWithMaximum() {
		if(max != NOT_SET_MAX) {
			if(score1 == max || score2 == max) {
				return getWinner();
			}
		}
		return -1;
	}


	@Override
	public void reset() {
		score1 = 0;
		score2 = 0;
	}
	@Override
	public String toString() {
		return this.getPlayer1Name()+":"+this.getScore1() + " - " + this.getPlayer2Name()+":"+this.getScore2();
	}
	public String getPlayer1Name() {
		return player1Name;
	}
	public String getPlayer2Name() {
		return player2Name;
	}

}
