package it.unibo.infomanager.infomng.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import it.unibo.infomanager.infomng.controller.delegate.researchDelegate;
import it.unibo.infomanager.infomng.model.IFattura;
import it.unibo.infomanager.infomng.model.modelClientsI;
import it.unibo.infomanager.infomng.model.modelProvidersI;
import it.unibo.infomanager.infomng.model.modelReceiptsI;
import it.unibo.infomanager.infomng.model.modelReunionsI;
import it.unibo.infomanager.infomng.model.modelStoreI;
import it.unibo.infomanager.infomng.model.modelUsersI;
import it.unibo.infomanager.infomng.model.prodottoMovimento;
import it.unibo.infomanager.infomng.model.transactionsProducts;
import it.unibo.infomanager.infomng.model.transactionsProductsI;
import it.unibo.infomanager.infomng.view.FattureGUI;
import it.unibo.infomanager.infomng.view.ScontriniGUI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.interfaces.ViewInterface;

public class ObserverInterfaceImpl implements ObserverInterface {

	private static UtenteCorrente currentUser = new UtenteCorrente();

	private ViewInterface view;
	private JFrame attuale;
	private Optional<researchDelegate> delegate;
	
	@Override
	public Optional<JFrame> getAttuale() {
		return Optional.ofNullable(this.attuale);
	}

	@Override
	public void setAttuale(JFrame attuale) {
		this.attuale = attuale;
	}

	public ObserverInterfaceImpl(ViewInterface view) {
		this(view, null);
	}

	public ObserverInterfaceImpl(ViewInterface view, JFrame attuale) {
		this(view, attuale, null);
	}
	
	public ObserverInterfaceImpl(ViewInterface view, JFrame attuale, researchDelegate delegate) {
		this.view = view;
		if(view != null){
			this.view.setOggettoController(this);
		}
		this.attuale = attuale;
		this.delegate = Optional.ofNullable(delegate);
	}

	@Override
	public void mostraClienti() {
		this.view.viewClienti();

	}

	@Override
	public void mostraFornitori() {
		this.view.viewFornitori();

	}

	@Override
	public void mostraFatture() {
		this.view.viewFatture();

	}

	@Override
	public void start() {
		this.view.viewStart();
	}

	@Override
	public void mostraMenu() {
		this.view.viewMenu();
		if (this.attuale != null) {
			this.attuale.dispose();
		}
	}

	@Override
	public void mostraMagazzino() {
		this.view.viewMagazzino();
	}

	@Override
	public void mostraRegistiIva() {
		this.view.viewRegistiIva();
	}

	@Override
	public void mostraReportVendite() {
		this.view.viewReportVendite();

	}

	@Override
	public void mostraRiunioni() {
		this.view.viewRiunioni();

	}

	@Override
	public void mostraScontrini() {
		this.view.viewScontrini();

	}

	@Override
	public void mostraDialogCampoObbligatorio() {
		this.view.viewDialogCampoObbligatorio();
	}

	@Override
	public void mostraDialogCerca() {
		this.view.viewDialogCerca();
	}

	@Override
	public void mostraDialogNuovo() {
		this.view.viewDialogNuovo();

	}

	@Override
	public void mostraDialogRegistrati() {
		this.view.viewDialogRegistrati();
	}

	@Override
	public void mostraDialogWrongPass() {
		this.view.viewDialogWrongPass();
	}

	@Override
	public void mostraDialogWrongUser() {
		this.view.viewDialogWrongUser();
	}

	@Override
	public boolean salvaUtente(Map<String, String> dati) {
		// ottengo i dati utente
		String username = dati.get("Username");
		String password = dati.get("Password");
		String mail = dati.get("Email");
		@SuppressWarnings("unused") //lo so che non è usato
		String indirizzo = dati.get("Indirizzo");
		String nomeNegozio = dati.get("Nome");

		if(modelUsersI.usersList().stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList()).size() > 0){
			return false;
		}
		
		if (modelUsersI.builder(nomeNegozio, null, mail, username, password)) {
			UtenteCorrente.tmpUser tmp = new UtenteCorrente.tmpUser();
			tmp.nome = username;
			ObserverInterfaceImpl.currentUser.setUtente(tmp);
			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean salvaCliente(Map<String, String> dati) {
		String nome = dati.get("Nome");
		String cognome = dati.get("Cognome");
		String mail = dati.get("Email");
		String telefono = dati.get("Telefono");
		String nomeNegozio = ""; // in attesa del metodoto per il nome negozio
		return modelClientsI.builder(nome, cognome, mail, telefono, nomeNegozio);
	}

	@Override
	public boolean salvaFornitore(Map<String, String> dati) {
		String nome = dati.get("Nome");
		String cognome = dati.get("Cognome");
		String telefono = dati.get("Telefono");
		String mail = dati.get("Email");
		return modelProvidersI.builder(nome, cognome, mail, telefono);
	}

	@Override
	public saveResult salvaFattura(Map<String, Object> dati) throws ParseException, NumberFormatException {
		Integer numeroFattura = Integer.parseInt((String) dati.get("NumeroOrdine"));
		String cliente = (String) dati.get("Fornitore/Cliente");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(dateFormatter.parse((String) dati.get("DataOrdine")).getTime());

		Date inizio = new Date(dateFormatter.parse((String) dati.get("Dal")).getTime());
		Date fine = new Date(dateFormatter.parse((String) dati.get("Al")).getTime());
		String banca = (String) dati.get("Banca");

		Double sconto = Double.parseDouble((String) dati.get("Sconto"));
		Integer iva = Integer.parseInt((String) dati.get("IVA"));

		String descrizione = String.format("Tipo ordine : %s, Banca %s, note %s", banca,
				(String) dati.get("Tipo ordine"), (String) dati.get("Note"));
		String nomeNegozio = (String) dati.get("Negozio");

		IFattura.FatturaBuilder builder = new IFattura.FatturaBuilder();
		// imposto il numero della fattura
		builder.setNumeroOrdine(numeroFattura);
		// cerco il fornitore
		Optional<modelProvidersI> tmpFornitore = this.ottieniFornitoreDaNome(cliente);
		if (tmpFornitore.isPresent()) {
			// il fornitore è stato trovato
			builder.setFornitore(tmpFornitore.get());
		} else {
			// il fornitore non è stato trovato
			// cerco il cliente
			Optional<modelClientsI> tmpCliente = this.ottieniClienteDaNome(cliente);
			if (tmpCliente.isPresent()) {
				// il cliente è stato trovato
				builder.setCliente(tmpCliente.get());
			} else {
				// non è stato trovato ne cliente ne fornitore
				return saveResult.errorData;
			}
		}

		// imposto le date di richiesta e di consegna
		builder.setData(data);
		builder.getConsegna()[0] = inizio;
		builder.getConsegna()[1] = fine;

		builder.setBanca(banca);

		builder.setSconto(sconto);
		builder.setIVA(iva);
		builder.setNomeNegozio(nomeNegozio);
		builder.setNote(descrizione);

		// ho finito di impostare i campi della fattura ora aggiungo i prodotti
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> prodotti = (List<Map<String, Object>>) dati.get("Prodotti");
		prodotti.stream().map(e -> {
			String nomeProdotto = (String) e.get("Nome");
			Integer quantita = Integer.parseInt((String) e.get("Quantita"));
			Double prezzo = Double.parseDouble((String) e.get("Prezzo"));
			// cerco il prodotto
			Optional<modelStoreI> tmpProdotto = this.ottengoProdottoDaNome(nomeProdotto);
			prodottoMovimento ritorno = null;
			if (tmpProdotto.isPresent()) {
				ritorno = new prodottoMovimento();
				ritorno.prodotto = tmpProdotto.get();
				ritorno.prezzo = prezzo;
				ritorno.quantita = quantita;
			} else {
				System.out.println(String.format("Prodotto %s non trovato", nomeProdotto));
			}
			return Optional.ofNullable(ritorno);
		}).filter(e -> e.isPresent()).map(e -> e.get()).forEach(e -> builder.addProdotto(e));

		// ho aggiunto tutti i prodotti che sono riuscito a trovare
		IFattura nuova = builder.salva();
		if (nuova != null) {
			return saveResult.success;
		} else {
			return saveResult.errorSave;
		}
	}

	private Optional<modelStoreI> ottengoProdottoDaNome(String nome) {
		List<modelStoreI> tmp = modelStoreI.serachProductsByName(nome);
		if (tmp.size() > 0) {
			return Optional.of(tmp.get(0));
		} else {
			return Optional.empty();
		}
	}

	private Optional<modelProvidersI> ottieniFornitoreDaNome(String nome) {
		List<modelProvidersI> tmp = modelProvidersI.searchProviders(nome, null, null, null);
		if (tmp.size() > 0) {
			return Optional.of(tmp.get(0));
		} else {
			return Optional.empty();
		}
	}

	private Optional<modelClientsI> ottieniClienteDaNome(String nome) {
		List<modelClientsI> tmp = modelClientsI.searchClients(nome, null, null, null, null);
		if (tmp.size() > 0) {
			return Optional.of(tmp.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean salvaRiunione(Map<String, String> dati) throws ParseException {
		@SuppressWarnings("unused") //lo so
		String evento = dati.get("Evento");
		String giorno = dati.get("Giorno");
		String mese = dati.get("Mese");
		String anno = dati.get("Anno");
		String note = dati.get("Note");

		String nome = String.format("Riunione del %s/%s/%s", giorno, mese, anno);

		DateFormat formatterData = new SimpleDateFormat("dd-MM-yyyy");
		Date dataEora = new Date(formatterData.parse(String.format("%s-%s-%s", giorno, mese, anno)).getTime());
		return modelReunionsI.builder(dataEora, nome, note, modelUsersI.getUtenteCorrente());

	}

	@Override
	public void abilitaFrame(boolean attiva) {
		if (attiva) {
			this.attuale.setEnabled(attiva);
		} else {
			this.attuale.setEnabled(attiva);
		}
	}

	@Override
	public void salvaScontrini(Map<String, Object> dati) {
		// ottengo i dati dalla mappa
		
		
		/*
		 * Ho inserito un suppress warning per la variabile in quanto sono sicuro che all'interno della mappa è presente una lista
		 * per sistemare il problema sarebbe sufficente modificare il tipo all'interno della mappa con una classe contenente la lista e il relativo getter
		 */
		@SuppressWarnings("unchecked")
		List<String> tmpProdotti = (List<String>) dati.get("Prodotti");
		
		Integer iva = Integer.parseInt((String) dati.get("Iva"));
		Integer sconto = Integer.parseInt((String) dati.get("Sconto"));
		Date data = new Date(new java.util.Date().getTime());
		List<Map<String, Object>> proodtti = this.estraiProdottiDaLista(tmpProdotti);
 
		List<transactionsProductsI> lista = proodtti.stream()
				.map(d -> {
					Integer idProdotto = ((modelStoreI)d.get("Prodotto")).getID();
					Integer quantita = (Integer) d.get("Quantita");
					Double prezzo = (Double)d.get("Prezzo");
					modelStoreI prodotto = modelStoreI.productsList().stream()
							.filter(p -> p.getID().equals(idProdotto))
							.findFirst().get();
					transactionsProductsI t = new transactionsProducts(prodotto, quantita, prezzo);
					return t;
				}).collect(Collectors.toList());
		
		modelReceiptsI.builder(Optional.empty(), Optional.empty(), data, iva, sconto, lista);
		
	}
	

	@Override
	public List<modelStoreI> ricercaProdotti(String nome) {
		return modelStoreI.serachProductsByName(nome);
	}

	@Override
	public int quantitaProdotti() {
		List<modelStoreI> tmp = modelStoreI.productsList();
		return tmp == null ? 0 : tmp.size();
	}

	@Override
	public Navigator<modelProvidersI> cercaFornitori(String nome) {
		Navigator<modelProvidersI> col = new ListOfObjectImpl<>(modelProvidersI.searchProviders(nome, "", "", ""), this.attuale);
		return col;
	}

	@Override
	public Navigator<modelClientsI> cercaClienti(String nome) {
		Navigator<modelClientsI> col = new ListOfObjectImpl<>(modelClientsI.searchClients(nome, "", "", "", ""), this.attuale);
		return col;
	}

	@Override
	public List<modelReunionsI> cercaRiunioni(String data, String nome) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date giorno = new Date(format.parse(data).getTime());
		return modelReunionsI.reunionsList().stream().filter(r -> {
			return r.getNameReunion().equals(nome) && r.getDate().equals(giorno);
		}).collect(Collectors.toList());
	}

	@Override
	public Navigator<modelReceiptsI> cercaScontrini(String numero, String nome) throws NumberFormatException {
		Integer numeroScontrino = Integer.parseInt(numero);
		modelReceiptsI scontrino = modelReceiptsI.searchReceiptByNumber(numeroScontrino);
		if(this.attuale.getClass().equals(ScontriniGUI.class)){
			ScontriniGUI scontriniView = (ScontriniGUI)this.attuale;
			
			Navigator<modelReceiptsI> ritorno = new ListOfObjectImpl<>(Arrays.asList(scontrino), scontriniView);
			scontriniView.setNavigator(ritorno);
			return ritorno;
			
		}
		return null;
	}

	@Override
	public boolean userLogin(String user, String pass) {
		try{
			if (modelUsersI.usersLogin(user, pass)) {
				UtenteCorrente.tmpUser tmp = new UtenteCorrente.tmpUser();
				tmp.nome = user;
				ObserverInterfaceImpl.currentUser.setUtente(tmp);
				return true;
			} else {
				return false;
			}
		}catch(NullPointerException e){
			return false;
		}
	}

	@Override
	public Navigator<IFattura> cercaFatture(String numero, String nome, String cognome) throws NumberFormatException {
		Integer invoiceNumber = Integer.parseInt(numero);
		FattureGUI view = (FattureGUI) this.attuale;
		Optional<IFattura> fattura;
		Navigator<IFattura> ritorno = null;
		try {
			fattura = IFattura.searchIvoicesForNumber(invoiceNumber, nome, cognome);
			if(fattura.isPresent()){
				ritorno = new ListOfObjectImpl<IFattura>(Arrays.asList(fattura.get()), view);
			}
			else{
				ritorno = null;
			}
			view.setNavigator(ritorno);
			
		} catch (SQLException e) {
			fattura = Optional.empty();
		}
		
		if(fattura.isPresent() && this.delegate.isPresent()){
			this.delegate.get().ricercaCompletata(fattura.getClass(), Arrays.asList(fattura));
		}
		
		return ritorno;
	}
	
	@Override
	public List<modelStoreI> listOfProducts() {
		List<modelStoreI> tmp = modelStoreI.productsList();
		if(tmp == null){
			return new ArrayList<>();
		}
		else{
			return tmp;
		}
	}
	
	


	public enum saveResult {
		success(""), errorData("Dati non corretti"), errorSave("Errore durante il salvataggio");

		public String rawValue;

		private saveResult(String rawValue) {
			this.rawValue = rawValue;
		}
	}
	
	private List<Map<String, Object>> estraiProdottiDaLista(List<String> obj){
		List<Map<String, Object>> ritorno = new ArrayList<>();
		for(int i = 0; i < obj.size(); i += 3){
			Integer quantita = Integer.parseInt(obj.get(i));
			Optional<modelStoreI> prodotto = this.ottengoProdottoDaNome(obj.get(i+1));
			Double prezzo = Double.parseDouble(obj.get(i+2));
			if(prodotto.isPresent()){
				Map<String, Object> tmp = new HashMap<>();
				tmp.put("Prodotto", prodotto.get());
				tmp.put("Quantita", quantita);
				tmp.put("Prezzo", prezzo);
				ritorno.add(tmp);
			}
		}
		return ritorno;
	}
}
