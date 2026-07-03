package utilities;

public enum Medicine {

	F1("10 euro", "F1", "Paracetamolo", "10/12/2020"),
	F2("20 euro", "F2", "Benzodiazepina", "10/12/2020");

	private String prezzo;
	private String cod;
	private String nome;
	private String dataScad;

	private Medicine(final String prezzo, final String cod, final String nome, final String dataScad) {
		this.prezzo = prezzo;
		this.cod = cod;
		this.nome = nome;
		this.dataScad = dataScad;
	}

	public String getPrezzo() {
		return this.prezzo;
	}

	public String getCod() {
		return this.cod;
	}

	public String getNome() {
		return this.nome;
	}

	public String dataScad() {
		return this.dataScad;
	}
}
