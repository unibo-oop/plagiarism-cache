package dataModel;

import dataEnum.Natures;
import dataEnum.Sections;

/**
 * classe per la gestione del singolo conto
 * 
 * @author niky
 *
 */
public class Account implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Natura", "Sezione", "Nome", "Saldo" };

	/**
	 * 
	 */
	private static final long serialVersionUID = 14756423876583L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Natures natura;
	private String nome;
	private float saldo;
	private Sections sezione;

	public Account(String nome, Natures natura, Sections sezione, float saldo) {
		this.nome = nome;
		this.natura = natura;
		this.saldo = saldo;
		this.sezione = sezione;
	}

	public float decrSaldo(Float variazione) {
		return this.saldo -= variazione;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (natura != other.natura)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getName() {
		return nome;
	}

	public Natures getNatura() {
		return natura;
	}

	public float getSaldo() {
		return saldo;
	}

	public Sections getSezione() {
		return sezione;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getNatura().toString();
		case 1:
			return getSezione().toString();
		case 2:
			return getName();
		case 3:
			return Float.toString(getSaldo());
		default:
			return "";
		}
	}

	public float incrSaldo(float variazione) {
		return this.saldo += variazione;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public void setNatura(Natures natura) {
		this.natura = natura;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public void setSezione(Sections sezione) {
		this.sezione = sezione;
	}

	@Override
	public String toString() {
		return nome;
	}

}