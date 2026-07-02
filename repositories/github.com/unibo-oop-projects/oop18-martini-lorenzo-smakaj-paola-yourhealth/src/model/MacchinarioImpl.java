package model;

import java.io.Serializable;

import util.Enums.TipoMacchinario;

public class MacchinarioImpl implements Macchinario, Serializable {

	private static final long serialVersionUID = -8318780297411065021L;
	private final String codice;
	private final TipoMacchinario tipo;

	public MacchinarioImpl(final String codice, final TipoMacchinario tipo) {
		this.codice = codice;
		this.tipo = tipo;
	}

	public static class Builder {
		private String codice1;
		private TipoMacchinario tipo1;

		public Builder codice(String c) {
			this.codice1 = c;
			return this;
		}

		public Builder tipo(TipoMacchinario t) {
			this.tipo1 = t;
			return this;
		}

		public Macchinario build() throws IllegalArgumentException {
			if (this.codice1.isEmpty() || this.tipo1.toString().isEmpty()) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new MacchinarioImpl(this.codice1, this.tipo1);
		}
	}

	@Override
	public String getCodice() {
		return this.codice;
	}

	@Override
	public String getTipo() {
		return this.tipo.getTipoMacchinario();
	}
}
