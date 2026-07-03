package model.interfaces;

import java.util.List;
import java.util.Set;

import model.Pair;
import model.Room;

public interface IDailyMenu {

	/**
	 * Metodo per aggiungere un piatto all'istanza di dailymenu presente nel
	 * nostro albergo. I parametri in ingresso rappresentano il tipo e il nome
	 * del piatto che si vuole inserire.
	 * 
	 * @param key
	 *            tipo del piatto("antipasto","primo"...)
	 * @param value
	 *            il nome del piatto(Orata al cartoccio, gatto al forno etc...)
	 */
	public void addPiatto(final String key, final String value);

	/**
	 * Metodo per la rimozione di un piatto dal nostro menu giornaliero.
	 * 
	 * @param key
	 *            il tipo di piatto da rimuovere
	 * @param value
	 *            il nome del piatto da rimuovere
	 */

	public void removePiatto(final String key, final String value);

	/**
	 * Metodo per la cancellazione di tutto il daily menù(pensata per ogni
	 * transizione tra pranzo e cena dove il menù varia considerevolmente e vi è
	 * più praticità a fare tabula rasa piuttosto che rimuovere uno ad uno N
	 * piatti.
	 */

	public void cleanAllandUpdate();

	/**
	 * Metodo per l'aggiunta di un'ordinazione da parte di una stanza
	 * 
	 * @param room
	 *            la stanza che effettua l'ordinazione
	 * @param starter
	 *            lista di coppie<Stringa,Intero> dove il primo rappresenta il
	 *            nome del piatto, il secondo la quantità
	 * @param first
	 *            lista di coppie<Stringa,Intero> dove il primo rappresenta il
	 *            nome del piatto, il secondo la quantità
	 * @param second
	 *            lista di coppie<Stringa,Intero> dove il primo rappresenta il
	 *            nome del piatto, il secondo la quantità
	 * @param dessert
	 *            lista di coppie<Stringa,Intero> dove il primo rappresenta il
	 *            nome del piatto, il secondo la quantità
	 */

	public void addRoomMeal(final Room room, final List<Pair<String, Integer>> starter,
			final List<Pair<String, Integer>> first, List<Pair<String, Integer>> second,
			List<Pair<String, Integer>> dessert);

	/**
	 * Metodo che ritorna la stringa di tutte le ordinazioni per questo pasto,
	 * usata per la fileWrite sul desktop del file testuale con tutte le
	 * ordinazioni. (pensato in ottica di stampa come comanda da inviare alla
	 * cucina)
	 * 
	 * @return
	 */

	public String getOrdersString();

	/**
	 * Metodo che ritorna la stringa contenente tutti i piatti di ogni
	 * tipologia(antipasto,secondo,terzo...) sotto forma di stringa
	 * 
	 * @return
	 */

	public String getMenuString();

	/**
	 * Metodo che viene usato per richiamare il metodo canEat() di ogni booking
	 * relativo ad ogni stanza presente nell'hotel, per poi aggiornare la mappa
	 * <Room,Boolean> che tiene traccia del fatto se quella stanza può o meno
	 * mangiare per il pasto successivo, in base alla tipologia di pensione.
	 * 
	 * @param allRooms
	 *            lista di tutte le stanze dell'albergo
	 */

	public void computeRoomMealAvaliable(final List<Room> allRooms);

	/**
	 * Ritorna il set associato al tipo di piatto passato come parametro in
	 * ingresso, usata nella view per la visualizzazione tramite jlist dei
	 * piatti per tipologia.
	 * 
	 * @param type
	 *            il tipo di piatto da settare(primo,secondo...)
	 * @return il set contenente l'insieme dei piatti di un determinato tipo
	 */

	public Set<String> getMealList(final String type);

}
