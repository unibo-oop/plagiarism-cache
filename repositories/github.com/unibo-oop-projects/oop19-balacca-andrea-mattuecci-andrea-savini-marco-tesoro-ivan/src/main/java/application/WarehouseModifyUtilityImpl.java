package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * @author marco
 *
 */
public class WarehouseModifyUtilityImpl implements WarehouseModifyUtility {

	/*
	 * The array that saves the food waste to be added
	 */
	final private ArrayList<Scarto> scarti = new ArrayList<>();

	/*
	 * Empty constructor
	 */
	public WarehouseModifyUtilityImpl() {

	}

	/**
	 * Modify an information or a food waste on Tipologia
	 * 
	 * @param catena    the container of typology
	 * @param idTipo    the typology to be modified
	 * @param key       the info to be modified
	 * @param value     of the info
	 * @param newIdTipo the new name of typology
	 * @param newIdInfo the new name of the key
	 */
	public void modifyTipo(final Catena catena, final String idTipo, final String key, final String value,
			final String newIdTipo, final String newIdInfo) {

		Tipologia tipologia = null;
		final HashMap<String, String> info = new HashMap<>();

		if (catena.ottieniDallInventario(idTipo).isPresent()) {
			tipologia = (Tipologia) catena.ottieniDallInventario(idTipo).get();
			if (!tipologia.getInfo().containsKey(key) && !key.isBlank() && !value.isBlank()) {
				System.out.println("Aggiungo le informazioni alla tipologia");
				info.put(key, value);
				tipologia.aggiungiInfo(info);
				System.out.println(tipologia.getInfo());
			} else if (!tipologia.getInfo().get(key).equals(value) && !idTipo.isEmpty() && !value.isBlank()) {
				System.out.println("Modifico le informazioni alla tipologia");
				info.put(key, value);
				tipologia.modificaInfo(info);
				System.out.println(tipologia.getInfo());
			}
		}

		this.changeName(catena, idTipo, newIdTipo);

		this.changeidInfo(catena, newIdTipo, key, newIdInfo);

	}

	/**
	 * Modify an information or a food waste on Prodotto
	 * 
	 * @param catena       the container of typology
	 * @param idProd       the id of the product to be modified
	 * @param idScarto     the waste of the product
	 * @param valoreScarto the value of waste
	 * @param key          the name of the info
	 * @param value        the name of the info
	 * @param newId        the new name of product
	 * @param newIdInfo    the new name of the key
	 * @param newIdScarto  the new name of a waste
	 */
	public void modifyProdotto(final Catena catena, final String idProd, final String idScarto,
			final String valoreScarto, final String key, final String value, final String newId, final String newInfo,
			final String newIdScarto) {

		Prodotto prodotto = null;
		Scarto scarto = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();

		if (catena.ottieniDallInventario(idProd).isPresent() && !idProd.isBlank()) {
			prodotto = (Prodotto) catena.ottieniDallInventario(idProd).get();
			if (!prodotto.getInfo().containsKey(key) && !key.isBlank()) {
				System.out.println("Aggiungo le informazioni al prodotto");
				info.put(key, value);
				prodotto.aggiungiInfo(info);
				System.out.println(prodotto.getInfo());
			} else if (!prodotto.getInfo().get(key).equals(value) && !key.isBlank() && !value.isBlank()) {
				System.out.println("Modifico le informazioni al prodotto");
				info.put(key, value);
				prodotto.modificaInfo(info);
				System.out.println(prodotto.getInfo());
			}
			if (!idScarto.isBlank() && !catena.ottieniUnoScarto(idScarto).isPresent()) {
				scarto = new Scarto(idScarto);
				System.out.println("Aggiungo lo scarto al prodotto");
				if (!valoreScarto.isBlank()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.getScarti().add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);
			} else if (!idScarto.isBlank()) {
				scarto = (Scarto) catena.ottieniUnoScarto(idScarto).get();
				if (!valoreScarto.isBlank() && scarto.getQuantita().floatValue() != Float.valueOf(valoreScarto)) {
					System.out.println("Modifico lo scarto al prodotto");
					scarto.setQuantita(Float.valueOf(valoreScarto));
					this.scarti.add(scarto);
					prodotto.modificaScarti(this.scarti);
				}
			}
		}

		this.changeName(catena, idProd, newId);

		this.changeidInfo(catena, newId, key, newInfo);

		this.changeidScarto(catena, newId, idScarto, newIdScarto);

	}

	/**
	 * Modify an information or a food waste on ProdConcreto
	 * 
	 * @param catena       the container of typology
	 * @param idProdCon    the id of the product to be modified
	 * @param idScarto     the waste of the product
	 * @param valoreScarto the value of waste
	 * @param key          the name of the info
	 * @param value        the value of the info
	 * @param newId        the new name of product
	 * @param newIdInfo    the new name of the key
	 * @param newIdScarto  the new name of a waste
	 */
	public void modifyProdConcreto(final Catena catena, final String idProdCon, final String idScarto,
			final String valoreScarto, final String key, final String value, final String newId, final String newInfo,
			final String newIdScarto) {

		ProdConcreto prodotto = null;
		Scarto scarto = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();

		if (catena.ottieniDallInventario(idProdCon).isPresent() && !idProdCon.isBlank()) {
			prodotto = (ProdConcreto) catena.ottieniDallInventario(idProdCon).get();
			if (!prodotto.getInfo().containsKey(key) && !key.isBlank()) {
				System.out.println("Aggiungo le informazioni al prodotto concreto");
				info.put(key, value);
				prodotto.aggiungiInfo(info);

				System.out.println(prodotto.getInfo());
			} else if (!prodotto.getInfo().get(key).equals(value) && !key.isBlank() && !value.isBlank()) {
				System.out.println("Modifico le informazioni al prodotto concreto");
				info.put(key, value);
				prodotto.modificaInfo(info);

				System.out.println(prodotto.getInfo());
			}
			if (!idScarto.isBlank() && !catena.ottieniUnoScarto(idScarto).isPresent()) {
				scarto = new Scarto(idScarto);
				System.out.println("Aggiungo lo scarto al prodotto concreto");
				if (!valoreScarto.isEmpty()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);

			} else if (!idScarto.isBlank()) {
				scarto = (Scarto) catena.ottieniUnoScarto(idScarto).get();
				if (!valoreScarto.isBlank() && scarto.getQuantita().floatValue() != Float.valueOf(valoreScarto)) {
					System.out.println("Modifico lo scarto al prodotto concreto");
					scarto.setQuantita(Float.valueOf(valoreScarto));
					this.scarti.add(scarto);
					prodotto.modificaScarti(this.scarti);
				}
			}
		}

		this.changeName(catena, idProdCon, newId);

		this.changeidInfo(catena, newId, key, newInfo);

		this.changeidScarto(catena, newId, idScarto, newIdScarto);

	}

	/**
	 * Modify an information or a food waste on ProdFornito
	 * 
	 * @param catena       the container of the product
	 * @param idProdFor    the id of the product to be modified
	 * @param idProdCon    the "father" of the product
	 * @param idScarto     the waste of the product
	 * @param valoreScarto the value of waste
	 * @param key          the name of the info
	 * @param value        the value of the info
	 * @param idForn       the name of the supplier
	 * @param newId        the new name of product
	 * @param newIdInfo    the new name of the key
	 * @param newIdScarto  the new name of a waste
	 */
	public void modifyProdFornito(final Catena catena, final String idProdFor, final String idScarto,
			final String valoreScarto, final String key, final String value, final String iForn, final String newId,
			final String newInfo, final String newIdScarto) {

		ProdFornito prodotto = null;
		Scarto scarto = null;
		float val = 0;
		final HashMap<String, String> info = new HashMap<>();

		if (catena.ottieniDallInventario(idProdFor).isPresent() && !idProdFor.isBlank()) {
			prodotto = (ProdFornito) catena.ottieniDallInventario(idProdFor).get();
			if (!prodotto.getInfo().containsKey(key) && !key.isBlank()) {
				System.out.println("Aggiungo le informazioni al prodotto fornito");
				info.put(key, value);
				prodotto.aggiungiInfo(info);

				System.out.println(prodotto.getInfo());
			} else if (!prodotto.getInfo().get(key).equals(value) && !key.isBlank() && !value.isBlank()) {
				System.out.println("Modifico le informazioni al prodotto fornito");
				info.put(key, value);
				prodotto.modificaInfo(info);

				System.out.println(prodotto.getInfo());
			}
			if (!idScarto.isBlank() && !catena.ottieniUnoScarto(idScarto).isPresent()) {
				scarto = new Scarto(idScarto);
				System.out.println("Aggiungo lo scarto al prodotto fornito");
				if (!valoreScarto.isEmpty()) {
					val = Float.parseFloat(valoreScarto);
				}
				scarto.setQuantita(val);
				this.scarti.add(scarto);
				prodotto.aggiungiScarti(this.scarti);
				catena.aggiungiScarti(this.scarti);
			} else if (!idScarto.isBlank()) {
				scarto = (Scarto) catena.ottieniUnoScarto(idScarto).get();
				if (!valoreScarto.isEmpty() && scarto.getQuantita().floatValue() != Float.valueOf(valoreScarto)) {
					System.out.println("Modifico lo scarto al prodotto fornito");
					scarto.setQuantita(Float.valueOf(valoreScarto));
					this.scarti.add(scarto);
					prodotto.modificaScarti(this.scarti);
				}
			}
		}

		this.changeName(catena, idProdFor, newId);

		this.changeidInfo(catena, newId, key, newInfo);

		this.changeidScarto(catena, newId, idScarto, newIdScarto);

	}

	/**
	 * Modify an information on the Dispensa
	 * 
	 * @param catena     the container of the product
	 * @param idTipo     the typology to be added
	 * @param idHotel    the name of the hotel
	 * @param idDispensa the name of the dispensa
	 */
	public void modifyTipoDispensa(final Catena catena, String idTipo, String idHotel, String idDispensa,
			final String newId) {
		if (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).isPresent() && !idHotel.isBlank()
				&& !idDispensa.isBlank()) {
			if (catena.ottieniDallInventario(idTipo).isPresent() && !catena.ottieniUnAlbergo(idHotel).get()
					.ottieniUnaDispensa(idDispensa).get().getTipologia().contains(idTipo)) {
				System.out.println("La dispensa è presente, aggiungo la tipologia già esistente");
				catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().aggiungiUnTipo(idTipo);
			} else {
				System.out.println("Creare la tipologia prima di aggiungerla");
			}
			if (!newId.isBlank() && !idDispensa.equals(newId)) {
				catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().setNome(newId);
			}
		}
	}

	/**
	 * Modify an information on the Fornitore
	 * 
	 * @param catena      the container of the product
	 * @param idFornitore the name of the supplier
	 * @param idProdotto  the name of product to be modified
	 * @param prezzo      the new price of the product
	 */
	public void modifyFornitore(final Catena catena, final String idFornitore, final String idProdotto,
			final String prezzo, final String newId) {

		final HashMap<String, Float> tmp = new HashMap<>();

		if (catena.ottieniUnFornitore(idFornitore).isPresent()) {
			if (!catena.ottieniUnFornitore(idFornitore).get().getProdotti(catena).containsKey(idProdotto)
					&& !prezzo.isBlank()) {
				if (Float.parseFloat(prezzo) > 0) {
					tmp.put(idProdotto, Float.parseFloat(prezzo));
				}
				catena.ottieniUnFornitore(idFornitore).get().aggiungiProdotti(tmp);
			}
			/*
			 * if
			 * (catena.ottieniUnFornitore(idFornitore).get().getProdotti(catena).containsKey
			 * (idProdotto) && Float.valueOf(prezzo) > 0 &&
			 * catena.ottieniUnFornitore(idFornitore).get().getProdotti(catena)
			 * .get(idProdotto).equals(Float.valueOf(prezzo))) { tmp.put(idProdotto,
			 * Float.valueOf(prezzo)); System.out.println("Modifico il prezzo al prodotto" +
			 * " " + idProdotto);
			 * catena.ottieniUnFornitore(idFornitore).get().modificaProdotti(tmp); } else if
			 * (catena.ottieniUnFornitore(idFornitore).get().getMiglioriProdotti(catena).
			 * containsKey(idProdotto) && Float.valueOf(prezzo) > 0 &&
			 * catena.ottieniUnFornitore(idFornitore).get()
			 * .getMiglioriProdotti(catena).get(idProdotto).equals(Float.valueOf(prezzo))) {
			 * tmp.put(idProdotto, Float.valueOf(prezzo));
			 * System.out.println("Modifico il prezzo al miglior prodotto" + idProdotto);
			 * catena.ottieniUnFornitore(idFornitore).get().modificaMiglioriProdotti(tmp); }
			 */
			if (!newId.isBlank() && !idFornitore.equals(newId)) {
				catena.ottieniUnFornitore(idFornitore).get().setID(newId);
			}

		}
	}

	/**
	 * Modiify an information on the Hotel
	 * 
	 * @param catena  the container of the product
	 * @param idHotel the name of the hotel
	 * @param newInfo the new information to be added
	 * @param newId   the new name of the hotel
	 */
	public void modifyHotel(Catena catena, String idHotel, String newInfo, String newId) {
		if (catena.ottieniUnAlbergo(idHotel).isPresent()) {
			if (!newInfo.isBlank() && !catena.ottieniUnAlbergo(idHotel).get().getInfo().equals(newInfo)) {
				catena.ottieniUnAlbergo(idHotel).get().setInfo(newInfo);
			}
			if (!newId.isBlank() && !catena.ottieniUnAlbergo(idHotel).get().getNome().equals(newId)) {
				catena.ottieniUnAlbergo(idHotel).get().setNome(newId);
			}
		}

	}

	/**
	 * Add the quantity of product to the storage
	 * 
	 * @param catena     the container of the product
	 * @param idHotel    the name of the hotel
	 * @param idDispensa the name of the dispensa
	 * @param idProdForn the name of the product to be added
	 * @param quantita   the quantity of the prodcut
	 */
	public void load(final Catena catena, String idHotel, String idDispensa, String idProdForn, String quantita) {

		if (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).isPresent() && !idHotel.isBlank()
				&& !idDispensa.isEmpty() && !idProdForn.isBlank()
				&& catena.ottieniDallInventario(idProdForn).isPresent()) {
			final ProdFornito prod = (ProdFornito) catena.ottieniDallInventario(idProdForn).get();

			if (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().getTipologia()
					.contains(prod.getPadre().getPadre().getPadre().getID())) {
				System.out.println("Il prodotto fornito è nel mio inventario e lo posso aggiungere");
				HashMap<String, Float> tmp = new HashMap<>();
				if (Float.parseFloat(quantita) > 0) {
					tmp.put(idProdForn, Float.parseFloat(quantita));
					catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().aggiungiContenuti(tmp);
					catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().getCarichi()
							.put(new Date(), tmp);
				}
			}
		} else {
			System.out.println("Il prodotto fornito non è nell'invetario e non posso aggiungerlo alla dispensa");
		}

	}

	/**
	 * Remove the quantity of product to the storage
	 * 
	 * @param catena     the container of the product
	 * @param idHotel    the name of the hotel
	 * @param idDispensa the name of the dispensa
	 * @param idProdForn the name of the product to be removed
	 * @param quantita   the quantity of the prodcut
	 */
	public void dump(final Catena catena, String idHotel, String idDispensa, String idProdForn, String quantita) {

		if (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).isPresent() && !idHotel.isBlank()
				&& !idDispensa.isBlank() && !idProdForn.isBlank()
				&& catena.ottieniDallInventario(idProdForn).isPresent()) {
			final ProdFornito prod = (ProdFornito) catena.ottieniDallInventario(idProdForn).get();

			if (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().getTipologia()
					.contains(prod.getPadre().getPadre().getPadre().getID())) {
				System.out.println("Il prodotto fornito è nel mio inventario e lo posso eliminare");
				HashMap<String, Float> tmp = new HashMap<>();

				if (Float.parseFloat(quantita) > 0
						&& (catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().getContenuto()
								.get(idProdForn) - Float.parseFloat(quantita)) > 0) {
					tmp.put(idProdForn, Float.parseFloat(quantita));
					catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().rimuoviContenuti(tmp);
					catena.ottieniUnAlbergo(idHotel).get().ottieniUnaDispensa(idDispensa).get().getScarichi()
							.put(new Date(), tmp);
				} else {
					System.out.println("Non è presenta la quantità che si vuole eliminare");
				}
			}
		} else {
			System.out.println("Il prodotto fornito non è nell'invetario e non posso rimuoverlo dalla dispensa");
		}
	}

	/**
	 * Change the name of the typology
	 * 
	 * @param catena the container of the product
	 * @param id     the name of the typology
	 * @param newId  the new of the typology
	 */
	public void changeName(final Catena catena, final String id, final String newId) {
		if (catena.ottieniDallInventario(id).isPresent()) {
			if (!newId.isBlank()) {
				Typology t = (Typology) catena.ottieniDallInventario(id).get();
				if (t instanceof Tipologia && !t.getID().equals(newId)) {
					((Tipologia) catena.ottieniDallInventario(id).get()).setID(newId);
				}
				if (t instanceof Prodotto && !t.getID().equals(newId)) {
					((Prodotto) catena.ottieniDallInventario(id).get()).setID(newId);
				}
				if (t instanceof ProdConcreto && !t.getID().equals(newId)) {
					((ProdConcreto) catena.ottieniDallInventario(id).get()).setID(newId);
				}
				if (t instanceof ProdFornito && !t.getID().equals(newId)) {
					((ProdFornito) catena.ottieniDallInventario(id).get()).setID(newId);
				}
			}
		}
	}

	/**
	 * Change the id of an information
	 * 
	 * @param catena    the container of the product
	 * @param id        the name of the typology
	 * @param idInfo    the name of the information
	 * @param newIdInfo the new name of the information
	 */
	public void changeidInfo(final Catena catena, final String id, final String idInfo, final String newIdInfo) {
		if (catena.ottieniDallInventario(id).isPresent()) {
			Typology t = (Typology) catena.ottieniDallInventario(id).get();
			String s;
			if (!newIdInfo.isBlank()) {
				if (t instanceof Tipologia && !((Tipologia) t).getInfo().containsKey(newIdInfo)) {
					s = ((Tipologia) t).getInfo().get(idInfo);
					((Tipologia) catena.ottieniDallInventario(id).get()).getInfo().remove(idInfo);
					((Tipologia) catena.ottieniDallInventario(id).get()).getInfo().put(newIdInfo, s);
				}
				if (t instanceof Prodotto && !((Prodotto) t).getInfo().containsKey(newIdInfo)) {
					s = ((Prodotto) t).getInfo().get(idInfo);
					((Prodotto) catena.ottieniDallInventario(id).get()).getInfo().remove(idInfo);
					((Prodotto) catena.ottieniDallInventario(id).get()).getInfo().put(newIdInfo, s);
				}
				if (t instanceof ProdConcreto && !((ProdConcreto) t).getInfo().containsKey(newIdInfo)) {
					s = ((ProdConcreto) t).getInfo().get(idInfo);
					((ProdConcreto) catena.ottieniDallInventario(id).get()).getInfo().remove(idInfo);
					((ProdConcreto) catena.ottieniDallInventario(id).get()).getInfo().put(newIdInfo, s);
				}
				if (t instanceof ProdFornito && !((ProdFornito) t).getInfo().containsKey(newIdInfo)) {
					s = ((ProdFornito) t).getInfo().get(idInfo);
					((ProdFornito) catena.ottieniDallInventario(id).get()).getInfo().remove(idInfo);
					((ProdFornito) catena.ottieniDallInventario(id).get()).getInfo().put(newIdInfo, s);
				}
			}
		}
	}

	/**
	 * Change the id of a waste
	 * 
	 * @param catena      the container of the product
	 * @param id          the name of the product
	 * @param idScarto    the name of a waste
	 * @param newIdScarto the new name of a waste
	 */
	public void changeidScarto(final Catena catena, final String id, final String idScarto, final String newIdScarto) {
		if (catena.ottieniDallInventario(id).isPresent() && catena.ottieniUnoScarto(idScarto).isPresent()) {
			Typology t = (Typology) catena.ottieniDallInventario(id).get();
			Scarto s = catena.ottieniUnoScarto(idScarto).get();
			ArrayList<String> tmpRemove = new ArrayList<>();
			ArrayList<Scarto> tmpAdd = new ArrayList<>();
			Float f;
			if (!newIdScarto.isBlank() && !idScarto.equals(newIdScarto)) {
				Scarto newS = new Scarto(newIdScarto);
				if (t instanceof Prodotto && ((Prodotto) t).getScarti().contains(s)) {
					System.out.println("entro");
					tmpRemove.add(s.getID());
					int index = ((Prodotto) t).getScarti().indexOf(s);
					f = ((Prodotto) t).getScarti().get(index).getQuantita();
					newS.setQuantita(f);
					tmpAdd.add(newS);
					((Prodotto) catena.ottieniDallInventario(id).get()).getScarti().remove(s);
					((Prodotto) catena.ottieniDallInventario(id).get()).aggiungiScarti(tmpAdd);
				}
				if (t instanceof ProdConcreto && ((ProdConcreto) t).getScarti().contains(s)) {
					System.out.println("entro");
					tmpRemove.add(s.getID());
					int index = ((ProdConcreto) t).getScarti().indexOf(s);
					f = ((ProdConcreto) t).getScarti().get(index).getQuantita();
					newS.setQuantita(f);
					tmpAdd.add(newS);
					((ProdConcreto) catena.ottieniDallInventario(id).get()).getScarti().remove(s);
					((ProdConcreto) catena.ottieniDallInventario(id).get()).aggiungiScarti(tmpAdd);
				}
				if (t instanceof ProdFornito && ((ProdFornito) t).getScarti().contains(s)) {
					System.out.println("entro");
					tmpRemove.add(s.getID());
					int index = ((ProdFornito) t).getScarti().indexOf(s);
					f = ((ProdFornito) t).getScarti().get(index).getQuantita();
					newS.setQuantita(f);
					tmpAdd.add(newS);
					((ProdFornito) catena.ottieniDallInventario(id).get()).getScarti().remove(s);
					((ProdFornito) catena.ottieniDallInventario(id).get()).aggiungiScarti(tmpAdd);
				}

			}
		}
	}

}
