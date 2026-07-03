package knight;

public class Chronometer {

	public long startTime;
	public long stopTime;
	public long totalTime;
	public double timeScore;

	/**
	 * Setta l'orario di inizio della partita
	 */
	public void startTime() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * Setta l'orario di fine della partita
	 */
	public void stopTime() {
		stopTime = System.currentTimeMillis();
	}

	/**
	 * Setta il peso che il tempo impiegato avrà nel punteggio
	 */
	public double timeScore() {
		if (totalTime < 100) {
			return timeScore = 1.5;
		} else if (totalTime < 150) {
			return timeScore = 1.3;
		} else {
			return timeScore = 1;
		}
	}
	
	/**
	 * Setta l'orario totale della partita
	 */
	public long setTime() {
		return totalTime = (stopTime - startTime) / 1000;
	}
}
