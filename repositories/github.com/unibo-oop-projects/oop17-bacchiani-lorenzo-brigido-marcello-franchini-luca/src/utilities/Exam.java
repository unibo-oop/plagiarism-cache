package utilities;

public enum Exam {

	E1("E1", "30 euro", "Acido Urico"),
	E2("E2", "25 euro", "ACTH"),
	E3("E3", "25 euro", "Aldosterone"),
	E4("E4", "35 euro", "AMH"),
	E5("E5", "50 euro", "Anticorpi HCV"),
	E6("E6", "15 euro", "AST"),
	E7("E7", "42 euro", "Bilirubina"),
	E8("E8", "15 euro", "Calcio Totale"),
	E9("E9", "20 euro", "Cloro"),
	E10("E10", "30 euro", "Colesterolo HDL"),
	E11("E11", "30 euro", "Colesterolo LDL"),
	E12("E12", "25 euro", "Colesterolo TOT"),
	E13("E13", "20 euro", "Cortisolo"),
	E14("E14", "30 euro", "Creatichinasi"),
	E15("E15", "30 euro", "Creatinina e clearance creatinina"),
	E16("E16", "15 euro", "Elettroforesi sieroproteica"),
	E17("E17", "20 euro", "Emocromo completo"),
	E18("E18", "15 euro", "Emoglobina glicata"),
	E19("E19", "20 euro", "Esame delle urine");

	private String code;
	private String price;
	private String name;

	private Exam(final String code, final String price, final String name) {
		this.code = code;
		this.price = price;
		this.name = name;
	}

	public String getExamCode() {
		return this.code;
	}

	public String getExamPrice() {
		return this.price;
	}

	public String getExamName() {
		return this.name;
	}

}
