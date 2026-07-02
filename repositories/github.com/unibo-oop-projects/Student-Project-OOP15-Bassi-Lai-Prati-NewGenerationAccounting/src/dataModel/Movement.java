package dataModel;

/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movement implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Data", "Nome Conto", "Dare", "Avere" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -65774517331356915L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Date data;

	private LinkedList<Operation> listaConti;

	public Movement(Date data, LinkedList<Operation> object) {
		this.data = data;
		this.listaConti = new LinkedList<>(object);
	}

	public Date getData() {
		return data;
	}

	public List<Operation> getListaConti() {
		return listaConti;
	}

	@Override
	public Object getValueAt(int column) {
		String str = "";
		if (listaConti.size() > 0) {
			switch (column) {
			case 0:
				return getData();
			case 1:
				str = listaConti.get(0).getConto().getName();
				for (int i = 1; i < listaConti.size(); i++) {
					str += "\n" + listaConti.get(i).getConto().getName();
				}
				break;
			case 2:
				str = String.valueOf(listaConti.get(0).getDare());
				for (int i = 1; i < listaConti.size(); i++) {
					str += "\n" + String.valueOf(listaConti.get(i).getDare());
				}
				break;
			case 3:
				str = String.valueOf(listaConti.get(0).getAvere());
				for (int i = 1; i < listaConti.size(); i++) {
					str += "\n" + String.valueOf(listaConti.get(i).getAvere());
				}
				break;
			}
		}
		return str;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setListaConti(LinkedList<Operation> listaContiUsati) {
		this.listaConti = listaContiUsati;
	}

	@Override
	public String toString() {
		return "Movement [data=" + data + ", listaConti=" + listaConti + "]";
	}

}
