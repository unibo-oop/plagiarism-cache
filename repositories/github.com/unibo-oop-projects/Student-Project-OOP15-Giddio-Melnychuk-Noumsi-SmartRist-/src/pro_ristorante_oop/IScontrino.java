package pro_ristorante_oop;

import java.util.List;

public interface IScontrino {
	public void setTot(List<Piatti> p,List<Drink> d);
	Double getTot();
	String ToString();
	void clear();
}
