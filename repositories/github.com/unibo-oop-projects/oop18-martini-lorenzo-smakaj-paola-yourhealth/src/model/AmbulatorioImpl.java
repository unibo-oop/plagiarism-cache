package model;

import java.io.Serializable;

import util.Enums.TipoAmbulatorio;

public class AmbulatorioImpl implements Ambulatorio, Serializable {

	private static final long serialVersionUID = -8959280214682117086L;
	private final String codice;
	private final TipoAmbulatorio tipo;

	public AmbulatorioImpl(final String codice, final TipoAmbulatorio tipo) {
		this.codice = codice;
		this.tipo = tipo;
	}

	public static class Builder {
		private String codice1;
		private TipoAmbulatorio tipo1;

		public Builder codice(String c) {
			this.codice1 = c;
			return this;
		}

		public Builder tipo(TipoAmbulatorio t) {
			this.tipo1 = t;
			return this;
		}

		public Ambulatorio build() throws IllegalArgumentException {
			if (this.codice1.isEmpty() || this.tipo1.toString().isEmpty()) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new AmbulatorioImpl(this.codice1, this.tipo1);
		}
	}

	@Override
	public String getCodice() {
		return this.codice;
	}

	@Override
	public String getTipo() {
		return this.tipo.getTipoAmbulatorio();
	}
}
