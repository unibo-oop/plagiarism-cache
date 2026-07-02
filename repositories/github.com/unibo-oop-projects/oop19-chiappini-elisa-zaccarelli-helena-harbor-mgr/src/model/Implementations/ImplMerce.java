package model.Implementations;

import model.Interfaces.Merce;
import java.io.Serializable;

/**
 * Implementazione di una tipologia di merce
 * @author Helena Zaccarelli
 *
 */

public class ImplMerce implements Merce, Serializable{

    	private static final long serialVersionUID = 1L;
    	private int codice;
		private String nome;
		
		/**
		 * Costruttore di merce
		 * @param nome 		nome della tipologia di merce
		 */
		public ImplMerce(final String nome){
			this.nome=nome;
		}
		
	    public int getCod() {
	        return codice;
	    }
	    
	    public void setCod(int codice) {
	        this.codice = codice;
	    }

		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}

		public String toString() {
			return "ImplMerce [Codice merce=" + codice + ", nome=" + nome + "]";
		}		
}
