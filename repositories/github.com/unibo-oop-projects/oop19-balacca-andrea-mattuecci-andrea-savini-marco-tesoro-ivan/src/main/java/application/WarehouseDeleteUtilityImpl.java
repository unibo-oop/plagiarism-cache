package application;

import java.util.ArrayList;

/**
 * @author marco
 *
 */
public class WarehouseDeleteUtilityImpl implements WarehouseDeleteUtility {
	
	/**
	 * Empty constructor
	 */
	public WarehouseDeleteUtilityImpl() {
		
	}

	/**
	 * Delete an information from Tipologia
	 * 
	 * @param catena the container of typology
	 * @param the    container of typology
	 * @param idInfo the info to be deleted
	 */

	public void deleteTipo(final Catena catena, final String idTipo, final String idInfo) {
		Tipologia tipologia = null;
		final ArrayList<String> tmp = new ArrayList<>();

		if (catena.ottieniDallInventario(idTipo).isPresent()) {
			tipologia = (Tipologia) catena.ottieniDallInventario(idTipo).get();
			if (!idInfo.isBlank() && tipologia.getInfo().containsKey(idInfo)) {
				System.out.println("Rimuovo l'informazione alla tipologia");
				tmp.add(idInfo);
				tipologia.rimuoviInfo(tmp);
				System.out.println(tipologia.getInfo());
			}
		}
	}

	/**
	 * Delete an information or a food waste from Prodotto
	 * 
	 * @param catena   the container of typology
	 * @param idProd   the name of the product
	 * @param key      the name of the info to be deleted
	 * @param idScarto the name of the waste to be deleted
	 */
	public void deleteProdotto(final Catena catena, final String idProd, final String key, final String idScarto) {

		Prodotto prodotto = null;
		final ArrayList<String> tmp = new ArrayList<>();

		if (catena.ottieniDallInventario(idProd).isPresent()) {
			prodotto = (Prodotto) catena.ottieniDallInventario(idProd).get();
			if (!key.isBlank() && prodotto.getInfo().containsKey(key)) {
				System.out.println("Rimuovo le informazioni al prodotto");
				tmp.add(key);
				prodotto.rimuoviInfo(tmp);
				System.out.println(prodotto.getInfo());
				tmp.clear();
			}
			if (!idScarto.isBlank()) {
				final Scarto s = catena.ottieniUnoScarto(idScarto).get();
				if (prodotto.getScarti().contains(s)) {
					System.out.println("Rimuovo lo scarto dal prodotto");
					tmp.add(idScarto);
					prodotto.rimuoviScarti(tmp);
				}
			}
		}
	}

	/**
	 * Delete an information or a food waste from ProdConcreto
	 * 
	 * @param catena    the container of typology
	 * @param idProdCon the name of the product
	 * @param key       the name of the info to be deleted
	 * @param idScarto  the name of the waste to be deleted
	 */
	public void deleteProdCon(final Catena catena, final String idProdCon, final String key, final String idScarto) {

		ProdConcreto prodotto = null;
		final ArrayList<String> tmp = new ArrayList<>();

		if (catena.ottieniDallInventario(idProdCon).isPresent()) {
			prodotto = (ProdConcreto) catena.ottieniDallInventario(idProdCon).get();
			if (!key.isBlank() && prodotto.getInfo().containsKey(key)) {
				System.out.println("Rimuovo le informazioni al prodotto concreto");
				tmp.add(key);
				prodotto.rimuoviInfo(tmp);
				System.out.println(prodotto.getInfo());
				tmp.clear();
			}
			final Scarto s = catena.ottieniUnoScarto(idScarto).get();
			if (prodotto.getScarti().contains(s) && !idScarto.isBlank()) {
				System.out.println("Rimuovo lo scarto al prodotto concreto");
				tmp.add(idScarto);
				prodotto.rimuoviScarti(tmp);
			}
		}
	}

	/**
	 * Delete an information or a food waste from ProdFornito
	 * 
	 * @param catena    the container of typology
	 * @param idProdFor the name of the product
	 * @param key       the name of the info to be deleted
	 * @param idScarto  the name of the waste to be deleted
	 */
	public void deleteProdFor(final Catena catena, final String idProdFor, final String key, final String idScarto) {

		ProdFornito prodotto = null;
		final ArrayList<String> tmp = new ArrayList<>();

		if (catena.ottieniDallInventario(idProdFor).isPresent()) {
			prodotto = (ProdFornito) catena.ottieniDallInventario(idProdFor).get();
			if (!key.isBlank() && prodotto.getInfo().containsKey(key)) {
				System.out.println("Rimuovo le informazioni al prodotto fornito");
				tmp.add(key);
				prodotto.rimuoviInfo(tmp);
				tmp.clear();
			}
			Scarto s = catena.ottieniUnoScarto(idScarto).get();
			if (prodotto.getScarti().contains(s) && !idScarto.isBlank()) {
				System.out.println("Rimuovo lo scarto al prodotto fornito");
				tmp.add(idScarto);
				prodotto.rimuoviScarti(tmp);
				tmp.clear();
			}
		}
	}

	/**
	 * Delete a Hotel
	 * 
	 * @param catena  the container of typology
	 * @param idHotel the name of the hotel to be deleted
	 */
	public void deleteHotel(final Catena catena, final String idHotel) {

		if (catena.ottieniUnAlbergo(idHotel).isPresent()) {
			System.out.println("Rimuovo l'Hotel :" + idHotel);
			catena.rimuoviUnAlbergo(idHotel);
		}
	}

	/**
	 * Delete a food storage from the Hotel
	 * 
	 * @param catena     the container of typology
	 * @param idDispensa the of the dispensa to be deleted
	 * @param idHotel    the name of the hotel
	 */
	public void deleteDispensa(final Catena catena, final String idDispensa, final String idHotel) {

		if (catena.ottieniUnAlbergo(idHotel).isPresent()
				&& catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).isPresent()) {
			System.out.println("Rimuovo la dispensa dall'hotel :" + idHotel);
			catena.ottieniUnAlbergo(idHotel).get().rimuoviUnaDispensa(idDispensa);
		}
	}

	/**
	 * Delete a product from the Fornitore
	 * 
	 * @param catena      the container of typology
	 * @param idFornitore the name of the supplier
	 * @param idProdotto  the name of the product to be deleted
	 */
	public void deleteFornitore(final Catena catena, final String idFornitore, final String idProdotto) {

		final ArrayList<String> tmp = new ArrayList<String>();

		if (catena.ottieniUnFornitore(idFornitore).isPresent()) {
			final Fornitore fornitore = (Fornitore) catena.ottieniUnFornitore(idFornitore).get();
			if (fornitore.getProdotti(catena).containsKey(idProdotto)) {
				System.out.println("Rimuovo il prodotto dal fornitore");
				tmp.add(idProdotto);
				fornitore.rimuoviProdotti(tmp);
				System.out.println(catena.ottieniUnFornitore(idFornitore).get().getProdotti(catena));
			}
			if (fornitore.getMiglioriProdotti(catena).containsKey(idProdotto)) {
				System.out.println("Rimuovo il prodotto migliore dal fornitore");
				tmp.add(idProdotto);
				fornitore.rimuoviMiglioriProdotti(tmp);
				System.out.println(catena.ottieniUnFornitore(idFornitore).get().getMiglioriProdotti(catena));
			}
		}
	}
}
