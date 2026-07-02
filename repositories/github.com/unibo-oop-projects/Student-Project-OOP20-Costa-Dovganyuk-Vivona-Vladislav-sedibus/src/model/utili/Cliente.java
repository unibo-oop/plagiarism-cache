package model.utili;

public class Cliente  {

	private static final String REGEX_NOME_COGN = ".*\\d.*";
	private static final String REGEX_TEL = "[0-9]+";
	private String nome;
	private String cognome;
	private String email;
	private String nTelefono;
	
	public Cliente(String nomeCliente, String cognomeCliente, String emailCliente, String telefonoCliente) {
		this.nome = nomeCliente;
		this.cognome = cognomeCliente;
		this.email = emailCliente;
		this.nTelefono = telefonoCliente;
	}
	
	/**
	 * @return customer's name
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @return customer's surname
	 */
	public String getCognome() {
		return this.cognome;
	}
	
	/**
	 * @return customer's email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @return customer's phone number
	 */
	public String getTelefono() {
		return this.nTelefono;
	}
	
	/**
	 * @return true if true if name, surname, email, telephone number respect certain constraints, false otherwise
	 */
	public boolean rispettaControlli() {
		return this.controlloNome(this.nome) && this.controlloCognome(this.cognome) && 
			   this.controlloEmail(this.email) && this.controlloTelefono(this.nTelefono);
	}
	
	private boolean controlloNome(String nome) {
		return !this.haNumeri(nome) && !this.campoVuoto(nome);
	}
	
	private boolean controlloCognome(String cognome) {
		return !this.haNumeri(cognome) && !this.campoVuoto(cognome);
	}
	
	private boolean controlloEmail(String email) {
		return email.contains("@") && email.contains(".") && !this.campoVuoto(email);
	}
	
	private boolean controlloTelefono(String telefono) {
		return telefono.matches(REGEX_TEL) && !this.campoVuoto(telefono);
	}
	
	private boolean haNumeri(String s) {
		return s.matches(REGEX_NOME_COGN);
	}
	
	private boolean campoVuoto(String s) {
		return s.isEmpty() || s.isBlank();
	}

}
