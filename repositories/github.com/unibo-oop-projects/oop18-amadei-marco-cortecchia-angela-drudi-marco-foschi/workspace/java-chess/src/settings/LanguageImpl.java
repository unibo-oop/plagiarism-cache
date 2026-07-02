package settings;

public class LanguageImpl implements Language{
	
	public Lang currentLanguage= null;
	
	public void setCurrentLanguage(Lang lang) {
		currentLanguage = lang;
	}
	
	public String textCheckMate() {
		switch(currentLanguage) {
		case ITALIAN:
			return "Scacco matto!";
		case ENGLISH:
			return "Checkmate!";
		case GERMAN:
			return "Schachmatt!";
		case SPANISH:
			return "Jaque mate!";
		case FRENCH:
			return "Éches et matt!";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textSettings() {
		switch(currentLanguage) {
		case ITALIAN:
			return "Impostazioni";
		case ENGLISH:
			return "Settings";
		case GERMAN:
			return "Einstellungen";
		case SPANISH:
			return "Configuración";
		case FRENCH:
			return "Paramètres";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textBishop() {
		switch(currentLanguage) {
		case ITALIAN:
			return "Alfiere";
		case ENGLISH:
			return "Bishop";
		case GERMAN:
			return "Läufer";
		case SPANISH:
			return "Perón";
		case FRENCH:
			return "Pion";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textKing() {
		switch(currentLanguage) {
		case ITALIAN:
			return "Re";
		case ENGLISH:
			return "King";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textQueen() {
		switch(currentLanguage) {
		case ITALIAN:
			return "Regina";
		case ENGLISH:
			return "Queen";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textKnight() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textRook() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPawn() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textChess() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPlayer() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textHelp() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textCustomize() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textHint() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textDifficulty() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textRematch() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPlayAgain() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textStatistics() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textHistory() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPause() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPlay() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textRestart() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textWin() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textLose() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textPoints() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textScore() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textRank() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String textBoard() {
		switch(currentLanguage) {
		case ITALIAN:
			return "";
		case ENGLISH:
			return "";
		case GERMAN:
			return "";
		case SPANISH:
			return "";
		case FRENCH:
			return "";
		default:
			throw new IllegalStateException();
		}
	}
}