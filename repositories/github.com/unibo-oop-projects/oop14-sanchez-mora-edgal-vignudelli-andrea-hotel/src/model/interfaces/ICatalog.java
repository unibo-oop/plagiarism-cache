package model.interfaces;

import java.time.LocalDate;
import java.util.HashMap;

import model.Pair;
import model.SeasonType;

public interface ICatalog {

	/**
	 * Classe che modella il listino prezzi di un albergo. Il prezzo base è dato
	 * da day, i successivi campi double indicano tutti un incremento o un
	 * decremento di percentuale sul prezzo base giornaliero(es.incremento =
	 * midSeasonOverPrice che rappresenta l'incremento percentuale sul giorno
	 * base durante la media stagione) I parametri scelti per indicare un
	 * incremento/sconto sul prezzo base sono:
	 * 
	 * 
	 */

	/**
	 * metodo che ritorna il prezzo giornaliero base
	 * 
	 * @return dayprice
	 */

	public double getDay();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getMidSeasonOverPrice();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getHighSeasonOverPrice();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getBBOverPrice();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getHalfBoardOverPrice();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getFullBoardOverPrice();

	/**
	 * 
	 * Metodo che ritorna l'età sotto la quale viene applicato lo sconto
	 * relativo ai bambini
	 * 
	 * @return
	 */

	public int getChildAge();

	/**
	 * 
	 * Metodo che ritorna l'età sotto la quale viene applicata l'esenza dal
	 * pagamento per i neonati. set=0 se non si vuole applicare lo sconto.
	 * 
	 * @return
	 */
	public int getBabyAge();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getChildPercentage();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getPremiumPercentage();

	/**
	 * Metodo che ritorna l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%)
	 * 
	 * @return
	 */

	public double getSuitePercentage();

	/**
	 * Metodo che ritorna la mappa relativa alle stagioni(bassa media alta) e ai
	 * relativi estremi dell'intervallo.
	 * 
	 */

	public HashMap<Pair<LocalDate, LocalDate>, SeasonType> getSeasonMap();

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setDay(final double day);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setBBOverPrice(final double BBOverPrice);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setFullBoardOverPrice(final double fullBoardOverPrice);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setPremiumPercentage(final double premiumPercentage);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setSuitePercentage(final double suitePercentage);

	/**
	 * Metodo che setta l'età sotto la quale questo hotel applica lo sconto
	 * bambini
	 * 
	 * @param childAge
	 */

	public void setChildAge(final int childAge);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 */

	public void setChildPercentage(final double childPercentage);

	/**
	 * Metodo che setta l'età sotto la quale questo hotel applica l'esenzione
	 * dal pagamento per i neonati
	 * 
	 * @param babyAge
	 *            l'età massima per essere considerati neonati
	 */

	public void setBabyAge(final int babyAge);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 * @param midPercentage
	 */

	public void setMidSeason(final double midPercentage);

	/**
	 * Metodo che setta l'incremento percentuale(rappresentato in double come
	 * es. 1.02,equivamente al 2%(inserire 2 come valore per applicare il 2%))
	 * 
	 * @param highPercentage
	 */

	public void setHighSeason(final double highPercentage);

	/**
	 * Metodo per settare tutti i campi del catalogo, utilizzato nella view
	 * 
	 * @param highPercentage
	 * @param midPercentage
	 * @param day
	 * @param BBOverPrice
	 * @param halfBoardOverPrice
	 * @param fullBoardOverPrice
	 * @param premiumOverPrice
	 * @param suiteOverPrice
	 * @param childPercentage
	 * @param childAge
	 * @param babyAge
	 */

	public void setAll(final double highPercentage, final double midPercentage, final double day,
			final double BBOverPrice, final double halfBoardOverPrice, final double fullBoardOverPrice,
			final double premiumOverPrice, final double suiteOverPrice, final double childPercentage,
			final int childAge, final int babyAge);

	/**
	 * Metodo utilizzato per controllare se vi è conflitto nell'inserimento
	 * delle stagioni, nel caso si voglia inserire una stagione in un periodo
	 * nel quale vi è collisione tra l'intervallodel periodo di un'altra
	 * stagione.
	 * 
	 * @param start
	 *            start of the season type
	 * @param end
	 *            end of the season type
	 * @return true if there is conflict, false altrimenti.
	 */

	public boolean checkConflict(LocalDate start, LocalDate end);

	/**
	 * 
	 * Metodo che ritorna la stagione relativa alla data passata come parametro
	 * andandola a leggere dall'hashmap. Usato specialmente nell'inserimento di
	 * un booking per selezionare automaticamente la stagione relativa alla data
	 * d'inizio del soggiorno.
	 * 
	 * @param date
	 *            la data della quale si vuole conoscere la stagione
	 * @return 
	 */

	public SeasonType getSeason(LocalDate date);

}
