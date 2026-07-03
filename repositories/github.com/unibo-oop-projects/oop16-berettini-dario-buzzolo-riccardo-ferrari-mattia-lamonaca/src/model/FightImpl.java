package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FightImpl implements Fight {
	
	ArrayList<Match> listaMatch = new ArrayList<Match>();
	
	public FightImpl(){
		
	}
	
	public String[] getScoreBlue(Integer punti){
		
		String[] s = new String[2];
		if(punti<10){
			
			s[0]="/puntitaekwondo/"+ punti+"_blue.png";
			return s;
			
		}if(punti>=10){

			for(int i=10; i<100; i+=10){
				if(punti-i <= 9){
				
					for(int j=0; j<10; j++){
						
						if(punti-i-j == 0){
							s[0]="/puntitaekwondo/" + i/10 + "_blue.png";
							s[1]="/puntitaekwondo/" + j + "_blue.png";
							return s;
						}
					}
				}	
			}	
		}
		return null;		
	}
	
	public String[] getScoreRed(Integer punti){
		
		
		String[] s = new String[2];
		if(punti<10){
			
			s[0]="/puntitaekwondo/"+ punti+"_red.png";
			return s;
			
		}if(punti>=10){

			for(int i=10; i<50; i+=10){
				
				if(punti-i <= 9){
					
					for(int j=0; j<10; j++){
						
						if(punti-i-j == 0){
							s[0]="/puntitaekwondo/" + i/10 + "_red.png";
							s[1]="/puntitaekwondo/" + j + "_red.png";
							return s;
						}
					}
				}	
			}	
		}
		return null;		
	}
	
	public String getWarningRed(Integer warnings){
		
		if(warnings%2==0){
			return "/puntitaekwondo/red_warning.png";
		}else{
			return "/puntitaekwondo/yellow_warning.png";
		}	
		
	}
	
	public String getWarningBlue(Integer warnings){
		
		if(warnings%2==0){
			
			return "/puntitaekwondo/red_warning.png";
		}else{
			return "/puntitaekwondo/yellow_warning.png";
		}	
		
	}
	
	public void playSound(File Sound){
		
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public void insertListaMatch(String atleta1, String atleta2, String risultato){
		
		this.listaMatch.add(new MatchImpl(atleta1,atleta2,risultato));
	}
	
	public void setListaMatch(ArrayList<Match> lista){
		
		this.listaMatch=lista;
	}
	
	public ArrayList<Match> getListaMatch(){
		
		return this.listaMatch;
	}
	
	public void insertListaMatchFile() {

		try {

			FileOutputStream stream = new FileOutputStream("resource/storici/ListaMatch.dat");

			ObjectOutputStream osStream = new ObjectOutputStream(stream);

			osStream.writeObject(listaMatch);
			
			osStream.flush();

			osStream.close();

		} catch (Exception e) {

			System.out.println("I/O errore"+e);
		}
	}
	
	public ArrayList<Match> getListaMatchFile() {

		try {
			FileInputStream stream = new FileInputStream("resource/storici/ListaMatch.dat");

			ObjectInputStream osStream = new ObjectInputStream(stream);

			@SuppressWarnings("unchecked")
			ArrayList<Match> listaMatchFile = (ArrayList<Match>) osStream.readObject();

			osStream.close();

			return listaMatchFile;

		} catch (Exception e) {

			System.out.println("I/O errore di stampa"+e);
		}

		ArrayList<Match> arrayEmpty = new ArrayList<Match>();
		return arrayEmpty;
	}
}
