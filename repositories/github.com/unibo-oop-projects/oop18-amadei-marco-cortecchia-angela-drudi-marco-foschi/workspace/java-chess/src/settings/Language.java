package settings;

public interface Language {

	public enum Lang{
		ITALIAN,
		ENGLISH,
		GERMAN,
		SPANISH,
		FRENCH;
	}
	
	String textCheckMate();
	
	String textSettings();
	
	String textBishop();
	
	String textKing();
	
	String textQueen();
	
	String textKnight();
	
	String textRook();
	
	String textPawn();
	
	String textChess();
	
	String textPlayer();
	
	String textHelp();
	
	String textCustomize();
	
	String textHint();
	
	String textDifficulty();
	
	String textRematch();
	
	String textPlayAgain();
	
	String textStatistics();
	
	String textHistory();
	
	String textPause();
	
	String textPlay();
	
	String textRestart();
	
	String textWin();
	
	String textLose();
	
	String textPoints();
	
	String textScore();
	
	String textRank();
	
	String textBoard();
}
