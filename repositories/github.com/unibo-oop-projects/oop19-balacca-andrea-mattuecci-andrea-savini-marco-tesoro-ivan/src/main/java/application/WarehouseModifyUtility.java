package application;

/**
 * @author marco
 *
 */
public interface WarehouseModifyUtility {

	/**
	 * Modify an information or a food waste on Tipologia
	 * 
	 * @param catena
	 * @param idTipo
	 * @param key
	 * @param value
	 * @param newIdInfo
	 */
	void modifyTipo(final Catena catena, final String idTipo, final String key, final String value, final String idNewTipo, final String newIdInfo);

	/**
	 * Modify an information or a food waste on Prodotto
	 * 
	 * @param catena
	 * @param idProd
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @param newId
	 * @param newInfo
	 * @param newScarto
	 */
	void modifyProdotto(final Catena catena, final String idProd, final String idScarto, final String valoreScarto,
			final String key, final String value, final String newId, final String newInfo, final String newScarto);

	/**
	 * Modify an information or a food waste on ProdConcreto
	 * 
	 * @param catena
	 * @param idProdCon
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @param newId
	 * @param newInfo
	 * @param newScarto
	 */
	void modifyProdConcreto(final Catena catena, final String idProdCon, final String idScarto, final String valoreScarto,
			final String key, final String value, final String newId, final String newInfo, final String newScarto);

	/**
	 * Modify an information or a food waste on ProdFornito
	 * 
	 * @param catena
	 * @param idProdFor
	 * @param idScarto
	 * @param valoreScarto
	 * @param key
	 * @param value
	 * @param iForn
	 * @param newId
	 * @param newInfo
	 * @param newScarto
	 */
	void modifyProdFornito(final Catena catena, final String idProdFor, final String idScarto, final String valoreScarto,
			final String key, final String value, final String iForn, final String newId, final String newInfo, final String newScarto);

	/**
	 * Modify an information on the Dispensa
	 * 
	 * @param catena
	 * @param idTipo
	 * @param idHotel
	 * @param idDispensa
	 * @param newId
	 */
	void modifyTipoDispensa(final Catena catena, final String idTipo, final String idHotel, final String idDispensa, final String newId);

	/**
	 * Modify an information on the Fornitore
	 * 
	 * @param catena
	 * @param idFornitore
	 * @param idProdotto
	 * @param prezzo
	 * @param newId
	 */
	void modifyFornitore(final Catena catena, final String idFornitore, final String idProdotto, final String prezzo, final String newId);

	/**
	 * Modiify an information on the Hotel
	 * 
	 * @param catena
	 * @param idHotel
	 * @param newInfo
	 * @param newId
	 */
	void modifyHotel(final Catena catena, final String idHotel, final String newInfo, final String newId);
	
	/*
	 * Add the quantity of product to the storage
	 * 
	 * @param catena
	 * @param idHotel
	 * @param idDispensa
	 * @param idProdForn
	 * @param quantita
	 */
	void load(final Catena catena, final String idHotel, final String idDispensa, final String idProdForn,
			final String quantita);

	/**
	 * Remove the quantity of product to the storage
	 * 
	 * @param catena
	 * @param idHotel
	 * @param idDispensa
	 * @param idProdForn
	 * @param quantita
	 */
	void dump(final Catena catena, final String idHotel, final String idDispensa, final String idProdForn,
			final String quantita);
	
	/**
	 * Change the name of the typology
	 * 
	 * @param catena
	 * @param id
	 * @param newId
	 */
	void changeName(final Catena catena, final String id, final String newId);
	
	/**
	 * Change the id of an information
	 * 
	 * @param catena
	 * @param id
	 * @param idInfo
	 * @param newIdInfo
	 */
	void changeidInfo(final Catena catena, final String id, final String idInfo, final String newIdInfo);
	
	/**
	 * Change the id of a waste
	 * 
	 * @param catena
	 * @param id
	 * @param idScarto
	 * @param newIdScarto
	 */
	void changeidScarto(final Catena catena, final String id, final String idScarto, final String newIdScarto);
	
}
