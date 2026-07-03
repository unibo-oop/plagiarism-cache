package utilities;

public enum Role {

	O1("Personale Ausiliario", "O1"),
	O2("Portantini", "O2"),
	O3("Auto-trasportatori", "O3"),
	O4("Inservienti", "O4"),
	O5("Ascensoristi", "O5"),
	O6("Infermieri", "O6"),
	O7("Caposala", "O7"),
	O8("Medico", "O8"),
	O9("Primario", "O9");

	private String descr;
	private String cod;
	private Role(final String descr, final String cod) {
		this.cod = cod;
		this.descr = descr;
	}

	public String getDescr() {
		return this.descr;
	}

	public String getCod() {
		return this.cod;
	}

}
