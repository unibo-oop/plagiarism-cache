package application;

/**
 * @author marco
 *
 */
public interface WarehouseDeleteUtility {

	/**
	 * Delete an information from Tipologia
	 * 
	 * @param catena
	 * @param idTipo
	 * @param key
	 */
	void deleteTipo(final Catena catena, final String idTipo, final String key);

	/**
	 * Delete an information or a food waste from Prodotto
	 * 
	 * @param catena
	 * @param idProd
	 * @param key
	 * @param idScarto
	 */
	void deleteProdotto(final Catena catena, final String idProd, final String key, final String idScarto);

	/**
	 * Delete an information or a food waste from ProdConcreto
	 * 
	 * @param catena
	 * @param idProdCon
	 * @param key
	 * @param idScarto
	 */
	void deleteProdCon(final Catena catena, final String idProdCon, final String key, final String idScarto);

	/**
	 * Delete an information or a food waste from ProdFornito
	 * 
	 * @param catena
	 * @param idProdFor
	 * @param key
	 * @param idScarto
	 */
	void deleteProdFor(final Catena catena, final String idProdFor, final String key, final String idScarto);

	/**
	 * Delete a Hotel
	 * 
	 * @param catena
	 * @param idHotel
	 */
	void deleteHotel(final Catena catena, final String idHotel);

	/**
	 * Delete a food storage from the Hotel
	 * 
	 * @param catena
	 * @param idDispensa
	 * @param idHotel
	 */
	void deleteDispensa(final Catena catena, final String idDispensa, final String idHotel);

	/**
	 * Delete a product from the Fornitore
	 * 
	 * @param catena
	 * @param idFornitore
	 * @param idProdotto
	 */
	void deleteFornitore(final Catena catena, final String idFornitore, final String idProdotto);
}
