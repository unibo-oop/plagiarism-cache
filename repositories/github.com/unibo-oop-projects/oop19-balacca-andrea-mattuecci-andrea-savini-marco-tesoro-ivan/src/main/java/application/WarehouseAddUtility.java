package application;

/**
 * @author marco
 *
 */
public interface WarehouseAddUtility {

	/**
	 * Instantiate a new Tipologia
	 * 
	 * @param catena
	 * @param id
	 * @param key
	 * @param value
	 * @return new Tipologia
	 */
	Tipologia addNewTipologia(final Catena catena, final String id, final String key, final String value);

	/**
	 * Instantiate a new Prodotto
	 * 
	 * @param catena
	 * @param idProdotto
	 * @param idTipo
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @return new Prodotto
	 */
	Prodotto addNewProdotto(final Catena catena, final String idProdotto, final String idTipo, final String idScarto,
			final String valoreScarto, final String key, final String value);

	/**
	 * Instantiate a new ProdConcreto
	 * 
	 * @param catena
	 * @param idProdCon
	 * @param idProdotto
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @return new ProdConcreto
	 */
	ProdConcreto addNewProdConcreto(final Catena catena, final String idProdCon, final String idProdotto,
			final String idScarto, final String valoreScarto, final String key, final String value);

	/**
	 * Instantiate a new ProdConcreto
	 * 
	 * @param catena
	 * @param idProdFor
	 * @param idProdCon
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @param idForn
	 * @param prezzo
	 * @param valoreAssoluto
	 * @return ProdFornito
	 */
	ProdFornito addNewProdFornito(final Catena catena, final String idProdFor, final String idProdCon,
			final String idScarto, final String valoreScarto, final String key, final String value, final String idForn,
			final String prezzo, final String valoreAssoluto);

	/**
	 * Instantiate a new Hotel
	 * 
	 * @param catena
	 * @param idHotel
	 * @param info
	 * @return new Hotel
	 */
	Hotel addNewHotel(final Catena catena, final String idHotel, final String info);

	/**
	 * Instatiate a new Fornitore
	 * 
	 * @param catena
	 * @param idFornitore
	 * @return new Fornitore
	 */
	Fornitore addNewFornitore(final Catena catena, final String idFornitore);

	/**
	 * Instantiate a new Dispensa
	 * 
	 * @param catena
	 * @param idHotel
	 * @param idDispensa
	 * @return new Dispensa
	 */
	Dispensa addNewDispensa(final Catena catena, final String idHotel, final String idDispensa);

}
