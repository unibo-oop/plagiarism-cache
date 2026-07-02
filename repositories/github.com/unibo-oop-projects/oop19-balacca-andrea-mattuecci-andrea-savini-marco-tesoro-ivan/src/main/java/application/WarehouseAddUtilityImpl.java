package application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author marco
 *
 */
public class WarehouseAddUtilityImpl implements WarehouseAddUtility {

	/*
	 * The array that saves the Object to be added
	 */
	final private ArrayList<Typology> typology = new ArrayList<>();

	/*
	 * The array that saves the food waste to be added
	 */
	final private ArrayList<Scarto> scarti = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public void WarehouseAddUtility() {
		
	}

	/**
	 * Instantiate a new Tipologia
	 * 
	 * @param catena the container of typology
	 * @param id     the typology to be added
	 * @param key    the info to be added
	 * @param value  of the info to be added
	 * @return new Tipologia
	 */
	public Tipologia addNewTipologia(final Catena catena, final String id, final String key, final String value) {
		Tipologia tipologia = null;
		final HashMap<String, String> info = new HashMap<>();

		if (!catena.ottieniDallInventario(id).isPresent() && !id.isBlank()) {
			System.out.println("La tipologia non esiste e viene aggiunta");
			tipologia = new Tipologia(id);
			this.typology.add(tipologia);
			catena.aggiungiAllInventario(this.typology);
			if (!key.isBlank()) {
				info.put(key, value);
				tipologia.aggiungiInfo(info);
				
			}
			this.typology.add(tipologia);
			catena.aggiungiAllInventario(this.typology);
		}
		return tipologia;
	}

	/**
	 * Instantiate a new Prodotto
	 * 
	 * @param catena       the container of the product
	 * @param idProdotto   the name of the product to be added
	 * @param idTipo       the typology of the product
	 * @param idScarto     the waste of the product
	 * @param valoreScarto the value of waste
	 * @param key          the name of the info
	 * @param value        the name of the info
	 * @return new Prodotto
	 */
	public Prodotto addNewProdotto(final Catena catena, final String idProdotto, final String idTipo,
			final String idScarto, final String valoreScarto, final String key, final String value) {
		Prodotto prodotto = null;
		Scarto scarto = null;
		Tipologia tipologia = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();
		
		
		if (!catena.ottieniDallInventario(idProdotto).isPresent() && !idProdotto.isBlank()) {
			tipologia = (Tipologia) catena.ottieniDallInventario(idTipo).get();
			System.out.println("Il prodotto non esiste e viene creato");
			prodotto = new Prodotto(idProdotto, tipologia);

			if (!key.isBlank()) {
				info.put(key, value);
				prodotto.aggiungiInfo(info);
			}
			if (!idScarto.isBlank()) {
				scarto = new Scarto(idScarto);
				if (!valoreScarto.isEmpty()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);
			}
			this.typology.add(prodotto);
			catena.aggiungiAllInventario(this.typology);
		}
		return prodotto;
	}

	/**
	 * Instantiate a new ProdConcreto
	 * 
	 * @param catena       the container of the product
	 * @param idProdCon    the name of the product to be added
	 * @param idProdotto   the "father" of the product
	 * @param idScarto     the waste of the product
	 * @param valoreScarto the value of waste
	 * @param key          the name of the info
	 * @param value        the value of the info
	 * @return new ProdConcreto
	 */
	public ProdConcreto addNewProdConcreto(final Catena catena, final String idProdCon, final String idProdotto,
			final String idScarto, final String valoreScarto, final String key, final String value) {

		ProdConcreto prodotto = null;
		Scarto scarto = null;
		Prodotto prodPadre = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();

		if (!catena.ottieniDallInventario(idProdCon).isPresent() && !idProdCon.isBlank()) {
			prodPadre = (Prodotto) catena.ottieniDallInventario(idProdotto).get();
			System.out.println("Il prodConcreto non esiste e viene creato");

			prodotto = new ProdConcreto(idProdCon, prodPadre);
			prodotto.setIDMigliorFornitore(prodotto.getIDMigliorFornitore(catena));
			prodotto.setPrezzoPiuBasso(prodotto.getPrezzoPiuBasso(catena));
			prodotto.setPrezzoEffettivoMigliore(prodotto.getPrezzoEffettivoMigliore(catena));

			if (!key.isBlank()) {
				info.put(key, value);
				prodotto.aggiungiInfo(info);
			}
			if (!idScarto.isBlank()) {
				scarto = new Scarto(idScarto);
				if (!valoreScarto.isEmpty()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);
			}
			this.typology.add(prodotto);
			catena.aggiungiAllInventario(this.typology);
		}
		return prodotto;
	}

	/**
	 * Instantiate a new ProdFornito
	 * 
	 * @param catena         the container of the product
	 * @param idProdFor      the name of the product to be added
	 * @param idProdCon      the "father" of the product
	 * @param idScarto       the waste of the product
	 * @param valoreScarto   the value of waste
	 * @param key            the name of the info
	 * @param value          the value of the info
	 * @param idForn         the name of the supplier
	 * @param prezzo         the price of the product
	 * @param valoreAssoluto the absolute value of the product
	 * @return new ProdFornito
	 */
	public ProdFornito addNewProdFornito(final Catena catena, final String idProdFor, final String idProdCon,
			final String idScarto, final String valoreScarto, final String key, final String value, final String idForn,
			final String prezzo, final String valoreAssoluto) {

		Scarto scarto = null;
		ProdFornito prodotto = null;
		ProdConcreto prodPadre = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();

		if (!catena.ottieniDallInventario(idProdFor).isPresent() && !idProdFor.isBlank()) {
			
			prodPadre = (ProdConcreto) catena.ottieniDallInventario(idProdCon).get();
			System.out.println("Il prodFornito non esiste e viene creato");

			prodotto = new ProdFornito(idProdFor, prodPadre);
			prodotto.setIDFornitore(idForn);
			prodotto.setPrezzo(Float.parseFloat(prezzo));
			prodotto.setValoreAssoluto(Float.valueOf(valoreAssoluto));
			prodotto.setValoreNetto(prodotto.getValoreNetto());
			prodotto.setPercentualeNetto(prodotto.getPercentualeNetto());
			prodotto.setPrezzoEffettivo(prodotto.getPrezzoEffettivo());

			if (!key.isBlank()) {
				info.put(key, value);
				prodotto.aggiungiInfo(info);
			}
			if (!idScarto.isBlank()) {
				scarto = new Scarto(idScarto);
				if (!valoreScarto.isEmpty()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);
			}
			this.typology.add(prodotto);
			catena.aggiungiAllInventario(this.typology);
		}
		return prodotto;
	}

	/**
	 * Instantiate a new Hotel
	 * 
	 * @param catena  the container of the product
	 * @param idHotel the name of the hotel to be added
	 * @param info    the name of the info
	 * @return new Hotel
	 */
	public Hotel addNewHotel(final Catena catena, final String idHotel, final String info) {

		Hotel hotel = null;

		if (!catena.ottieniUnAlbergo(idHotel).isPresent() && !idHotel.isBlank()) {
			System.out.println("Aggiungo un Hotel");
			hotel = new Hotel(idHotel);
			if (!info.isBlank()) {
				hotel.setInfo(info);
			}
			catena.aggiungiUnAlbergo(hotel);
		}
		return hotel;
	}

	/**
	 * Instatiate a new Fornitore
	 * 
	 * @param catena      the container of the product
	 * @param idFornitore the name of the supplier
	 * @return new Fornitore
	 */
	public Fornitore addNewFornitore(final Catena catena, final String idFornitore) {

		Fornitore fornitore = null;

		if (!idFornitore.isBlank() && !catena.ottieniUnFornitore(idFornitore).isPresent()) {
			System.out.println("Aggiuingo un nuovo fornitore");
			fornitore = new Fornitore(idFornitore);
			catena.aggiungiUnFornitore(fornitore);
		}
		return fornitore;
	}

	/**
	 * Instantiate a new Dispensa
	 * 
	 * @param catena     the container of the product
	 * @param idHotel    the name of the hotel
	 * @param idDispensa the name of the dispensa to be added
	 * @return new Dispensa
	 */
	public Dispensa addNewDispensa(final Catena catena, final String idHotel, final String idDispensa) {

		Dispensa dispensa = null;

		if (catena.ottieniUnAlbergo(idHotel).isPresent()) {
			if (!catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).isPresent()
					&& !idDispensa.isBlank()) {
				System.out.println("Aggiungo la dispensa all'hotel");
				dispensa = new Dispensa(idDispensa, catena);
				catena.ottieniUnAlbergo(idHotel).get().aggiungiUnaDispensa(dispensa);
			}
		} else {
			System.out.println("Id hotel errato");
		}
		return dispensa;
	}
}
