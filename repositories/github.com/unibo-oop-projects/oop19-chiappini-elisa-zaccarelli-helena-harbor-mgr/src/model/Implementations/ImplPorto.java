package model.Implementations;

import model.Interfaces.Porto;
import java.io.Serializable;

/**
 * Implementazione di un porto
 * @author Helena Zaccarelli
 *
 */

public class ImplPorto implements Porto, Serializable{

		private static final long serialVersionUID = 1L;
		private int codice;
		private String nome;
		private final String nazione;
		
		/**
		 * Costruttore di porto
		 * @param nome 		nome porto
		 * @param nazione	nazione in cui è situato il porto
		 */
		public ImplPorto(final String nome, final String nazione){
			this.nome=nome;
			this.nazione=nazione;
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

		public String getNazione() {
			return nazione;
		}
		
		public String toString() {
			return "ImplPorto [Codice porto=" + codice + ", nome =" + nome + ", nazione=" + nazione + "]";
		}
}
