package model;

import java.io.Serializable;

public interface Score extends Serializable{
	public void addScorePlayer1();
	public void addScorePlayer2();
	public void reset();
	public int getWinner();
	public int getWinnerWithMaximum();
	public int getScore1();
	public int getScore2();
	public String getPlayer1Name();
	public String getPlayer2Name();
}
