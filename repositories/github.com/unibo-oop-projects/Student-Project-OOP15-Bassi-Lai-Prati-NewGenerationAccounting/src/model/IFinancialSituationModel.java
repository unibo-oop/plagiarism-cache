package model;

import dataEnum.Natures;

/**
 * interfaccia delle operazioni per la destione di stato patrimoniale conto
 * economico e analisi per margini e indici
 * 
 * @author niky
 *
 */
public interface IFinancialSituationModel {

	/**
	 * funzione per commentare la situazione finanziaria derivata dal calcolo di
	 * margini e indici
	 * 
	 * @return il commento agli indici di bilancio
	 */
	String AnalisiFinanziaria();

	/**
	 * funzione per popolare la colonna di nomi e sezioni dello stato
	 * patrimoniale dalla parte delle attività
	 * 
	 * @return la stringa da inserire nel prospetto dello stato patrimoniale per
	 *         le attività
	 */
	String Attivita();

	/**
	 * funzione per popolare la colonna di nomi e sezioni del Conto Economico
	 * dalla parte dei Costi
	 * 
	 * @return la stringa da inserire nel prospetto del Conto Economico per i
	 *         Costi
	 */
	String Costi();

	/**
	 * funzione per calcolare il totale delle colonne
	 * 
	 * @return la stringa con il saldo
	 */
	Float getTot(Natures natura);

	/**
	 * funzione per popolare la colonna di nomi e sezioni dello stato
	 * patrimoniale dalla parte delle passività
	 * 
	 * @return la stringa da inserire nel prospetto dello stato patrimoniale per
	 *         le passività
	 */
	String Passivita();

	/**
	 * funzione per popolare la colonna di nomi e sezioni del Conto Economico
	 * dalla parte dei Ricavi
	 * 
	 * @return la stringa da inserire nel prospetto del Conto Economico per i
	 *         Ricavi
	 */
	String Ricavi();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce dello stato patrimoniale dalla parte delle attività
	 * 
	 * @return la stringa con i saldi
	 */
	String Saldi_Attivita();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce del Conto Economico dalla parte dei costi
	 * 
	 * @return la stringa con i saldi
	 */
	String Saldi_Costi();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce dello stato patrimoniale dalla parte delle passività
	 * 
	 * @return la stringa con i saldi
	 */
	String Saldi_Passivita();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce del Conto Economico dalla parte dei Ricavi
	 * 
	 * @return la stringa con i saldi
	 */
	String Saldi_Ricavi();

}
