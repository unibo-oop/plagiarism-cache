package pro_ristorante_oop;

import java.io.Serializable;
import java.util.Random;

public abstract class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final String nome;
	final String cognome ;
	final boolean sesso;
	String password;
	
	
	Persona(String nome,String cognome,boolean sesso){
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		Random r=new Random();
		this.password= ""+this.nome.charAt(0)+this.cognome.charAt(0)+r.nextInt(9)+r.nextInt(9)+r.nextInt(9)+r.nextInt(9)+r.nextInt(9);
	}

	public String getNome(){
	return nome;
}

	public String getCognome() {
		return cognome;
	}

	public boolean isSesso(){
		return sesso;// maschi = true femmina = false
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
}
