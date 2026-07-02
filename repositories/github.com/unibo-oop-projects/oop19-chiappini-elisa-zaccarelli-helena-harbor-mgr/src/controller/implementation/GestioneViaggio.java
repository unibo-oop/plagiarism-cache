package controller.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exception.ExceptionEqualRecord;
import exception.ExceptionNegativeQty;
import exception.ExceptionNotPossibleOp;
import model.Implementations.ImplViaggio;


/**
 * Gestore viaggio per creare ed eliminare viaggi, visualizzare i viaggi non ancora partiti e aggiornare lo stato dei viaggi (partenza true) al momento della partenza della nave
 * @author Helena Zaccarelli
 *
 */

public class GestioneViaggio extends GestioneFile<ImplViaggio> {
	private static GestioneViaggio viaggi = null;
    public final static String PERCORSO = "viaggi.file";

    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza viaggi
     */
    public static GestioneViaggio creaIst() {
        if (GestioneViaggio.viaggi == null) {
        	GestioneViaggio.viaggi = new GestioneViaggio();
        	GestioneViaggio.viaggi.carica();
        }
        return GestioneViaggio.viaggi;
    }

    /**
     * Metodo che individua il percorso del file .txt di archivio
     * @return Percorso file
     */
    @Override
    public String individuaPercorso() {
        return PERCORSO;
    }
    
    /**
     * Metodo che inserisce un nuovo viaggio
     * @param viaggio
     */
    public void aggiungiViaggio(ImplViaggio viaggio) throws ExceptionEqualRecord{
        for (ImplViaggio v : this.getList()) {
        	//Verifica che non ci siano viaggi relativi alla stessa nave, per la stessa destinazione, lo stesso giorno per evitare che un viaggio venga inserito più volte
            if (v.getNome().equals(viaggio.getNome()) && (v.getDestinazione().equals(viaggio.getDestinazione())) && (v.getData().equals(viaggio.getData()))) {
                throw new ExceptionEqualRecord();
            }
        }
        viaggio.setId(this.assegnaC());
        this.file.put(viaggio.getId(), viaggio);
        this.salva();
    }
    
	/**
     * Metodo che crea una lista per visualizzare tutti i viaggi previsti transitare dal porto dopo una certa data
     * 
     * @param data
     * @return lista dei viaggi disponibili con data di attracco uguale o successiva a quella fornita in input
     */
    public ArrayList<ImplViaggio> cercaPerData(Date data) {
        ArrayList<ImplViaggio> lista = new ArrayList<>(50);
        for (ImplViaggio v : getList() ) {
            if (!(v.getData().before(data))) {
                lista.add(v);
            }
        }
        return lista;
    }
	
	/**
     * Metodo che crea una lista per visualizzare i viaggi ancora disponibili, selezionati per destinazione e tipologia di merce caricabile
     * 
     * @param destinazione
     * @param tipoMerce
     * @return lista 
     */
    public ArrayList<ImplViaggio> cercaDisponibili(String destinazione, String tipoMerce) {
        ArrayList<ImplViaggio> lista = new ArrayList<>(50);
        for (ImplViaggio v : getList() ) {
            if ((v.getPartenza() == false) && (v.getDestinazione().equals(destinazione)) && (v.getTipo().equals(tipoMerce))){
                lista.add(v);
            }
        }
        return lista;
    }
    
	/**
     * Metodo che crea una lista per visualizzare i viaggi già partiti
     * @return lista dei viaggi con campo partenza = true
     */
    public ArrayList<ImplViaggio> cercaPartiti() {
        ArrayList<ImplViaggio> lista = new ArrayList<>(50);
        for (ImplViaggio v : getList() ) {
            if (v.getPartenza() == true) {
                lista.add(v);
            }
        }
        return lista;
    }

    /**
     * Metodo che rimuove un viaggio 
     * Presente exeption che impedisce la cancellazione dei viaggi già transitati che devono rimanere a sistema per finalità statistiche
     * 
     * @param id
     * @exception ExceptionNotPossibleOp
     */
    public void rimuoviViaggio(int id) throws ExceptionNotPossibleOp {
    	ImplViaggio v = this.file.get(id);
    	if (v.getPartenza() == true) {
    		throw new ExceptionNotPossibleOp();
    	}
    	this.file.remove(id);
    	GestionePrenotazione.creaIst().aggiornamentoDataBase(id);
        salva();
    }
    
    /**
     * Metodo imposta il viaggio come "partito"
     * @param id
     */
    public void partenzaViaggio(int id) {
    	ImplViaggio v = this.file.get(id);
        v.setPartenza(true);
        this.file.remove(id);
        this.file.put(id, v);
        //Chiamata ad aggiornamentoDataBase; metodo che si occupa della cancellazione di tutte le prenotazioni relative ad un dato viaggio
        GestionePrenotazione.creaIst().aggiornamentoDataBase(id);
		salva();
    }
    
    /**
     * Metodo che aggiunge merce alla lista di carico di un viaggio, aggiornando i campi relativi allo spazio libero e al peso ancora caricabile
     * 
     *  @param colli
     *  @param quantita
     *  @param id
     *  @throws ExceptionNegativeQty
     */
    public void aggiornaSpaziPeso(int colli, int peso, int id) throws ExceptionNegativeQty {
        ImplViaggio v = this.file.get(id);
        if (colli > v.getSpaziCarico() || peso > v.getCarico()) {
            throw new ExceptionNegativeQty();
        }
        GestionePiazzale.creaIst().depositaMerce(colli);
        int rimanenzaColli = v.getSpaziCarico() - colli;
        int rimanenzaTonnellate = v.getCarico() - peso;
        v.setSpaziCarico(rimanenzaColli);
        v.setCarico(rimanenzaTonnellate);
        this.file.remove(id);
        this.file.put(id, v);
		salva();
    }
    
    /**
     * Metodo che libera spazio e peso disponibile, in seguito alla cancellazione di una prenotazione
     */
    public void liberaSpaziPeso(int colli, int peso, int id) {
        ImplViaggio v = this.file.get(id);
        int rimanenzaColli = v.getSpaziCarico() + colli;
        int rimanenzaTonnellate = v.getCarico() + peso;
        v.setSpaziCarico(rimanenzaColli);
        v.setCarico(rimanenzaTonnellate);
        this.file.remove(id);
        this.file.put(id, v);
		salva();
    }
    
    /**
     * Metodo che permette di visualizzare il volume di merce in entrata ed uscita relativo ad ogni trimestre di un dato anno
     * Valore espresso in tonnellate
     * 
     * @param anno
     * @param tipoOperazione (1 = export, 2 = import)
     * 
     * @return tonnellate importate o esportate divise per trimestre
     */
	public int[] statisticheMensili(int anno, int tipoOperazione) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(anno, 0, 1);
    	Date inizioPrimoT = calendar.getTime();
    	calendar.set(anno, 2, 31);
    	Date finePrimoT = calendar.getTime();
    	calendar.set(anno, 3, 1);
    	Date inizioSecondoT = calendar.getTime();
    	calendar.set(anno, 5, 30);
    	Date fineSecondoT = calendar.getTime();
    	calendar.set(anno, 6, 1);
    	Date inizioTerzoT = calendar.getTime();
    	calendar.set(anno,  8, 30);
    	Date fineTerzoT = calendar.getTime();
    	calendar.set(anno,  9, 1);
    	Date inizioQuartoT = calendar.getTime();
    	calendar.set(anno,  11, 31);
    	Date fineQuartoT = calendar.getTime();
    	int[] results = {0, 0, 0, 0};
    	for (ImplViaggio v : this.getList()) {
            if (v.getPartenza() == true) {
            	if (!(v.getData().before(inizioPrimoT) || v.getData().after(finePrimoT))) {
            		results[0] = calcoloTonnellate(results[0], tipoOperazione, v);
            	} else if (!(v.getData().before(inizioSecondoT) || v.getData().after(fineSecondoT))) {
            		results[1] = calcoloTonnellate(results[1], tipoOperazione, v);
            	} else if (!(v.getData().before(inizioTerzoT) || v.getData().after(fineTerzoT))) {
            		results[2] = calcoloTonnellate(results[2], tipoOperazione, v);
            	} else if (!(v.getData().before(inizioQuartoT) || v.getData().after(fineQuartoT))) {
            		results[3] = calcoloTonnellate(results[3], tipoOperazione, v);
            	}
            }
        }
    	return results;	
    } 
	
	/**
	 * Metodo privato che aggiorna il "contatore" delle tonnellate importate o esportate per il metodo statisticheMensili 
	 * 
	 * @param t = "contatore" da aggiornare
	 * @param tipoOperazione (1 = export, 2 = import)
	 * @param v = viaggio in oggetto di operazione
	 * 
	 * @return "contatore" tonnellate aggiornato
	 */
	private int calcoloTonnellate(int t, int tipoOperazione, ImplViaggio v) {
		if (tipoOperazione == 2) {
			t = t + v.getSbarco();
		} else {
    		t = t + (v.getPortata() - v.getCarico());
		}
		return t;
	}
}
