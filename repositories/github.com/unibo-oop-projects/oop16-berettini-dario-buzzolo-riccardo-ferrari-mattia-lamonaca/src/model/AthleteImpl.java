package model;

import java.io.Serializable;

public class AthleteImpl implements Athlete, Serializable{
	
	private static final long serialVersionUID = 1L;
	private final String name;
	private final String surname;
	private final Belt belt;
	
	private final String nomeForma;
	//Voto in ordine: Calci - Forma - Combattimento
	private final int[] voti;
	private int avanzamento;
	private boolean promosso;
	private Double votoForma;

	public AthleteImpl(String name, String surname, Belt belt) {
		this.name = name;
		this.surname = surname;
		this.belt = belt;
		this.voti = new int[3];
		this.avanzamento = 0;
		this.promosso=false;
		this.nomeForma=null;
		
	}
	
	public AthleteImpl(String name, String surname, Belt belt,String forma, Double votoForma){
		this.name = name;
		this.surname = surname;
		this.belt = belt;
		this.voti = new int[3];
		this.avanzamento = 0;
		this.promosso=false;
		this.nomeForma = forma;
		this.votoForma=votoForma;
		
	}
	
	public String getName() {
		
		return name;
	}

	public String getSurname() {

		return surname;
	}

	public Belt getBelt() {

		return belt;
	}
	
	public void setVoto(int indice, int voto){
		this.voti[indice]=voto;
		this.avanzamento++;
	}
	
	public int getVoto(int indice){
		return this.voti[indice];
	}
	
	public int getAvanzamento(){
		return this.avanzamento;
	}
	
	public String toString(){
		return name+" "+surname+" "+belt;
	}
	
	public void isPromosso(){
		if(this.avanzamento == 3 && (this.voti[0]+this.voti[1]+this.voti[2]) >= 18){
			
			this.promosso=true;
		}else{
			
			this.promosso=false;
		}
	}
	
	public void setVotoForma(Double voto){
		this.votoForma = voto;
	}
	
	public double getVotoForma(){
		return this.votoForma;
	}
	
	public String getNomeForma(){
		return this.nomeForma;
	}
	
	public boolean getPromosso(){
		return this.promosso;
	}
}
