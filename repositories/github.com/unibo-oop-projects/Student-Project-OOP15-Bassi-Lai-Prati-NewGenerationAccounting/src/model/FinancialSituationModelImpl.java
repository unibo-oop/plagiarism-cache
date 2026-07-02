package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;

/**
 * classe implementativa per la gestione di stato patrimoniale conto economico e
 * analisi per margini e indici
 * 
 * @author niky
 *
 */
public class FinancialSituationModelImpl implements IFinancialSituationModel {

	private static String ROI;
	private static String ROS;
	private static String leverage;
	private static String ROT;
	private static String ROE;
	private static String margineStrutturaPrimario;
	private static String margineStrutturaSecondario;
	private static String margineTesoreria;
	private DBDataModel db;
	private LinkedList<Account> contiRegistrati;
	private Map<String, Float> indiciMargini = new HashMap<>();
	private float ricVend = 0;
	private float capSociale = 0;
	private float ammortamenti = 0;

	public FinancialSituationModelImpl(DBDataModel db) {
		this.db = db;
		contiRegistrati = db.getAccounts();
	}

	@Override
	public String AnalisiFinanziaria() {
		calcolaIndici_Margini();
		String commento = "";
		// controllo ROI
		if (indiciMargini.get(ROI) >= 4) {
			commento += "<b>Tasso di redditività del capitale investito</b> = " + indiciMargini.get(ROI) + "%."
					+ " Indica buona redditività del capitale invesitito <br/>";
		} else {
			commento += "<b>Tasso di redditività del capitale investito</b> = " + indiciMargini.get(ROI) + "%."
					+ " Indica pessima redditività del capitale investito <br/>";
		}
		// controllo ROS
		if (indiciMargini.get(ROS) > 0) {
			commento += "<b>Tasso di redditività delle vendite</b> = " + indiciMargini.get(ROS) + "%."
					+ " Indica che i ricavi sono in grado di coprire tutte le spese ordinarie e anche quelle extra<br/>";
		} else if (indiciMargini.get(ROS) == 0) {
			commento += "<b>Tasso di redditività delle vendite</b> = " + indiciMargini.get(ROS) + "%."
					+ " Indica che i ricavi coprono solo le spese ordinarie<br/>";
		} else {
			commento += "<b>Tasso di redditività delle vendite</b> = " + indiciMargini.get(ROS) + "%."
					+ " Indica che i ricavi non riescono a coprire nemmeno le spese ordinarie<br/>";
		}
		// controllo ROT
		if (indiciMargini.get(ROT) > 12) {
			commento += "<b>Indice di rotazione degli impieghi</b> = " + indiciMargini.get(ROT) + "%."
					+ " Indica alta capacità di ritorno del capitale investito tramite le vendite<br/>";
		} else {
			commento += "<b>Indice di rotazione degli impieghi</b> = " + indiciMargini.get(ROT) + "%."
					+ " Indica bassa capacità di ritorno del capitale investito tramite le vendite<br/>";
		}
		// controllo ROE
		if (indiciMargini.get(ROE) > 6) {
			commento += "<b>Tasso di redditività del capitale proprio</b> = " + indiciMargini.get(ROE) + "%."
					+ " Indica buona redditività del capitale proprio<br/>";
		} else {
			commento += "<b>Tasso di redditività del capitale proprio</b> = " + indiciMargini.get(ROE) + "%."
					+ " Indica cattiva redditività del capitale proprio<br/>";
		}
		// controllo Leverage
		if (indiciMargini.get(leverage) == 1) {
			commento += "<b>Indice di indebitamento </b> = " + indiciMargini.get(leverage) + "%"
					+ " Indica che gli investimenti sono finanziati dal Capitale Proprio<br/>";
		} else if (indiciMargini.get(leverage) > 1 && indiciMargini.get(leverage) < 2) {
			commento += "<b>Indice di indebitamento </b> = " + indiciMargini.get(leverage) + "%"
					+ " Indica che esiste un buon rapporto tra capitale Proprio e capitale di terzi <br/>";
		} else if (indiciMargini.get(leverage) > 2) {
			commento += "<b>Indice di indebitamento </b> = " + indiciMargini.get(leverage) + "%"
					+ " Indica un indebitamento <br/>";
		}
		// controllo margine di struttura primario
		if (indiciMargini.get(margineStrutturaPrimario) > 0) {
			commento += "<b>Margine di struttura primario </b> = " + indiciMargini.get(margineStrutturaPrimario) + "%"
					+ " Indica che il capitale permanente finanzia anche una parte delle attività circolanti. <br/>";
		} else if (indiciMargini.get(margineStrutturaPrimario) == 0) {
			commento += "<b>Margine di struttura primario </b> = " + indiciMargini.get(margineStrutturaPrimario) + "%"
					+ " Indica che le attività immobilizzate sono coperte dal capitale proprio, ma senza extra. <br/>";
		} else {
			commento += "<b>Margine di struttura primario </b> = " + indiciMargini.get(margineStrutturaPrimario) + "%"
					+ " Indica che l'impresa si trova in una situazione di dipendenza finanziaria,"
					+ "la parte mancante dovrà essere coperta anche con risorse di terzi<br/>";
		}
		// controllo margine di struttura secondario
		if (indiciMargini.get(margineStrutturaSecondario) > 0) {
			commento += "<b>Margine di struttura secondario </b> = " + indiciMargini.get(margineStrutturaSecondario)
					+ "%" + " Indica che la struttura fonti-impieghi risulta equilibrata,"
					+ "i capitali permanenti risultano superiori alle attività fisse <br/>";
		} else {
			commento += "<b>Margine di struttura secondario </b> = " + indiciMargini.get(margineStrutturaSecondario)
					+ "%"
					+ " Indica che il Capitale permanente non finanzia interamente le attività immobilizzate <br/>";
		}
		// controllo margine di tesoreria
		if (indiciMargini.get(margineTesoreria) > 0) {
			commento += "<b>Margine di tesoreria </b> = " + indiciMargini.get(margineTesoreria) + "%"
					+ " Indica equilibrio finanziario per quello che riguarda le liquidità a breve termine <br/>";
		} else {
			commento += "<b>Margine di tesoreria </b> = " + indiciMargini.get(margineTesoreria) + "%"
					+ " Indica squilibrio finanziario per quello che riguarda le liquidità a breve termine <br/>";
		}
		return commento;
	}

	@Override
	public String Attivita() {
		return getContiNatura(Sections.getAttivita(), Natures.ATTIVITA);
	}

	private Map<String, Float> calcolaIndici_Margini() {
		indiciMargini.put(ROS, getROS());
		indiciMargini.put(ROT, getROT());
		indiciMargini.put(ROI, getROI());
		indiciMargini.put(ROE, getROE());
		indiciMargini.put(leverage, getLeverage());
		indiciMargini.put(margineStrutturaPrimario, getMargineStrutturaPrimario());
		indiciMargini.put(margineStrutturaSecondario, getMargineStrutturaSecondario());
		indiciMargini.put(margineTesoreria, getMargineTesoreria());
		return indiciMargini;

	}

	@Override
	public String Costi() {
		return getContiNatura(Sections.getCosti(), Natures.COSTO);
	}

	private String getContiNatura(LinkedList<Sections> sezioniDellaNatura, Natures natura) {
		String str = "";
		for (Sections s : sezioniDellaNatura) {
			str += "</br><b>" + s + "</b><br/>";
			for (int j = 0; j < contiRegistrati.size(); j++) {
				if (contiRegistrati.get(j).getNatura() == natura && contiRegistrati.get(j).getSezione() == s) {
					str += contiRegistrati.get(j).getName() + "<br/>";
				}
			}
			str += "<b>totale</b><br/>";
		}
		return str;
	}

	private Float getLeverage() {
		float totAttivo = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA) {
				totAttivo += a.getSaldo();
			}
		}
		return totAttivo / capSociale;
	}

	private Float getMargineStrutturaPrimario() {
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.COSTO && a.getSezione() == Sections.COSTI_DELLA_PRODUZIONE
					&& a.getName().contains("ammortamento")) {
				ammortamenti += a.getSaldo();
			}
		}
		return capSociale - ammortamenti;
	}

	private Float getMargineStrutturaSecondario() {
		float debM_Lterm = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.PASSIVITA && a.getSezione() == Sections.FONDI_RISCHI_E_ONERI
					&& a.getName().contains("fondo")) {
				debM_Lterm += a.getSaldo();
			}
		}
		return (capSociale + debM_Lterm) - ammortamenti;
	}

	private Float getMargineTesoreria() {
		float dispLiq = 0;
		float debBterm = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA && a.getSezione() == Sections.DISPONIBILITA_LIQUIDE)
				dispLiq += a.getSaldo();
			else if (a.getNatura() == Natures.PASSIVITA && a.getSezione() == Sections.DEBITI) {
				if (a.getName().contains("vs fornitori") || a.getName().contains("vs banche")
						|| a.getName().contains("vs controllanti")) {
					debBterm += a.getSaldo();
				} else if (a.getSezione() == Sections.RATEI_E_RISCONTI_PASSIVI)
					debBterm += a.getSaldo();
				else if (a.getSezione() == Sections.TFR)
					debBterm += a.getSaldo();
			}
		}
		return dispLiq - debBterm;
	}

	private Float getROE() {
		float valDellaProd = 0;
		float costDellaProd = 0;
		float provFin = 0;
		float oneriFin = 0;
		float rival = 0;
		float sval = 0;
		float provStr = 0;
		float oneriStr = 0;

		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.RICAVO) {
				if (a.getSezione() == Sections.VALORE_DELLA_PRODUZIONE)
					valDellaProd += a.getSaldo();
				else if (a.getSezione() == Sections.PROVENTI_FINANZIARI)
					provFin += a.getSaldo();
				else if (a.getSezione() == Sections.RIVALUTAZIONI)
					rival += a.getSaldo();
				else if (a.getSezione() == Sections.PROVENTI_STRAORDINARI)
					provStr += a.getSaldo();
			} else if (a.getNatura() == Natures.COSTO) {
				if (a.getSezione() == Sections.COSTI_DELLA_PRODUZIONE)
					costDellaProd += a.getSaldo();
				else if (a.getSezione() == Sections.ONERI_FINANZIARI)
					oneriFin += a.getSaldo();
				else if (a.getSezione() == Sections.SVALUTAZIONI)
					sval += a.getSaldo();
				else if (a.getSezione() == Sections.ONERI_STRAORDINARI)
					oneriStr += a.getSaldo();
			}
		}
		return (valDellaProd - costDellaProd + provFin - oneriFin + rival - sval + provStr - oneriStr) / capSociale;
	}

	private Float getROI() {
		float roi = 0;
		roi = (getROS() * getROT());
		return roi;
	}

	private Float getROS() {
		float costiAcq = 0;
		for (Account a : contiRegistrati) {
			if (a.getName() == "ricavi delle vendite" && a.getNatura() == Natures.RICAVO
					&& a.getSezione() == Sections.VALORE_DELLA_PRODUZIONE) {
				ricVend += a.getSaldo();
			} else if (a.getNatura() == Natures.COSTO) {
				costiAcq += a.getSaldo();
			}
		}
		return (ricVend - costiAcq) / ricVend;
	}

	private Float getROT() {
		float scorte = 0;
		float cassa = 0;
		float crediti = 0;

		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA && a.getSezione() == Sections.RIMANENZE) {
				scorte += a.getSaldo();
			} else if (a.getName().contains("cassa") && a.getNatura() == Natures.ATTIVITA
					&& a.getSezione() == Sections.DISPONIBILITA_LIQUIDE) {
				cassa += a.getSaldo();
			} else if (a.getSezione() == Sections.CREDITI) {
				crediti += a.getSaldo();
			} else if (a.getSezione() == Sections.PATRIMONIO_NETTO && a.getName().contains("capitale")) {
				capSociale += a.getSaldo();
			}
		}

		return ricVend / (scorte + cassa + crediti + capSociale);
	}

	private String getSaldi(LinkedList<Sections> sezioniDellaNatura, Natures natura) {
		float totSezione = 0;
		String saldo = "";
		for (Sections elem : sezioniDellaNatura) {
			for (Account a : contiRegistrati) {
				if (a.getNatura().equals(natura) && a.getSezione().equals(elem)) {
					totSezione += a.getSaldo();
					saldo += "<br/>" + a.getSaldo();
				}
			}
			saldo += "<br/><b>" + totSezione + "</b><br/>";
			totSezione = 0;
		}
		return saldo;
	}

	@Override
	public Float getTot(Natures natura) {
		float tot = 0;
		for (Account conto : contiRegistrati) {
			if (conto.getNatura().equals(natura)) {
				tot += conto.getSaldo();
			}
		}
		return tot;
	}

	@Override
	public String Passivita() {
		return getContiNatura(Sections.getPassivita(), Natures.PASSIVITA);
	}

	@Override
	public String Ricavi() {
		return getContiNatura(Sections.getRicavi(), Natures.RICAVO);
	}

	@Override
	public String Saldi_Attivita() {
		return getSaldi(Sections.getAttivita(), Natures.ATTIVITA);
	}

	@Override
	public String Saldi_Costi() {
		return getSaldi(Sections.getCosti(), Natures.COSTO);
	}

	@Override
	public String Saldi_Passivita() {
		return getSaldi(Sections.getPassivita(), Natures.PASSIVITA);
	}

	@Override
	public String Saldi_Ricavi() {
		return getSaldi(Sections.getRicavi(), Natures.RICAVO);
	}

	public DBDataModel saveDBAndClose() {
		return db;
	}

}
