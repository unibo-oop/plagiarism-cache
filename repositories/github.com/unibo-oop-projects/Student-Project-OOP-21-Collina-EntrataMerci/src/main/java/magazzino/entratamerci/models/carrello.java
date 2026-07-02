package magazzino.entratamerci.models;

import java.util.ArrayList;
import java.util.Optional;

public class carrello {
	

	private area area;
	private locazione locazione;
	
	private ArrayList<itemCarrello> items;

	public carrello() {
		super();
		items = new ArrayList<itemCarrello>();
	}


	public carrello(String codiceMatricola, magazzino.entratamerci.models.area area,
			magazzino.entratamerci.models.locazione locazione, magazzino.entratamerci.models.fornitore fornitore,
			ArrayList<itemCarrello> items) {
		this.area = area;
		this.locazione = locazione;
		this.items = items;
	}

	public void removeItem(String articoloCodice) {
		Optional<itemCarrello> item = items.stream().filter(x -> x.articolo.getCodice().equals(articoloCodice))
				.findFirst();

		if (item.isPresent()) {
			items.remove(item.get());
		}
	}
	
	public void addItem(articolo a, int qta) {
		addItem(new itemCarrello(a, qta));
	}
	
	public void addItem(itemCarrello itemToAdd) {
		Optional<itemCarrello> item = items.stream().filter(x -> x.articolo.getCodice().equals(itemToAdd.articolo.getCodice()))
				.findFirst();

		if (item.isPresent()) {
			
			item.get().addQuantity(itemToAdd.quantita);
		}
		else {			
			items.add(itemToAdd);
		}
	}

	public void changePosition(area area, locazione locazione) {
		this.area = area;
		this.locazione = locazione;
	}

	public ArrayList<itemCarrello> getItems() {
		return items;
	}

	public magazzino.entratamerci.models.locazione getLocazione() {
		return locazione;
	}

	public class itemCarrello {
		private articolo articolo;
		private int quantita;

		public itemCarrello(articolo articolo, int quantita) {
			super();
			this.articolo = articolo;
			this.quantita = quantita;
		}
		
		public void addQuantity(int quantita){
			this.quantita = this.quantita + quantita;
		}
		
		public void removeQuantity(int quantita) throws Exception {
			if(this.quantita < 0 ) {
				throw new Exception("Impossibile rettificare quantita in negativo");
			}
		}

		public void updateQuantita(int quantita){
			this.quantita = quantita;
		}

		public magazzino.entratamerci.models.articolo getArticolo() {
			return articolo;
		}

		public int getQuantita() {
			return quantita;
		}
	}
}
