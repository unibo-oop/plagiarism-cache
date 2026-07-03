package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ExamDoneImpl implements ExamDone,Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Master> listaMaestri;
	private ArrayList<Athlete> listaAtleti;

	private String dataS;

	public ExamDoneImpl(ArrayList<Master> listaMaestri, ArrayList<Athlete> listaAtleti, String data){
		
		this.setListaMaestri(listaMaestri);
		this.setListaAtleti(listaAtleti);
		this.dataS = data;
	}
	
	public ArrayList<Athlete> getListaAtleti() {
		return listaAtleti;
	}
	public void setListaAtleti(ArrayList<Athlete> listaAtleti) {
		this.listaAtleti = listaAtleti;
	}
	public ArrayList<Master> getListaMaestri() {
		return listaMaestri;
	}
	public void setListaMaestri(ArrayList<Master> listaMaestri) {
		this.listaMaestri = listaMaestri;
	}
	public String getDate(){
		return this.dataS;
	}
}
