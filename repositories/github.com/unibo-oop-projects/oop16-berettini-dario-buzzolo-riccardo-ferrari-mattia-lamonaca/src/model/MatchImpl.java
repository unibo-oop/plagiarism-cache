package model;

import java.io.Serializable;

public class MatchImpl implements Match, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String atleta1;
	private String atleta2;
	private String risultato;
	
	public MatchImpl(String atleta1, String atleta2, String risultato){
		
		this.atleta1=atleta1;
		this.atleta2=atleta2;
		this.risultato=risultato;
	}

	public String getAtleta1() {
		
		return atleta1;
	}
	
	public String getAtleta2() {
		
		return atleta2;
	}
	
	public String getRisultato(){
		
		return risultato;
	}
}
