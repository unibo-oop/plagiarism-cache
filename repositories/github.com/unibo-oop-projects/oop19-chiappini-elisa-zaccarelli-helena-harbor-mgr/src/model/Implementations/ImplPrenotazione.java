package model.Implementations;

import java.io.Serializable;

import model.Interfaces.Prenotazione;


/**
 * Implementazione di una prenotazione
 * @author Helena Zaccarelli
 *
 */

public class ImplPrenotazione implements Prenotazione, Serializable{
	
	private static final long serialVersionUID = 1L;

		private int codice;
		private final int id;						
		private final String tipo;
		private final int colli;
		private final int peso;

		/**
		 * Costruttore di prenotazione
		 * @param id			identificativo viaggio scelto
		 * @param tipo			tipo di merce da caricare (container, rotabili, etc.)
		 * @param colli			numero colli da caricare
		 * @param peso			peso totale della merce da caricare
		 */
		public ImplPrenotazione(final int id, final String tipo, final int colli, final int peso){
			this.id=id;
			this.tipo=tipo;
			this.colli=colli;
			this.peso=peso;			
		}
		
		public int getCodice() {
			return codice;
		}
		
		public void setCodice(int codice) {
			this.codice=codice;
		}

		public int getId() {
			return id;
		}

		public String getTipo() {
			return tipo;
		}

		public int getColli() {
			return colli;
		}

		public int getPeso() {
			return peso;
		}
		
		public String toString() {
			return "ImplPrenotazione [Codice prenotazione=" + codice + ", ID viaggio=" + id + ", tipo di merce=" + tipo + ", colli da caricare=" + colli + ""
							+ ", peso totale della merce da caricare=" + peso + "]";
		}	
}
