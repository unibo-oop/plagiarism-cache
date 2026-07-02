package dataModel;

public class Operation implements IEdiTableDataModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 14756423876583L;

	private static final String[] INTESTAZIONE = { "Conto", "Dare", "Avere" };

	public static Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return Account.class;
		case 1:
			return Float.class;
		case 2:
			return Float.class;
		default:
			return null;
		}
	}

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Account conto;

	private Float dare;

	private Float avere;

	public Operation(Account conto, float dare, float avere) {
		this.conto = conto;
		this.dare = dare;
		this.avere = avere;
	}

	public float getAvere() {
		return avere;
	}

	public Account getConto() {
		return conto;
	}

	public float getDare() {
		return dare;
	}

	@Override
	public Object getValueAt(int column) {
		switch (column) {
		case 0:
			return getConto();
		case 1:
			return getDare();
		case 2:
			return getAvere();
		default:
			return "";
		}
	}

	public void setAvere(float avere) {
		this.avere = avere;
	}

	public void setConto(Account conto) {
		this.conto = conto;
	}

	public void setDare(float dare) {
		this.dare = dare;
	}

	@Override
	public void setValueAt(Object value, int column) throws IllegalArgumentException {
		switch (column) {
		case 0:
			if (value instanceof Account) {
				setConto((Account) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		case 1:
			if (value instanceof Float) {
				setDare((float) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		case 2:
			if (value instanceof Float) {
				setAvere((float) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		}
	}

	@Override
	public String toString() {
		return "Operation [conto=" + conto + ", dare=" + dare + ", avere=" + avere + "]";
	}

}
