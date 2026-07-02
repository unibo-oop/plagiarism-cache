package model.Implementations;

import java.io.Serializable;

import model.Interfaces.Piazzale;

/**
 * Implementazione del piazzale di deposito merci del porto
 * @author Helena Zaccarelli
 *
 */

public class ImplPiazzale implements Piazzale, Serializable{

		private static final long serialVersionUID = 1L;
		private final int spazioTot;
		private int spazioLibero;
		
		/**
		 * Costruttore piazzale
		 * @param spazioTot		spazio totale disponibile a piazzale vuoto
		 */
		public ImplPiazzale(final int spazioTot){
			this.spazioTot=spazioTot;
			this.spazioLibero=spazioTot;
		}

		public int getSpazioTot() {
			return spazioTot;
		}

		public int getSpazioLibero() {
			return spazioLibero;
		}

		public void setSpazioLibero(int spazioLibero) {
			this.spazioLibero = spazioLibero;
		}
		
		public boolean piazzalePieno() {
			if (this.spazioLibero <= spazioTot && this.spazioLibero > 0){
				return false;
			}
			return true;
		}
				
		public String toString() {
			return "ImplPiazzale [Spazio totale in colli=" + spazioTot + ", spazio libero=" + spazioLibero + "]";
		}	
}
