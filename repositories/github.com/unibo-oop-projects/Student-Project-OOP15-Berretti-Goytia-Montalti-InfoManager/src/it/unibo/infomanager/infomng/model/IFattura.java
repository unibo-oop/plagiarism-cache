package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;

public interface IFattura {
	
	/**
	 * The list of invoices than save into db
	 * @return
	 * the list of invoices
	 * @throws SQLException
	 * error durring the reading of db
	 */
	static List<IFattura> elencoFatture() throws SQLException{
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Fatture");
		return query.find().stream()
				.map(c -> new Fattura(c))
				.collect(Collectors.toList());
	}
	
	/**
	 * Search invoce for the invoces number
	 * @param invoiceNumber
	 * number of invoces required
	 * @return
	 * an optional of invoces whith invoches number equal to required number
	 * @throws SQLException
	 * error during the db reading
	 */
	static Optional<IFattura> searchIvoicesForNumber(Integer invoiceNumber, String clientName, String clientSurname) throws SQLException{
		return IFattura.elencoFatture().stream()
				.filter(f -> f.getNumeroOrdine().equals(invoiceNumber) )
				.filter(f -> {
					if (f.getCliente() != null){
						return f.getCliente().getName().equals(clientName);
					}
					else if(f.getFornitore() != null){
						return f.getFornitore().getName().equals(clientName);
					}
					else{
						return false;
					}
				})
				.filter(f -> {
					if(f.getCliente() != null){
						return f.getCliente().getLastName().equals(clientSurname);
					}
					else if(f.getFornitore() != null){
						return f.getFornitore().getLastName().equals(clientSurname);
					}
					else{
						return false;
					}
				})
				.findFirst();
	}
	
	List<prodottoMovimento> getProdotti();
	
	Date getData();
	Integer getNumeroOrdine();
	
	modelClientsI getCliente();
	modelProvidersI getFornitore();
	
	Date[] getConsegna();
	
	String getTipoOrdine();
	
	String getNomeNegozio();
	
	String getTipoPagamento();
	
	String getBanca();
	
	String getNote();
	
	Integer getSconto();
	Integer getIVA();
	
	
	public static class FatturaBuilder{
		List<prodottoMovimento> prodotti;
		
		Date data;
		Integer numeroOrdine;
		
		modelClientsI cliente;
		modelProvidersI fornitore;
		
		Date[] consegna;
		
		String tipoOrdine;
		
		String nomeNegozio;
		
		String tipoPagamento;
		
		String banca;
		
		String note;
		
		Double sconto;
		Integer IVA;
	
		public void addProdotto(modelStoreI prodotto, Integer quantita, Double prezzo){
			prodottoMovimento tmp = new prodottoMovimento();
			tmp.prodotto = prodotto;
			tmp.quantita = quantita;
			tmp.prezzo = prezzo;
			this.prodotti.add(tmp);
		}
		
		public void addProdotto(prodottoMovimento nuovo){
			this.prodotti.add(nuovo);
		}
		
		public Date getData() {
			return data;
		}

		public Integer getNumeroOrdine() {
			return numeroOrdine;
		}

		public modelClientsI getCliente() {
			return cliente;
		}

		public modelProvidersI getFornitore() {
			return fornitore;
		}

		public Date[] getConsegna() {
			return consegna;
		}

		public String getTipoOrdine() {
			return tipoOrdine;
		}

		public String getNomeNegozio() {
			return nomeNegozio;
		}

		public String getTipoPagamento() {
			return tipoPagamento;
		}

		public String getBanca() {
			return banca;
		}

		public String getNote() {
			return note;
		}

		public Double getSconto() {
			return sconto;
		}

		public Integer getIVA() {
			return IVA;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public void setNumeroOrdine(Integer numeroOrdine) {
			this.numeroOrdine = numeroOrdine;
		}

		public void setCliente(modelClientsI cliente) {
			this.cliente = cliente;
		}

		public void setFornitore(modelProvidersI fornitore) {
			this.fornitore = fornitore;
		}

		public void setConsegna(Date[] consegna) {
			this.consegna = consegna;
		}

		public void setTipoOrdine(String tipoOrdine) {
			this.tipoOrdine = tipoOrdine;
		}

		public void setNomeNegozio(String nomeNegozio) {
			this.nomeNegozio = nomeNegozio;
		}

		public void setTipoPagamento(String tipoPagamento) {
			this.tipoPagamento = tipoPagamento;
		}

		public void setBanca(String banca) {
			this.banca = banca;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public void setSconto(Double sconto) {
			this.sconto = sconto;
		}

		public void setIVA(Integer iVA) {
			IVA = iVA;
		}

		public FatturaBuilder(){
			this.prodotti = new ArrayList<>();
			consegna = new Date[2];
			
		}
		
		public IFattura salva(){
			TableRow padre = TableRow.oggettoDaTabella("Fatture");
			padre.setObjectValue("Numero", numeroOrdine);
			if(this.cliente != null){
				padre.setObjectValue("IDCliente", this.cliente.getID());
			}
			else {
				padre.setObjectValue("IDFornitore", this.fornitore.getID());
			}
			
			padre.setObjectValue("Data", this.data);
			
			padre.setObjectValue("da", this.consegna[0]);
			padre.setObjectValue("al", this.consegna[1]);
			padre.setObjectValue("TipoOrdine", this.tipoOrdine);
			padre.setObjectValue("NomeNegozio", this.nomeNegozio);
			padre.setObjectValue("TipoPagamento", this.tipoPagamento);
			padre.setObjectValue("Banca", this.banca);
			padre.setObjectValue("Sconto", this.sconto);
			padre.setObjectValue("IVA", this.IVA);
			
			if(padre.salva()){
				this.prodotti.stream()
				.map(p -> {
					TableRow figlio = TableRow.oggettoDaTabella("ProdottoVendita");
					figlio.setObjectValue("IDProdotto", p.prodotto.getID());
					figlio.setObjectValue("Quantita", p.quantita);
					figlio.setObjectValue("Prezzo", p.prezzo);
					figlio.setObjectValue("IDFattura", padre.objectId());
					return figlio;
				})
				.forEach(c -> c.salva());
			}
			
			try {
				return new Fattura(padre.objectId());
			} catch (SQLException e) {
				return null;
			}
		}
		
	}
}
