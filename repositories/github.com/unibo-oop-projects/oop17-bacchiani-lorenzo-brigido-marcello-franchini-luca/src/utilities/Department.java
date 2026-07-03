package utilities;

import java.io.Serializable;

public enum Department implements Serializable {

	Cardiologia("R01", "0541327654", "Cardiologia"),
	TerapiaIntensiva("R02", "0541768453", "Terapia Intensiva"),
	Neurologia("R03", "0541762847", "Neurologia"),
	Oncologia("R04", "0541264537", "Oncologia"),
	Psichiatria("R05", "0541849272", "Psichiatria"),
	Oculistica("R06", "0541372947", "Oculistica"),
	Ginecologia("R07", "0541947436", "Ginecologia"),
	Riabilitazione("R08", "0541847629", "Riabilitazione"),
	Ortopedia("R09", "0541849374", "Ortopedia"),
	Gastroenterologia("R10", "0541846293", "Gastroenterologia"),
	Geriatria("R11", "0541767876", "Geriatria"),
	Pediatria("R12", "0541435431", "Pediatria");

	private String code;
	private String phone;
	private String name;
	private Department(final String code, final String phone, final String name) {
		this.code = code;
		this.phone = phone;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getPhoneNumber() {
		return this.phone;
	}

	public String getCode() {
		return this.code;
	}

	public String toString() {
		return "[Codice Reparto:" + getCode() + "]" + "[Nome:" + getName() + "]";
	}

}
