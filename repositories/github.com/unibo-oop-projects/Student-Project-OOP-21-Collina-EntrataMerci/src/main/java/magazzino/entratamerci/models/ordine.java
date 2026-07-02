package magazzino.entratamerci.models;

import java.util.Date;
import java.util.Objects;

public class ordine {

	private int anno;
	private int numero;

	public String data;
	
	private fornitore fornitore;
	private carrello carrello;
	
	public ordine(int anno, int numero, String data, magazzino.entratamerci.models.fornitore fornitore,
			magazzino.entratamerci.models.carrello carrello) {
		super();
		this.anno = anno;
		this.numero = numero;
		this.data = data;
		this.fornitore = fornitore;
		this.carrello = carrello;
	}

	public int getAnno() {
		return anno;
	}

	public int getNumero() {
		return numero;
	}

	public String getData() {
		return data;
	}

	public magazzino.entratamerci.models.fornitore getFornitore() {
		return fornitore;
	}

	public String getAnnoNumero() {
		return anno + "/" + numero;
	}

	public int getNumeroPezzi(){
		System.out.println(carrello.getItems().stream().count());
		return carrello.getItems().stream().mapToInt(x-> x.getQuantita()).sum();
	}

	public magazzino.entratamerci.models.carrello getCarrello() {
		return carrello;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ordine ordine = (ordine) o;
		return anno == ordine.anno && numero == ordine.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, numero);
	}
}
