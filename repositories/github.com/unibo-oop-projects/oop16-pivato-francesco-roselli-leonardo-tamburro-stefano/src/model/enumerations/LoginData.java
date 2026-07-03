package model.enumerations;

public enum LoginData {
	
	LEO("Leonardo","Leo"),
	STE("Stefano", "Ste"),
	PIVOT("Francesco","Pivot"),
	DEMO("demo", "demo");
	
	private final String id;
	private final String password;
	
	private LoginData(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
}
