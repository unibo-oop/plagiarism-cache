package dataEnum;

import java.util.LinkedList;

public enum Sections implements IDataEnum { // insieme di tutte le sezioni di SP
											// e CE
	// NON SELEZIONATA
	NESSUNO,
	// ATTIVITA'
	CREDITI_VS_SOCI, IMMOBILIZZAZIONI_IMMATERIALI, IMMOBILIZZAZIONI_MATERIALI, IMMOBILIZZAZIONI_FINANZIARIE, RIMANENZE, CREDITI, ATTIVITA_FINANZIARIE, DISPONIBILITA_LIQUIDE, RATEI_E_RISCONTI_ATTIVI,
	// PASSIVITA'
	PATRIMONIO_NETTO, FONDI_RISCHI_E_ONERI, TFR, DEBITI, RATEI_E_RISCONTI_PASSIVI,
	// COSTI
	COSTI_DELLA_PRODUZIONE, ONERI_FINANZIARI, SVALUTAZIONI, ONERI_STRAORDINARI, IMPOSTE_D_ESERCIZIO,
	// RICAVI
	VALORE_DELLA_PRODUZIONE, PROVENTI_FINANZIARI, RIVALUTAZIONI, PROVENTI_STRAORDINARI;

	public static LinkedList<Sections> getAttivita() {
		LinkedList<Sections> attivita = new LinkedList<>();
		// valori da 0 a 10
		attivita.add(CREDITI_VS_SOCI);
		attivita.add(IMMOBILIZZAZIONI_IMMATERIALI);
		attivita.add(IMMOBILIZZAZIONI_MATERIALI);
		attivita.add(IMMOBILIZZAZIONI_FINANZIARIE);
		attivita.add(RIMANENZE);
		attivita.add(CREDITI);
		attivita.add(ATTIVITA_FINANZIARIE);
		attivita.add(DISPONIBILITA_LIQUIDE);
		attivita.add(RATEI_E_RISCONTI_ATTIVI);
		return attivita;
	}

	public static LinkedList<Sections> getCosti() {
		LinkedList<Sections> costi = new LinkedList<>();
		// valori da 15 a 21
		costi.add(COSTI_DELLA_PRODUZIONE);
		costi.add(ONERI_FINANZIARI);
		costi.add(SVALUTAZIONI);
		costi.add(ONERI_STRAORDINARI);
		costi.add(IMPOSTE_D_ESERCIZIO);
		return costi;
	}

	public static LinkedList<Sections> getPassivita() {
		LinkedList<Sections> passivita = new LinkedList<>();
		// valori da 10 a 15
		passivita.add(PATRIMONIO_NETTO);
		passivita.add(FONDI_RISCHI_E_ONERI);
		passivita.add(TFR);
		passivita.add(DEBITI);
		passivita.add(RATEI_E_RISCONTI_PASSIVI);
		return passivita;
	}

	public static LinkedList<Sections> getRicavi() {
		LinkedList<Sections> ricavi = new LinkedList<>();
		// valori da 21 a 24
		ricavi.add(VALORE_DELLA_PRODUZIONE);
		ricavi.add(PROVENTI_FINANZIARI);
		ricavi.add(RIVALUTAZIONI);
		ricavi.add(PROVENTI_STRAORDINARI);
		return ricavi;
	}

	@Override
	public Enum<?>[] getEnumValues() {
		return values();
	}

}
