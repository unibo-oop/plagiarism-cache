package magazzino.entratamerci.models;

import java.util.Objects;

public class locazione extends posizione {
	private area area;

	public locazione(area area, String codice, String descrizione) {
		super(codice, descrizione);
		this.area = area;
	}

	public magazzino.entratamerci.models.area getArea() {
		return area;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)){
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			locazione locazione = (locazione) o;
			return Objects.equals(getArea(), locazione.getArea());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
