package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormImpl implements Form {
	
	private List<Athlete> listaAtletiForma = new ArrayList<>();
	public FormImpl(){
		
		this.listaAtletiForma = getListaAtletiFormaFile();
	}
		
	public String[] getScoreRed(int punti){
		
		String[] s = new String[3];
		
		if(punti < 100){
			
			
			
			int unita = punti-((punti/10)*10);
			int decine = punti - unita;
			System.out.println("unita ="+unita+"  decine="+decine);
			s[0]="/puntitaekwondo/" + 0 + "_red.png";
			s[1]="/puntitaekwondo/" + decine/10 + "_red.png";
			s[2]="/puntitaekwondo/" + unita + "_red.png";
			return s;						
		}
		
		return null;		
	}
	public List<Athlete> getListaAtletiForma() {
	
		return listaAtletiForma;
	}
	
	public ArrayList<String> stampaAtleti(){
	
		ArrayList<String> stampata = new ArrayList<>();
		for(Athlete atleta : listaAtletiForma){
			
			stampata.add(atleta.getName()+" "+atleta.getSurname());
		}
		
		return stampata;
	}
	
	public void addAtletiForma(Athlete atleta) {
		
		this.listaAtletiForma.add(atleta);
	}
	
	public void insertListaAtletiFormaFile() {

		try {

			FileOutputStream stream = new FileOutputStream("resource/storici/ListaAtletiForma.dat");
			ObjectOutputStream osStream = new ObjectOutputStream(stream);

			osStream.writeObject(listaAtletiForma);
			osStream.flush();
			osStream.close();

		} catch (Exception e) {

			System.out.println("I/O errore");
		}
	}
	
	public ArrayList<Athlete> getListaAtletiFormaFile() {

	try {

		FileInputStream stream = new FileInputStream("resource/storici/ListaAtletiForma.dat");
		ObjectInputStream osStream = new ObjectInputStream(stream);

		@SuppressWarnings("unchecked")
		ArrayList<Athlete> listaAtletiFile = (ArrayList<Athlete>) osStream.readObject();

		osStream.close();
		
		return listaAtletiFile;

	} catch (Exception e) {

		System.out.println("I/O errore di stampa");
	}

	ArrayList<Athlete> arrayEmpty = new ArrayList<Athlete>();
		
		return arrayEmpty;
	}
}
