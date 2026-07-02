package model.Implementations;

import model.Interfaces.Viaggio;

import java.util.Date;


/**
 * Implementazione di un viaggio
 * @author Helena Zaccarelli
 * 
 */

public class ImplViaggio extends ImplNave implements Viaggio{

		private static final long serialVersionUID = 1L;

		private int id;
		private final String provenienza;
		private final String destinazione;
		private final int sbarco;
		private final int transito;
		private final int spaziTransito;
		private final Date data;
		private int carico;
		private int spaziCarico;
		private final int durata;
		private final boolean opzione;
		private boolean partenza;
		
		/**
		 * Costruttore viaggio
		 * @param nome 			nome nave
		 * @param portata 		portata in tonnellate della nave
		 * @param spazi			spazi presenti sulla nave per stivare le merci
		 * @param bandiera 		bandiera nave
		 * @param tipo			tipo di merce imbarcabile sulla nave
		 * @param provenienza	porto di provenienza della nave
		 * @param destinazione	porto di destinazione della nave
		 * @param sbarco		peso in tonnellate della merce da sbarcare
		 * @param transito		peso in tonnellate della merce che rimane sulla nave 
		 * @param spaziTransito	spazi occupati dalla merce in transito
		 * @param data			data in cui la nave tocca il porto
		 * @param carico		disponibilita' in tonnellate per la merce da caricare
		 * @param spaziCarico	spazi disponibili per la merce in carico
		 * @param durata		durata del viaggio	
		 * @param opzione		disponibilita' o meno della nave a caricare
		 * @param partenza		campo che indica se la nave è prevista arrivare in porto o è già ripartita
		 */
		public ImplViaggio(String nome, final int portata, final int spazi, String bandiera, String tipo, final String provenienza,
				final String destinazione, final int sbarco, final int transito, final int spaziTransito, final Date data, final int durata, final boolean opzione){
			super(nome, portata, spazi, bandiera, tipo);
			this.provenienza=provenienza;
			this.destinazione=destinazione;
			this.sbarco=sbarco;
			this.transito=transito;
			this.spaziTransito=spaziTransito;
			this.data=data;
			this.carico=(portata-transito);
			this.spaziCarico=(spazi-spaziTransito);
			this.durata=durata;
			this.opzione=opzione;
			this.partenza=false;
		}

		public int getId() {
			return id;
		}
		
	    public void setId(int id) {
	        this.id = id;
	    }

		public String getProvenienza() {
			return provenienza;
		}

		public String getDestinazione() {
			return destinazione;
		}

		public int getSbarco() {
			return sbarco;
		}

		public int getTransito(){
			return transito;
		}
		
		public int getSpaziTransito(){
			return spaziTransito;
		}
		
		public Date getData(){
			return data;
		}
		
		public int getCarico(){
			return carico;
		}
		
		public void setCarico(int carico){
			this.carico = carico;
		}
		
		public int getSpaziCarico(){
			return spaziCarico;
		}
		
		public void setSpaziCarico(int spaziCarico){
			this.spaziCarico = spaziCarico;
		}
		
		public int getDurata(){
			return durata;
		}
		
		public boolean getOpzione(){
			return opzione;
		}
		
		public boolean getPartenza(){
			return partenza;
		}
		
		public void setPartenza(boolean partenza){
			this.partenza = partenza;
		}
		
		public void aggColli (int colliCaricati){
			this.spaziCarico = (spaziCarico-colliCaricati);
		}
		
		public void aggPesi (int pesoCaricato){
			this.carico = (carico-pesoCaricato);
		}
		
		public String toString() {
			return "ImplViaggio [Codice viaggio=" + id + ", porto di provenienza=" + provenienza + ", porto di destinazione=" + destinazione + ""
							+ ", peso merce in sbarco=" + sbarco + ", peso merce in transito=" + transito + ", spazi occupati dalla merce in transito=" + spaziTransito +", data di arrivo=" + data + ", "
									+ ", peso disponibile per il carico=" + carico + ", spazi disponibili per il carico=" + spaziCarico +", durata del viaggio=" + durata + "]";
		}	
}
