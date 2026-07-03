package model;

import java.util.ArrayList;

public interface ExamDone {

	public ArrayList<Athlete> getListaAtleti();
	
	public void setListaAtleti(ArrayList<Athlete> listaAtleti);
	
	public ArrayList<Master> getListaMaestri();
	
	public void setListaMaestri(ArrayList<Master> listaMaestri);
	
	public String getDate();
}
