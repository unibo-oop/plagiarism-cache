package model.Implementations;

import model.Interfaces.Nave;
import java.io.Serializable;

/**
 * Implementazione di una nave
 * @author Helena Zaccarelli
 *
 */

public class ImplNave implements Nave, Serializable{

		private static final long serialVersionUID = 1L;
		protected int codice;
		protected String nome;
		protected final int portata;
		protected final int spazi;
		protected String bandiera;
		protected String tipo;
		
		/**
		 * Costruttore di nave
		 * @param nome 		nome nave
		 * @param portata 	portata in tonnellate della nave
		 * @param spazi		spazi disponibili sulla nave
		 * @param bandiera 	bandiera nave
		 * @param tipo		tipo di merce imbarcabile sulla nave
		 */
		public ImplNave(String nome, final int portata, final int spazi, String bandiera, String tipo){
			this.nome=nome;
			this.portata=portata;
			this.spazi=spazi;
			this.bandiera=bandiera;
			this.tipo=tipo;
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

		public int getPortata() {
			return portata;
		}

		public int getSpazi() {
			return spazi;
		}
		
		public String getBandiera() {
			return bandiera;
		}

		public void setBandiera(String bandiera) {
			this.bandiera = bandiera;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String toString() {
			return "ImplNave [Codice nave=" + codice + ", nome=" + nome + ", portata=" + portata + ", spazi disponibili=" + spazi + ", bandiera=" + bandiera + ", tipo di carico="
					+ tipo + "]";
		}
}
